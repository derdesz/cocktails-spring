#!/usr/bin/env bash

set -euo pipefail

TARGET_DIR=$1

mkdir -p "$TARGET_DIR"

for l in {a..z}; do
  echo "Downloading cocktails starting with letter '$l'"
  curl -o "$TARGET_DIR/$l.json" "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=$l"
done
