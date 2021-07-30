#package com.latch.aws

def s3_sync(String COMPARE_BRANCH) {
    echo "jenkinsfile"}
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
@Library {MPL mansonry}
pipeline {
    agent any 
    stages {
        stage('Build') { 
            steps {
                // 
            }
        }
      stage ('Configure Environment') {
        steps {
          script {
            sh '/usr/local/bin/aws s3 sync /Users/cedricniamba/Desktop/s3test/. s3://testop1'
          }

        }
      }
    }

credentials = new AWSCredentials(accessKey, secretKey)
service = new RestS3Service(credentials)
bucket = new S3Bucket(bucketName)
file = new File(fileName)
fileObject = new S3Object(file)
fileObject.key = fileName
service.putObject(bucket, fileObject)
expiryTime = new Date() + 1 // 24 hours from current date
link = service.createSignedGetUrl(bucket.name, fileObject.key, expiryTime)
println "$fileName : $link"

