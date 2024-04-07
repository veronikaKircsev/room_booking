package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.domainRepositories.RoomRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import at.fhv.lab1.commandclient.writeModell.Reservation;
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
             event.setName(command.getName());
             event.setAddress(command.getAddress());
             event.setBirthDate(command.getBirthDate());
             event.setId(guest.getId());
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
        Reservation result = handler.bookRoom(command);
        if (result != null &&
                command.getNumberOfGuests() <= roomRepository.getRoomByNumber(command.getRoomNumber()).getMaxGuestCapacity()) {
            BookRoomEvent event = new BookRoomEvent();
            event.setContent("bookRoom");
            event.setBookingID(result.getId());
            event.setCustomer(command.getName() + " " + command.getAddress() + " " + command.getBirthDate());
            event.setRoomNumber(command.getRoomNumber());
            event.setDuration(command.getNights());
            event.setStartDate(command.getStart());
            event.setTotalNumberOfGuest(command.getNumberOfGuests());
            event.setRoomNumber(command.getRoomNumber());
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);
        }

        if (!handler.existsGuest(command)){
            CreateCustomerEvent event = new CreateCustomerEvent();
            event.setContent("createCustomer");
            event.setName(command.getName());
            event.setAddress(command.getAddress());
            event.setBirthDate(command.getBirthDate());
            event.setId(result.getGuestId());
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);
        }


    }

    @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            Stream.of(
                    new Room(1, 2, true),
                    new Room(2, 3, true),
                    new Room(3, 2, false),
                    new Room(4, 3, false),
                    new Room(5, 2, true),
                    new Room(6, 4, true),
                    new Room(7, 2, false),
                    new Room(8, 4, false),
                    new Room(9, 2, true),
                    new Room(10, 4, true),
                    new Room(11, 2, true),
                    new Room(12, 3, true),
                    new Room(13, 2, false),
                    new Room(14, 3, false),
                    new Room(15, 2, true),
                    new Room(16, 4, true),
                    new Room(17, 2, false),
                    new Room(18, 4, false),
                    new Room(19, 2, true),
                    new Room(20, 4, true),
                    new Room(21, 2, true),
                    new Room(22, 3, true),
                    new Room(23, 2, false),
                    new Room(24, 3, false),
                    new Room(25, 2, true),
                    new Room(26, 4, true),
                    new Room(27, 2, false),
                    new Room(28, 4, false),
                    new Room(29, 2, true),
                    new Room(30, 4, true),
                    new Room(31, 2, true),
                    new Room(32, 3, true),
                    new Room(33, 2, false),
                    new Room(34, 3, false),
                    new Room(35, 2, true),
                    new Room(36, 4, true),
                    new Room(37, 2, false),
                    new Room(38, 4, false),
                    new Room(39, 2, true),
                    new Room(40, 4, true)
            ).forEach(this.roomRepository::save);
            System.out.println(roomRepository);
        };
    }



}
