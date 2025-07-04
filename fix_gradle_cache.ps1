# Script per risolvere problemi di cache Gradle
Write-Host "Risoluzione problemi di cache Gradle in corso..." -ForegroundColor Green

# Fermiamo il daemon di Gradle
Write-Host "Fermando il daemon di Gradle..." -ForegroundColor Yellow
& .\gradlew.bat --stop

# Attendi che i processi Gradle terminino
Start-Sleep -Seconds 2

# Pulizia della cache locale del progetto
Write-Host "Pulizia cache del progetto..." -ForegroundColor Yellow
& .\gradlew.bat clean

# Rimozione della directory .gradle nel progetto
if (Test-Path -Path ".\.gradle") {
    Write-Host "Rimozione directory .gradle del progetto..." -ForegroundColor Yellow
    Remove-Item -Path ".\.gradle" -Recurse -Force -ErrorAction SilentlyContinue
}

# Pulizia della cache build
Write-Host "Pulizia della cache di build..." -ForegroundColor Yellow
& .\gradlew.bat cleanBuildCache

# Rimuovi la directory transforms-4 nella cache globale se esiste
$transformsPath = "C:\Users\tesfa\.gradle\caches\transforms-4"
if (Test-Path -Path $transformsPath) {
    Write-Host "Rimozione della cache transforms-4 corrotta..." -ForegroundColor Yellow
    Remove-Item -Path $transformsPath -Recurse -Force -ErrorAction SilentlyContinue
}

# Invalidazione delle cache di Gradle
Write-Host "Invalidazione delle cache di Gradle..." -ForegroundColor Yellow
& .\gradlew.bat --refresh-dependencies

Write-Host "Rebuild del progetto..." -ForegroundColor Green
& .\gradlew.bat build

Write-Host "Risoluzione problemi di cache completata!" -ForegroundColor Green
Write-Host "Se il problema persiste, considera di rimuovere manualmente l'intera directory C:\Users\tesfa\.gradle\caches\" -ForegroundColor Yellow
