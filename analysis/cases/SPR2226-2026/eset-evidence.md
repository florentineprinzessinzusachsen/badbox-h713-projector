# SPR2226 — ESET detection evidence and provenance

## Domain detection

The owner supplied a redacted evidence package on 18 October 2026. It contains analyst transcriptions of ESET screenshots, selected redacted strings from a private binary log, a reported domain hash comparison, a manifest and checksums. Original screenshots, raw binary logs and malware samples are not included. The reviewer verified all seven package checksum entries and all manifest sizes/hashes; this checks package consistency, not the authenticity of the transcribed ESET event.

The supplied event transcription records:

| Field | Transcribed value |
| --- | --- |
| Product | ESET Internet Security |
| Scanner | Real-time filesystem protection |
| Event time, as displayed | 18 October 2026 01:02:33; timezone not established |
| First seen locally | 18 October 2026 01:02:28 |
| Object | `[ANALYSIS_DIRECTORY]/domain_extract2/classes.dex` |
| Detection | A variant of `Android/TrojanDownloader.Agent.BOA` |
| Action | Cleaned by deletion |
| Accessing process | PowerShell `pwsh.exe` |
| Object SHA-256 | `8f013074186630aa31d6146bd9431bb6a0568c25438103731c81106176715d1a` |

The transcribed DEX hash matches the hash recorded in the [domain code analysis](code-analysis.md). That report identifies the enclosing APK as version 1.0.6 / 106, 3,959,797 bytes, SHA-256 `5b029abe4ad2cd6dfa18064899b50190e00ce0f50bac57cbd849502ab3108f30`. The redacted package reports rehashing the 6,750,996-byte DEX directly from the APK entry. The reviewer did not receive that APK and has not independently repeated that extraction/hash comparison.

The package therefore supplies a documented, hash-correlated **reported detection**, not a new independent antivirus scan. Its `detect.xml` contains an empty `LOG` and confirms no event. Selected `virlog.dat` strings contain the Agent.BOA name but are not a full event parser or independently authenticated original log. Product build and confirmed detection-module versions were not supplied. The raw string `34013 (20261017)` must not be assigned a module/version meaning without evidence.

Deletion concerns the local Windows DEX copy, not removal from the projector. PowerShell accessing an Android sample does not mean PowerShell is infected or that the Android code ran on Windows. Repeated extraction/detection strings do not establish multiple independent infections.

## Separate Keenadu reference samples

The supplied transcriptions also report `Android/TrojanDownloader.Keenadu.A` detections of two `plugin.jar` copies introduced by cloning the public AT-M269 reference repository. They are not evidence that this plugin was collected from the SPR2226.

The redacted transcription's Keenadu hash has **63 hexadecimal characters**, so it is not a complete SHA-256. This transcription defect must be checked against the retained original screenshot. It must not silently be treated as a verified ESET object hash.

Independent hashing during review of the repository's `downloads/plugin.jar` and `evidence/data/data/com.android.umanalytics.yiyou/cache/plugin.jar` gave the same complete SHA-256:

```text
e2e5f86df18e0cdef9595bd886280f158001250f1565fa3e6dfe492b114be6f7
```

This is a new repository-file hash check, not a correction authenticated against the omitted ESET screenshots. The original supplied archive is unchanged.

## Interpretation and publication

ESET's reported Agent.BOA classification, the separately reviewed downloader code, and the [later domain-correlated update response](captured-domain-response.md) are distinct, mutually relevant observations. They do not establish ESET's internal signature rationale, BADBOX attribution, residential-proxy operation or controller identity.

No personal email, complete MAC, JWT-like token, UUID or personal home-directory path was found in the supplied text package by the review's pattern checks. Exact event times and sample hashes can still make the case recognizable. Raw logs, screenshots and PCAPs remain private; no sample binaries are added by this documentation update.
