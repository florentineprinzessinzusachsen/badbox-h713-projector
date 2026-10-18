# SPR2226 native system services and privilege boundaries

## Collection and byte identity

The following files were pulled again from the USB-connected SPR2226, hashed locally, and compared with device-side `sha256sum`. The first four also match the original AT-M269 repository samples byte-for-byte by SHA-256. This is a specific shared-binary finding; it does not make the entire firmware or APK inventory identical.

| File | Bytes | SHA-256 |
| --- | ---: | --- |
| `/system/bin/systemmixservice` | 4,464 | `08647d3268388e6f9a06233950f520a0e527f0bfb6079550918b94b94548d10d` |
| `/system/lib/libsystemmixservice.so` | 32,824 | `91a204ab267f3e498c47670587adf00cf653609ef45054e6a4065e829adec759` |
| `/system/lib/libsystemmix_jni.so` | 8,704 | `deec3320c4fc7ad86b64339413503442e376e1303e40d02788d9644ee36fb9c7` |
| `/system/bin/qw` | 388,324 | `022b638767ddf981ba09b8092460ea4cb837a7cdf713949ecbea7c0a0ced5759` |
| `/system/xbin/su` | 6,248 | `398c9cb495d67e5721887f33b32b0dac31511ea1966c65347d0440474848b4e3` |

ELF parsing used pyelftools/LIEF and ARM/Thumb disassembly used Capstone. LIEF was necessary for Android-packed dynamic relocations; reading the unrelocated zero entries in a vtable would produce incorrect conclusions. PLT entry sizes were checked before annotating imported calls. ELF virtual addresses below are relative to the loaded image, not absolute process addresses.

No sample library was loaded into the Windows process. Device checks were limited to file reads, hashes, process/service/socket inventory, and existing package state. No Binder write, remount, deletion, logo update, root-shell installation or unknown payload execution was tested.

## 1. Startup and observed runtime

`/system/etc/init/systemmix.rc` defines:

```text
service systemmix /system/bin/systemmixservice
    class main
    user root
    group root audio camera graphics inet net_bt net_bt_admin net_raw everybody
    seclabel u:r:systemmix:s0
    ioprio rt 4
    oneshot
```

The process was present as UID root, blocked in a Binder wait. `service list` reports `softwinner.systemmix: [com.softwinner.ISystemMixService]`; `service check softwinner.systemmix` returned found. `oneshot` means init does not automatically respawn it on ordinary exit; it does not mean the process handles only one request.

The service library publishes a Binder interface, rather than a network server in its reviewed implementation. Its dynamic imports contain no `socket`, `bind`, `listen`, `accept` or `connect`. This narrowly describes the inspected binary, not every process on the device.

## 2. Binder dispatcher reconstructed from code

`android::BnSystemMixService::onTransact` begins at Thumb address `0x4d90`, with size 752 bytes. It subtracts one from the transaction number, bounds-checks for 13 cases, and branches through a `TBH` table at `0x4da6`. Each case uses the Binder interface-token check and reads arguments from the parcel before calling a vtable method.

The following map was reconstructed from the jump-table targets, indirect-call vtable offsets, and symbol relocations. **Property-set is not transaction 2**; it has a special out-of-table transaction number.

| Transaction | Case address | Operation | Implementation address |
| ---: | --- | --- | --- |
| 1 | `0x4dc0` | get property | `0x61c0` |
| 2 | `0x4e52` | read file bytes | `0x6204` |
| 3 | `0x4eb4` | write file bytes | `0x62b8` |
| 4 | `0x4f10` | mount | `0x6390` |
| 5 | `0x4f58` | unmount | `0x64e0` |
| 6 | `0x4f72` | delete file | `0x65cc` |
| 7 | `0x4f8c` | can-write mode check | `0x662c` |
| 8 | `0x4fa6` | rename (`renewName`) | `0x66ac` |
| 9 | `0x4fce` | mkdir | `0x66e4` |
| 10 | `0x4fe6` | can-read mode check | `0x666c` |
| 11 | `0x4ffe` | set boot animation | `0x6bf8` |
| 12 | `0x5016` | set boot logo | `0x6720` |
| 13 | `0x502e` | export boot logo | `0x6a8c` |
| `0x00ffffff` | `0x4e2e` | set property | `0x6190` |

The primary vtable function slots begin at `0x8a68`: slot `+0x10` resolves to getProperty and `+0x14` to setProperty. The special path compares against a value constructed by `mvn r0, #0xff000000`, producing `0x00ffffff`, then calls slot `+0x14`.

This is a custom C++ parcel format. Paths/keys are read with `Parcel::readCString`, and byte buffers with explicit sizes and raw parcel reads. Java/AIDL string-marshalling assumptions must not be substituted when interpreting it.

## 3. Operation semantics and access controls

The service's reviewed dispatcher calls `Parcel::checkInterface`, which validates the interface token. There is no caller-UID/PID allowlist, package-signature check or permission-service call in the examined dispatcher and concrete operation bodies. Imported symbols likewise contain no such caller-check routine. A correct interface token is not caller authentication.

That makes this a significant privileged local interface. However, no cross-UID client test was performed, so this report does not present “any application successfully wrote as root” as an observed exploit. Binder service-manager policy, device-specific access and caller context remain relevant; the unit's observed SELinux state is permissive.

The implementation bodies establish:

- `setProperty` forwards caller-provided key/value to `property_set`. `getProperty` calls `property_get` with a null default. Platform property rules can still make a request fail.
- `getFileData` checks a non-null path and service-process `access(path, R_OK)`, then reads with stdio in chunks up to `0x800` bytes. It has no reviewed path allowlist.
- `putFileData` similarly receives path, bytes and length; it performs service-side access/open/write operations. The path/length come from the transaction. These are accesses under the root service's credentials, not the caller's identity.
- `mountDev` forwards source, target, filesystem, flags and options to `mount`; `umountDev` invokes `umount2`. It also toggles `mediasw.stopscaner` around the media-scan behavior.
- `deleteFile`, `renewName` and `makeDir` wrap removal, rename and directory creation. The can-read/can-write functions inspect file mode bits; they are not a per-caller ACL decision.

The read/write transaction paths allocate buffers from supplied lengths. No application-level maximum length or per-path authorization was found in the reviewed dispatcher. No malformed-size transaction was sent, and a memory-corruption or denial-of-service exploit is not claimed.

## 4. Boot assets and filesystem effects

`setBootLogo` opens a supplied source file and writes `/Reserve0/advert.bmp`, using `open`, `read`, checked `write` and `fsync`. `updateReserve0Crc` reads logo material and writes `/Reserve0/advert.crc`; this is boot-asset bookkeeping, not a firmware publisher signature check. `exportBootLogo` reads the saved bitmap and writes to the supplied output path.

`setBootAnimation` copies a supplied source into `/data/local/bootanimation.zip`, fsyncs and adjusts permissions. These are implemented root file operations. No invocation by netservice or change to the projector's assets was demonstrated in this collection.

The names of these methods do not establish an HTTP updater, proxy module or arbitrary firmware flashing implementation. Their security significance is the privileged local file/mount/property surface.

## 5. JNI registration and Java boundary

`JNI_OnLoad` is at `0x1b1c` in `libsystemmix_jni.so`. The registration data names `com/softwinner/SystemMix`, and the method table at `0x46b4` contains 12 entries:

| Registered method | Native address | Java signature/role |
| --- | --- | --- |
| `nativeInit` | `0x1bb4` | `()V`; obtains `softwinner.systemmix`. |
| `nativeSetProperty` | `0x1cc0` | two strings -> int |
| `nativeGetProperty` | `0x1d58` | string -> string |
| `nativeGetFileData` | `0x1e04` | byte array, int, path -> int |
| `nativePutFileData` | `0x1eec` | byte array, int, path -> int |
| `nativeMount` | `0x1fd4` | source, target, fs, flags, options -> int |
| `nativeUmount` | `0x20c0` | path -> int |
| `nativeDelete` | `0x2128` | path -> int |
| `nativeCanWrite` | `0x2190` | path -> int |
| `nativeRename` | `0x21f8` | two paths -> int |
| `nativeMkdir` | `0x2290` | path -> int |
| `nativeCanRead` | `0x22f8` | path -> int |

The wrappers acquire Java strings/arrays, call the cached `ISystemMixService` interface and release converted resources. Registration of these names does not prove a particular APK loads the JNI library. The three boot-asset methods are present in Binder's interface but absent from this 12-entry JNI table.

Targeted searches did not locate `com.softwinner.SystemMix` in the pulled `framework.jar` or `com.softwinner.tv.jar`. Its location or presence in another vendor jar/APK was not established. The native registration name is documented without inventing a Java caller.

## 6. qw daemon versus the separate su binary

`qw.rc` defines `/system/bin/qw --daemon`, `class core`, `user root`, `group root`. Runtime inventory shows a root `qw` process and the Unix socket `/dev/com.koushikdutta.superuser.daemon/server`. The socket's captured mode is `srwxrwxrwx`, owned by root; filesystem permissions alone do not establish that its protocol authorizes every connecting caller.

The static binary includes ClockworkMod/Superuser-style paths, a `su.sqlite` policy database path, daemon/socket strings and root/system/radio policy messages. These, runtime socket presence and exact hash equivalence to the original sample support identifying it as a local superuser daemon. The full stripped daemon and bundled static C library were not all reconstructed into source. Socket authorization for a new unprivileged application was not exercised.

There is also a **different** executable at `/system/xbin/su`, size 6,248, with captured mode `-rwsr-sr-x` (setuid/setgid). Its implementation is a small UID/GID-switching command tool, not the same 388,324-byte daemon. In its Thumb main routine (`0x1a30`), it parses a requested identity, calls `setgroups` when applicable, `setgid` at `0x1ae8`, `setuid` at `0x1b02`, prepares the environment, then `execvp` at `0x1bac`. With no command supplied, it chooses `/system/bin/sh`.

No password prompt or caller-UID allowlist was found in that small reviewed main path; its imports include UID-changing calls but no `getuid/geteuid` caller check. The earlier shell test `su 0 id -> uid=0(root)` confirms root access for the ADB shell. It does not by itself test every Android app context or prove that this small `su` binary delegates to `qw`; no such delegation was found in the reviewed small binary.

## 7. Boot scripts and Google components

`appsdisable` and `gmsopt` are root init services triggered after `sys.boot_completed=1`. Both wait four seconds and inspect the shared `global start_disable` flag. When the numeric test permits the body, they enumerate `BOOT_COMPLETED` receiver **components** containing `com.google.android` and pass those components to `pm disable`. `gmsopt` removes permissioncontroller matches first. Both write the flag to 1 afterwards.

The distinction between components and packages matters. `pm list packages -d` being empty does not mean that no receiver/service/activity is disabled. The recheck found `start_disable=1` and disabled GMS components in `dumpsys package`, including DroidGuard services and other components. Their origin is not established: GMS can also change component states itself, and these two scripts select boot receivers rather than every component shown by dumpsys. No pre-change snapshot or execution trace ties those specific disabled entries to these scripts.

Thus the proven finding is firmware-resident code capable of disabling selected Google boot receivers, with a once-style shared guard. It is not proof that every Google package is disabled, that both scripts execute their bodies on every boot, or that every disabled GMS entry was caused by the firmware.

## 8. Relationship to the APK chain

The code supports the following separate links:

```text
init -> root systemmixservice -> Binder file/property/mount operations
init -> root qw -> local Superuser-style socket
setuid /system/xbin/su -> requested UID/GID -> command execution

netservice -> Android PackageInstaller -> downloaded APK
netservice [IK316-only self-update branch] -> su -> pm install
netservice <-> domain provider: shared persisted server candidates
```

Both netservice DEX files were scanned for direct `SystemMix` references; none were found. Its `com.android` code has a root-shell install helper but no observed `System.loadLibrary` call. The native services are confirmed firmware privilege surfaces alongside the demonstrated downloader chain. They are not a demonstrated required step in task 601's install on this unit.

## Reproducibility and remaining limits

The [offline verifier](tools/verify_samples.py) pins sample hashes, decodes the Binder table, resolves Android-packed vtable relocations, and reads JNI registration data. The reports preserve virtual/code offsets so a reviewer can repeat the disassembly without executing a sample. Runtime checks can be repeated with `service list`, `service check`, `ps`, filesystem hashes and `/proc/net/unix` inspection.

No residential-proxy forwarding loop was established in these native components. Common binary hashes establish shared code files, not the identity of an attacker or supplier. The unresolved items are historical server responses, actual cross-UID Binder/socket authorization, the JNI library's Java host, and comprehensive recovery of the stripped `qw` policy implementation.
