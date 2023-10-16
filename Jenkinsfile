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
       
        
       
        stage('Mvn Compile&Mvn Clean') {
            steps {
                    sh 'mvn clean'
                sh 'mvn compile'
		    sh 'mvn package'
                 
            }
        }
              
       
   }
}
