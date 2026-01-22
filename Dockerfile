# ===============================
# STAGE 1: BUILD
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiamos solo lo necesario para aprovechar cache
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descarga dependencias (cacheable)
RUN ./mvnw -B dependency:go-offline

# Copiamos el código
COPY src src

# Build Quarkus
RUN ./mvnw -B package -DskipTests


# ===============================
# STAGE 2: RUNTIME
# ===============================
FROM eclipse-temurin:21-jre-jammy

WORKDIR /work

# Usuario no root (seguridad)
RUN useradd -r -u 1001 quarkus
USER 1001

# Copiamos solo el runner
COPY --from=build /app/target/quarkus-app/lib/ lib/
COPY --from=build /app/target/quarkus-app/app/ app/
COPY --from=build /app/target/quarkus-app/quarkus/ quarkus/
COPY --from=build /app/target/quarkus-app/quarkus-run.jar quarkus-run.jar

EXPOSE 8080

# JVM optimizada para contenedores
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 \
               -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]