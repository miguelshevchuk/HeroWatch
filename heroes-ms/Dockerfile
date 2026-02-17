# ------------ Build stage ------------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

# Copy pom first to leverage Docker layer caching for dependencies
COPY pom.xml ./
# Pre-fetch dependencies (no sources yet to maximize cache)
RUN mvn -q -e -B -DskipTests dependency:go-offline

# Now copy the rest of the source
COPY src ./src

# Build the application (skip tests for faster image builds)
RUN mvn -q -e -B -DskipTests package

# ------------ Runtime stage ------------
FROM eclipse-temurin:21-jre-jammy AS runtime

# Create non-root user
RUN useradd -ms /bin/bash appuser

WORKDIR /app

# Copy the fat jar from the build stage
# If you change the project version, update the jar name below accordingly
COPY --from=build /workspace/target/heroes-ms-0.0.1.jar /app/app.jar

# Default environment variables (can be overridden at runtime or via K8s ConfigMap)
ENV PROFILE=default \
    SERVER_PORT=8080 \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=50.0 -XX:MinRAMPercentage=25.0"

# Expose the HTTP port expected by K8s Service/Probes
EXPOSE 8080

USER appuser

# Use exec form; allow JVM opts and pass Spring Boot overrides for profile and port
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar /app/app.jar --spring.profiles.active=${PROFILE} --server.port=${SERVER_PORT}"]
