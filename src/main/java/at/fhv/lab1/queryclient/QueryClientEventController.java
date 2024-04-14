package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import at.fhv.lab1.eventbus.events.DeleteEvents;
import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readRepository.AvailableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryClientEventController {

    private final EventProjection projection;
    private final AvailableRoomRepository roomRepository;

    @Autowired
    public QueryClientEventController(EventProjection projection, AvailableRoomRepository roomRepository) {
    this.projection = projection;
        this.roomRepository = roomRepository;
    }
    @PostMapping(value = "/bookRoomEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody BookRoomEvent event) {
        projection.handle(event);
        return true;
    }
    @PostMapping(value = "/cancelBookingEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CancelBookingEvent event) {
        projection.handle(event);
        return true;
    }
    @PostMapping(value = "/createCustomerEvent", consumes = "application/json")
    public boolean addEvent(@RequestBody CreateCustomerEvent event) {
        projection.handle(event);
        return true;
    }

    @PostMapping(value = "/deleteEvents", consumes = "application/json")
    public boolean addEvent(@RequestBody DeleteEvents event) {
        projection.handle(event);
        return true;
    }

}
