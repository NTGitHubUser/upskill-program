FROM gradle:6.8.2-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon


FROM openjdk:11
ARG JAR_FILE=build/libs/\*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar upskillproject.jar
ENTRYPOINT ["java","-jar","/upskillproject.jar"]