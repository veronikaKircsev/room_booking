package at.fhv.lab1.queryclient.projection;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readModell.Booking;
import at.fhv.lab1.queryclient.readRepository.AvailableRoomRepository;
import at.fhv.lab1.queryclient.readRepository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomProjection {
    private BookingRepository bookingRepository;
    private AvailableRoomRepository availableRoomRepository;

    @Autowired
    public RoomProjection(BookingRepository bookingRooms, AvailableRoomRepository availableRoom) {
        this.bookingRepository = bookingRooms;
        this.availableRoomRepository = availableRoom;
    }
    public List<Booking> getBooking(LocalDate start, LocalDate end) {
            return bookingRepository.getBookings(start, end);
    }

    public List<AvailableRoom> getFreeRooms(LocalDate start, LocalDate end, int numberOfGuest) {
        return availableRoomRepository.getFreeRooms(start, end, numberOfGuest);
    }

    public void handle(BookRoomEvent event){
        Booking booking = new Booking();
        booking.setRoomNumber(event.getRoomNumber());
        booking.setStartDate(event.getStartDate());
        booking.setNights(event.getDuration());
        booking.setTotalNumberOfGuests(event.getTotalNumberOfGuest());
        booking.setGuestName(event.getCustomer());
        bookingRepository.save(booking);
        availableRoomRepository.setRoomBooked(event.getRoomNumber(),event.getStartDate(),event.getDuration());
    }

    public void handle(CancelBookingEvent event){
        Booking booking = bookingRepository.getBooking(event.getReservationNumber());
        availableRoomRepository.setRoomFree(booking.getRoomNumber(),booking.getStartDate(),booking.getNights());
        bookingRepository.delete(booking);
    }
}
