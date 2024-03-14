FROM maven:latest

# Install javafx
RUN apt-get update && \
    apt-get install -y openjfx=21.0.1 && \
    apt-get clean;

# Create a directory for the application
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code
COPY src /app/src

# Run Maven package to resolve dependencies, build the project, and skip tests
RUN mvn -DskipTests clean package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]




