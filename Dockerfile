FROM maven:latest

# Install OpenJFX
RUN apt-get update && \
    apt-get install -y openjfx && \
    apt-get clean;

WORKDIR /app

COPY pom.xml /app/
COPY src /app/src/

# Run Maven package to build the project
RUN mvn -DskipTests=true package

# Copy the compiled classes and resources to the container
COPY target/classes /app/classes
COPY target/libs /app/libs
COPY src/main/resources /app/resources

# Set the classpath to include the compiled classes and dependencies
ENV CLASSPATH /app/target/classes:/app/target/libs/*

CMD ["java", "-cp", "$CLASSPATH", "controller.MainController"]



