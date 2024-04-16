package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.*;
import at.fhv.lab1.commandclient.domainRepositories.RoomRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import at.fhv.lab1.commandclient.writeModell.Reservation;
import at.fhv.lab1.eventbus.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;


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

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/createCustomer")
    public void createCustomer(@RequestBody CreateCustomer command) {
         Guest guest = handler.createCustomer(command);
         if (guest!=null) {
             CreateCustomerEvent event = new CreateCustomerEvent();
             event.setContent("createCustomer");
             event.setName(command.getName());
             event.setAddress(command.getAddress());
             event.setBirthDate(command.getBirthDate());
             event.setCustomerId(guest.getId());
             event.setTimestamp(System.currentTimeMillis());
             publisher.publishEvent(event);
         }
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/cancelBooking")
    public void cancelBooking(@RequestBody CancelBooking command) {
        boolean isCanceled=handler.cancelBooking(command);
        if (isCanceled) {
            CancelBookingEvent event = new CancelBookingEvent();
            event.setContent("cancelBooking");
            event.setReservationNumber(command.getReservationNumber());
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);
        }
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/deleteEvents")
    public void deleteEvents(@RequestBody Delete command) {
            DeleteEvents event = new DeleteEvents();
            event.setContent("deleteEvents");
            event.setTimestamp(System.currentTimeMillis());
            publisher.publishEvent(event);
        }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/restoreQueryEvents")
    public void cancelBooking(@RequestBody RestoreQuery command) {
        RestoreQueryEvents event = new RestoreQueryEvents();
        event.setContent("restoreQueryEvents");
        event.setTimestamp(System.currentTimeMillis());
        publisher.publishEvent(event);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/bookRoom")
    public void bookRoom(@RequestBody BookRoom command) {
        if (command.getNumberOfGuests() <= roomRepository.getRoomByNumber(command.getRoomNumber()).getMaxGuestCapacity()
        && command.getNights() > 0) {
            Reservation result = handler.bookRoom(command);
            if (result != null) {
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
            if (!handler.existsGuest(command) && Period.between(command.getBirthDate(), LocalDate.now()).getYears() >= 18) {
                CreateCustomerEvent event = new CreateCustomerEvent();
                event.setContent("createCustomer");
                event.setName(command.getName());
                event.setAddress(command.getAddress());
                event.setBirthDate(command.getBirthDate());
                event.setCustomerId(result.getGuestId());
                event.setTimestamp(System.currentTimeMillis());
                publisher.publishEvent(event);
            }
        }
    }

}
