def DOCKER_REPO = "satyambotadara2710";
def IMAGE_NAME = "crudangular";
def PORT = 80
def CONTAINER_NAME = "cicdFrontend"
pipeline {
    
    agent any;
    
    parameters {
        string(name: 'imageVersion', defaultValue: '1', description: 'image version')
    }
    
    environment {
        DOCKERHUB_CRED = credentials('docker-hub-cred')

    }
    stages{
        
       stage("clean workspace"){
           steps{
               cleanWs();
           }
       }
       
       stage("checkout") {
           tools { git 'Default' }
           steps{
              git branch: 'main', url: 'https://github.com/satyambotadara2710/cicdFrontend.git'
             
           }
       }
       
       stage("build docker image") {
           steps{
                bat "docker build -t ${DOCKER_REPO}/${IMAGE_NAME}:${params.imageVersion} -t ${DOCKER_REPO}/${IMAGE_NAME}:latest ."
           }
       }
       
       stage("push docker image"){
           steps{
                bat " docker login -u $DOCKERHUB_CRED_USR -p $DOCKERHUB_CRED_PSW"
                bat "docker push -a ${DOCKER_REPO}/${IMAGE_NAME}"
           }
       }
    }
    post{
        always {
            bat "docker pull ${DOCKER_REPO}/${IMAGE_NAME}"
            bat "docker run --detach --publish $PORT:8100 --name $CONTAINER_NAME ${DOCKER_REPO}/${IMAGE_NAME}"
        }
    }
}