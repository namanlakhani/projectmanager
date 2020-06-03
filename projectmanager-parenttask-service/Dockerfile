FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD projectmanager-parenttask-service/target/parenttask-service.jar  parenttask-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "parenttask-service.jar"]
CMD ["--spring.profiles.active=docker"]