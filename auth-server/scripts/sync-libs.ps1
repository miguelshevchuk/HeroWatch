param(
  [string]$OutputDir = "libs",
  [string]$IncludeGroupIds = "ar.com.gha",   # cambiá esto a tu prefijo real
  [string]$Scope = "runtime"
)

$ErrorActionPreference = "Stop"

# Limpia / crea carpeta
if (Test-Path $OutputDir) { Remove-Item -Recurse -Force $OutputDir }
New-Item -ItemType Directory -Path $OutputDir | Out-Null

# Copia dependencias desde ~/.m2 a ./libs (según lo declarado en el pom)
# - includeGroupIds limita a tus artefactos (recomendado)
# - excludeTransitive=false para traer también transitivas internas si las hubiera
& mvn -q -B -DskipTests dependency:copy-dependencies `
  "-DoutputDirectory=$OutputDir" `
  "-DincludeScope=$Scope" `
  "-DincludeGroupIds=$IncludeGroupIds" `
  "-DexcludeTransitive=false" `
  "-Dmdep.useRepositoryLayout=false"

Write-Host "OK: dependencias copiadas a $OutputDir"