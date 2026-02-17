#!/bin/sh
set -eu

# Uso:
#   ./scripts/install-local-jars.sh libs
LIB_DIR="${1:-libs}"

GROUP_ID="ar.com.gha"

if [ ! -d "$LIB_DIR" ]; then
  echo "[ERROR] No existe el directorio: $LIB_DIR" >&2
  exit 1
fi

if ! command -v mvn >/dev/null 2>&1; then
  echo "[ERROR] No encuentro 'mvn' en PATH. ¿Tu imagen base trae Maven?" >&2
  exit 1
fi

# Si no hay jars, salimos OK (no rompe el build)
if ! find "$LIB_DIR" -type f -name "*.jar" | grep -q .; then
  echo "[WARN] No encontré .jar en $LIB_DIR (nada para instalar)."
  exit 0
fi

find "$LIB_DIR" -type f -name "*.jar" -print | while IFS= read -r jar_file; do
  base="$(basename "$jar_file")"
  name="${base%.jar}"

  # Parseo robusto:
  version="${name##*-}"     # después del último "-"
  artifactId="${name%-*}"   # antes del último "-"

  if [ -z "$artifactId" ] || [ -z "$version" ] || [ "$artifactId" = "$name" ]; then
    echo "[ERROR] No pude parsear artifactId/version desde: $base" >&2
    echo "        Esperaba: <artifactId>-<version>.jar" >&2
    exit 1
  fi

  # Guardrail (NO valida ":", porque el GAV se expresa con ":")
  # Bloqueamos espacios y algunos caracteres que suelen romper properties/CLI
  case "$GROUP_ID$artifactId$version" in
    *" "*|*"@"*|*"\\"*|*"$"*|*"'"*|*"\""* )
      echo "[ERROR] Metadata sospechosa: groupId=$GROUP_ID artifactId=$artifactId version=$version" >&2
      exit 1
    ;;
  esac

  echo ">> Procesando: $jar_file"
  echo "   -> $GROUP_ID:$artifactId:$version"

  mvn -q install:install-file \
    -Dfile="$jar_file" \
    -DgroupId="$GROUP_ID" \
    -DartifactId="$artifactId" \
    -Dversion="$version" \
    -Dpackaging=jar \
    -DgeneratePom=true
done

echo "Listo: instalación de jars local completada."