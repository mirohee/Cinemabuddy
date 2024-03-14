FROM maven:latest

# Set the path to the JavaFX SDK directory in the Docker image
ENV JAVA_FX_SDK_PATH /usr/share/javafx-sdk

# Copy the JavaFX SDK directory from the local machine to the Docker image
COPY javafx-sdk-21.0.2 $JAVA_FX_SDK_PATH

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN mvn package

# Set the module path to include the JavaFX SDK
CMD ["java", "--module-path", "$JAVA_FX_SDK_PATH/lib", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "target/cinemabuddy.jar"]



