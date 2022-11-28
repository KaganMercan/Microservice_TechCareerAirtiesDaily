FROM adoptopenjdk/openjdk11
ARG JAR_FILE=Microservice_TeachCareerAirtiesDaily-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} daily.jar
ENTRYPOINT [ "java","-jar","/daily.jar" ]
