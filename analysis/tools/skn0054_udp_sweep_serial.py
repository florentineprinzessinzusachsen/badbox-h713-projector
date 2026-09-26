"""
1:1 timing-faithful replication of the SKN0054 (com.szns.sdk.ad.c()) UDP
fallback-discovery sweep, decompiled from analysis/decrypted_modules/SKN0054/
(see analysis/decompiled/SKN0054/szns_core.java.txt lines 205-276).

Unlike skn0054_udp_sweep_check.py (a fast burst census of the whole range),
this one matches the real client's actual wire behavior, not just its
payload/port/range:

  - ONE DatagramSocket for the whole sweep (not one per host), exactly as
    `int v1_1 = new java.net.DatagramSocket()` in the decompiled source.
  - SO_TIMEOUT = 1000ms, exactly `v1_1.setSoTimeout(1000)`.
  - strictly serial: send to host N, block on recv up to 1000ms, THEN
    move to host N+1 -- never more than one outstanding probe in flight,
    matching the nested while-loops with `v1_1.send(...)` immediately
    followed by `v1_1.receive(...)`.
  - recv() is NOT filtered by source address, exactly like the real
    (unconnected) DatagramSocket.receive(), which accepts a reply from
    ANY sender, not just the host just probed -- this looks like a
    latent bug in the original code (a reply to host N-1 arriving late
    could be misattributed to host N), and it's kept here deliberately,
    since the point of this script is fidelity to the real behavior,
    bugs included.
  - on the FIRST reply that starts with "http", stop immediately and
    report it, exactly like `return v7_9` in the decompiled source.
  - on a timeout, move to the next host -- fourth octet 1..100, then
    third octet 12..80 -- exactly the loop bounds in the source.

One control-flow detail in the decompiled source is ambiguous: the
increment of the inner loop variable (`v6_1++`) is only visible inside
the timeout-exception handler, not after a *successful but non-"http"*
receive. Treated here as a DAD decompiler artifact (it sometimes misplaces
loop-continuation statements in complex control flow) rather than real
behavior, since the alternative reading is an infinite loop on the first
host that ever replies with anything at all. This script implements the
obviously-intended behavior: advance to the next host after processing
each one (timeout, non-matching reply, or send error alike), and only
stop the whole sweep on an actual "http"-prefixed hit.

Expected worst-case runtime if nothing ever replies: ~6900 hosts x up to
1.0s each = up to ~115 minutes. Meant to be run in the background;
progress and any replies are logged to skn0054_udp_sweep_serial.log as
they happen, and a final JSON summary is written on completion.
"""
import socket
import time
import json
import os

PAYLOAD = b"moon2"
PORT = 8080
THIRD_RANGE = range(12, 81)   # 12..80 inclusive
FOURTH_RANGE = range(1, 101)  # 1..100 inclusive
SO_TIMEOUT_S = 1.0

HERE = os.path.dirname(os.path.abspath(__file__))
OUT_PATH = os.path.join(HERE, "skn0054_udp_sweep_serial_results.json")
LOG_PATH = os.path.join(HERE, "skn0054_udp_sweep_serial.log")


def log(msg):
    line = f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] {msg}"
    print(line, flush=True)
    with open(LOG_PATH, "a") as f:
        f.write(line + "\n")


def main():
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.settimeout(SO_TIMEOUT_S)

    total = len(THIRD_RANGE) * len(FOURTH_RANGE)
    start = time.time()
    probed = 0
    non_http_replies = []
    result = None

    log(f"starting serial 1:1 sweep: {total} hosts, "
        f"43.153.{THIRD_RANGE.start}.{FOURTH_RANGE.start} .. "
        f"43.153.{THIRD_RANGE.stop - 1}.{FOURTH_RANGE.stop - 1}, port {PORT}, "
        f"payload={PAYLOAD!r}, timeout={SO_TIMEOUT_S}s/host, "
        f"worst case ~{total * SO_TIMEOUT_S / 60:.0f} min")

    try:
        done = False
        for third in THIRD_RANGE:
            if done:
                break
            for fourth in FOURTH_RANGE:
                ip = f"43.153.{third}.{fourth}"
                try:
                    sock.sendto(PAYLOAD, (ip, PORT))
                except OSError as e:
                    log(f"  send error to {ip}: {e}")
                    probed += 1
                    continue
                probed += 1
                try:
                    data, addr = sock.recvfrom(1024)
                except socket.timeout:
                    continue
                except OSError as e:
                    log(f"  recv error after probing {ip}: {e}")
                    continue

                text = data.decode("utf-8", errors="replace")
                if text.startswith("http"):
                    log(f"  *** HIT *** probed {ip}, reply from {addr[0]}:{addr[1]}: {text!r} -- stopping sweep")
                    result = {"probed_host": ip, "reply_from": f"{addr[0]}:{addr[1]}", "reply": text}
                    done = True
                    break
                else:
                    log(f"  non-http reply while probing {ip} (actually from {addr[0]}:{addr[1]}): {data[:200]!r}")
                    non_http_replies.append({
                        "probed_host": ip,
                        "reply_from": f"{addr[0]}:{addr[1]}",
                        "reply_hex": data.hex(),
                    })

                if probed % 200 == 0:
                    elapsed = time.time() - start
                    log(f"  progress: {probed}/{total} probed, {elapsed / 60:.1f} min elapsed")
    finally:
        sock.close()

    elapsed = time.time() - start
    log(f"sweep finished: probed={probed}/{total} elapsed={elapsed / 60:.1f}min "
        f"hit={'yes' if result else 'no'}")

    out = {
        "probed_count": probed,
        "total_targets": total,
        "elapsed_seconds": elapsed,
        "port": PORT,
        "payload": PAYLOAD.decode(),
        "timeout_s_per_host": SO_TIMEOUT_S,
        "timestamp": time.strftime("%Y-%m-%dT%H:%M:%SZ", time.gmtime()),
        "stopped_early_on_hit": result is not None,
        "hit": result,
        "non_http_replies": non_http_replies,
    }
    with open(OUT_PATH, "w") as f:
        json.dump(out, f, indent=2, ensure_ascii=False)
    log(f"wrote {OUT_PATH}")


if __name__ == "__main__":
    main()
