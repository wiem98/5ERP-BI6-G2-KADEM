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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=fares123'
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

       
		
       stage('Docker build')
        {
            steps {
                 sh 'sudo docker build -t faffousa/tpachat  .'
            }
        }
        stage('Docker login')
        {
            steps {
                sh 'echo $dockerhub_PSW | docker login -u faffousa -p dckr_pat_9f0g2XMz_iBfcGOGIsOL0EqpP_g'
            }    
       
        }
      stage('Push') {

			steps {
				sh 'docker push faffousa/tpachat'
			}
		}
       
        
       stage('Run app With DockerCompose') {
              steps {
                  sh "docker-compose -f docker-compose.yml up -d  "
              }
              }
		
		    		 stage('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests'
                  
            }
        }
             stage('Cleaning up') {
         steps {
			sh "docker rmi -f faffousa/tpachat"
         }
     } 
   
    }
    }
