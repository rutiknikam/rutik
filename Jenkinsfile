pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
    }

    agent any

    tools {
        maven 'maven'
    }
    stages {
        stage('Code Compilation') {
            steps {
                echo 'code compilation is starting'
                sh 'mvn clean compile'
				echo 'code compilation is completed'
            }
        }
        stage('Code Package') {
            steps {
                echo 'code packing is starting'
                sh 'mvn clean package'
				echo 'code packing is completed'
            }
        }
        stage('Building & Tag Docker Image') {
            steps {
                echo 'Starting Building Docker Image'
                sh 'docker build -t rutik044/rutik .'
                sh 'docker build -t rutik .'
                echo 'Completed  Building Docker Image'
            }
        }
        stage('Docker Image Scanning') {
            steps {
                echo 'Docker Image Scanning Started'
                sh 'java -version'
                echo 'Docker Image Scanning Started'
            }
        }
        stage(' Docker push to Docker Hub') {
           steps {
              script {
                 withCredentials([string(credentialsId: 'dockerhubCred', variable: 'dockerhubCred')]){
                 sh 'docker login docker.io -u rutik044 -p ${dockerhubCred}'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'docker push rutik044/rutik:latest'
                 echo "Push Docker Image to DockerHub : In Progress"
                 sh 'whoami'
                 }
              }
            }
        }
        stage(' Docker Image Push to Amazon ECR') {
           steps {
              script {
                 withDockerRegistry([credentialsId:'ecr:ap-south-1:ecr-Cred', url:"https://764303521386.dkr.ecr.ap-south-1.amazonaws.com/rutik-ms"]){
                 sh """
                 echo "List the docker images present in local"
                 docker images
                 echo "Tagging the Docker Image: In Progress"
                 docker tag rutik:latest 764303521386.dkr.ecr.ap-south-1.amazonaws.com/rutik-ms:latest
                 echo "Tagging the Docker Image: Completed"
                 echo "Push Docker Image to ECR : In Progress"
                 docker push 764303521386.dkr.ecr.ap-south-1.amazonaws.com/rutik-ms:latest
                 echo "Push Docker Image to ECR : Completed"
                 """
                 }
              }
           }
        }
      stage('Upload Docker Images to Nexus') {
                  steps {
                      script {
                          withCredentials{[usernamePassword(credentialsId: 'nexus_cred', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]} {
                          sh 'docker login http://3.108.215.191:8085/repository/restro-ms/ -u admin -p $(PASSWORD)'
                          echo "Push Docker Image to Nexus : In Progress"
                          sh 'docker tag Rutik 3.108.215.191:8085/Rutik:latest'
                          sh 'docker push 3.108.215.191:8085/Rutik'
                          echo "Push Docker Image to Nexus : Completed"
                          }
                      }
                 }
            }

       }
   }