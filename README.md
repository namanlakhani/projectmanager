# Project Manager Application - Final SBA Certification - IIHT - FSD: Naman Lakhani


<h4>projectmanager-server - contains the REST end-points for application </h4>
<h4>projectmanager-web - contains the angular cli code for application</h4>

Repo: https://github.com/namanlakhani/projectmanager.git

_Prerequisite_
1. MySQL Server should be up and running and should have database named projectmanager
2. Should have JRE installed
3. [Optional]: Jenkins setup is optional
4. [Optional]: docker setup if running in docker container

_Building Application_

[Local Build]
1. projectmanager-server -> mvn clean install
2. projectmanager-web -> npm install

[Without Docker]
1. Configure Jenkins Pipeline Job with scripts from SCM
2. Refer Git URL and Script name in job (Jenkinsfile)
3. Application will be build and artifact will be saved in the CI Server

[With Docker]
1. Configure Jenkins Pipeline Job with scripts from SCM.
2. Refer Git URL and Script name in job (Jenkinsfile).
3. Jenkins file will call Dockerfile and corresponding docker-compose down and up call.
4. Project Manager Application instance will be started in CI server

_Running the Application_

[Local]
Locally front and back-end can be run seperately
1. projectmanager-server -> springboot:run
2. projectmanager-web -> npm start
3. Access the application by hitting `http://localhost:4200` 

[Without Docker - Packaged]
1. Use command `java -jar project-manager-app.jar`
2. Application will be running in 8090 port
3. Access the application by hitting `http://localhost:8090` 

[With Docker]
1. Goto folder "docker" and Use command `docker-compose -f docker-compose.yml up -d`
2. Application will be running in 8090 port in dokcer container
3. Access the application by hitting `http://localhost:8090` 

[Documentation and Reports]
1. Junit and Emma code coverage reports available in Documents folder
2. Jenkins will be running on `http://localhost:9000`
3. JMeter Load Run Script available in Documents folder
4. XAMPP MySQL Server is used for development purpose
6. npm goal is added to maven hence UI will be packaged in to same application as that of service
7. Application Screenshots available in Documents folder.
8. docker folder contains Jenkins and docker file for deployment
