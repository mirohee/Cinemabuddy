FROM maven:latest

# Set up environment variables for JavaFX
ENV JAVA_TOOL_OPTIONS="--module-path /usr/lib/jvm/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml"

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

# Unset JAVA_TOOL_OPTIONS before running Maven package command
RUN unset JAVA_TOOL_OPTIONS && mvn package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]






