package com.latch.aws

def s3Sync(options, src, dst) {
  withEnv(["AWS_DEFAULT_REGION=${options.region}"]) {
    withCredentials([[$class: "AmazonWebServicesCredentialsBinding", credentialsId: options.credentials]]) {
      sh """
      docker run \\
        --rm \\
        -e AWS_SECRET_ACCESS_KEY \\
        -e AWS_ACCESS_KEY_ID \\
        -e AWS_DEFAULT_REGION \\
        -v "\$(pwd):/files" \\
        --workdir="/files" \\
        anigeo/awscli \\
          s3 sync ${options.s3SyncArgs ?: ''} "${src}" "${dst}"
      """
    }
  }
}
pipeline {
    agent any 

  environment {
    AWS_ACCESS_KEY_ID = credentials('AWS_ACCESS_KEY_ID')
    AWS_SECRET_ACCESS_KEY = credentials('AWS_SECRET_ACCESS_KEY')
    AWS_SESSION_TOKEN = credentials ('AWS_SESSION_TOKEN')
  }

      stage ('Configure Environment') {
        steps {
          script {
            sh '/usr/local/bin/aws s3 sync /Users/cedricniamba/Desktop/s3test/. s3://testop1'
          }
        }
      }
    }