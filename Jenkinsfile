pipeline {
    agent any

    environment {
        SONAR_TOKEN = credentials('sonar-token')
    }

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

        /* ---------------- SONARQUBE STAGE ---------------- */
        stage('SonarQube Code Quality Scan') {
            steps {
                sh '''
                docker run --rm \
                  -e SONAR_HOST_URL=http://host.docker.internal:9000 \
                  -e SONAR_LOGIN=$SONAR_TOKEN \
                  -v "$PWD:/usr/src" \
                  sonarsource/sonar-scanner-cli \
                  -Dsonar.projectKey=numberguess-app \
                  -Dsonar.sources=.
                '''
            }
        }
        /* ------------------------------------------------- */

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t numberguess-app:latest .'
            }
        }

        stage('Trivy Image Security Scan') {
            steps {
                sh '''
                docker run --rm \
                  -v /var/run/docker.sock:/var/run/docker.sock \
                  aquasec/trivy:latest image \
                  --exit-code 1 \
                  --severity HIGH,CRITICAL \
                  numberguess-app:latest
                '''
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
            echo 'Pipeline executed successfully with SonarQube & Trivy checks!'
        }
        failure {
            echo 'Pipeline failed due to build, quality, or security issues!'
        }
    }
}
