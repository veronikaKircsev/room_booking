package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.domainRepositories.RoomRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import at.fhv.lab1.commandclient.writeModell.Room;
import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping("/command")
public class CommandClientHandler {

    private final CommandHandler handler;
    private final RoomRepository roomRepository;
    private final EventPublisher publisher;
    private boolean success;

    @Autowired
    public CommandClientHandler(CommandHandler handler, RoomRepository roomRepository, EventPublisher publisher) {
        this.handler = handler;
        this.roomRepository = roomRepository;
        this.publisher = publisher;
    }

    @PostMapping("/createCustomer")
    public void createCustomer(@RequestBody CreateCustomer command) {
         Guest guest = handler.createCustomer(command);
         if (guest!=null) {
             CreateCustomerEvent event = new CreateCustomerEvent();
             event.setContent("createCustomer");
             event.setCustomer(guest.toString());
             event.setTimestamp(System.currentTimeMillis());
             publisher.publishEvent(event);
         }
    }

    @PutMapping("/cancelBooking")
    public void cancelBooking(@RequestBody CancelBooking command) {
        if (handler.cancelBooking(command)) {
            CancelBookingEvent event = new CancelBookingEvent();
            event.setContent("cancelBooking");
            event.setReservationNumber(command.getReservationNumber());
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);
        }
    }

    @PostMapping("/bookRoom")
    public void bookRoom(@RequestBody BookRoom command) {
        int result = handler.bookRoom(command);
        if (result != 0) {
            BookRoomEvent event = new BookRoomEvent();
            event.setContent("bookRoom");
            event.setBookingID(result);
            event.setCustomer(command.getGuest().toString());
            event.getRoomNumber(command.getRoomNumber());
            event.getDuration(command.getNights());
            event.setRoomNumber(command.getRoomNumber());
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);

        }
    }

    @Bean
    public CommandLineRunner run() throws Exception {
        Stream.of(
                new Room(1, 2, true),
                new Room(2, 3, true),
                new Room(3, 2, false),
                new Room(4, 3, false)
        ).forEach(this.roomRepository::save);
        System.out.println(roomRepository);
        return args -> {

        };
    }



}
