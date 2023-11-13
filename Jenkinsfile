pipeline {

        agent any
        stages {
                stage('Checkout Git'){
                   
                steps{
                        echo 'Pulling...';
                        git branch: 'HediBenTiba-5BI6-G2',
                        url : 'https://github.com/wiem98/5ERP-BI6-G2-KADEM.git';
                         sh """mvn -version"""
                    }
                }
		
          
        stage('Clean the project') {
            steps {
                    sh 'mvn clean'
                 
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
		
		stage('Artifact Construction') {
            steps {
		    sh 'mvn package -DskipTests'
                 
            }
        }
		
               stage('Mvn SonarQube ') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=123'
            }
        }


		stage("Nexus"){
           steps{
               
               sh "mvn deploy -DskipTests"
             }
              }
		stage("Docker Build and Push ") {
            steps {
                
                sh 'docker build -t  bentibahedi-5bi6-kadem .'
		sh  "docker tag bentibahedi-5bi6-kadem bentibahedi/bentibahedi-5bi6-kadem:latest"
                sh "docker login -u bentibahedi -p Taraji1919"
                sh 'docker push  bentibahedi/bentibahedi-5bi6-kadem '
                
            }
        }
		stage("docker Compose Spring SQL"){
			steps{
				sh "docker login -u hedi.bentiba@esprit.tn -p Taraji1919"
				sh 'docker-compose up -d'
			}
		}
	 
		
       
   }
}
