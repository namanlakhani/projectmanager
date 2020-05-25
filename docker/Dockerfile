FROM openjdk:8
ADD projectmanager-server/target/project-manager-app.jar  project-manager-app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "project-manager-app.jar"]
CMD ["--spring.profiles.active=docker"]