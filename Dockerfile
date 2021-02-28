FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} upskillproject.jar
ENTRYPOINT ["java","-jar","/upskillproject.jar"]