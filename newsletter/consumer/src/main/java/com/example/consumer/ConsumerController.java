package com.example.consumer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.dapr.Topic;

@RestController
public class ConsumerController {

    @PostMapping("/dapr/subscribe")
    public void handleSubscription(@RequestBody String message) {
        // Process the message
        System.out.println("Received message: " + message);
    }

    /**
     * Handles a registered publish endpoint on this app.
     * 
     * @param cloudEvent The cloud event received.
     * @return A message containing the time.
     */
    @Topic(name = "subscriptions", pubsubName = "pubsub")
    @PostMapping(path = "/testingtopic")
    public void handleMessage(@RequestBody String message) {
        try {
            System.out.println("Subscriber got: " + message);
            // System.out.println("Subscriber got: " +
            // OBJECT_MAPPER.writeValueAsString(cloudEvent));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
