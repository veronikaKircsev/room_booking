package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryClientEventController {

    private final EventProjection projection;

    @Autowired
    public QueryClientEventController(EventProjection projection) {
    this.projection = projection;
    }
    @PostMapping(value = "/bookRoomEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event) {
        projection.handle(event);
        System.out.println("Event received: " + event);
        return true;
    }
    @PostMapping(value = "/cancelBookingevent", consumes = "application/json")
    public boolean addEvent(@RequestBody CancelBookingEvent event) {
        projection.handle(event);
        System.out.println("Event received: " + event);
        return true;
    }
    @PostMapping(value = "/createCustomerEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CreateCustomerEvent event) {
        projection.handle(event);
        System.out.println("Event received: " + event);
        return true;
    }
}
