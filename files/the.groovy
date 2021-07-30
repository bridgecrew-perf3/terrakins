// Gradle Script
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath "com.monochromeroad.gradle-plugins:gradle-aws-s3-sync:0.10"
    }
}

import com.monochromeroad.gradle.plugin.aws.s3.S3Sync

task deploy(type: S3Sync){
    description = "Deploys my site on a s3 bucket."

    accessKey awsAccessKey
    secretKey awsSecretKey

    configFile "jets3t.properties"
    mimeTypesFile "my-mime.types"

    from "local-site"
    into "my.bucket.name/subdirectory-optional"
}