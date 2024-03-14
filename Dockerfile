FROM maven:latest

# Set up environment variables for JavaFX
ENV PATH="/usr/lib/jvm/javafx-sdk-21.0.2/bin:${PATH}"

WORKDIR /app

COPY pom.xml /app/

COPY . /app/

RUN mvn package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]





