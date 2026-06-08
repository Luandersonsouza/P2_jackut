#!/usr/bin/env bash
set -euo pipefail

tests=(
  "us1_1.txt"
  "us1_2.txt"
  "us2_1.txt"
  "us2_2.txt"
  "us3_1.txt"
  "us3_2.txt"
  "us4_1.txt"
  "us4_2.txt"
)

echo "Compilando Jackut..."
mkdir -p bin
javac -encoding UTF-8 -cp "lib/easyaccept.jar" -d bin $(find Jackut -name "*.java")

for test in "${tests[@]}"; do
  output=$(java -cp "bin:lib/easyaccept.jar" easyaccept.EasyAccept Jackut.Facade "tests/$test" 2>&1)

  if echo "$output" | grep -Eq " errors?:"; then
    echo "FALHOU $test"
    echo "$output"
    exit 1
  fi

  count=$(echo "$output" | sed -n 's/.*: \([0-9][0-9]*\) tests OK.*/\1/p')
  if [ -n "$count" ]; then
    echo "OK $test - $count testes"
  else
    echo "OK $test"
  fi
done

echo "Todos os testes passaram."
