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
       stage('Nexus Deployment') {
    steps {
        script {
            echo 'Deploying to Nexus...'
            sh 'mvn deploy -DskipTests'
        }
    }
}

   }
}
