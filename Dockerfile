#
# Build stage
#
FROM maven:3.8.1-jdk-11-slim AS build
# Establish workspace to build the application
WORKDIR /mutant-api
# Copy the maven pom
COPY pom.xml .
# Copy all modules
COPY mutant-app ./mutant-app
COPY mutant-contract ./mutant-contract
COPY mutant-domain ./mutant-domain
#run the command to build .jar file
RUN mvn package

# More information: https://hub.docker.com/_/openjdk
FROM openjdk:11-jre-slim
# Establish the workspace to run the applicaton
WORKDIR /opt/mutant-api
# Copy the java application to run
COPY --from=build /mutant-api/mutant-app/target/mutant-app-1.0-SNAPSHOT.jar app.jar

# Run the Spring Boot application with the java command
ENTRYPOINT ["java", "-jar", "app.jar"]
