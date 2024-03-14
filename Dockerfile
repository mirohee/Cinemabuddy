FROM maven:latest

# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

# Run Maven package to build the project
RUN mvn -DskipTests=true package

CMD ["java", "-jar", "target/cinemabuddy.jar"]

