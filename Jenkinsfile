pipeline {
    agent any
    tools { 
        maven 'Maven'
        jdk 'JDK8'
    }
    triggers {
        pollSCM('H/5 * * * *')
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }
   
   stages
	{
	   stage('Checkout from SCM GIT') {
		   steps {
			     checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
				      doGenerateSubmoduleConfigurations: false, extensions: [], 
				      submoduleCfg: [], 
				      userRemoteConfigs: [[url: 'https://github.com/namanlakhani/projectmanager.git']]])
			     echo 'Git checkout succeeded'
		   }
	   }

	   stage('Build & Analyse') {
		   steps {
			bat "mvn clean install -DskipTests"
		   }
	   }
	   stage('Build Docker Image') {
		   steps {
			bat 'docker system prune --all --force --volumes'
			bat 'docker build -f discovery-server/Dockerfile -t namanlakhani/discovery-server:latest .'
			bat 'docker build -f api-gateway/Dockerfile -t namanlakhani/api-gateway:latest .'
			bat 'docker build -f projectmanager-user-service/Dockerfile -t namanlakhani/user-service:latest .'
			bat 'docker build -f projectmanager-parenttask-service/Dockerfile -t namanlakhani/parenttask-service:latest .'
			bat 'docker build -f projectmanager-project-service/Dockerfile -t namanlakhani/project-service:latest .'
			bat 'docker build -f projectmanager-task-service/Dockerfile -t namanlakhani/task-service:latest .'

		   }
	   }
	  stage('Push Docker Image') {
	          steps {  
		       withCredentials([usernamePassword(credentialsId: 'Docker', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
			   bat 'docker login -u "%USERNAME%"  -p "%PASSWORD%"'
		       }
		bat 'docker push namanlakhani/discovery-server:latest'
		bat 'docker push namanlakhani/api-gateway:latest'
		bat 'docker push namanlakhani/user-service:latest'
		bat 'docker push namanlakhani/parenttask-service:latest'
		bat 'docker push namanlakhani/project-service:latest'
		bat 'docker push namanlakhani/task-service:latest'
	        }
   	  }
	  stage('Start Application')
		{
			steps {
				bat 'docker-compose -f docker-compose.yml down'
				bat 'docker-compose -f docker-compose.yml up -d'
			}
		}
		
	}
}