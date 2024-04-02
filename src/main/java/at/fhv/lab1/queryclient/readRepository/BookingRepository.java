package at.fhv.lab1.queryclient.readRepository;

import at.fhv.lab1.queryclient.readModell.Booking;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository {

    private Map<Integer, Booking> bookedRooms = new HashMap<>();

    public List<Booking> getBookings() {
        ArrayList<Booking> rooms = new ArrayList<>();
        for (Booking room : bookedRooms.values()) {
            rooms.add(room);
        }
        return rooms;
    }
    public void save(Booking room) {
        bookedRooms.put(room.getRoomNumber(), room);
    }

    public void delete(Booking room) {
        bookedRooms.remove(room.getRoomNumber());
    }

    public List<Booking> getBookings(LocalDateTime start, LocalDateTime end) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking book : bookedRooms.values()) {
            if ((book.getStartDate().isAfter(start) && book.getStartDate().isBefore(end)) ||
                    (book.getEndDate().isAfter(start) && book.getEndDate().isBefore(end))
            || book.isBooked(start) || book.isBooked(end)) {
                bookings.add(book);
            }
        }
        return bookings;
    }
}
