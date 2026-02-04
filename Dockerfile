# Stage 1: Build the application
FROM eclipse-temurin:25-jdk-noble AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:25-jre-noble AS run
WORKDIR /app
# Copy the packaged application from the build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]