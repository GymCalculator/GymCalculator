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


# FROM Anazurakizz - https://gitlab.com/ivan.andreski/food-base/-/blob/main/docker-compose.yml?ref_type=heads
FROM maven:3.8.6-eclipse-temurin-17 as builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package spring-boot:repackage -Dmaven.test.skip

FROM openjdk:17-jdk-alpine

# copy jar
COPY --from=builder target/FoodWizBackend-food-wiz-backend.jar FoodWizBackend.jar
