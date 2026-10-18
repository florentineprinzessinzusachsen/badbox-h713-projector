# SPR2226 — naturally captured domain update response

## Finding and attribution

At **07:57 CEST (05:57 UTC) on 18 October 2026**, a naturally occurring API exchange from the examined projector, strongly correlated with `com.android.domain`, received a successful encrypted update response. The acquisition analyst reports that offline decryption recovered **two APK offers: domain 1.0.6 and netservice 3.6.0**, both already installed. The `domains` array was empty. This documents live update metadata delivery, not a new downstream installation, code execution or residential-proxy operation.

**Process attribution is indirect.** Socket inventories taken approximately every 30 seconds did not record PID/socket ownership of the short TCP 9901 exchange. Attribution is supported by matching APK API paths, protocol, embedded AES key, scheduling and subsequent preference changes. A later domain-process connection to `8.8.8.8:443` does not establish ownership of the API stream or the purpose of that separate connection. This is strongly correlated traffic, not a directly PID-verified capture.

The following capture, parsing and decryption results were supplied by the acquisition analyst. Raw PCAPs and decoder code were not supplied to the documentation reviewer; these results have not been independently reproduced in this repository review. Private evidence is distinguished from public reproduction material throughout.

The request was not forced. The analyst reports no identifier, timestamp, preference or token changes to obtain it, no replay of server tasks or credentials, no direct request to returned APK URLs, and no payload execution. Lamp-off operation used the original repository's wakelock/sleep procedure, leaving Android and Wi-Fi available.

## 1. Preserved evidence and protocol timeline

Private evidence: `capture-017.pcap`, **3,489 bytes**, SHA-256:

```text
8113557f3a6bc002834debc400777d31c0472df5727145393ba9f4a17a99e731
```

The analyst reports an independently calculated file hash matching the acquisition sidecar. The capture contains two complete HTTP exchanges with **47.251.54.180:9901**. Retained preferences name `d.rdnss.cloud`; the packet endpoint does not identify an operator or hosting customer.

| Captured time, UTC | CEST | Event |
| --- | --- | --- |
| 05:57:23.306616 | 07:57:23.306616 | GET `/app-api/hnc2fng/gnbht53hdv2/dhgb35dg` |
| 05:57:23.490317 | 07:57:23.490317 | HTTP 200 / business code 200 time response |
| 05:57:23.820387 | 07:57:23.820387 | POST `/app-api/hnc2fng/ghbn1hebdsh/yh65hbfvg3jfdbs`, 24-byte body |
| 05:57:24.010653 | 07:57:24.010653 | HTTP 200 / business code 200 encrypted update response |

Both responses use chunked transfer encoding and gzip. The analyst reports that each complete HTTP response fits in one captured TCP payload (240 and 942 bytes respectively), permitting separate `dpkt.http.Response` parsing without joining missing TCP bytes. The continuous helper reported no TCP gaps, conflicting overlaps, stream errors or response-decode errors. Raw request headers, identifiers and PCAPs remain private.

## 2. Response decryption

After chunk/gzip decoding, the update JSON body is **927 bytes**, SHA-256:

```text
28cf9d69a101879b3f5853def6f67950612f8ba7ba7e2a4c977ed660033712a5
```

Its Base64 `data` decodes to **672 bytes**: a 16-byte IV and 656 bytes of AES-CBC ciphertext. The reviewed APK's embedded UTF-8 application constant is `7Djdbh237GD2s106`; it is not a device-specific credential. PKCS7 unpadding yields **642 bytes of UTF-8 JSON**. Java names the equivalent padding `AES/CBC/PKCS5Padding`.

The analyst reports identical decryption results from Python `cryptography` and .NET `System.Security.Cryptography.Aes`. Exact plaintext SHA-256:

```text
6369d118a7f2683d2c15dfede36204da311347f5cbe8c02e71adbbe911cd32e9
```

Six synthetic decoder tests reportedly passed. Synthetic format tests, real-response decryption and local sample comparisons are distinct checks. Successful decryption establishes a client-readable payload; it does not authenticate the operator or supply the missing TLS/response authentication.

## 3. Recovered application offers

| Field | Domain offer | Netservice offer |
| --- | --- | --- |
| packageName | `com.android.domain` | `com.android.netservice` |
| versionName | 1.0.6 | 3.6.0 |
| versionCode | 106 | 60 |
| size | 3,959,797 | 4,681,852 |
| platform | allwinner | allwinner |
| id | 20 | 23 |
| infraFileId | 7346 | 7660 |
| md5 | `72ac40ce7df55d586f21c6d89cce6b7b` | `ce8e6ff74f689652f14b8f307d05f724` |
| download host/port | `40.160.68.219:38513` | `135.148.233.189:38513` |
| filename | `Domain-1.0.6-20260709-release_aw.apk` | `netservice-3.6.0-AW-202608201805-release.apk` |

Returned URLs are evidence strings, not requests made during this follow-up:

```text
http://40.160.68.219:38513/ib01/20260711172ac40ce7df55d586f21c6d89cce6b7bDomain-1.0.6-20260709-release_aw.apk
http://135.148.233.189:38513/ib01/202609011ce8e6ff74f689652f14b8f307d05f724netservice-3.6.0-AW-202608201805-release.apk
```

The acquisition analyst reports that previously retained APKs match the offered sizes and MD5 values. Their pinned SHA-256 identities are:

- Domain: `5b029abe4ad2cd6dfa18064899b50190e00ce0f50bac57cbd849502ab3108f30`.
- Netservice: `33feb99dfca28f97aed5ef1cbcb1c9ebfe215fcb8c54c56cd79a7efcfac1e77f`.

MD5 joins metadata to retained samples, not to an authenticated publisher. The server supplies `md5`, but the reviewed domain `AppInfoBean` has no corresponding field and no enforcement was found in the traced download/install path. Server inclusion and client enforcement are different claims.

The recovered `domains` value is exactly `[]`. Under the [reviewed response handler](code-analysis.md#3-server-selection-and-domain-replacement), this response does not replace the provider's candidate rows.

## 4. Post-response observations

Read-only queries at approximately **08:10 CEST** reportedly confirmed:

| Package | Active installed version | lastUpdateTime, device-local |
| --- | --- | --- |
| `com.android.domain` | 1.0.6 / 106 | 30 August 2026 12:37:44 |
| `com.android.netservice` | 3.6.0 / 60 | 18 September 2026 21:03:45 |

Netservice's system-base version 3.4.9 / 49 is inactive beneath its data update. Comparing offers against that base would misstate the update decision. The [reviewed code](code-analysis.md#6-update-decision-and-download-task) predicts no download for this list because both active packages already meet the offered version.

Reported supporting observations:

- Domain PID 4768 and netservice PID 4190 were unchanged.
- Domain's external cache contained only empty `nebula_log.txt`; no staged APK or temporary download was found.
- A database acquisition at 08:11 CEST passed SQLite integrity checking; `DOWN_LOAD_TASK_BEAN` had zero rows and `sqlite_sequence` was empty. Database SHA-256: `df904994c89c0fb2a70ecf37dd2faf8de9b6b806134985656a24d0025a952250`.
- Package `lastUpdateTime` values remained unchanged.
- Preferences advanced to attempt time `1792303042492` and server time `1792303045052`. Server time is not substituted for the packet-capture clock.

These observations support the predicted skip-update outcome; **no new installation was observed**. They are later snapshots, not continuous execution traces, and do not exclude every transient transfer or failed install attempt. Unchanged PIDs alone do not establish an absence of code execution.

## 5. Interpretation and capture limits

The record supports **live bidirectional update metadata**: a separately captured netservice task at 04:47 names domain for installation/autostart, while this domain-correlated catalogue offers both clients. This does not demonstrate execution of both installation paths during the capture. The historically observed installer relationship, current metadata and provider feedback mechanism are separate evidence categories.

The decrypted response contains no proxy module, new third-party payload, arbitrary shell command or proxy-relay instruction. It does not establish residential-proxy operation, benign infrastructure, controller identity, or what another device/profile/time would receive.

Capture filters cover TCP 9901 and 8742–8746, **not all traffic and not APK download port 38513**. API captures cannot alone exclude binary transfers or proxy traffic outside that filter. No returned APK URL was fetched to expand the sample set.

## 6. Completed recording

The analyst reports that the bounded worker finished at **09:59:59.526 CEST on 18 October 2026**. At the 10:09 read-only check, the host worker and device tcpdump were stopped, the dedicated `spr2226_capture` wakelock was absent, and Android reported `mWakefulness=Asleep` / display OFF. The recording was not extended or restarted. No application was disabled, removed or installed by the analyst.

Final private evidence comprises **26 PCAP files, 41,883 bytes and 200 packet records**. The analyst reports that every PCAP hash matched its sidecar. The decoder retained one time response and one decrypted domain update response, without framing/gap/overlap/decryption errors.

| Request | Count | Retained result |
| --- | ---: | --- |
| netservice heartbeat, TCP 8743 | 13 | Periodic control responses; not APK binaries |
| netservice task check, TCP 8742 | 2 | Both returned task 601; no additional task ID |
| netservice client-update check, TCP 8744 | 2 | Business code 200, `data: null` |
| package blacklist, TCP 8746 | 1 | Business code 200, `blacklist: []` |
| domain-correlated time GET, TCP 9901 | 1 | Successful time response |
| domain-correlated update POST, TCP 9901 | 1 | Already installed client versions; `domains: []` |

No additional decrypted domain response was reported after 07:57. A recurring follow-up was reportedly paused after completion; this documents the acquisition analyst's action, not an automation configured or checked by this review. The acquisition follow-up itself did not publish a Git update.

## 7. Reproduction boundary

The public report preserves endpoints, framing, sample hashes, byte counts and expected decrypted-result hashes. Independent reproduction still requires access to private PCAP bytes and the actual decoder. Decoder script commit/hash, exact dependency versions, invocation commands and synthetic-test sources were not supplied to this review and must not be invented. They should accompany a future sanitized reproduction package. Raw captures must first be assessed for device identifiers, tokens and other private request data.
