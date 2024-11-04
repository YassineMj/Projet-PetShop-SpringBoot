# Use the official Maven image to build the project
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the working directory
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/PROJET-PETSHOP-SPRING-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to match the default port used by Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
