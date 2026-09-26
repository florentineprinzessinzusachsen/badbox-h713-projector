#!/usr/bin/env bash
# Usage: ./check-ota.sh
set -u

URL="http://wjtysj.ishanghd.com/hx_kt.php"
PRODUCTID="74865231"
MODELS=("AT-M269" "ADT-3" "H713_ATM_720P")
VERSIONS=("eng.work3.20251208.154308" "eng.work3.20250101.000000")
TYPES=(1 2)
OUT="ota-responses.txt"

log() { echo "[$(date +%H:%M:%S)] $*"; }

pretty() {
  if command -v python3 >/dev/null 2>&1; then
    python3 -c 'import sys,json
try:
  d=json.load(sys.stdin); print(json.dumps(d,ensure_ascii=False,indent=2))
except Exception:
  print(sys.stdin.read())' 2>/dev/null
  else
    cat
  fi
}

log "Update server: $URL"
log "productid=$PRODUCTID"
log "Testing ${#MODELS[@]} models x ${#VERSIONS[@]} versions x ${#TYPES[@]} update types"
log "update_type 1 = normal update, 2 = forced update"
: > "$OUT"
FOUND=0

for M in "${MODELS[@]}"; do
  for V in "${VERSIONS[@]}"; do
    for T in "${TYPES[@]}"; do
      echo
      log "Request: device_mode=$M  devices_version=$V  update_type=$T"
      R=$(curl -s -m 15 -G "$URL" \
        --data-urlencode "act=project" --data-urlencode "do=getPackageInfo" \
        --data-urlencode "productid=$PRODUCTID" --data-urlencode "device_mode=$M" \
        --data-urlencode "devices_version=$V" --data-urlencode "update_type=$T")
      RC=$?
      if [ $RC -ne 0 ]; then
        log "  No response (curl exit code $RC: timeout or network blocked)."
        continue
      fi
      echo "=== $M | $V | type $T ===" >> "$OUT"; echo "$R" >> "$OUT"
      case "$R" in
        *'"code":200'*'"data":[]'*) log "  Server OK, no package for this combination." ;;
        *'"code":1001'*)            log "  Server says: request parameter missing." ;;
        *'"code":200'*)
          FOUND=1
          log "  >>> PACKAGE FOUND <<<"
          echo "$R" | pretty | sed 's/^/    /'
          echo "$R" | grep -oE 'https?://[^"]+' | sed 's/\\//g' | sort -u | while read -r u; do log "  Download link: $u"; done
          ;;
        *) log "  Unexpected response:"; echo "$R" | pretty | sed 's/^/    /' ;;
      esac
    done
  done
done

echo
if [ $FOUND -eq 1 ]; then
  log "RESULT: at least one package"
else
  log "RESULT: no package for any combination."
fi
log "All raw responses saved to $OUT"
