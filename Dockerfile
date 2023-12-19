FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/userService-0.0.1-SNAPSHOT.jar /app/userService-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot app is running on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "userService-0.0.1-SNAPSHOT.jar"]