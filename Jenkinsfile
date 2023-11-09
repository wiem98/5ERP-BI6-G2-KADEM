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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonarqube'
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
	
   }
}
