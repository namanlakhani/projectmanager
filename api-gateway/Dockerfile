FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD api-gateway/target/api-gateway.jar  api-gateway.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
CMD ["--spring.profiles.active=docker"]
