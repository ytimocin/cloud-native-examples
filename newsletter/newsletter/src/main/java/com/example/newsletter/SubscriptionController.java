package com.example.newsletter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.dapr.client.DaprClient;
import io.dapr.client.DaprClientBuilder;

@RestController
public class SubscriptionController {

    private final DaprClient daprClient;

    public SubscriptionController() {
        this.daprClient = new DaprClientBuilder().build();
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody Subscription subscription) {
        if (subscription.getEmail() == null || subscription.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        // Prepare the SQL statement
        String sql = "INSERT INTO subscriptions (email) VALUES ('" + subscription.getEmail() + "')";

        // Prepare the metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("sql", sql);

        // Save to Postgres
        daprClient.invokeBinding("postgres", "exec", null, metadata).block();

        // Publish to Pub/Sub
        daprClient.publishEvent("pubsub", "subscriptions", subscription.getEmail()).block();

        return "Subscription received for email: " + subscription.getEmail();
    }
}
