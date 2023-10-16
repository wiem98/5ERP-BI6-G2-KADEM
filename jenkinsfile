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
       
        stage('Mvn Compile&Mvn Clean') {
            steps {
                    sh 'mvn clean'
                sh 'mvn compile'
		    sh 'mvn package'
                 
            }
        }
                 stage('Docker build')
        {
            steps {
                 sh 'docker buildx build -t wiemj/jouiniwiem5bi6-kadem .'
            }
        }
        stage('Docker login')
        {
            steps {
                sh 'echo $dockerhub_PSW | docker login -u wiemj -p dckr_pat_eQvpmvgdMnFnRpzIgY9MFl882S8'
            }    
       
        }
      stage('Push') {

			steps {
				sh 'docker push wiemj/jouiniwiem5bi6-kadem'
			}
		}
       
        
             stage('Cleaning up') {
         steps {
			sh "docker rmi -f wiemj/jouiniwiem5bi6-kadem"
         }
     } 
   }
}
