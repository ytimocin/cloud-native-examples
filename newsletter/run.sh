#!/bin/bash

# Make sure that Postgres and Redis containers are running.
# docker compose up -d
# sleep 30

# Start the newsletter application
cd newsletter
mvn clean install && mvn clean package
dapr run --app-id newsletter --app-port 8080 --dapr-http-port 3500 --resources-path dapr/components -- java -jar target/newsletter-0.0.1-SNAPSHOT.jar &

sleep 10

# Start the consumer application
cd ../consumer
mvn clean install && mvn clean package
dapr run --app-id consumer --app-port 8081 --dapr-http-port 3501 --resources-path dapr -- java -jar target/consumer-0.0.1-SNAPSHOT.jar &

# Start the subscriptions watcher application (just another consumer that is configured with Beans)
cd ../subscriptions-watcher
mvn clean install && mvn clean package
dapr run --resources-path ./src/components/pubsub --app-id subscriptions-watcher --app-port 3000 -- java -jar target/subscriptions-watcher-0.0.1-SNAPSHOT.jar --server.port=3000 &
