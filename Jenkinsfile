pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                echo 'Spring Project @@@'
                sh 'mvn -B -DskipTests clean package'
            }
        }
    }
}
