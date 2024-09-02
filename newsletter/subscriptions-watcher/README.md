# Subscriptions Watcher

This project will watch subscribers to the newsletter and send them a Welcome email.

## Technologies

- Dapr

## Run Locally

- `mvn clean install`
- `mvn clean package`
- `dapr run --resources-path ./src/components/pubsub --app-id subscriptions-watcher --app-port 3000 -- java -jar target/subscriptions-watcher-0.0.1-SNAPSHOT.jar --server.port=3000`
