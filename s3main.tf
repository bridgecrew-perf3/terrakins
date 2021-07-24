#s3main.tf
variable "aws_region" {
  type = string
}

variable "domain_name" {
  type = string
}

provider "aws" {
  region = var.aws_region
  version = "~> 2.52"
}

module "website" {
  source = "./Web"
  domain_name = var.domain_name
}

resource "aws_s3_bucket_object" "object" {
  bucket = var.domain_name
  key    = "index.html"
  source = "./files/index.html"

depends_on = "aws_s3_bucket.website"
