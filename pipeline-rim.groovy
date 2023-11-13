pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
    }
  stages {
        stage('Building') {
            steps {
                script {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Unit Testing') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST*.xml'
                }
            }
        }
        stage('Sonarqube Analysis') {
            steps {
                script {
                    jacoco()
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    withSonarQubeEnv('sonar') {
                        sh "${mvnHome}/bin/mvn verify sonar:sonar"
                    }
                }
            }
        }

        stage("Quality Gate") {
            steps {
                script {
                    sleep(10)
                    timeout(time: 1, unit: 'HOURS') {
                        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Building jar') {
            steps {
                script {
                    def mvnHome = tool name: 'maven-3', type: 'maven'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }

//        stage("Deploying jar to Nexus Repository") {
//            steps {
//                script {
//                    script {
//                        def mvnHome = tool name: 'maven-3', type: 'maven'
//                        def groupId = 'tn.esprit' // Replace with your project's group ID
//                        def artifactId = 'kaddem' // Replace with your project's artifact ID
//                        def version = '0.0.1-SNAPSHOT' // Replace with the version of your artifact
//                        def packaging = 'jar' // Replace with the packaging type if different
//
//                        sh """
//                ${mvnHome}/bin/mvn deploy
//                -Durl=${env.NEXUS_PROTOCOL}://${env.NEXUS_URL}/repository/${env.NEXUS_REPOSITORY}/
//                -DrepositoryId=${env.NEXUS_REPOSITORY}
//                -DgroupId=${groupId}
//                -DartifactId=${artifactId}
//                -Dversion=${version}
//                -Dpackaging=${packaging}
//                -Dfile=target/${artifactId}-${version}.${packaging}
//            """
//                    }
//                }
//            }
//        }

        stage("publish to nexus") {
            steps {
                script {
                    artifactPath = "target/kaddem-0.0.1-SNAPSHOT.jar";

                    echo "*** File: ${artifactPath}, group: tn.esprit, packaging: jar, version 0.0.1-SNAPSHOT ***";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: 'tn.esprit',
                            version: '0.0.1-SNAPSHOT',
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: 'kaddem',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );

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
