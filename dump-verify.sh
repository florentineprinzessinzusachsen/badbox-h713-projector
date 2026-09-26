#!/usr/bin/env bash
# Usage: ./verify-dump.sh [ip:port | usb] [image-file] [-y]
set -u

TARGET="${1:-10.56.215.10:5868}"
IMG="${2:-mmcblk0.img}"
AUTO="${3:-}"
BS=4194304
MAX_TRIES=5
LIVE="userdata metadata misc"

fsize() { stat -f%z "$1" 2>/dev/null || stat -c%s "$1"; }
mb()    { echo $(( $1 / 1048576 )); }
log()   { echo "[$(date +%H:%M:%S)] $*"; }
sha()   { shasum -a 256 | cut -d' ' -f1; }

if [ "$TARGET" = "usb" ]; then
  ADB="adb -d"
  reconnect() { log "    Waiting for USB device..."; adb wait-for-usb-device; }
else
  ADB="adb -s $TARGET"
  reconnect() {
    log "    Reconnecting to $TARGET ..."
    adb disconnect "$TARGET" >/dev/null 2>&1; sleep 5
    adb connect "$TARGET" >/dev/null 2>&1
  }
  adb connect "$TARGET" >/dev/null 2>&1
fi
dsh() { $ADB shell "$@" | tr -d '\r'; }

log "Target: $TARGET | Image: $IMG"
dsh true >/dev/null 2>&1 || { log "ERROR: device not reachable."; exit 1; }
[ -f "$IMG" ] || { log "ERROR: $IMG not found."; exit 1; }

log "=== 1. Size check ==="
TOTAL=$(dsh blockdev --getsize64 /dev/block/mmcblk0)
CUR=$(fsize "$IMG")
log "eMMC: $TOTAL bytes ($(mb $TOTAL) MB) | Image: $CUR bytes ($(mb $CUR) MB)"
if [ "$CUR" -ne "$TOTAL" ]; then
  log "Size differs -> image is incomplete. Run ./resume-dump.sh first, then this script again."
  exit 1
fi
log "OK: sizes match."

log "=== 2. Partition table (GPT) ==="
P_SIG=$(tail -c +513 "$IMG" | head -c 8)
B_SIG=$(tail -c 512 "$IMG" | head -c 8)
[ "$P_SIG" = "EFI PART" ] && log "OK: primary GPT header present (sector 1)." || log "PROBLEM: primary GPT header missing."
[ "$B_SIG" = "EFI PART" ] && log "OK: backup GPT header present (last sector)." || log "PROBLEM: backup GPT header missing."
D_GPT=$($ADB exec-out "dd if=/dev/block/mmcblk0 bs=512 count=34 2>/dev/null" | sha)
L_GPT=$(head -c $((34*512)) "$IMG" | sha)
[ "$D_GPT" = "$L_GPT" ] && log "OK: first 34 sectors (MBR + GPT) identical to device." || log "PROBLEM: MBR/GPT area differs (will be fixed below if you confirm)."

log "=== 3. eMMC hardware boot areas ==="
for b in mmcblk0boot0 mmcblk0boot1; do
  if dsh "[ -b /dev/block/$b ] && echo y" | grep -q y; then
    D=$($ADB exec-out "cat /dev/block/$b" | sha)
    if [ -f "$b.img" ] && [ "$(sha < "$b.img")" = "$D" ]; then
      log "OK: $b.img present and identical."
    else
      log "$b exists on device, local copy missing or different -> dumping now."
      $ADB exec-out "cat /dev/block/$b" > "$b.img"
      [ "$(sha < "$b.img")" = "$D" ] && log "OK: $b.img dumped and verified." || log "PROBLEM: $b.img still differs, run again."
    fi
  else
    log "$b does not exist on this device (normal for many boards)."
  fi
done

log "=== 4. Partitions (device hash vs. image section) ==="
PARTS=$(dsh 'ls /dev/block/by-name/' | grep -vE '^mmcblk0$')
BAD=""
for n in $PARTS; do
  p=$(dsh readlink /dev/block/by-name/$n | xargs basename)
  s=$(dsh cat /sys/block/mmcblk0/$p/start)
  z=$(dsh cat /sys/block/mmcblk0/$p/size)
  if echo " $LIVE " | grep -q " $n "; then
    log "SKIP  $n ($p, $(mb $((z*512))) MB): changes while the system runs, a mismatch is normal."
    continue
  fi
  log "CHECK $n ($p, start sector $s, $(mb $((z*512))) MB) ..."
  d=$(dsh sha256sum /dev/block/by-name/$n | cut -d' ' -f1)
  l=$(tail -c +$((s*512+1)) "$IMG" | head -c $((z*512)) | sha)
  if [ "$d" = "$l" ]; then
    log "  OK"
  else
    log "  MISMATCH  device=${d:0:16}...  image=${l:0:16}..."
    BAD="$BAD $n:$p:$s:$z:$d"
  fi
done

GPT_BAD=0; [ "$D_GPT" != "$L_GPT" ] && GPT_BAD=1
if [ -z "$BAD" ] && [ $GPT_BAD -eq 0 ]; then
  log "=== RESULT: backup is complete and correct. ==="
  exit 0
fi

log "=== 5. Correction ==="
confirm() {
  [ "$AUTO" = "-y" ] && return 0
  printf "%s [y/N] " "$1"; read -r a; [ "$a" = "y" ] || [ "$a" = "Y" ]
}

if [ $GPT_BAD -eq 1 ] && confirm "Re-read MBR/GPT (34 sectors) from device and write it into the image?"; then
  $ADB exec-out "dd if=/dev/block/mmcblk0 bs=512 count=34 2>/dev/null" > fix.bin
  dd if=fix.bin of="$IMG" bs=512 seek=0 conv=notrunc 2>/dev/null
  [ "$(head -c $((34*512)) "$IMG" | sha)" = "$D_GPT" ] && log "  FIXED: MBR/GPT." || log "  STILL WRONG: MBR/GPT."
fi

pull_part() {
  local name=$1 sectors=$2 bytes=$(( $2 * 512 ))
  local blocks=$(( bytes / BS )) rest=$(( bytes % BS )) i=0 c
  : > fix.bin
  while [ $i -lt $blocks ]; do
    c=$(( blocks - i < 64 ? blocks - i : 64 ))
    local ok=0
    for try in $(seq 1 $MAX_TRIES); do
      $ADB exec-out "dd if=/dev/block/by-name/$name bs=$BS skip=$i count=$c 2>/dev/null" > part.bin
      [ "$(fsize part.bin)" -eq $(( c * BS )) ] && { ok=1; break; }
      log "    Attempt $try/$MAX_TRIES broke off."; reconnect
    done
    [ $ok -eq 1 ] || return 1
    cat part.bin >> fix.bin; i=$(( i + c ))
    log "    read $(mb $(( i * BS ))) / $(mb $bytes) MB"
  done
  if [ $rest -gt 0 ]; then
    $ADB exec-out "dd if=/dev/block/by-name/$name bs=512 skip=$(( blocks * BS / 512 )) count=$(( rest / 512 )) 2>/dev/null" >> fix.bin
  fi
  rm -f part.bin
  [ "$(fsize fix.bin)" -eq "$bytes" ]
}

for entry in $BAD; do
  IFS=: read -r n p s z d <<< "$entry"
  confirm "Re-read partition $n ($(mb $((z*512))) MB) from device and write it into the image?" || { log "  Skipped $n."; continue; }
  log "  Reading $n from device ..."
  if ! pull_part "$n" "$z"; then log "  ABORT $n: transfer failed repeatedly. Run the script again."; continue; fi
  f=$(sha < fix.bin)
  if [ "$f" != "$d" ]; then
    d2=$(dsh sha256sum /dev/block/by-name/$n | cut -d' ' -f1)
    if [ "$f" = "$d2" ]; then
      log "  NOTE: $n changed on the device during the check (live partition). Writing the fresh copy anyway."
    else
      log "  PROBLEM: freshly read copy of $n does not match the device either. Not writing. Try again."
      continue
    fi
  fi
  log "  Writing $n into image at sector $s ..."
  dd if=fix.bin of="$IMG" bs=512 seek=$s conv=notrunc 2>/dev/null
  l=$(tail -c +$((s*512+1)) "$IMG" | head -c $((z*512)) | sha)
  [ "$l" = "$f" ] && log "  FIXED: $n." || log "  STILL WRONG: $n."
done
rm -f fix.bin part.bin

log "=== Done. Run this script once more; every line should now read OK. ==="
