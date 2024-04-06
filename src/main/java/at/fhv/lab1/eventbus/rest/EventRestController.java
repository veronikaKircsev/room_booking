package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventRepository;
import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import at.fhv.lab1.eventbus.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventRestController {

    private final EventRepository repository;

    public EventRestController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/bookRoomEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event) {
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/cancelBookingEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CancelBookingEvent event) {
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/createCustomerEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CreateCustomerEvent event) {
        repository.processEvent(event);
        return true;
    }
}
