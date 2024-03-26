package at.fhv.lab1.eventbus.rest;

import at.fhv.lab1.eventbus.EventRepository;
import at.fhv.lab1.eventbus.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventRestController {

    private final EventRepository repository;

    public EventRestController(EventRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/event", consumes = "application/json")
    public boolean addEvent(@RequestBody Event event) {
        // TODO: process event in repository
        repository.processEvent(event);
        System.out.println("Event received: " + event);
        return true;
    }
}
