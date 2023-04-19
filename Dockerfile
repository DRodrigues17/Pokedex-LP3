FROM gradle:7.6-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle --build-file=build.gradle
FROM openjdk:17-jdk-alpine
EXPOSE 8081
MAINTAINER Daniel Rodrigues
COPY --from=build /home/gradle/src/build/libs/*.jar /app/pokedex.jar
ENTRYPOINT ["java", "-jar","/app/pokedex.jar"]