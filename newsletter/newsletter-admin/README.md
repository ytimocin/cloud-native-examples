# Newsletter Admin

This service is for the newsletter admin. Features:

- Admin can upload an HTML file to an S3 Bucket which then will be consumed by the consumer and send that email to the subscribers.

## Run Locally

- `mvn clean install && mvn clean package`
- `curl -X POST http://localhost:3001/upload -F "file=@example.txt"`
- `awslocal s3api list-objects --bucket sample-bucket`
