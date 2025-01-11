# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /user-details
COPY . .
RUN mvn clean package

# Stage 2: Create a lightweight runtime image
FROM openjdk:17-alpine
WORKDIR /user-details
COPY --from=build /user-details/target/*.jar app.jar

# Use a minimal command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
