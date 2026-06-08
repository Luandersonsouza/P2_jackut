# P2_jackut

Projeto Jackut em Java, com estrutura orientada a objetos e testes de
aceitacao via EasyAccept.

## Estrutura

- `Jackut/Facade.java`: fachada publica usada pelo EasyAccept.
- `Jackut/Main.java`: executa todos os testes do projeto.
- `Jackut/models`: entidades do dominio.
- `Jackut/services`: regras de negocio, banco em memoria, sessoes e validacoes.
- `Jackut/exceptions`: excecoes de negocio.
- `lib/easyaccept.jar`: biblioteca EasyAccept incluida no proprio projeto.
- `tests`: scripts de teste de aceitacao.
- `run-tests.ps1`: executa todos os testes no Windows PowerShell.
- `run-tests.sh`: executa todos os testes no Linux/macOS.

## Compilar no Windows

```powershell
javac -encoding UTF-8 -cp "lib\easyaccept.jar" -d bin (Get-ChildItem -Recurse Jackut -Filter *.java).FullName
```

## Compilar no Linux/macOS

```bash
mkdir -p bin
javac -encoding UTF-8 -cp "lib/easyaccept.jar" -d bin $(find Jackut -name "*.java")
```

## Executar o Main no Windows

```powershell
java -cp "bin;lib\easyaccept.jar" Jackut.Main
```

## Executar o Main no Linux/macOS

```bash
java -cp "bin:lib/easyaccept.jar" Jackut.Main
```

## Rodar um teste do EasyAccept no Windows

```powershell
java -cp "bin;lib\easyaccept.jar" easyaccept.EasyAccept Jackut.Facade tests\us1_1.txt
```

## Rodar um teste do EasyAccept no Linux/macOS

```bash
java -cp "bin:lib/easyaccept.jar" easyaccept.EasyAccept Jackut.Facade tests/us1_1.txt
```

## Rodar todos os testes no Windows

```powershell
powershell -ExecutionPolicy Bypass -File .\run-tests.ps1
```

## Rodar todos os testes no Linux/macOS

```bash
bash run-tests.sh
```

No Windows, o classpath usa `;`. No Linux/macOS, usa `:`.
