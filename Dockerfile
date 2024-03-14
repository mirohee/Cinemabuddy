FROM maven:latest

# Set up environment variables for JavaFX
ENV JAVA_TOOL_OPTIONS="--module-path /usr/lib/jvm/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml"

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

RUN mvn package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]





