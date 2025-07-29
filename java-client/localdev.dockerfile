FROM alpine:latest

RUN mkdir -p /app/src
WORKDIR /app
RUN export DEBIAN_FRONTEND="noninteractive"
RUN apk add openjdk21 curl
COPY src /app/src
COPY .mvn /app/.mvn
COPY mvnw* /app/
COPY pom.xml /app/pom.xml

EXPOSE 8080

ENTRYPOINT ["/app/mvnw", "spring-boot:run"]
