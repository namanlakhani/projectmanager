FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD projectmanager-task-service/target/task-service-0.0.1-SNAPSHOT.jar  task-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "task-service.jar"]
CMD ["--spring.profiles.active=docker"]
