FROM maven:latest

# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

RUN mvn package

# Set system property for JavaFX rendering
CMD ["java", "-Dprism.order=sw", "--module-path", "/usr/share/openjfx/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "target/cinemabuddy.jar"]
