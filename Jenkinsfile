pipeline {
    agent any

    tools {
        maven 'Maven_3_9_6'    // <-- must match name under "Manage Jenkins" → "Global Tool Configuration"
        jdk 'JDK_17'           // <-- optional if you configured JDK globally
    }

    environment {
        REPORT_DIR = 'Report'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/YOUR_USERNAME/YOUR_REPO.git'
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
                    reportFiles: 'cucumber-html-reports/overview-features.html',
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
