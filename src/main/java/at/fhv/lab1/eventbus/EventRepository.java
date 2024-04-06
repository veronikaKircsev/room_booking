package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventRepository {

    private final List<Event> events = new ArrayList<>();
    private final EventPublisher publisher;

    @Autowired
    public EventRepository(EventPublisher publisher) {
        this.publisher = publisher;
    }



    public void processEvent(Event event) {
        // TODO: store events in log/DB
        events.add(event);
        String eventType = event.getContent();
        switch (eventType) {
            case "createCustomer":
                CreateCustomerEvent event1 = (CreateCustomerEvent) event;
                publisher.publishEvent(event1);
                break;
            case "cancelBooking":
                CancelBookingEvent event2 = (CancelBookingEvent) event;
                publisher.publishEvent(event2);
                break;
            case "bookRoom":
                BookRoomEvent event3 = (BookRoomEvent) event;
                publisher.publishEvent(event3);
            default:
                break;

        }
        System.out.println("Processing Event");
    }

}
