package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readRepository.AvailableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

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
        System.out.println("Event received: " + event);
        return true;
    }
    @PostMapping(value = "/cancelBookingEvent", consumes = "application/json")
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

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Stream.of(
                    new AvailableRoom(1, 2, true),
                    new AvailableRoom(2, 3, true),
                    new AvailableRoom(3, 2, false),
                    new AvailableRoom(4, 3, false),
                    new AvailableRoom(5, 2, true),
                    new AvailableRoom(6, 4, true),
                    new AvailableRoom(7, 2, false),
                    new AvailableRoom(8, 4, false),
                    new AvailableRoom(9, 2, true),
                    new AvailableRoom(10, 4, true),
                    new AvailableRoom(11, 2, true),
                    new AvailableRoom(12, 3, true),
                    new AvailableRoom(13, 2, false),
                    new AvailableRoom(14, 3, false),
                    new AvailableRoom(15, 2, true),
                    new AvailableRoom(16, 4, true),
                    new AvailableRoom(17, 2, false),
                    new AvailableRoom(18, 4, false),
                    new AvailableRoom(19, 2, true),
                    new AvailableRoom(20, 4, true),
                    new AvailableRoom(21, 2, true),
                    new AvailableRoom(22, 3, true),
                    new AvailableRoom(23, 2, false),
                    new AvailableRoom(24, 3, false),
                    new AvailableRoom(25, 2, true),
                    new AvailableRoom(26, 4, true),
                    new AvailableRoom(27, 2, false),
                    new AvailableRoom(28, 4, false),
                    new AvailableRoom(29, 2, true),
                    new AvailableRoom(30, 4, true),
                    new AvailableRoom(31, 2, true),
                    new AvailableRoom(32, 3, true),
                    new AvailableRoom(33, 2, false),
                    new AvailableRoom(34, 3, false),
                    new AvailableRoom(35, 2, true),
                    new AvailableRoom(36, 4, true),
                    new AvailableRoom(37, 2, false),
                    new AvailableRoom(38, 4, false),
                    new AvailableRoom(39, 2, true),
                    new AvailableRoom(40, 4, true)
            ).forEach(this.roomRepository::save);
            System.out.println(roomRepository);
        };
    }
}
