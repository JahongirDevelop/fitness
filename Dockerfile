#FROM openjdk:17
#EXPOSE 8080
#ARG JAR_FILE=target/fitness-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} fitness.jar
#ENTRYPOINT ["java","-jar","/fitness.jar"]
FROM openjdk:17
WORKDIR /app
COPY target/fitness-0.0.1-SNAPSHOT.jar fitness.jar
ENTRYPOINT ["java", "-jar", "fitness.jar"]
