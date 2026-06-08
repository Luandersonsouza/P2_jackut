$ErrorActionPreference = "Stop"

$tests = @(
    "us1_1.txt",
    "us1_2.txt",
    "us2_1.txt",
    "us2_2.txt",
    "us3_1.txt",
    "us3_2.txt",
    "us4_1.txt",
    "us4_2.txt"
)

Write-Host "Compilando Jackut..."
javac -encoding UTF-8 -cp "lib\easyaccept.jar" -d bin (Get-ChildItem -Recurse Jackut -Filter *.java).FullName

foreach ($test in $tests) {
    $path = Join-Path "tests" $test
    $output = & java -cp "bin;lib\easyaccept.jar" easyaccept.EasyAccept Jackut.Facade $path 2>&1
    $exitCode = $LASTEXITCODE
    $text = $output -join [Environment]::NewLine

    if ($exitCode -ne 0 -or $text -match " errors?:") {
        Write-Host "FALHOU $test" -ForegroundColor Red
        Write-Host $text
        exit 1
    }

    if ($text -match "Test file .*: ([0-9]+) tests OK") {
        Write-Host "OK $test - $($Matches[1]) testes" -ForegroundColor Green
    } else {
        Write-Host "OK $test" -ForegroundColor Green
    }
}

Write-Host "Todos os testes passaram." -ForegroundColor Green
