FROM maven:latest

# Create a directory for the application
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code
COPY src /app/src

# Run Maven package to resolve dependencies and build the project
RUN mvn dependency:go-offline package

# Set the entry point to run the application
CMD ["java", "-jar", "target/cinemabuddy.jar"]




