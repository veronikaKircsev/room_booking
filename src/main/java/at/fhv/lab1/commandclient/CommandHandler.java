package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.aggregate.GuestService;
import at.fhv.lab1.commandclient.aggregate.ReservationService;
import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.writeModell.Guest;
import at.fhv.lab1.commandclient.writeModell.Reservation;
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

    public Reservation bookRoom(BookRoom bookRoom) {
            CreateCustomer guest = new CreateCustomer();
            guest.setName(bookRoom.getName());
            guest.setAddress(bookRoom.getAddress());
            guest.setBirthDate(bookRoom.getBirthDate());
            Guest guest1 = guestService.createCustomer(guest);
            Reservation booking = reservationService.bookRoom(bookRoom, guest1.getId());
            return booking;
    }

    public boolean cancelBooking(CancelBooking cancelBooking) {
        return reservationService.cancelBooking(cancelBooking);
    }

    public Guest createCustomer(CreateCustomer createCustomer){

        return guestService.createCustomer(createCustomer);
    }


    public boolean existsGuest(BookRoom book){
        return guestService.isExists(book);
    }
}
