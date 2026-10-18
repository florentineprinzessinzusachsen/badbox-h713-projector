# SPR2226 — case chronology and response

**18 October 2026 · Device-specific case**

The [case overview](README.md) summarizes the findings. This appendix retains identity, chronology and response recommendations; collected records are centralized in [evidence.md](evidence.md), and method-level results in the linked code reports.

## Device identity

| Property | Recorded value |
| --- | --- |
| Commercial name supplied by owner | Senz projector |
| Android/USB model | SPR2226; VID/PID `1F3A:1001` |
| Board / platform | `h713m-tuna_p3` / `ares` |
| Boot hardware | `sun50iw12p1` |
| Firmware | `H713M-android11-v1.0` |
| OS / kernel | Android 11 / SDK 30; Linux 5.4.99, `armv7l` |
| Build | `userdebug`; 16 April 2026 11:38:29 CST, as reported by Android |
| Display | 1280 × 720 |
| Verified Boot | `green`; VBMeta state `locked` |

The commercial name is not attribution of firmware authorship or infrastructure ownership. Green/locked boot state does not establish that every signed component is trustworthy.

## Chronology

| Time | Record | Basis |
| --- | --- | --- |
| 16 April 2026 | Firmware build and netservice base installation | Build properties / package metadata |
| 30 August 2026 12:37:44 | domain 1.0.6 first installed | Package metadata; installer netservice |
| 18 September 2026 21:03:45 | Active netservice updated to 3.6.0 | Package metadata |
| 17 October 2026, around 23:57 | domain configuration names `d.rdnss.cloud` | Retained configuration |
| 18 October 2026 01:02:33, ESET display time | Agent.BOA detection of extracted domain DEX | Supplied transcription; timezone not established |
| 18 October 2026 05:57:23–24 UTC / 07:57:23–24 CEST | Catalogue offers both already installed clients | Analyst-reported passive capture; indirect process attribution |
| 18 October 2026 09:59:59.526 CEST | Bounded recording completes | Analyst-reported completion checks |

Package/configuration times are device-local records. ESET display time is not assumed to share the PCAP timezone. None of these records dates the infrastructure's first operation.

## 7. APK evidence

The retained domain APK is version 1.0.6 / 106, 3,959,797 bytes:

```text
SHA-256: 5b029abe4ad2cd6dfa18064899b50190e00ce0f50bac57cbd849502ab3108f30
MD5:     72ac40ce7df55d586f21c6d89cce6b7b
```

The supplied ESET transcription reports a variant of `Android/TrojanDownloader.Agent.BOA` on its extracted DEX. The transcribed object hash matches the DEX hash in the code analysis; original event records were not independently reviewed here. [ESET provenance](eset-evidence.md) records that boundary and separates Keenadu reference-repository detections from projector evidence.

For the install relationship, use [package and task evidence](evidence.md#package-inventory). For behavior, use the [domain code report](code-analysis.md), [netservice code report](netservice-code-analysis.md), [native report](native-system-services.md) and [captured catalogue](captured-domain-response.md). The APK hash does not by itself authenticate the remote publisher.

## Recommended response

For the owner: preserve sample hashes and redacted evidence, isolate the unit from sensitive networks, and seek a verified remediation or return/reklamasjon.

For the supplier: provide the exact firmware image and SHA-256; explain the purpose and ownership of netservice/domain and task 601; remediate the unauthenticated ADB, permissive SELinux and privileged update surfaces; and document signed firmware/package changes.

The evidence supports escalation of the privileged downloader and reported malware detection. It does not identify the operator, establish proxy activity, or extend the findings to every device sold under the same name.
