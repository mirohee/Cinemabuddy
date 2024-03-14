FROM maven:latest

# Set JAVA_HOME environment variable
ENV JAVA_HOME="/usr/lib/jvm/corretto-17.0.9"

# Set up environment variables for JavaFX
ENV PATH="$JAVA_HOME/bin:${PATH}"
ENV JAVA_TOOL_OPTIONS="--module-path /usr/lib/jvm/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml"

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

RUN mvn package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]





