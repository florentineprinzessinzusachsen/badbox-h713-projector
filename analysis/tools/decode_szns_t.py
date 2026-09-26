import base64, hashlib, sys

def xor(s, key):
    return ''.join(chr(ord(c) ^ ord(key[i % len(key)])) for i, c in enumerate(s))

def md5_upper_hex(s: str) -> str:
    return hashlib.md5(s.encode()).hexdigest().upper()

def decode(p5: str) -> str:
    v0 = base64.b64decode(p5).decode('latin1')
    fixed_key = "119aca32a5360e2884c767ceb10889b0"
    tail13 = v0[len(v0)-13:]
    v5_2 = xor(tail13, fixed_key)
    mid13 = v0[len(v0)-26:len(v0)-13]
    v3_3 = xor(mid13, v5_2)
    combined = v3_3 + v5_2 + fixed_key
    final_key = md5_upper_hex(combined).lower()
    head = v0[0:len(v0)-26]
    inner_b64 = xor(head, final_key)
    return base64.b64decode(inner_b64.encode('latin1')).decode('utf-8')

if __name__ == '__main__':
    strings = {
      "ac.a() [domain list?]": "V3s0BFopfA97SwhcaQR6UgIKLwVUXX9DbQQLQQJfWFlcflMOUVJbWVkCWVYOCCtRCwBeWV5f",
      "ac.b() [domain_info bootstrap]": "USlkBVN2X0Z9C2tHAFJ/W21reUVRMQkOBVUMBwEmYx1VIgNfUgAARmhhe0AuU2dAVAByWFchKwpdUXh4XHJUd0FEe0B1XVd2LQwgX3wSRn5Ddw==",
      "ac.c() [oss_domain_info bootstrap]": "AHsxBFApdAJ8H19NYjNlCnozDxQ5XA4XBgp6SnxhJUQtay1CVzlrW2oxIE1cJwMZegklEgJpWlMGVHpKfFstRQNgWllqOWNCUwsKB2I3BhFvVltcYABCQnsDeEMHTwddTGAGTBcgVnlIVU0EWEQ=",
      "ac.d() [ip_tcp_info bootstrap]": "LXB/TXtkWkJ6UnVJfRhXSyp0JgJ7Y3EHeXQEXGd2XFdAVgZJUUcEeFBncFICGwIDSwlAAHhT",
    }
    for name, s in strings.items():
        try:
            print(name, "->", decode(s))
        except Exception as e:
            print(name, "ERROR", e)
