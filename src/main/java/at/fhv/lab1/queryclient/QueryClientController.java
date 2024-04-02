package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryClientController {


    @PostMapping(value = "/event", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event) {
        // TODO: process event through projection
        System.out.println("Event received: " + event);
        return true;
    }
}
