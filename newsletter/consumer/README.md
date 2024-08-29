# Newsletter Consumer App (Java Spring)

This Java Application is listening to the messages in the pubsub which is Redis.

## Postgres & Redis

- `docker run --name newsletter-db -e POSTGRES_USER=adminuser -e POSTGRES_PASSWORD=adminpass -e POSTGRES_DB=newsletter -p 5433:5432 -d postgres`
- `docker run --name newsletter-redis -p 6380:6379 -d redis`
- Create the table:

  ```sql
  CREATE TABLE subscriptions (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );
  ```

## Run the app with Dapr

- `mvn clean package`
- `dapr run --app-id consumer --app-port 8081 --dapr-http-port 3501 --resources-path dapr -- java -jar target/consumer-0.0.1-SNAPSHOT.jar`

## Test

- `curl -X POST http://localhost:8080/subscribe -H "Content-Type: application/json" -d '{"email":"test.from.cli@example.com"}'`
- The response should be: `Subscription received for email: test@example.com`
- Check Postgres
- Check Redis

![Redis - DataGrip](redis.png)
