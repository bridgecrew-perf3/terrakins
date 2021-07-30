package com.latch.aws

pipeline {
    agent any 

  environment {
    AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
    AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
    AWS_SESSION_TOKEN = credentials ('AWS_SESSION_TOKEN')
  }
          script {
            sh '/usr/local/bin/aws s3 sync /Users/cedricniamba/Desktop/s3test/. s3://testop1'
          }
}