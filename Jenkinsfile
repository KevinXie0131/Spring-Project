pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                echo 'Spring Project @@@'
                sh 'mvn -B -DskipTests clean package'
            }
        },
        stage('Deploy') {
            steps {
                script {
                    withEnv(['JENKINS_NODE_COOKIE=background_job']) {
                        sh """
                            nohup java -jar target/gs-rest-service-0.1.0 > app.log &
                         """
                         }
                  }
             }
         }
     }
    
}
