package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventRepository;
import at.fhv.lab1.eventbus.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventRestController {

    private final EventRepository repository;

    @Autowired
    public EventRestController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/bookRoomEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event){
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/cancelBookingEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CancelBookingEvent event){
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/createCustomerEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CreateCustomerEvent event) {
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/deleteEvents", consumes = "application/json")
    public boolean addEvent(@RequestBody DeleteEvents event) {
        repository.processEvent(event);
        return true;
    }

    @PostMapping(value = "/restoreQueryEvents", consumes = "application/json")
    public boolean addEvent(@RequestBody RestoreQueryEvents event) {
        repository.restore();
        return true;
    }
}
