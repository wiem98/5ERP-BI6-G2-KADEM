pipeline {

        agent any
        stages {
                stage('Checkout Git'){
                   
                steps{
                        echo 'clonning...';
                        git branch: 'NourKchaou-5BI6-G2',
                        url : 'https://github.com/wiem98/5ERP-BI6-G2-KADEM.git';
                    }
                }
       
     
          
        stage('Mvn Compile Clean') {
            steps {
                    sh 'mvn clean'
                sh 'mvn compile'
		    sh 'mvn package'
                 
            }
        }
               stage('Mvn SonarQube ') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }
        }
		
	 stage('Mockito') {
            steps {
                sh 'mvn test'
            }
        }	

		
       stage('Nexus Deployment') {
    steps {
            sh 'mvn deploy -DskipTests'
         }
}
	stage('Docker Image') {
            steps {
                sh 'docker build -t nourkchaou/nourkchaou-5bi6-wiem-kaddem .'
            }
        }
	  stage('Docker Hub') {
            steps {
		 sh 'echo $dockerhub_PSW | docker login -u nourkchaou -p dckr_pat_FYPQ_xvO1Fko8uYgY4muC80uXmg'
                sh 'docker push nourkchaou/nourkchaou-5bi6-wiem-kaddem'
            }
        }

 stage('Run Docker Compose') {
            steps {
               sh 'docker-compose up -d '
            }
        }


   }
}
