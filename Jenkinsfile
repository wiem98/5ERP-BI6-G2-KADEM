pipeline {

        agent any
        stages {
                stage('Checkout Git'){
                   
                steps{
                      echo 'Pulling...';
                        git branch: 'WiemJouini-5BI6-G2',
                        url : 'https://github.com/wiem98/5ERP-BI6-G2-KADEM.git';
                         sh """mvn -version"""
                    }
                }
       
        stage('Testing maven') {
            steps {
                sh """mvn -version"""
                 
            }
        }
       
        stage('Mvn Clean') {
            steps {
                sh 'mvn clean'
                 
            }
        }
        stage('Mvn Compile') {
            steps {
                sh 'mvn compile'
                 
            }
        }
		
	 stage('SonarQube analysis 1') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
		
         stage('JUnit and Mockito Test'){
            steps{
                script
                {
                    if (isUnix())
                    {
                        sh 'mvn --batch-mode test'
                    }
                    else
                    {
                        bat 'mvn --batch-mode test'
                    }
                }
            }
       
        }

			stage('Sending email'){
           steps {
            mail bcc: '', body: '''Hello from Wiem,
            Devops Pipeline with success.
            Cordialement''', cc: '', from: '', replyTo: '', subject: 'Devops', to: 'wiem.jouini@esprit.tn'
            }
       }
       
		
       stage('Docker build')
        {
            steps {
                 sh ' docker build -t wiemj/wiemjouini-5bi6-g2-kadem  .'
            }
        }
        stage('Docker login')
        {
            steps {
                sh 'echo $dockerhub_PSW | docker login -u wiemj -p dckr_pat_EUS3Ia-YigSb84GwWyUuXn59oTY'
            }    
       
        }
      stage('Push') {

			steps {
				sh 'docker push wiemj/wiemjouini-5bi6-g2-kadem'
			}
		}
       
        
       stage('Run app With DockerCompose') {
              steps {
                  sh 'docker-compose -f docker-compose.yml up -d '
              }
              }
		
		    		 stage('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests'
                  
            }
        }
             stage('Cleaning up') {
         steps {
			sh "docker rmi -f wiemj/wiemjouini-5bi6-g2-kadem"
         }
     } 
	
       
    
   
    }
    }
