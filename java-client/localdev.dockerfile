FROM alpine:latest

RUN mkdir -p /app/src
WORKDIR /app
RUN export DEBIAN_FRONTEND="noninteractive"
RUN apk add openjdk21 curl
COPY src /app/src
COPY .mvn /app/.mvn
COPY mvnw* /app/
COPY pom.xml /app/pom.xml
RUN /app/mvnw clean package -DskipTests=true
RUN cp /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
