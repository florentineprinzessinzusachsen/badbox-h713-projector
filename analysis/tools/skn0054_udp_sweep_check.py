"""
Replicates the SKN0054 (com.szns.sdk.ad.c()) UDP fallback-discovery sweep,
decompiled from the .rf module in analysis/decrypted_modules/SKN0054/.

Real malware behavior (from the decompiled Java):
    - one DatagramSocket, 1000ms receive timeout
    - for third_octet in 12..80, for fourth_octet in 1..100:
        send ASCII "moon2" to 43.153.<third>.<fourth>:8080
        wait up to 1s for a UDP reply
        if reply starts with "http" -> that's the discovered dispatch URL, stop
    - the real client stops at the FIRST hit (bandwidth/time saving for the botnet)

This script sends the same single-packet "moon2" probe on the same port to
every address in the same range, but does it concurrently (non-blocking send
loop + a shared receive window) instead of strictly serially with a 1s wait
each, since that would take ~2 hours in the worst case. It does NOT stop at
the first hit -- it records every host that replies, and separately flags
which replies start with "http" (i.e. would be accepted as a real dispatch
URL by the actual malware), to get a full census for the abuse report
instead of just proof-of-concept of one live node.

Usage: python3 skn0054_udp_sweep_check.py
Output: prints every responding host live, and writes a JSON summary next
to this script.
"""
import socket
import select
import time
import json
import os

PAYLOAD = b"moon2"
PORT = 8080
THIRD_RANGE = range(12, 81)   # 12..80 inclusive
FOURTH_RANGE = range(1, 101)  # 1..100 inclusive
SEND_PACING_S = 0.001         # small stagger so we don't blast the whole /16 in one instant
COLLECT_WINDOW_AFTER_LAST_SEND_S = 4.0

targets = [f"43.153.{t}.{f}" for t in THIRD_RANGE for f in FOURTH_RANGE]
print(f"targets: {len(targets)} addresses, 43.153.{THIRD_RANGE.start}.{FOURTH_RANGE.start} .. "
      f"43.153.{THIRD_RANGE.stop-1}.{FOURTH_RANGE.stop-1}, port {PORT}, payload={PAYLOAD!r}")

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.setblocking(False)

replies = {}  # ip -> raw bytes received

def drain_replies():
    while True:
        r, _, _ = select.select([sock], [], [], 0)
        if not r:
            break
        try:
            data, addr = sock.recvfrom(2048)
        except BlockingIOError:
            break
        ip = addr[0]
        replies.setdefault(ip, []).append(data)
        tag = "HTTP-PREFIXED" if data.startswith(b"http") else "other"
        print(f"  reply from {ip}: [{tag}] {data[:200]!r}")

start = time.time()
sent = 0
for ip in targets:
    try:
        sock.sendto(PAYLOAD, (ip, PORT))
        sent += 1
    except OSError:
        pass
    drain_replies()
    if sent % 500 == 0:
        time.sleep(SEND_PACING_S * 50)  # brief pause every 500 sends

last_send_time = time.time()
print(f"sent {sent} probes in {last_send_time - start:.1f}s, now collecting replies for "
      f"{COLLECT_WINDOW_AFTER_LAST_SEND_S:.1f}s ...")

while time.time() - last_send_time < COLLECT_WINDOW_AFTER_LAST_SEND_S:
    drain_replies()
    time.sleep(0.05)

sock.close()

http_hits = {ip: [d.decode("utf-8", "replace") for d in datas]
             for ip, datas in replies.items()
             if any(d.startswith(b"http") for d in datas)}
other_hits = {ip: [d.hex() for d in datas]
              for ip, datas in replies.items()
              if ip not in http_hits}

print(f"\n=== summary ===")
print(f"probed: {len(targets)}")
print(f"any reply: {len(replies)}")
print(f"http-prefixed (would be accepted as a real dispatch URL by the malware): {len(http_hits)}")
for ip, urls in http_hits.items():
    print(f"  {ip}: {urls}")

out = {
    "probed_count": len(targets),
    "port": PORT,
    "payload": PAYLOAD.decode(),
    "timestamp": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
    "http_prefixed_hits": http_hits,
    "other_replies_hex": other_hits,
}
out_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "skn0054_udp_sweep_results.json")
with open(out_path, "w") as f:
    json.dump(out, f, indent=2, ensure_ascii=False)
print(f"\nwrote {out_path}")
