FROM openjdk:8
MAINTAINER Naman Lakhani <namanlakhani@gmail.com>
ADD discovery-server/target/discovery-server.jar  discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
CMD ["--spring.profiles.active=docker"]
