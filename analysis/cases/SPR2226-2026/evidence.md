# SPR2226 evidence appendix

This appendix records the reproducible, redacted evidence behind the SPR2226 case. It is deliberately text-only. Raw APKs, DEX files, private databases, credentials and unique hardware identifiers are retained outside the public repository.

## Collection conditions

| Field | Value |
| --- | --- |
| Transport | USB ADB |
| USB identity | `SPR2226`, VID/PID `1F3A:1001` |
| ADB serial | Redacted |
| Collection mode | Read-only inspection |
| Root check | `adb shell su 0 id` returned UID 0 |
| Display | 1280x720 |

Collection used USB ADB. Complete network isolation was not established in this appendix. Network observations are a point-in-time snapshot and are not a substitute for a packet capture.

## Build properties

Relevant non-unique properties:

```text
ro.product.model=SPR2226
ro.product.manufacturer=Allwinner
ro.product.device=h713m-tuna_p3
ro.board.platform=ares
ro.boot.hardware=sun50iw12p1
ro.build.version.release=11
ro.build.version.sdk=30
ro.build.version.incremental=H713M-android11-v1.0
ro.build.type=userdebug
ro.debuggable=1
ro.adb.secure=0
service.adb.tcp.port=5555
ro.boot.verifiedbootstate=green
ro.boot.vbmeta.device_state=locked
```

Additional security-state observations:

```text
Linux 5.4.99
architecture: armv7l
SELinux: permissive
```

The build date reported by Android was 16 April 2026 11:38:29 CST. These values identify the examined image but do not prove that all units sold under the same product name use the same image.

## Package inventory

### `com.android.netservice`

```text
system path: /system/app/netserevice-release
system version: netservice-3.4.9-allwinner
data version: 3.6.0
versionCode: 60
firstInstallTime: 2026-04-16 05:38:48
lastUpdateTime: 2026-09-18 21:03:45
installer: com.android.netservice
```

Observed permission groups included:

```text
INSTALL_PACKAGES
REQUEST_INSTALL_PACKAGES
RECEIVE_BOOT_COMPLETED
SYSTEM_ALERT_WINDOW
INTERNET
```

The package data directory contained configuration, a work database and `databases/sofa-db`.

### `com.android.domain`

```text
versionName: 1.0.6
versionCode: 106
firstInstallTime: 2026-08-30 12:37:44
lastUpdateTime: 2026-08-30 12:37:44
installer: com.android.netservice
codePath: /data/app/.../com.android.domain/.../base.apk
service: com.android.systemss.SystemService
running UID: 1000 (system)
```

The package requested high-impact capabilities including package installation/deletion, overlay, network, camera, microphone, system settings, reboot/shutdown and related system control. The complete manifest is retained locally; identifiers and unrelated permissions are omitted here for minimisation.

## Database evidence

The private `sofa-db` database was inspected read-only. Relevant logical records were:

```text
APP_CONFIG_BEAN
  taskId: 601
  package: com.android.domain
  component: com.android.domain/com.android.systemss.SystemService
  version: 1.0.6
  versionCode: 106
  AUTO_START: 1
  OPEN_TIMES: 2

TASK_INFO_BEAN
  taskType: 1
  taskId: 601
  file: Domain-1.0.6-20260709-release_aw.apk
  MIME: application/vnd.android.package-archive
  fileSize: 3959797
  MD5: 72ac40ce7df55d586f21c6d89cce6b7b
  download host: 40.160.68.219:38513
```

The task URL contained the same MD5 and filename shown above. Its full value appears in [the captured response](captured-domain-response.md#3-recovered-application-offers); the path encodes a date, digest and filename rather than a demonstrated unique hardware identifier.

This is the strongest chain on the examined unit:

```text
com.android.netservice
  -> sofa-db task 601
  -> Domain-1.0.6-20260709-release_aw.apk
  -> installed com.android.domain
```

## APK evidence

The APK pulled from the unit had:

```text
size:    3959797 bytes
MD5:     72AC40CE7DF55D586F21C6D89CCE6B7B
SHA-256: 5B029ABE4AD2CD6DFA18064899B50190E00CE0F50BAC57CBD849502AB3108F30
```

The independent file-detection result is recorded once in the [case report](reverse-engineering.md#7-apk-evidence). The retained APK supports the [code-level analysis](code-analysis.md); local detection records remain separate evidence.

## System files

`InstallXAPK` was identified as package `com.ik.installxapk`, version 1.0.5 / code 5. Its declared installation/boot permissions describe requested access, not proof of every operation being available or used.

The following files were present and inspected read-only:

```text
/system/bin/appsdisable
/system/bin/gmsopt
/system/bin/qw
/system/bin/systemmixservice
/system/lib/libsystemmix_jni.so
/system/lib/libsystemmixservice.so
/system/app/InstallXAPK/InstallXAPK.apk
```

Recorded hashes:

```text
/system/app/InstallXAPK/InstallXAPK.apk
  SHA-256 c17931b6873451649aade17908a355d69047f12a002760b65eb5b4ee7fc9342d

/system/bin/appsdisable
  SHA-256 e165ab7ce21b5d4fafba967b9d09f271be466f2835cdff7f63f52684932dbd6a

/system/bin/gmsopt
  SHA-256 c3a7fde8094063c9260cf5e1292f93e474cdb59116b6a27ff58ce149cb425fb8

/system/etc/init/appsdisable.rc
  SHA-256 1dccf2b7e7f436fa68006260b1781fd95a8c7fc9305f51d2ff33b79338e08a0

/system/etc/init/gmsopt.rc
  SHA-256 98e50bc5de107386d1b4c79d54b8051dcdce4603ea7b44ea574c2d7d5aedcc13
```

The two boot scripts use root-triggered services after `sys.boot_completed=1`. They query Google boot receiver components and can call `pm disable` on matching components. An empty disabled-package list does not establish that no components were disabled. Reinspection found `start_disable=1` and disabled GMS components; their historical cause is unresolved.

## Additional code-level evidence

The active netservice APK hash was independently reconfirmed on the device. Its two DEX hashes, the firmware-base APK hash, five native sample hashes, exact original-sample comparisons, Binder dispatch table and JNI table are documented in the [netservice](netservice-code-analysis.md) and [native](native-system-services.md) reports. The [joined chain](chain-analysis.md) records the shared domain-provider URI and the boundaries between implemented capability and observed execution.

The offline [sample-verification.json](sample-verification.json) records 57 passing checks without unique device identifiers. No package, provider row, Binder property or boot asset was changed to obtain those checks.

## Configuration indicators

Redacted configuration values observed on the device:

```text
netservice CONFIG_DOMAINNAME=business.dnsyc.top
netservice CONFIG_HEARTBEAT_DOMAINNAME=heartbeat.dnsyc.top
domain CONFIG_DOMAINNAME=d.rdnss.cloud
domain CONFIG_TEST_DOMAINNAME=d.rdnss.cloud
```

A system token containing device metadata was present in `netservice` configuration. It is not reproduced here.

## Process and network snapshot

At the time of the snapshot:

```text
com.android.netservice: running as system
com.android.domain: running as system
adbd: listening on TCP 5555
```

Connections observed belonged primarily to screen-display and normal Google/YouTube processes. No active connection owned by `com.android.domain` or `com.android.netservice` was observed in that snapshot. This is a negative observation, not proof that the services never communicate.

## Negative comparison against the AT-M269 sample

The following main-case indicators were not found in the examined SPR2226 package/file inventory:

```text
com.android.umanalytics.yiyou
com.android.sysapp
com.google.adtest
com.fotas.wanapp
com.ad.proxy
com.szns.sdk
com.link.core
ddth2
plugin.jar
encrypted .rf proxy modules
```

Therefore the evidence supports a related H713/H713M platform and a distinct system updater/service chain. It does not support claiming that the SPR2226 unit contains the exact AT-M269 payload set.

## Reproduction commands

Run against an isolated test unit. Store output outside a public repository and redact unique identifiers:

```bash
adb devices -l
adb shell getprop > getprop.txt
adb shell su 0 id > root-check.txt
adb shell pm list packages -f -i > packages-with-installers.txt
adb shell dumpsys package com.android.netservice > netservice-package.txt
adb shell dumpsys package com.android.domain > domain-package.txt
adb shell su 0 netstat -tnp > netstat.txt
adb shell su 0 sha256sum /system/app/InstallXAPK/InstallXAPK.apk > system-hashes.txt
```

For database collection, use a forensic copy and publish only selected rows after removing tokens, serials, MAC addresses, CPU IDs and local paths containing user names.

## Evidence limitations

- A file-detection result does not identify the manufacturer, seller or hosting provider.
- Hosting registration for an IP address is not attribution to the registrant.
- A package's permissions show capability, not proof that every capability was exercised.
- The process snapshot did not show proxy traffic.
- The absence of the known proxy packages/modules is meaningful for this snapshot but not a lifetime guarantee.
- Whole-firmware byte identity with AT-M269 is not established; captured build/package inventories differ.
