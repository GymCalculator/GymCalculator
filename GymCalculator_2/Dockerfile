FROM openjdk:11-jre-slim

WORKDIR /app

COPY . /app

EXPOSE 8080

# The application's jar file - ???
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]

# RUN javac Main.java

# GymCalculator2Application
