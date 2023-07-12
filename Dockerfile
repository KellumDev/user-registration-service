# Use a base image with Java 17 installed
FROM amazoncorretto:17.0.7-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/user-registration-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot application will be running on
EXPOSE 8030

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
