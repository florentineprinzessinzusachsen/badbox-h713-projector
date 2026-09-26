#!/usr/bin/env bash
# Usage: ./resume-dump.sh [ip:port | usb] [image-file]
set -u

TARGET="${1:-10.56.215.10:5868}"
IMG="${2:-mmcblk0.img}"
BS=4194304
CH=64
MAX_TRIES=5

fsize() { stat -f%z "$1" 2>/dev/null || stat -c%s "$1"; }
mb()    { echo $(( $1 / 1048576 )); }
log()   { echo "[$(date +%H:%M:%S)] $*"; }

if [ "$TARGET" = "usb" ]; then
  ADB="adb -d"
  reconnect() { log "  Waiting for USB device..."; adb wait-for-usb-device; }
else
  ADB="adb -s $TARGET"
  reconnect() {
    log "  Reconnecting to $TARGET ..."
    adb disconnect "$TARGET" >/dev/null 2>&1; sleep 5
    adb connect "$TARGET" | sed 's/^/  /'
  }
  adb connect "$TARGET" >/dev/null 2>&1
fi

log "Target: $TARGET | Image: $IMG"
$ADB shell true >/dev/null 2>&1 || { log "ERROR: device not reachable."; exit 1; }
[ -f "$IMG" ] || { log "No existing image found, starting from zero."; : > "$IMG"; }

TOTAL=$($ADB shell blockdev --getsize64 /dev/block/mmcblk0 | tr -d '\r')
TB=$(( TOTAL / BS ))
log "eMMC size: $(mb $TOTAL) MB ($TB blocks of 4 MiB)"

CUR=$(fsize "$IMG")
N=$(( CUR / BS ))
if [ $(( N * BS )) -ne "$CUR" ]; then
  log "Image ends mid-block ($CUR bytes). Trimming to $(( N * BS )) bytes so the append lines up exactly."
  dd if=/dev/null of="$IMG" bs=$BS seek=$N 2>/dev/null
fi
log "Already have $(mb $((N*BS))) MB. Missing: $(mb $((TOTAL - N*BS))) MB."

if $ADB shell 'command -v gzip' >/dev/null 2>&1; then
  GZ=1; log "gzip found on device: transferring compressed (fast for empty userdata)."
else
  GZ=0; log "No gzip on device: transferring uncompressed."
fi

pull_chunk() {
  local cmd="dd if=/dev/block/mmcblk0 bs=$BS skip=$1 count=$2 2>/dev/null"
  if [ $GZ -eq 1 ]; then
    $ADB exec-out "$cmd | gzip -1" | gunzip > chunk.bin 2>/dev/null
  else
    $ADB exec-out "$cmd" > chunk.bin
  fi
}

START_N=$N; T0=$(date +%s)
while [ $N -lt $TB ]; do
  C=$(( TB - N < CH ? TB - N : CH ))
  WANT=$(( C * BS ))
  log "Chunk: blocks $N-$((N+C-1)) (offset $(mb $((N*BS))) MB, $(mb $WANT) MB)"
  OK=0
  for try in $(seq 1 $MAX_TRIES); do
    pull_chunk $N $C
    GOT=$(fsize chunk.bin)
    if [ "$GOT" -eq "$WANT" ]; then OK=1; break; fi
    log "  Attempt $try/$MAX_TRIES: got $(mb $GOT) of $(mb $WANT) MB - transfer broke off."
    reconnect
  done
  if [ $OK -ne 1 ]; then
    log "ABORT at block $N. Image is intact up to $(mb $((N*BS))) MB. Run the script again to continue."
    rm -f chunk.bin; exit 1
  fi
  cat chunk.bin >> "$IMG"; N=$(( N + C ))
  EL=$(( $(date +%s) - T0 )); [ $EL -lt 1 ] && EL=1
  DONE=$(( (N - START_N) * BS )); RATE=$(( DONE / EL ))
  ETA=$(( (TOTAL - N*BS) / (RATE > 0 ? RATE : 1) ))
  log "  OK. Progress: $(mb $((N*BS))) / $(mb $TOTAL) MB ($(( N * 100 / TB ))%), $(( RATE / 1024 )) KB/s, ~$(( ETA / 60 )) min left"
done

REST=$(( TOTAL - TB * BS ))
if [ $REST -gt 0 ] && [ "$(fsize "$IMG")" -lt "$TOTAL" ]; then
  log "Fetching the final $REST bytes (not a whole 4 MiB block)."
  $ADB exec-out "dd if=/dev/block/mmcblk0 bs=512 skip=$(( TB * BS / 512 )) count=$(( REST / 512 )) 2>/dev/null" >> "$IMG"
fi
rm -f chunk.bin

FINAL=$(fsize "$IMG")
if [ "$FINAL" -eq "$TOTAL" ]; then
  log "DONE. Image $FINAL bytes = eMMC $TOTAL bytes. Now run the partition verification."
else
  log "WARNING: image $FINAL bytes, eMMC $TOTAL bytes. Run the script again."
  exit 1
fi
