# Use an official OpenJDK image as a build environment
FROM gradle:jdk20-jammy AS builder

RUN mkdir /code/
# Set the working directory in the container
WORKDIR /code/

# Copy the entire source code to the container
ADD . /code/

# Build the application
RUN ./gradlew build

# Use a lightweight base image for the application
FROM openjdk:20-ea-1-jdk-slim as development

# Set the working directory in the container
WORKDIR /code/

# Copy the JAR file from the build environment to the final image
COPY --from=builder /code/build/libs/Network-Automation.jar /code/app.jar

# Expose the port your Spring Boot application will listen on
EXPOSE 8000

# Define environment variables, if needed
# ENV VAR_NAME=value

# Command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
