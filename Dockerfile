FROM openjdk:8-jdk

RUN apt-get update && \
    apt-get install -y --no-install-recommends xvfb openjfx && \
    rm -rf /var/lib/apt/lists/*

FROM maven:latest

WORKDIR /app

COPY pom.xml /app/
COPY . /app/

RUN mvn package

CMD ["java", "-jar", "target/cinemabuddy.jar"]






