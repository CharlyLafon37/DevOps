node {
    def mavenHome = tool name: 'Maven'
    env.PATH = "${mavenHome}/bin:${env.PATH}"
}

pipeline {
    agent any
    stages {
        stage('Build'){
            steps{
                sh 'mvn -f CookieFactory/j2e/kcc/pom.xml clean package'
                sh 'mvn -f CookieFactory/j2e/CustomerRegistry/pom.xml clean package'
                sh 'mvn -f CookieFactory/j2e/Catalogue/pom.xml clean package'
                sh 'mvn -f CookieFactory/j2e/CartWebService/pom.xml clean package'
                sh 'mvn -f CookieFactory/j2e/CustomerCareService/pom.xml clean package'
            }
        }
        stage('Depploy'){
            steps {
                echo 'deploying'
            }
        }
    }

}