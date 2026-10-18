# com.android.domain 1.0.6 — code-level analysis

## Sample and method

APK SHA-256: `5b029abe4ad2cd6dfa18064899b50190e00ce0f50bac57cbd849502ab3108f30` (3,959,797 bytes). Package `com.android.domain`, versionCode 106. Analysis uses this retained APK, Android SDK 35.0.0 `aapt`/`dexdump`, and JADX 1.5.6 with targeted single-class decompilation. No extracted application code was executed. This static-analysis phase did not query production endpoints. A later passive capture is documented separately in [captured-domain-response.md](captured-domain-response.md); process attribution there is indirect, not PID/socket-verified.

The APK's `classes.dex` is 6,750,996 bytes, SHA-256 `8f013074186630aa31d6146bd9431bb6a0568c25438103731c81106176715d1a`. This was rehashed directly from the ZIP entry in memory, independently of the previously extracted file.

The Java below is analyst pseudocode reconstructed from the sample. Method names and DEX code-item offsets identify the original instructions. JADX renames short package names (for example `b.b` becomes `p001b.b`); those are decompiler aliases. The downloader's exception paths produce JADX warnings, so conclusions there are restricted to the straight-line transfer and callback instructions also present in the DEX.

## 1. Entry points and persistence

The binary manifest sets `sharedUserId="android.uid.system"`, `coreApp=true`, and target SDK 34. The captured package/process metadata establishes UID 1000 on this unit. A shared UID declaration alone would not establish that privilege on another device.

| Entry | Code behavior |
| --- | --- |
| `StartupBroadcast.onReceive`, `0x32b360` | Compares the action only with `BOOT_COMPLETED`; if the service is absent, starts it with boolean extra `boot_complleted=true` (original spelling). |
| `DomainProvider.onCreate` | Creates its SQLite helper; starts the foreground service when it is not already running. Provider initialization is another service entry point. |
| `SystemService.onCreate`, `0x32bc20` | Calls `startForeground(1001, buildNotification())`. The notification describes a running system service. |
| `SystemService.onStartCommand`, `0x32b70c` | Handles explicit extras, provider-style starts, and null-intent restarts; returns integer 1. |

`StartupBroadcast` declares `PACKAGE_ADDED` and `PACKAGE_REMOVED` in its manifest filter, but the examined `onReceive` body does not process those actions. Earlier wording that it *handles* all three actions was incorrect.

`onStartCommand` has three branches:

```java
if (intent != null && intent.extras != null) {
    if (extras.getBoolean("boot_complleted", true)) startService();
} else if (intent != null) {
    handler.postDelayed(startService, 60_000);
} else if (now - config["start_time"] > 1_800_000) {
    startService();
    config["start_time"] = now;
}
return 1;
```

`startService()` uses a process-static `isRunning` guard, registers a download callback, constructs `AppnetPresenter`, registers network listeners, and initializes the timer. It does not launch a shell command received from the server in this path.

## 2. Scheduling: ten-minute timer, eight-hour request gate

`initScheduleTask` (`0x32ba60`) calls `Timer.schedule(task, 0, 600000)`. The task reads `CONFIG_PARAM_LAST_TIME`:

```java
elapsed = now - lastAttempt;
if (elapsed >= 0 && elapsed < 28_800_000) return;
config["CONFIG_PARAM_LAST_TIME"] = now;
getTestAvailableDomainName();
presenter.createNewInstance(context);
presenter.getServerTime();
```

The timestamp is written **before** network success. A failed attempt can therefore suppress another scheduled request for eight hours. A negative elapsed time bypasses the gate. A short traffic snapshot can miss this client's activity for this reason; periodic traffic was not inferred from the constants alone.

The handler constants named activation, heartbeat, and log submission do not themselves establish implemented telemetry jobs: the examined `handleMessage` body only chains messages 0 to 1 to 2 with ten-second delays. The request path above is the demonstrated scheduling mechanism.

## 3. Server selection and domain replacement

`getTestAvailableDomainName()` returns early while `CONFIG_DOMAINNAME` is nonempty. Otherwise it increments an index and chooses a candidate modulo the list size. The list starts with a decrypted embedded hostname, followed by provider entries of `type=1` split on commas. `DomainPool.c()` uses the first qualifying nonempty row and breaks; it does not aggregate every row.

The embedded fallback is hex-encoded AES ciphertext. `b.b` uses the first 16 UTF-8 bytes of its embedded key string and `Cipher.getInstance("AES")`. Offline AES-ECB/PKCS7 decryption of the sample constant yields `d.rdnss.cloud`. This independently links the obfuscated fallback to the configuration hostname. The response-encryption routine described below is a separate AES-CBC implementation.

`ServerInterface.a()` prefers the configured domain, then the test domain, then the decoded fallback. `BizImpl.resolveFirstDns()` calls `DnsUtils.resolveFirst()`. Both GET and POST create a Retrofit service at `http://<resolved-address>:9901`. Before POST, the signature is recomputed using that resolved address.

On a successful time response, `onTimeTesult` (`0x32bf64`) copies `CONFIG_TEST_DOMAINNAME` to `CONFIG_DOMAINNAME`, stores the server time in `config_param_time`, and invokes `postServerAppUpdate()`. Server/network/parser failure callbacks clear `CONFIG_DOMAINNAME`, allowing a later candidate selection.

When an update response contains nonempty `domains`, `DomainPool.g` (`0x32affc`) starts a thread that deletes the provider's domain rows and inserts the supplied list. This is replacement of the persisted candidate list, not merely an in-memory hint. An empty list does not clear the table through `onAppUpdate`, which only calls `g()` for a nonempty list.

## 4. Request protocol and identifiers

`ServerApi` uses:

| Request | Path | Purpose in client |
| --- | --- | --- |
| GET | `/app-api/hnc2fng/gnbht53hdv2/dhgb35dg` | Server-time request; outer response code must equal 200. |
| POST | `/app-api/hnc2fng/ghbn1hebdsh/yh65hbfvg3jfdbs` | Update request, `application/json;charset=UTF-8`. |

`AppnetPresenter.postServerAppUpdate()` supplies a body with a `platform` field. `BizImpl.getHeardInfo` (`0x32a274`) constructs HTTP headers:

| Header | Source |
| --- | --- |
| `time` | Stored server time from `config_param_time`. |
| `api-version` | Literal `106`. |
| `mac` | `com.android.util.b.d()`: `NetworkInterface.getByName("eth0").getHardwareAddress()`, colon-separated uppercase hex. |
| `cpu` | Allwinner branch calls `com.android.util.b.c()`; Rockchip branch calls `com.android.util.i.b()`. |
| `d-time` | Local `System.currentTimeMillis()`. |
| `sign` | MD5-based concatenation described below; recomputed after DNS resolution. |

For Allwinner, `b.c()` reads `/sys/class/sunxi_info/sys_info`. If `sunxi_platform` equals `sun8iw7`, it chooses `sunxi_chipid`; otherwise it chooses `sunxi_serial`. The `/proc/cpuinfo`/`Serial` routine exists in the APK but is selected by the Rockchip header branch. It was incorrect to present `/proc/cpuinfo` as the established identifier source for this Allwinner request path.

These methods place identifiers into a request object. Actual successful delivery of this exact request was not established by packet capture. The code also logs header inputs, so unredacted app logs can contain hardware identifiers.

## 5. Signing and response encryption

`EncryptionUtils.sign` (`0x32d338`) computes lowercase hex MD5 of concatenated UTF-8 strings:

```text
host + K + "8622Nbdf52" + mac + K + "2d6fndw" + cpu
     + K + "3hd73bx6g" + serverTime + K + "23jhY6DGVhbd"
```

`K` is an embedded application constant, not a captured user credential. The method accepts a version argument but does not use it in the digest. `d-time` is also absent from the digest. This is a shared-secret digest construction, not a public-key signature or an HMAC. No conclusion about the server's actual verification policy follows from the client method alone.

`AppnetPresenter$2.onResponse` (`0x32c2c4`) first parses JSON, checks outer `code == 200` and a non-null `data`, then calls `EncryptionUtils.decrypt(data, embeddedKey)` (`0x32d208`). The format is:

```text
data = Base64( IV[16] || AES-CBC ciphertext )
key  = 16-byte UTF-8 application constant
mode = AES/CBC/PKCS5Padding
plaintext = UTF-8 JSON
```

The client extracts `uotaApps` and `domains`; missing arrays become empty lists. It posts decrypted JSON to EventBus and passes the parsed lists to `SystemService.onAppUpdate`.

No response MAC or signature verification occurs between this decryption and the update callback in the reviewed path. CBC encryption with an embedded shared key does not establish independent server authenticity. The service URLs use HTTP, so this route also lacks TLS server authentication. Android's package installer still imposes its own APK validity, signing and update rules; this analysis does not claim an arbitrary unsigned APK will install. A package installed by this UID-1000 client does not automatically inherit system UID.

## 6. Update decision and download task

`SystemService.onAppUpdate` (`0x32bac0`) iterates the server-supplied application list. `com.android.util.h.a()` queries installed package metadata and returns true when the installed versionCode is greater than or equal to the supplied versionCode. A missing package returns false.

```java
for (app : uotaApps) {
    if (!installedVersionAtLeast(app.packageName, app.versionCode)) {
        addDownloadTask(app.url, /*taskType=*/1, app.size);
    }
}
```

The `AppInfoBean` fields are `infraFileId`, `packageName`, `platform`, `size`, `url`, `versionCode`, and `versionName`. The reviewed model has no expected digest field. The task builder receives URL, basename, external-cache `APP` directory, taskType and expected size. It does **not** carry the expected package name or version into the installer callback.

The later [captured response](captured-domain-response.md) contains an extra `md5` field for each offered APK. The reviewed `AppInfoBean` model does not represent that field, and no enforcement was found in the traced download-to-install path. The list offers domain 1.0.6 and netservice 3.6.0, already installed at those versions; it is not evidence of a new download.

That distinction matters: the installed-package check is based on server metadata, while the eventual installer receives the downloaded file. No explicit archive-package-name comparison against that metadata was found along this path. This is a client validation gap, not evidence that Android's installer security checks are bypassed.

## 7. Transfer, resume, and integrity boundaries

`DownLoadTaskThread.run` (`0x328724`) uses `HttpURLConnection`, GET, a 20-second connect timeout, `Accept-Language: zh-CN`, and a legacy MSIE-style User-Agent. It stages downloads as `<filename>.tmp` using `RandomAccessFile("rwd")`.

The transfer logic includes:

- Reuse of an existing final file when its length equals the supplied total, followed by the completion callback.
- A resume offset of `tmp.length() - 1` when nonzero, and a `Range` header ending at `total - 1`.
- A seek to that offset, while the byte counter starts at the original temporary-file length. This makes the counter and actual offset differ by one on resumed transfers; the exact resulting file needs a controlled synthetic test before claiming a reproducible corruption bug.
- Completion when the byte counter is at least the supplied total, rename of the temporary file, and invocation of the completion callback.

No digest, pinned certificate, or APK-signature comparison was found in the reviewed downloader-to-install path. Its explicit completion conditions use lengths/counters. This differs from the separate **netservice task database's MD5**, which matched the collected domain APK; a digest in that upstream database does not prove this second-stage client checks downloaded payload digests.

The code declares directory categories `ADVERT`, `APP`, `LAUNCHER`, and `OTA`. `SystemService.onAppUpdate()` uses type 1 (`APP`). Category names alone do not establish working firmware flashing or advertisement execution in this sample.

## 8. Installer submission and misleading success log

`com.android.util.h.b` (`0x32de84`) creates a `PackageInstaller.SessionParams(1)` full-install session, writes the file in 65,536-byte chunks under session entry name `CocosDemo`, calls `fsync`, closes the streams, and commits with a broadcast `IntentSender`. The broadcast action string is `android.intent.action.PACKAGE_ADDED`.

The helper returns true immediately after `session.commit()` returns. It does not wait for a status callback. `SystemService.onFinish` (`0x32bd44`) then schedules a five-second runnable:

```java
submitted = installHelper(downloadPath);
after(5000, () -> {
    if (submitted) {
        log("apk is installed successfully");
        deleteFile(downloadPath);
    }
});
```

Therefore that log line means **submission did not throw**, not verified successful installation. The package broadcast receiver examined above ignores package actions. The reviewed runnable does not query final installed metadata or `PackageInstaller.EXTRA_STATUS`. This also explains why a downloaded staging file can disappear after submission.

## 9. Exported domain provider

The manifest exports `DomainProvider` with authority `com.android.providerdomain.provider`. Neither the application nor this provider declares a caller permission in the inspected binary manifest; the reviewed CRUD methods contain no caller UID or permission check.

`query`, `insert`, `update`, and `delete` access the SQLite `domains` table and notify changes. The URI matcher supports `domains` and `domains/#`; item operations use `_id`. `insert()` directly inserts supplied `ContentValues` without first checking the URI matcher. The MIME types still reference `com.example.providerapp.students`, suggesting copied provider scaffolding, which is not attribution.

Together with candidate selection, this creates a plausible local domain-list tampering surface. A cross-UID write and resulting server redirection were **not tested**. No provider insert/delete operation was performed on the projector for this analysis. It would be incorrect to call this a demonstrated exploit or claim system-UID code execution from the exported flag alone.

## 10. Conclusions supported by this code

The demonstrated function is a privileged remote APK update client with hardware identifiers in request headers, an eight-hour request gate, encrypted server-supplied application lists, persisted replacement domain lists, resumable downloads, and installer submission. The examined client has weak application-level authenticity/integrity checks and reports installation success prematurely. Subsequent [netservice analysis](netservice-code-analysis.md#10-the-bidirectional-link-with-comandroiddomain) establishes that type-0 rows in this provider also supply netservice's server candidates; domain itself selects type 1.

These are concrete code findings. They do not establish a residential-proxy implementation, arbitrary remote shell command dispatch, successful installation of every offered APK, or the identity of the controller. The separate [netservice](netservice-code-analysis.md) and [native](native-system-services.md) reports now document those components' reviewed control flow; the [joined chain report](chain-analysis.md) lists the remaining execution/authorization questions.

## Reproduction

With a locally held sample and official JADX/Android SDK tools:

```text
jadx --config none --no-res --no-debug-info --single-class com.android.systemss.SystemService --single-class-output SystemService.java sample.apk
jadx --config none --no-res --no-debug-info --single-class com.android.nebulasdk.api.biz.bizimpl.BizImpl --single-class-output BizImpl.java sample.apk
jadx --config none --no-res --no-debug-info --single-class com.android.systemss.presenter.AppnetPresenter --single-class-output AppnetPresenter.java sample.apk
aapt dump xmltree sample.apk AndroidManifest.xml
dexdump -d classes.dex
```

Repeat single-class extraction for `DomainPool`, `DomainProvider`, `EncryptionUtils`, `com.android.util.b`, `com.android.util.c`, `com.android.util.h`, `com.android.download.d`, `DownLoadTaskThread`, `AppInfoBean`, `BaseBiz`, and raw `b.b`. Preserve the APK hash and compare the code-item offsets above; line numbers vary with decompiler settings. Keys and identifiers from an unrelated device/configuration must not be substituted to manufacture a matching result.

### Offline verification results

An analyst-side PowerShell check completed with 11 passing checks: timer/gate constants, metadata use, installer submission semantics, delayed cleanup, identifier header call sites, response arrays, CBC framing, provider insert/caller-check inspection, size-based completion, fallback-hostname decryption, and a synthetic CBC frame round trip. APK SHA-256 was required to match before those checks ran. These checks validate the reconstruction against local decompiler output and the crypto format; they are not runtime tests of the projector or proof of live server behavior.
