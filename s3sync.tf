#s3sync.tf
variable "aws_region" {
  type    = string
  default = "us-east-1"
}

provider "aws" {
  region  = var.aws_region
}

resource "null_resource" "remove_and_upload_to_s3" {
  provisioner "local-exec" {
    command = "/usr/local/bin/aws s3 sync /Users/cedricniamba/Desktop/s3test/. s3://testop1"
  }
}