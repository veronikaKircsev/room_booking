package at.fhv.lab1.commandclient.aggregate;

import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.domainRepositories.ReservationRepository;
import at.fhv.lab1.commandclient.writeModell.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationService {

    private ReservationRepository repository;

    @Autowired
    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation bookRoom(BookRoom command, int customerId) {
        Reservation reservation = new Reservation();
        reservation.setRoomNumber(command.getRoomNumber());
        reservation.setGuestId(customerId);
        reservation.setStart(command.getStart());
        reservation.setNights(command.getNights());
            if (!repository.isBooked(reservation)) {
                repository.save(reservation);
                return reservation;
            }
        return null;

    }

    public boolean cancelBooking(CancelBooking command) {

        return repository.remove(command.getReservationNumber());
    }
}
