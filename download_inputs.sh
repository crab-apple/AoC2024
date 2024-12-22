#!/bin/bash
set -e

FETCH_UNTIL_DAY=22
URL="https://adventofcode.com/2024/day/%d/input"
COOKIE_VAR="AOC_FETCH_INPUT_COOKIE"

if [ -z "${!COOKIE_VAR}" ]; then
  echo "Error: cookie not set"
  exit 1
fi

COOKIE="${!COOKIE_VAR}"

for ((i=1; i<=FETCH_UNTIL_DAY; i++)); do
  DIR=$(printf "resources/day%02d" "$i")
  FILE="$DIR/input"

  if [ ! -d "$DIR" ]; then
    mkdir "$DIR"
  fi

  # Fetch URL contents and save to file
  curl -s -H "Cookie: $COOKIE" "$(printf $URL "$i")" -o "$FILE"

  if [ $? -eq 0 ]; then
    echo "Fetched and saved input for day $i."
  else
    echo "Failed to fetch input for day $i."
  fi
done
