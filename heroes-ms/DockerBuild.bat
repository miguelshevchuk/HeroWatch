@echo off
setlocal EnableExtensions EnableDelayedExpansion

REM --- Validar parámetro (tag/version) ---
if "%~1"=="" (
  echo Uso: %~nx0 ^<tag^>
  echo Ejemplo: %~nx0 1.0.0
  exit /b 1
)

set "TAG=%~1"

echo.
echo ==============================
echo Preparando libs locales...
echo ==============================

powershell -ExecutionPolicy Bypass -File ".\scripts\sync-libs.ps1"
if errorlevel 1 (
  echo ERROR: fallo sync-libs.ps1
  exit /b 1
)

echo.
echo ==============================
echo Construyendo imagen Docker...
echo ==============================

docker build -t heroWatch/heroes-ms:%TAG% .
if errorlevel 1 (
  echo ERROR: fallo docker build
  exit /b 1
)

echo.
echo ==============================
echo OK - Imagen creada:
echo heroWatch:auth-server:%TAG%
echo ==============================

endlocal
