package at.fhv.lab1.commandclient.domainRepositories;

import at.fhv.lab1.commandclient.writeModell.Reservation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReservationRepository {

    private Map<Integer, Reservation> reservationList = new HashMap<>();
    private int counter;

    public boolean save(Reservation reservation) {
        reservation.setId(++counter);
        reservationList.put(reservation.getId(), reservation);
        return true;
    }

    public boolean remove(int id) {
        if (id != 0 && reservationList.containsKey(id)) {
            reservationList.remove(id);
            return true;
        }
        return false;
    }

    public Reservation getById(int id) {
        if (id != 0 && reservationList.containsKey(id)) {
            return reservationList.get(id);
        }
        return new Reservation();
    }

    public boolean isBooked(Reservation reservation) {
        for (Reservation r : reservationList.values()) {
            if (r.exists(reservation))
                return true;
        }
            return false;
    }
}
