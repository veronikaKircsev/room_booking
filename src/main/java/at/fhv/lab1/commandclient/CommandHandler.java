package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.aggregate.GuestService;
import at.fhv.lab1.commandclient.aggregate.ReservationService;
import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.writeModell.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {

    private final GuestService guestService;
    private final ReservationService reservationService;

    @Autowired
    public CommandHandler(GuestService guestService, ReservationService reservationService) {
        this.guestService = guestService;
        this.reservationService = reservationService;
    }

    public int bookRoom(BookRoom bookRoom) {
            int booking = reservationService.bookRoom(bookRoom);
            CreateCustomer guest = new CreateCustomer();
            guest.setName(bookRoom.getGuest().getName());
            guest.setAddress(bookRoom.getGuest().getAddress());
            guest.setBirthDate(bookRoom.getGuest().getBirthDate());
            guestService.createCustomer(guest);
            return booking;
    }

    public boolean cancelBooking(CancelBooking cancelBooking) {

        return reservationService.cancelBooking(cancelBooking);
    }

    public Guest createCustomer(CreateCustomer createCustomer){

        return guestService.createCustomer(createCustomer);
    }
}
