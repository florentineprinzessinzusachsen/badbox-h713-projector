import sys, os, hashlib, json, base64
from Crypto.Cipher import AES
from Crypto.Hash import MD5
from Crypto.PublicKey import DSA
from Crypto.Signature import DSS
from Crypto.Util.asn1 import DerSequence

AES_IV = bytes([48,49,48,50,48,51,48,52,48,53,48,54,48,55,48,56])  # "0102030405060708"
AES_KEY_SEED = "968a84be78d3421d5e771147dce2b766"
DSA_PUBKEY_B64 = ("MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7"
"WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrit"
"h1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAQZAl2oCfwh3WExKuiMcg3njQRZBWmDHQiEWN2vvZ4YogljxVlRccyDS+7u7aseq3+mO1qyISs548Qc50cnN39xiZaS39qvkNFbsxaPmK8edkXntGqXH874w3m7gyXNeAjYhHHG4h6jSwgDndAUjpNVQld348/SKq1E7tBvRGxBo=")

def bytes2int_le(b):
    return int.from_bytes(b, 'little')

def aes_cfb_decrypt(data, key_str):
    key = hashlib.md5(key_str.encode('utf-8')).digest()
    cipher = AES.new(key, AES.MODE_CFB, iv=AES_IV, segment_size=128)
    return cipher.decrypt(data)

def read_cipher_bean(buf):
    version = bytes2int_le(buf[0:4])
    salt = buf[4100:4132].decode('utf-8')
    off = 4132
    ilen = bytes2int_le(buf[off:off+4]); off += 4
    invocation_cipher = buf[off:off+ilen]; off += ilen
    ilen2 = bytes2int_le(buf[off:off+4]); off += 4
    dex_cipher = buf[off:off+ilen2]; off += ilen2
    siglen = bytes2int_le(buf[off:off+4]); off += 4
    signature = buf[off:off+siglen]; off += siglen
    signed_region = buf[0:off-4-siglen]  # bytes 0 .. before sig-length-prefix
    return version, salt, invocation_cipher, dex_cipher, signature, signed_region, off

def verify_dsa(signed_region, signature):
    der = DSA.import_key(base64.b64decode(DSA_PUBKEY_B64))
    h = hashlib.sha1(signed_region).digest()
    # DSA signature here is ASN.1 DER encoded (r,s) - standard Java Signature "DSA" output
    seq = DerSequence()
    seq.decode(signature)
    r, s = seq[0], seq[1]
    from Crypto.Math.Numbers import Integer
    pub_num_y = der.y
    # use pycryptodome low-level verify
    from Crypto.PublicKey.DSA import construct
    key = construct((der.y, der.g, der.p, der.q, der.x)) if der.has_private() else der
    # verify manually via DSA math
    p, q, g, y = der.p, der.q, der.g, der.y
    if not (0 < r < q and 0 < s < q):
        return False
    w = pow(s, q-2, q)
    hnum = int.from_bytes(h, 'big') % q
    u1 = (hnum * w) % q
    u2 = (r * w) % q
    v = ((pow(g, u1, p) * pow(y, u2, p)) % p) % q
    return v == r

def decrypt_rf(path):
    buf = open(path, 'rb').read()
    version, salt, inv_cipher, dex_cipher, sig, signed_region, total_used = read_cipher_bean(buf)
    ok = verify_dsa(signed_region, sig)
    inv_plain = aes_cfb_decrypt(inv_cipher, AES_KEY_SEED + salt)
    dex_plain = aes_cfb_decrypt(dex_cipher, AES_KEY_SEED + salt)
    return {
        'version': version, 'salt': salt, 'sig_ok': ok,
        'invocation_json': inv_plain.decode('utf-8', errors='replace'),
        'dex_bytes': dex_plain,
        'file_len': len(buf), 'consumed': total_used, 'trailing': len(buf)-total_used,
    }

if __name__ == '__main__':
    for path in sys.argv[1:]:
        print("="*30, os.path.basename(path))
        try:
            r = decrypt_rf(path)
            print("version:", r['version'], "salt:", r['salt'], "sig_ok:", r['sig_ok'])
            print("invocation:", r['invocation_json'])
            print("dex magic:", r['dex_bytes'][:8])
            print("file_len:", r['file_len'], "consumed:", r['consumed'], "trailing:", r['trailing'])
            outpath = path + ".dex"
            with open(outpath, 'wb') as f:
                f.write(r['dex_bytes'])
            print("wrote", outpath)
        except Exception as e:
            import traceback; traceback.print_exc()
