# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copiamos lo mínimo primero para aprovechar cache
COPY pom.xml ./
COPY libs ./libs
COPY scripts/install-local-jars.sh ./scripts/install-local-jars.sh

# Dependencias del script (unzip) + arreglar CRLF + instalar jars locales
RUN apt-get update && apt-get install -y --no-install-recommends unzip \
 && rm -rf /var/lib/apt/lists/* \
 && sed -i 's/\r$//' ./scripts/install-local-jars.sh \
 && chmod +x ./scripts/install-local-jars.sh \
 && ./scripts/install-local-jars.sh libs

# Bajar dependencias para acelerar builds siguientes
RUN mvn -q -B -DskipTests dependency:go-offline

# Copiamos el código y compilamos
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# (Opcional pero recomendado) correr como usuario no-root
RUN useradd -r -u 1001 appuser
USER appuser

# Copiamos el jar construido (asumimos 1 jar "final" en target/)
# Si tenés más de uno, esto puede copiar el "wrong jar"; en ese caso te ajusto el patrón.
COPY --from=build /workspace/target/*.jar /app/app.jar

# Exponer puerto típico de Spring Boot (ajustalo si usás otro)
EXPOSE 8080

# JVM flags razonables para contenedor (sin pasarnos de rosca)
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -Djava.security.egd=file:/dev/./urandom"

# Arranque
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]