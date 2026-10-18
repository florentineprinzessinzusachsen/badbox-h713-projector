# SPR2226 / H713M — case overview

The examined Senz `SPR2226` contains a system-privileged APK update chain. Its captured package inventory differs from AT-M269, while four native binaries match that case exactly. Whole-firmware identity, BADBOX attribution and residential-proxy operation are not established.

## Main findings

| Finding | Basis | Details |
| --- | --- | --- |
| Unsafe runtime configuration | Android 11 / H713M; `ro.adb.secure=0`, shell UID 0, permissive SELinux | [Collected properties](evidence.md#build-properties) |
| netservice installed domain | Task 601, matching APK size/MD5 and package-manager installer field | [Database records](evidence.md#database-evidence) |
| Agent.BOA detection of domain DEX | Supplied ESET transcription's DEX hash matches the code report; originals not reviewed here | [Detection provenance](eset-evidence.md) |
| Live update catalogue | Analyst-reported capture offers already installed domain 1.0.6 and netservice 3.6.0; indirect process attribution | [Captured response](captured-domain-response.md) |
| Domain download/install weaknesses | MD5 supplied by server is not enforced in traced client path; success logged before final install result | [Domain code](code-analysis.md#6-update-decision-and-download-task) |
| Shared native files | Exact SHA-256 matches for qw/systemmix executable and two libraries | [Native comparison](native-system-services.md#collection-and-byte-identity) |

## Installation relationship

The firmware contains netservice 3.4.9 as a system base; its active data update is 3.6.0. Its database task 601 names domain 1.0.6 and `com.android.systemss.SystemService`. Package metadata records netservice as domain's installer. Both active clients run as UID 1000.

Domain's later catalogue offers both clients at their already installed versions. This is bidirectional update metadata, not proof of two new installations. The separate provider feedback path is explained in the [joined architecture](chain-analysis.md).

## Evidence map

| Report | Purpose |
| --- | --- |
| [Evidence appendix](evidence.md) | Redacted properties, package/task records, hashes, indicators and collection commands |
| [Joined chain](chain-analysis.md) | Component relationships and static-versus-runtime boundaries |
| [Domain code analysis](code-analysis.md) | Scheduling, identifiers, AES response parsing, provider, downloader and installer |
| [Netservice code analysis](netservice-code-analysis.md) | Heartbeat dispatch, tasks, MD5 gate, install callbacks, blacklist and diagnostics |
| [Native services](native-system-services.md) | Binder/JNI map, qw, separate su and Google receiver scripts |
| [ESET evidence](eset-evidence.md) | Reported detection, DEX hash link and separate Keenadu reference samples |
| [Captured response](captured-domain-response.md) | HTTP/decryption results, offered versions, post-response observations and capture limits |
| [Case chronology](reverse-engineering.md) | Device identity, historical package dates and response recommendations |
| [Offline verifier](tools/verify_samples.py) / [result](sample-verification.json) | Reported 57 checks against pinned netservice/native samples; does not cover domain APK |

## Scope and handling

The AT-M269 proxy modules and `yiyou`/`sysapp`/`adtest` packages were not found in the captured inventory. Their absence is a snapshot result, not a lifetime guarantee. A restricted-port capture cannot exclude traffic on other ports.

Static code establishes implemented functionality; task records establish local package relationships. Exported-provider access and native interfaces were not exercised from another UID. ESET originals and PCAP/sample bytes were not independently inspected by this documentation reviewer. The detailed reports distinguish analyst-supplied results from checks repeated during review.

Raw APK/DEX/JAR samples, PCAPs, tokens, serials, MACs and CPU identifiers remain outside this contribution. Use the commands in the [evidence appendix](evidence.md#reproduction-commands) on an isolated test unit and redact output before publication.
