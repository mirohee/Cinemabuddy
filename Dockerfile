FROM maven:latest
# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN mvn package

CMD ["java", "--module-path", "/usr/share/openjfx/lib", "--add-modules", "javafx.controls,javafx.fxml", "controller.MainController"]



