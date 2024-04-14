package at.fhv.lab1.queryclient.readRepository;

import at.fhv.lab1.queryclient.readModell.Booking;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
        bookedRooms.put(room.getBookingId(), room);
    }

    public void delete(Booking room) {
        bookedRooms.remove(room.getBookingId());
    }
    public void deleteAll(){
        bookedRooms.clear();
    }

    public String getBookings(LocalDate start, LocalDate end) {
        if (bookedRooms == null){
            return "nothing there!";
        } else {
            ArrayList<Booking> bookings = new ArrayList<>();
            for (Booking book : bookedRooms.values()) {
                if ((book.getStartDate().isAfter(start) && book.getStartDate().isBefore(ChronoLocalDate.from(end))) ||
                    (book.getEndDate().isAfter(ChronoLocalDate.from(start)) && book.getEndDate().isBefore(ChronoLocalDate.from(end)))
                || book.isBooked(start.atStartOfDay()) || book.isBooked(end.atStartOfDay())) {
                bookings.add(book);
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < bookings.size(); i++) {
                    sb.append(bookings.get(i).toString());
                    if (i < bookings.size() - 1) {
                        sb.append(", ");
                    }
            }
            sb.append("]");
            return sb.toString();}
    }

    public Booking getBooking(int id){
        System.out.println(id + "id" + bookedRooms.size());
        System.out.println(bookedRooms.get(id).toString());
        return bookedRooms.get(id);
    }
}
