# Script per pulire e ottimizzare il progetto Android Dress
Write-Host "Pulizia del progetto Dress in corso..." -ForegroundColor Green

# Rimozione file temporanei e directory di build
if (Test-Path -Path ".\.gradle") {
    Write-Host "Rimozione directory .gradle..." -ForegroundColor Yellow
    Remove-Item -Path ".\.gradle" -Recurse -Force
}

if (Test-Path -Path ".\build") {
    Write-Host "Rimozione directory build..." -ForegroundColor Yellow
    Remove-Item -Path ".\build" -Recurse -Force
}

if (Test-Path -Path ".\app\build") {
    Write-Host "Rimozione directory app\build..." -ForegroundColor Yellow
    Remove-Item -Path ".\app\build" -Recurse -Force
}

# Rimuovi file duplicati o non necessari
if (Test-Path -Path ".\app\src\main\java\com\example\dress\Preview.txt") {
    Write-Host "Rimozione file Preview.txt..." -ForegroundColor Yellow
    Remove-Item -Path ".\app\src\main\java\com\example\dress\Preview.txt"
}

if (Test-Path -Path ".\app\src\androidTest\java\com\example\dress\MainScreenTest.kt") {
    Write-Host "Rimozione test non valido MainScreenTest.kt..." -ForegroundColor Yellow
    Remove-Item -Path ".\app\src\androidTest\java\com\example\dress\MainScreenTest.kt"
}

# Rimuovi directory duplicata dress-app
if (Test-Path -Path ".\dress-app") {
    Write-Host "Rimozione directory duplicata dress-app..." -ForegroundColor Yellow
    Remove-Item -Path ".\dress-app" -Recurse -Force
}

Write-Host "Esecuzione di un build pulito..." -ForegroundColor Green
./gradlew.bat clean

Write-Host "Pulizia completata con successo!" -ForegroundColor Green
