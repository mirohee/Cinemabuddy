FROM maven:latest


# Set up environment variables for JavaFX
ENV PATH="/usr/lib/jvm/javafx-sdk-21.0.2/bin:${PATH}"
ENV JAVA_HOME="/usr/lib/jvm/corretto-17.0.9"

# Create a directory for the application
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code
COPY src /app/src

# Run Maven package to resolve dependencies and build the project (skip tests)
RUN mvn -DskipTests clean package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]





