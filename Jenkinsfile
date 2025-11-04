pipeline {
    agent any

    tools {
        maven 'Maven3.9.11'    // <-- must match name unde
        jdk 'JDK_21'           // <-- optional if you 
    }

    environment {
        REPORT_DIR = 'Report'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Hkalathil/CIBC_Automation_Testing.git'
            }
        }

        stage('Build and Test') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean test' // use 'sh' instead of 'bat' on Linux/Mac
            }
        }

        stage('Generate Report') {
            steps {
                echo 'Publishing Cucumber HTML Report...'
                publishHTML (target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: "${REPORT_DIR}",
                    reportFiles: 'cucumber.html',
                    reportName: 'Cucumber Test Report'
                ])
            }
        }
    }

    post {
        success {
            echo 'Build and tests completed successfully!'
        }
        failure {
            echo 'Build failed! Check console output and reports for details.'
        }
    }
}
