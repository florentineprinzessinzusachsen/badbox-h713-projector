"""Offline verification for locally held SPR2226 samples; never executes sample code.

Dependencies: androguard, cryptography, lief, capstone.
Usage: python verify_samples.py --netservice active.apk --native-dir native-files
The native directory must contain systemmixservice, libsystemmixservice.so,
libsystemmix_jni.so, qw and su. No network/device connection is made.
"""
import argparse
import hashlib
import json
import re
import zipfile
from pathlib import Path

from loguru import logger
logger.disable('androguard')
from androguard.core.dex import DEX
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes
from cryptography.hazmat.primitives.padding import PKCS7
import lief
from capstone import Cs, CS_ARCH_ARM, CS_MODE_THUMB

EXPECTED = {
    'active.apk': '33feb99dfca28f97aed5ef1cbcb1c9ebfe215fcb8c54c56cd79a7efcfac1e77f',
    'systemmixservice': '08647d3268388e6f9a06233950f520a0e527f0bfb6079550918b94b94548d10d',
    'libsystemmixservice.so': '91a204ab267f3e498c47670587adf00cf653609ef45054e6a4065e829adec759',
    'libsystemmix_jni.so': 'deec3320c4fc7ad86b64339413503442e376e1303e40d02788d9644ee36fb9c7',
    'qw': '022b638767ddf981ba09b8092460ea4cb837a7cdf713949ecbea7c0a0ced5759',
    'su': '398c9cb495d67e5721887f33b32b0dac31511ea1966c65347d0440474848b4e3',
}
CASE_TARGETS = [0x4dc0,0x4e52,0x4eb4,0x4f10,0x4f58,0x4f72,0x4f8c,
                0x4fa6,0x4fce,0x4fe6,0x4ffe,0x5016,0x502e]
SLOTS = [0x10,0x18,0x1c,0x20,0x24,0x28,0x2c,0x30,0x34,0x38,0x3c,0x40,0x44]
METHODS = ['getProperty','getFileData','putFileData','mountDev','umountDev',
           'deleteFile','canWrite','renewName','makeDir','canRead',
           'setBootAnimation','setBootLogo','exportBootLogo']

def require(condition, description):
    if not condition:
        raise AssertionError(description)
    checks.append(description)

def read(binary, address, size):
    return bytes(binary.get_content_from_virtual_address(address, size))

def word(binary, address):
    return int.from_bytes(read(binary,address,4),'little')

def cstring(binary,address):
    return read(binary,address,160).split(b'\0')[0].decode('ascii')

def decrypt_default(value, key):
    decryptor=Cipher(algorithms.AES(key),modes.ECB()).decryptor()
    padded=decryptor.update(bytes.fromhex(value))+decryptor.finalize()
    unpadder=PKCS7(128).unpadder()
    return (unpadder.update(padded)+unpadder.finalize()).decode('utf-8')

parser=argparse.ArgumentParser(description=__doc__)
parser.add_argument('--netservice',required=True,type=Path)
parser.add_argument('--native-dir',required=True,type=Path)
args=parser.parse_args()
checks=[]
files={'active.apk':args.netservice}
files.update({name:args.native_dir/name for name in EXPECTED if name!='active.apk'})
hashes={}
for name,path in files.items():
    digest=hashlib.sha256(path.read_bytes()).hexdigest()
    require(digest==EXPECTED[name],f'Exact sample hash: {name}')
    hashes[name]=digest

strings=set()
method_refs=[]
code={}
dex_info=[]
with zipfile.ZipFile(args.netservice) as archive:
    for name in archive.namelist():
        if not re.fullmatch(r'classes\d*\.dex',name):
            continue
        raw=archive.read(name)
        dex=DEX(raw)
        strings.update(dex.get_strings())
        dex_info.append({'name':name,'bytes':len(raw),'sha256':hashlib.sha256(raw).hexdigest()})
        method_refs.extend((m.get_class_name(),m.get_name()) for m in dex.get_methods())
        for cls in dex.get_classes():
            if not cls.get_name().startswith('Lcom/android/'):
                continue
            for method in cls.get_methods():
                instructions=method.get_instructions()
                text='\n'.join(ins.get_name()+' '+ins.get_output() for ins in instructions)
                code.setdefault((cls.get_name(),method.get_name()),[]).append(text)
require(len(dex_info)==2,'Both netservice DEX files inspected')
require(not any('systemmix' in (cls+name).lower() for cls,name in method_refs),
        'No direct SystemMix method reference in either DEX (reflection not excluded)')
require('content://com.android.providerdomain.provider/domains' in strings,
        'Shared domain-provider URI present')
require('android.content.pm.extra.STATUS' in strings and 'session_id' in strings,
        'Installer completion status and session identifiers present')
finish='\n'.join(code[('Lcom/android/download/DownloadFinishHandler;','e')])
require('getFileMD5' in finish and 'isEmpty' in finish,'Optional MD5 gate in download completion')
installer='\n'.join(code[('Lcom/android/util/PackageInstallUtil;','silentInstallWithCallBack')])
require('Runtime;->exec' in installer and '"su"' in installer and 'pm install -r' in installer,
        'Root-shell install helper instructions present')
launch='\n'.join(code[('Lcom/android/download/TaskManager;','l')])
launch_wrapper='\n'.join(code[('Lcom/android/download/g;','a')])
require('getOpenTimes' in launch and 'Lcom/android/download/g;->a' in launch
        and 'startForegroundService' in launch_wrapper,
        'Autostart limit and explicit service-launch instructions present')
key_candidates=[s for s in strings if s.startswith('asla01slajf1ALls')]
require(bool(key_candidates),'Embedded hostname-obfuscation key found in sample')
key=key_candidates[0].encode()[:16]
business=decrypt_default('3feada7df2a3101bfcac3b96f6f7c1fef5ec35268b059b1ca2d3e15c0b11577a',key)
heartbeat=decrypt_default('ea3ea815590b4afaa7da717ab80dadf99bef9117956945d6aa54e9f1a2e5db29',key)
require(business=='business.dnsyc.top' and heartbeat=='heartbeat.dnsyc.top',
        'Independent fallback hostname decryption')

binary=lief.parse(str(args.native_dir/'libsystemmixservice.so'))
relocs={r.address:r for r in binary.relocations}
disassembler=Cs(CS_ARCH_ARM,CS_MODE_THUMB)
binder=[]
for i,(expected,slot,method) in enumerate(zip(CASE_TARGETS,SLOTS,METHODS),start=1):
    target=0x4da6+2*int.from_bytes(read(binary,0x4da6+2*(i-1),2),'little')
    require(target==expected,f'Binder case {i} target')
    body=list(disassembler.disasm(read(binary,target,0xa0),target))
    require(any(ins.mnemonic=='ldr' and re.search(r'\[r[01], #'+hex(slot)+r'\]',ins.op_str)
                for ins in body),f'Binder case {i} virtual dispatch slot')
    relocation=relocs.get(0x8a68+slot)
    require(relocation is not None and relocation.has_symbol and method in relocation.symbol.name,
            f'Packed relocation resolves Binder case {i} to {method}')
    binder.append({'transaction':i,'case_address':hex(target),'operation':method})
special=relocs[0x8a7c]
require(special.has_symbol and 'setProperty' in special.symbol.name,'Special property-set vtable target')
dispatch=list(disassembler.disasm(read(binary,0x4e24,0x2e),0x4e24))
require(any(ins.mnemonic=='mvn' and '#0xff000000' in ins.op_str for ins in dispatch),
        'Special property-set transaction constant is 0x00ffffff')
jni=lief.parse(str(args.native_dir/'libsystemmix_jni.so'))
entries=[]
for address in range(0x46b4,0x4744,12):
    entries.append({'name':cstring(jni,word(jni,address)),
                    'signature':cstring(jni,word(jni,address+4)),
                    'function':hex(word(jni,address+8)&~1)})
require(len(entries)==12 and entries[0]['name']=='nativeInit' and entries[-1]['name']=='nativeCanRead',
        'Twelve JNI registration entries decoded')
print(json.dumps({'checks_passed':len(checks),'checks':checks,'hashes':hashes,
                  'dex':dex_info,'fallbacks':[business,heartbeat],
                  'binder':binder,'jni':entries},indent=2))
