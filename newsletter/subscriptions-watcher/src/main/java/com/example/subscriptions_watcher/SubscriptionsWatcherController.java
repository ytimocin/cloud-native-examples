package com.example.subscriptions_watcher;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dapr.Topic;
import io.dapr.client.domain.CloudEvent;
import reactor.core.publisher.Mono;

@RestController
public class SubscriptionsWatcherController {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Handles a registered publish endpoint on this app.
   *
   * @param cloudEvent The cloud event received.
   * @return A message containing the time.
   */
  @Topic(name = "subscriptions", pubsubName = "${myAppProperty:pubsub}")
  @PostMapping(path = "/subscriptions")
  public Mono<Void> handleMessage(@RequestBody(required = false) CloudEvent<String> cloudEvent) {
    return Mono.fromRunnable(() -> {
      try {
        System.out.println("Subscriber got: " + cloudEvent.getData());
        System.out.println("Subscriber got: " + OBJECT_MAPPER.writeValueAsString(cloudEvent));
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    });
  }
}
