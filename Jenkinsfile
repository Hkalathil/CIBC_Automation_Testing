pipeline {
    agent any

    tools {
        maven 'Maven3.9.11'
        jdk 'JDK_21'           
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
                bat 'mvn clean test'
            }
        }
        
        stage('Generate Cucumber Report') {
            steps {
                echo 'Generating Cucumber report...'
                cucumber buildStatus: 'UNSTABLE',
                         fileIncludePattern: '**/cucumber.json',
                         jsonReportDirectory: 'Report',
                         sortingMethod: 'ALPHABETICAL',
                         trendsLimit: 10,
                         failedFeaturesNumber: 0,
                         undefinedStepsNumber: 0
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
