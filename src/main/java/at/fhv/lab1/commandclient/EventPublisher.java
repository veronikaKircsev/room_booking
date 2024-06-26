package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.RestoreQuery;
import at.fhv.lab1.eventbus.events.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EventPublisher {

    private final WebClient localApiClient = WebClient.create("http://localhost:8080");


    public EventPublisher() {
    }

    public Boolean publishEvent(BookRoomEvent event) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/bookRoomEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(CancelBookingEvent event) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/cancelBookingEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(CreateCustomerEvent event) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/createCustomerEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(DeleteEvents event) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/deleteEvents")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public Boolean publishEvent(RestoreQueryEvents event) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/restoreQueryEvents")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(event),Event.class)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
