package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryClientEventController {


    @PostMapping(value = "/bookRoomEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }
    @PostMapping(value = "/cancelBookingevent", consumes = "application/json")
    public boolean addEvent(@RequestBody CancelBookingEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }
    @PostMapping(value = "/createCustomerEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CreateCustomerEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }
}
