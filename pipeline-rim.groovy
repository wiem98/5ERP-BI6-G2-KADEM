pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-rim-credentials"
    }
    stages{
        stage('Compile-package'){
            steps{
                script{
                    sh 'mvn package'
                }
            }
        }
        stage('Sonarqube Analysis'){
            steps{
                script{
                    jacoco()
                    def mvnHome = tool name: 'maven', type: 'maven'
                    withSonarQubeEnv('sonar'){
                        sh "${mvnHome}/bin/mvn verify sonar:sonar"
                    }
                }
            }
        }
        stage("Quality gate status check") {
            steps {
                script{
                    sleep(10)
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate()
                        if(qg.status != 'OK') {
                            slackSend baseUrl: 'https://hooks.slack.com/services/', channel: '#jenkins-notifications', color: 'danger', message: 'Pipeline aborted: Sonarqube Analysis marked as failed', teamDomain: 'Legion14', tokenCredentialId: 'slack-channel'
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }
        stage("Deploying jar to Nexus Repository"){
            steps{
                script{
                    nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: './target/achat-1.0.jar']],mavenCoordinate: [artifactId: 'achat', groupId: 'tn.esprit.rh', packaging: 'jar', version: '1']]]
                }
            }
        }
        stage('Email Notification'){
            steps{
                script{
                    mail bcc: '', body: '''Hi,
Welcome to jenkins email alerts.
Thanks,
Rim''', cc: '', from: '', replyTo: '', subject: 'Jenkins Job', to: 'rim.boukari@esprit.tn'
                }
            }
        }
        stage('Slack Notification'){
            steps{
                script{
                    slackSend baseUrl: 'https://hooks.slack.com/services/', channel: '#jenkins-notifications', color: 'good', message: 'Welcome to jenkins notifications channel, legionaries. Sent from Jenkins', teamDomain: 'Legion14', tokenCredentialId: 'slack-channel'
                }
            }
        }
        stage('Build And Deploy Docker Image'){
            steps{
                script{
                    echo "deploying the application"
                    withCredentials([usernamePassword(credentialsId:'dockerhub',usernameVariable:'USER',passwordVariable:'PWD')]) {
                        sh "echo $PWD | docker login -u $USER --password-stdin"
                        sh "docker build -t rimboukari/spring-app:1.0 ."
                        sh "docker push rimboukari/spring-app:1.0"

                    }
                }
            }
        }
    }
}