import boto3
from mypy_boto3_s3 import S3Client

s3_client: S3Client = boto3.client(
    "s3",
    endpoint_url="http://localhost:4566",
    aws_access_key_id="LSIAQAAAAAAVNCBMPNSG",
    aws_secret_access_key="test"
)

# Create a bucket
# s3_client.create_bucket(Bucket="test")

# Put an object in the bucket
s3_client.put_object(Bucket="sample-bucket", Key="cache/test", Body="test")

# Copy the object within the bucket
s3_client.copy_object(Bucket="sample-bucket-2", Key="test2",
                      CopySource="sample-bucket/cache/test", MetadataDirective="REPLACE")
