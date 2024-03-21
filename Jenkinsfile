def DOCKER_REPO = 'satyambotadara2710'
def IMAGE_NAME = 'crudangular'
def PORT = 80
def CONTAINER_NAME = 'cicdFrontend'
def externalScript = load 'Lib.groovy'
pipeline {
    agent any

    parameters {
        string(name: 'imageVersion', defaultValue: '1', description: 'image version')
    }

    environment {
        DOCKERHUB_CRED = credentials('docker-hub-cred')
    }
    stages {
        stage('clean workspace') {
            steps {
                cleanWs()
            }
        }

        stage('checkout') {
            tools { git 'Default' }
            steps {
                git branch: 'main', url: 'https://github.com/satyambotadara2710/cicdFrontend.git'
            }
        }

        stage('build docker image') {
            steps {
                 script{
                //    dockerbuild = load 'DockerBuild.groovy';
                   externalScript.buildDockerImage(DOCKER_REPO,IMAGE_NAME,params.imageVersion)
               }
            }
        }

        stage('push docker image') {
            steps {
                script{
                //    dockerPushScript = load 'PushDockerImage.groovy'
                //    dockerPushScript.
                // }
                bat " docker login -u $DOCKERHUB_CRED_USR -p $DOCKERHUB_CRED_PSW"
                bat "docker push -a ${DOCKER_REPO}/${IMAGE_NAME}"
            }
        }
    }
    post {
        always {
            bat "docker pull ${DOCKER_REPO}/${IMAGE_NAME}"
            bat ".\\runcontainer.bat ${PORT} $CONTAINER_NAME  $DOCKER_REPO/$IMAGE_NAME"
        }
    }
}
}