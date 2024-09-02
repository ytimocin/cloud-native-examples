# Newsletter

## Architecture

### Services

There are 4 micro-services here:

1. Newsletter App: With only one POST endpoint where users can subscribe to a newsletter.
2. Consumer App: When there is a new subscriber, the email is written to a pubsub topic. Consumer app consumes the message from the pubsub.
3. Subscriptions Watcher App: Another consumer application configured via Beans. This app also consumes messages from the configured pubsub.
4. Newsletter Admin App: An app where the admin can see the list of subscribers (WIP), upload files (newsletters) to S3. The upload process also sends a message to the uploads topic in the Redis which will be picked up by one of the consumers to send the uploaded file to the subscribers.

### Diagram

![Architecture](architecture.png)

### Dapr Components Used

- **pubsub.redis**
- **bindings.postgres**
- **bindings.aws.s3**

### Dapr Subscriptions Used

- subscription of **Consumer App** to the **pubsub.redis**

## Run locally

- `./run.sh`
- You can also run `dapr dashboard` to see the running applications. There should be 4 applications on the dashboard.

![Dapr Dashboard](dapr-dashboard.png)

## Test locally

- `curl -X POST http://localhost:8080/subscribe -H "Content-Type: application/json" -d '{"email":"test.from.cli@example.com"}'`

## Test outputs

### From the Newsletter App (Producer)

![Consumer App](consumer.png)

### From the Newsletter App (Consumer)

![Producer App](producer.png)

## References
