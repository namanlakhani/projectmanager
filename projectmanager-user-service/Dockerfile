FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD projectmanager-user-service/target/user-service.jar  user-service.jar
EXPOSE 2222
ENTRYPOINT ["java", "-jar", "user-service.jar"]
CMD ["--spring.profiles.active=docker"]
