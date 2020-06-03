FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD projectmanager-project-service/target/project-service.jar  project-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "project-service.jar"]
CMD ["--spring.profiles.active=docker"]
