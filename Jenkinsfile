pipeline {
    agent any

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Compile Java') {
            steps {
                sh 'javac Numbergame11.java'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t numberguess-app:latest .'
            }
        }

        stage('Stop Old Container') {
            steps {
                script {
                    sh """
                    if [ \$(docker ps -aq -f name=numberguess-container) ]; then
                        docker stop numberguess-container || true
                        docker rm numberguess-container || true
                    fi
                    """
                }
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d --name numberguess-container numberguess-app:latest'
            }
        }

    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}

