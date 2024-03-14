FROM maven:latest

# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

# Run Maven package with JavaFX plugin
RUN mvn -DskipTests=true package javafx:compile javafx:run

CMD ["java", "-jar", "target/cinemabuddy.jar"]

