FROM alpine:latest AS base

RUN <<EOT
export DEBIAN_FRONTEND="noninteractive" && \
apk add maven openjdk21 && \
mkdir -p /app/src && \
mvn -v
EOT
WORKDIR /app

FROM base AS builder
COPY src /app/src
COPY pom.xml /app/pom.xml

RUN <<EOT
mvn -v
mvn clean package -DskipTests=true
EOT

FROM base AS runner
COPY --from=builder /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
