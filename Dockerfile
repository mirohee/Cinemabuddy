FROM maven:latest

# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

# Run Maven package with JavaFX plugin
RUN mvn -DskipTests=true package com.zenjava:javafx-maven-plugin:8.8.3:compile com.zenjava:javafx-maven-plugin:8.8.3:run

CMD ["java", "-jar", "target/cinemabuddy.jar"]

