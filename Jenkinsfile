node {
    def mavenHome = tool name: 'Maven'
    env.PATH = "${mavenHome}/bin:${env.PATH}"
}

pipeline {
    agent any
    stages {
        stage('Build'){
            steps{
                sh 'mvn -f CookieFactory/j2e/kcc/pom.xml install'
                sh 'mvn -f CookieFactory/j2e/CustomerRegistry/pom.xml install'
                sh 'mvn -f CookieFactory/j2e/Catalogue/pom.xml install'
                sh 'mvn -f CookieFactory/j2e/CartWebService/pom.xml install'
                sh 'mvn -f CookieFactory/j2e/CustomerCareService/pom.xml install'
            }
        }
        stage('Depploy'){
            steps {
                echo 'deploying'
            }
        }
    }

}
