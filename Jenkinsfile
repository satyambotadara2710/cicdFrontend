def DOCKER_REPO = 'satyambotadara2710'
def IMAGE_NAME = 'crudangular'
def PORT = 80
def CONTAINER_NAME = 'cicdFrontend'
def externalScript

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

        stage('load external lib') {
            steps {
                script {
                    externalScript = load 'Lib.groovy'
                }
            }
        }

        stage('build docker image') {
            steps {
                script {
                    externalScript.buildDockerImage(DOCKER_REPO, IMAGE_NAME, params.imageVersion)
                }
            }
        }

        stage('push docker image') {
            steps {
                script {
                    externalScript.pushImageToRepo(DOCKERHUB_CRED_USR, DOCKERHUB_CRED_PSW, IMAGE_NAME, DOCKER_REPO)
                }
            }
        }
        stage('run docker container') {
            steps {
                script {
                    externalScript.runContainer(DOCKER_REPO, IMAGE_NAME, CONTAINER_NAME, PORT)
                    // externalScript.hello()
                }
            }
        }
    }
}
