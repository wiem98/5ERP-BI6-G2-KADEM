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
		
       
        
     
          
        stage('Mvn Compile&Mvn Clean') {
            steps {
                    sh 'mvn clean'
                sh 'mvn compile'
		    sh 'mvn package'
                 
            }
        }
               stage('Mvn SonarQube ') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=123'
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

		stage("Nexus"){
           steps{
               
               sh "mvn deploy -DskipTests"
             }
              }
		stage("Docker Build and Run") {
            steps {
                // Build the Docker image
                sh 'docker build -t  bentibahedi/bentibahedi-5bi6-kadem .'
                sh "docker login -u bentibahedi -p Taraji1919"

                // Run the Docker container in detached mode (-d)
                sh 'docker run -d -p 8089:8089 bentibahedi/bentibahedi-5bi6-kadem  '

                // Push the Docker image to a Docker registry (e.g., Docker Hub)
                sh 'docker push  bentibahedi/bentibahedi-5bi6-kadem '

                // Optionally, if you have a docker-compose.yml file, you can use docker-compose to start your services
                sh 'docker-compose up -d'
            }
        }
	 
		
       
   }
}
