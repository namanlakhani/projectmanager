# Project Manager Application - Final SBA Certification - IIHT - FSD: Naman Lakhani

Application running on VM - `http://localhost:4200` which is UI point to cloud api gateway `http://localhost:8090` using docker container.

Repo: https://github.com/namanlakhani/projectmanager.git

## There are four microservices:

- **user-service**
- **parenttask-service**
- **project-service**
- **task-service**

## There are two microservices using netflix OSS and spring cloud repo
- **api-gateway** : **Spring Cloud API gateway** is used for gateway service.
- **discovery-server**  : **Netflix Eureka** is used for discovery service.

### Gateways ###

URI for gateway : *http://localhost:8090*
Eureka Dashboard: *http://localhost:8761*

## Build & Run
CI/CD
- *> Jennkis Pipeline is setup in VM with git hook on commits. 
Manual
- *>mvn clean install* : to build 
- *>docker-compose up* --build : build docker images and containers and run containers
- *>docker-compose stop* : stop the dockerized services
- Each maven module has a Dockerfile.

In docker-compose.yml file:

- Parenttask Service : **__2222__** port is mapped to **__8010__** port of host
- Project Service : **__2222__** port is mapped to **__8011__** port of host
- User Service : **__2222__** port is mapped to **__8013__** port of host
- Task Service : **__2222__** port is mapped to **__8012__** port of host
- Eureka Discovery Service : **__8761__** port is mapped to **__8761__** port of host
- Spring API Cloud Gateway Service : **__8090__** port is mapped to **__8762__** port of host  

[Documentation and Reports]
1. Junit and Emma code coverage reports available in Documents folder
2. Jenkins will be running on `http://localhost:9000`
3. JMeter Load Run Script available in Documents folder
4. XAMPP MySQL Server is used for development purpose
5. Application Screenshots available in Documents folder.
