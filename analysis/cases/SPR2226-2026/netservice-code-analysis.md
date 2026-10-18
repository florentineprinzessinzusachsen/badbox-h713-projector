# com.android.netservice — request, task and installation control flow

## Samples and evidence boundary

The active package on the examined Senz/SPR2226 unit is `com.android.netservice` 3.6.0, versionCode 60, UID 1000. Its APK was rehashed on the device during this analysis and matched the retained local APK:

| Sample | Bytes | SHA-256 |
| --- | ---: | --- |
| Active data APK, version 3.6.0 | 4,681,852 | `33feb99dfca28f97aed5ef1cbcb1c9ebfe215fcb8c54c56cd79a7efcfac1e77f` |
| System APK, `netservice-3.4.9-allwinner`, code 49 | 4,747,893 | `cc8c01a176b04b7087932cb3caf57b599c7c99dd48dbfa14bca7635020faebb7` |
| Active APK `classes.dex` | 8,279,540 | `0c9d9384d9ec1ec4f5d0840b0ef87e2de63843e302545706bde8d0a088c01be0` |
| Active APK `classes2.dex` | 1,159,440 | `3901b28d2c4c81eaede04f95d75d4d3c96b11f690375253b87183b023fc8c34b` |

The system APK is `/system/app/netserevice-release/netserevice-release.apk`; its existence establishes a firmware-resident base, while the active code analysed below is the later data update. Package metadata reports firstInstallTime 16 April 2026 05:38:48 and lastUpdateTime 18 September 2026 21:03:45. Those device-local timestamps do not date every behavior in version 3.6.0 back to manufacture.

Targeted JADX 1.5.6 decompilation was checked against Androguard DEX instructions. Several Kotlin/coroutine and resource methods produce decompiler errors; linear/simple output and original instruction calls were used for affected branches. An invalid-looking `throw th` inside JADX's installer loop is a reconstruction error: the DEX has `read -> write -> goto read`, followed by `fsync` on EOF. It was not treated as application behavior.

## 1. Startup and recovery

`StartupBroadcast.b` (`classes.dex:0x345b00`) distinguishes:

- `BOOT_COMPLETED`: increments `CONFIG_ACTIVATE_COUNT` when a verification string is non-null, then starts the service with boot/reason extras.
- `MY_PACKAGE_REPLACED`: requests a restart even if a service-running check reports true.
- package add/remove: sets the installed-app-list update flag.
- a GMS package-change action: logs the event; this branch alone does not disable GMS.

`ConfigurationProvider.onCreate()` resets its service-running flag and starts the foreground service. Its CRUD methods return null/zero; unlike `domain`'s provider, it is not an implemented configuration-write interface in the inspected code.

`SystemService` calls `startForeground`, initializes SDK/database/download managers, recovers pending self-update state, starts application/device monitoring, cleans download cache and processes locally stored disable/autostart policies. Explicit starts initialize immediately; starts without extras wait one minute. Null-intent restarts have a 30-minute throttle. A process-static guard prevents duplicate initialization.

The handler sequences local download recovery, event submission and heartbeat startup. `NoneBizTask.executeOnce()` only logs in the examined build; its name does not establish a hidden task executor.

## 2. Business and heartbeat endpoints

The misleadingly named `WebsocketClient` is a polling `Thread` in the reviewed implementation. It calls `HeartbeatInteractor`, which uses the HTTP client; no WebSocket handshake or frame loop is implemented in that class.

`ServerInterface` contains hex-encoded fallback hostnames. `UtilEncrypt` derives a 16-byte AES key by truncating its embedded string and calls `Cipher.getInstance("AES")`. Independent offline AES-ECB/PKCS7 decryption yields:

```text
BUSINESS_IP_ADDRESS -> business.dnsyc.top
SERVER_IP_ADDRESS   -> heartbeat.dnsyc.top
```

This obfuscation pattern and embedded key are also present in the [domain client](code-analysis.md); that supports shared implementation material, without identifying its author.

`DomainType` provides the port mapping:

| Purpose | Port | Request path |
| --- | ---: | --- |
| Network-access certification | 8742 | POST `/app-api/task/newNetworkAccessCertification/` |
| Task-list check | 8742 | POST `/app-api/task/checkTaskPush/` |
| Heartbeat (enum name `WEBSOCKET`) | 8743 | GET `/app-api/task/deviceHeartBeat/v2/{mac}/{updateVersion}` |
| Client/self-update check | 8744 | POST `/app-api/task/checkAppVersionIsUpdate/` |
| Event records | 8745 | POST `/app-api/task/recoedDeviceEvent` |
| Blacklist | 8746 | GET `/app-api/app/blacklisted` |
| Log archive upload | 8742 | multipart POST `/app-api/task/device/log/upload` |

`DomainResolver` uses cached available/test names, resolves through `DnsUtils.resolveFirst`, and falls back to the unresolved name on DNS-resolution exceptions. `NetClient.c/d` explicitly build `http` URLs. A private development fallback `192.168.1.87` exists in code; its presence does not show that address was contacted on this unit.

The heartbeat template's `{updateVersion}` name is misleading: the active `NetRepository.deviceHeartbeat(context, mac, cpu)` substitutes the **CPU identifier** into that path segment, not the application version. Both MAC and CPU can consequently occur in the request URL as well as headers.

`NetClient.i` (`0x3419f0`) adds `deviceToken`, `api-version: 1`, `mac`, and uppercase header `CPU`, then merges extra headers. `getEthernetMac()` prefers `eth0` and falls back to Wi-Fi. CPU identification first uses saved preferences, then platform reads, then a persistent-file/generated-UUID fallback. An identifier field is therefore not necessarily an immutable hardware serial.

Certification submits MAC, device/name, Android version, brand, build, CPU ID, memory/storage description, device model, platform, SDK version and launcher identity. A returned certification string is persisted. A successful heartbeat can replace `SYSYTEM_TOKEN` (original spelling), which becomes the subsequent `deviceToken` header. Actual token values are excluded here.

The reviewed `NetRepository` parses JSON into typed response models and checks the business `code == 200`. Unlike `domain`'s AES-CBC `data` envelope, the reviewed netservice task/heartbeat route uses these JSON models directly. Encrypted fallback **hostnames** are not evidence that this HTTP request/response traffic is encrypted.

## 3. Heartbeat dispatch and offline continuation

The heartbeat loop reads `HERAT_INTERVAL_TIME`, defaulting to 1,800,000 ms when zero. Each returned `NewHeartBean` carries `type` and two integer parameters. `HeartEventThread` dispatches `type, param[0], param[1]`, pausing 60 seconds between entries. Parameter-length/index failures are caught per entry.

| Type | Dispatcher target | Demonstrated role |
| ---: | --- | --- |
| 1 | `TaskUpgradeManager.handleHeartbeatTaskCheck` | Retrieve/update push tasks. |
| 2 | `BlacklistManager.handleHeartbeatBlacklistCheck` | Retrieve remote package blacklist. |
| 3 | app-list manager | Installed-application inventory/check path. |
| 4 | `AppUpgradeManager.handleHeartbeatAppUpgrade` | Check client update metadata. |
| 5 | `handleAppRunningMonitor` | Start/stop usage tracking and set interval. |
| 6 | `EventRecordManager.handleHeartbeatEventSubmit` | Submit accumulated event records. |
| 7 | `handleHeartbeatInterval` | Set poll interval in minutes. |
| 8 | `handleKillBlacklistInterval` | Change blacklist process-kill interval. |
| 9 | `handleDeviceRunningInterval` | Change device-runtime recording interval. |

For task checks, parameter 0 disables the API when zero; parameter 1 is an interval in minutes. `com.android.netservice.c.checkAndUpdate()` compares elapsed time, stores the new attempt timestamp before the request, and handles negative elapsed time by resetting the stored timestamp and allowing the attempt.

Successful heartbeat configurations are persisted as JSON plus a timestamp. On applicable failures, `HeartbeatInteractor` replays the last successful configuration; if absent, it constructs fixed defaults. The cached configuration reader checks that a timestamp exists but does not enforce an expiry age. A failed heartbeat therefore does not necessarily stop local checks. Business error 700 takes the activation/certification-recovery path.

## 4. Remote task to database

`TaskUpgradeManager` sends the MAC/device identifier, certification string, CPU ID and stored task version. `TaskUpgradeInteractor` resets autostart counters for tasks included with `isAutoStart=1`, then calls `SystemService.onTaskUpgrade -> TaskManager.addTaskInfos` (`0x337ce0`).

The task processor applies an activation-count condition:

```java
if (task.countActivate != 0 && task.countActivate > localActivateCount) skip;
if (task.taskData == null) skip;
upsert TaskInfoBean and AppConfigBean;
if (task.disable == 0 && package/version needs download) queue download;
```

`com.android.download.f` makes the database correspondence explicit:

| Remote field | Persisted field |
| --- | --- |
| `taskId`, `taskType`, `taskVersionCode` | `TaskInfoBean` task identity/type/version |
| `taskData.fileUrl/fileName/fileSize/md5` | download URL, filename, size and optional digest |
| `taskData.packageName/versionCode/versionName` | `AppConfigBean` package metadata |
| `taskData.isSystemApp/installPath` | system-copy branch flag and destination path |
| `isAutoStart`, `classPath`, `openApptimes`, `disable` | launch/disable policy |

When a task is unchanged but the package has disappeared, the enabled-task branch can queue its download again. Startup also resumes pending local downloads and pending client updates. That is a concrete reinstall mechanism, rather than persistence inferred only from boot permissions.

## 5. Transfer and MD5 gate

`DownLoadTaskThread.run()` delegates to `HttpRangeDownloadEngine`. The engine checks positive expected size, directory availability, existing file lengths, HTTP range responses, `Content-Range` prefixes, and exact final-file size after rename. It sets 20-second connect and 30-second read timeouts. Resumption uses the actual temporary-file length, unlike the older `domain` downloader's length-minus-one behavior.

On completion, `DownloadFinishHandler` looks up the task by file URL and its app configuration by taskId. If either is missing, installation is skipped. If the required package/version is already installed, the task is cleaned up.

`DownloadFinishHandler.e` (`0x335e98`) checks MD5 only when metadata supplies one. A mismatch deletes the downloaded file and cleans the download task; an empty digest bypasses that check. Task 601 on this unit does supply an MD5, and its size/digest match the retained `domain` APK.

This is integrity against the metadata value, not independent publisher authenticity: both URL and expected digest originate in remote task data transported by the reviewed HTTP route. Android's own APK signing/install rules remain relevant.

## 6. Installation and automatic launch

For the ordinary-app branch, `DownloadFinishHandler.g` (`0x3360a0`) calls `PackageInstallUtil.silentInstall(context, path, callback)`. The helper (`0x354544`) stages bytes in a full-install `PackageInstaller` session, fsyncs, registers a session-specific receiver, and commits an explicit package-scoped result intent.

The receiver filters on `session_id`, reads `android.content.pm.extra.STATUS` and `STATUS_MESSAGE`, and calls success only for status 0. This is a meaningful difference from `domain`'s premature five-second success message. A callback argument uses the calling context's package name; that argument alone is not proof of the installed payload's identity.

On success, netservice records a type-2 push event, cleans the completed download and calls `TaskManager.k(taskId)`. `TaskManager.l` (`0x3377b4`) checks disable status, parses `package/class`, checks autostart limits, and starts an explicit service or activity. Service components use `startForegroundService` on API 26+.

The launch counter is incremented before the start call when `openTimes` is nonzero. `openTimes=0` means unlimited in this branch. Task 601's recorded `openTimes=2` is consequently a configured launch limit, not evidence of two proven successful launches. Counters can also be cleared by a later task response.

For `isSystemApp=true`, the finish handler instead calls a file-copy helper against the supplied install path. The helper requires both source and destination to exist and writes with `RandomAccessFile`; it does not itself create a missing destination, remount `/system`, or invoke `SystemMix`. This branch is not proof of a successful system-partition rewrite.

## 7. Self-update and root-helper boundary

`AppUpgradeDownloader` handles the client update. It performs an optional case-insensitive MD5 check; after three mismatches it records a give-up marker for that digest/version and suppresses the same digest for roughly one day. Empty MD5 skips verification. Pending metadata is saved so startup can resume an unfinished update.

Its ordinary path uses the same callback-aware installer. A separate branch is selected only when `ro.product.firmware` contains `IK316`:

```text
AppUpgradeDownloader.lambda$downloadApp$0
  -> PackageInstallUtil.silentInstallWithCallBack (0x3547cc)
  -> Runtime.exec("su")
  -> write a pm-install command, waitFor(), collect stdout/stderr
  -> success when exit code is zero and output lacks "failure"
```

That helper executes a root shell, and the native `su` binary is present. The examined unit reports `H713M-android11-v1.0`, which does not select the `IK316` branch. An actual root-shell install by netservice on this unit was not observed or triggered. The helper is an implemented conditional capability, not evidence of its historical use here.

## 8. Remote blacklist and monitoring

The blacklist is more than a label: `BlackListInteractor.b` (`0x33dad0`, reconstructed in simple mode after structured-decompilation failure) compares remote version lists with installed packages. Type 0 invokes the uninstall helper and checks after five seconds. Nonzero types are stored as `DBlackAppgBean` policies. An empty/missing version list is represented by `-1`, for which the helper treats package presence as a match.

The monitor thread (`com.android.monitor.b.b`) compares running packages with those policies, calls `killBackgroundProcesses`, and reflectively invokes `ActivityManager.forceStopPackage`. Results can become blacklist-frequency records. This establishes remote-configured package-removal/process-control capability, without identifying any particular antivirus or competitor app as an actual target on this projector.

Usage tracking records package/version, opening counts and duration; device-runtime records are also maintained. Historical app-usage rows are serialized into type-3 event records; blacklist kill records become type 7, and device runtime has type 8. The existence of a record submission path is not proof that every recorded value reached a server.

## 9. Log collection and upload

`LogUploadManager` scans the app's log directory for qualifying `app_*.log`/`.txt` files and `crash.log`, excluding current-day app logs from the historical candidate set. It packages archives with a manifest/sidecar and fingerprints, uploads multipart field `files` to `/app-api/task/device/log/upload`, and supplies MAC/CPU parameters. Successful handling marks fingerprints and removes uploaded source files/archives.

`LogUploadScheduler` uses network-connected WorkManager jobs, startup bursts and a four-hour periodic fallback. These are code paths for the app's diagnostic material, not evidence of unrestricted collection of every file on the projector. Upload success and historical contents were not independently reconstructed from network captures.

Its configured startup increments are 0, 10, 20, 40 and 80 minutes, accumulated by the loop into delays of 0, 10, 30, 70 and 150 minutes when the immediate job is included. Those are configured job delays, not observed upload timestamps.

## 10. The bidirectional link with com.android.domain

This is the strongest additional connection beyond the install database:

```text
netservice DBDomain.CONTENT_URI
  = content://com.android.providerdomain.provider/domains
  -> domain's exported DomainProvider
```

`netservice`'s `com.android.nebulasdk.c.getDefaultDomain()` queries that URI and selects the first nonempty `type=0` row, splitting `domainNames` on commas. It adds `business.` or `heartbeat.` when selecting names. `domain`'s `DomainPool` reads `type=1` from the same table. Its server-response handler can replace all rows with a supplied `domains` list.

Consequently the supported code relationship is bidirectional: netservice can install/start domain, and domain's provider can supply netservice's future server candidates. Provider initialization itself can start domain's service. This is stronger than matching the word “Nebula” or a package naming pattern. Which replacement rows were delivered historically remains unproven; the captured provider database was empty.

## 11. Native connection: what is and is not linked

Scanning method references in both netservice DEX files found no direct `SystemMix` method reference and no `System.loadLibrary` call in its `com.android` classes. The confirmed normal installation path does not require `systemmixservice`. Reflection, future downloaded code, or other packages are not excluded by a direct-reference scan.

The native components are analysed separately in [native-system-services.md](native-system-services.md). They share the firmware and run alongside this chain. The code does not justify drawing an executed `netservice -> SystemMix -> root write` arrow.

## Reproduction and limitations

Use the retained APK hash above, single-class JADX extraction, and the offline [sample verifier](tools/verify_samples.py). Preserve original method descriptors: short names can be renamed by a decompiler. All offsets cited here are DEX code-item offsets within `classes.dex`, not offsets in the APK ZIP file.

This pass establishes request/task/install/launch logic and native interfaces. It does not reproduce a live server task, install or remove a payload, modify provider rows, perform a Binder write, prove residential proxy operation, or attribute the infrastructure to the retailer/manufacturer. The system-base APK was hashed and its version checked; the detailed code flow above belongs to active version 3.6.0.
