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
import java.util.stream.Stream;

@Service
public class RoomProjection {
    private BookingRepository bookingRepository;
    private AvailableRoomRepository availableRoomRepository;

    @Autowired
    public RoomProjection(BookingRepository bookingRooms, AvailableRoomRepository availableRoom) {
        this.bookingRepository = bookingRooms;
        this.availableRoomRepository = availableRoom;
    }
    public String getBooking(LocalDate start, LocalDate end) {
            return bookingRepository.getBookings(start, end);
    }

    public List<AvailableRoom> getFreeRooms(LocalDate start, LocalDate end, int numberOfGuest, boolean withBalkony) {
        return availableRoomRepository.getFreeRooms(start, end, numberOfGuest, withBalkony);
    }

    public void handle(BookRoomEvent event){
        Booking booking = new Booking();
        booking.setRoomNumber(event.getRoomNumber());
        booking.setStartDate(event.getStartDate());
        booking.setNights(event.getDuration());
        booking.setTotalNumberOfGuests(event.getTotalNumberOfGuest());
        booking.setGuestName(event.getCustomer());
        booking.setBookingId(event.getBookingID());
        bookingRepository.save(booking);
        availableRoomRepository.setRoomBooked(event.getRoomNumber(),event.getStartDate(),event.getDuration());
    }

    public void handle(CancelBookingEvent event){
        Booking booking = bookingRepository.getBooking(event.getReservationNumber());
        availableRoomRepository.setRoomFree(booking.getRoomNumber(),booking.getStartDate(),booking.getNights());
        bookingRepository.delete(booking);
    }

    public void delete(){
        bookingRepository.deleteAll();
        Stream.of(
                new AvailableRoom(1, 2, true),
                new AvailableRoom(2, 3, true),
                new AvailableRoom(3, 2, false),
                new AvailableRoom(4, 3, false),
                new AvailableRoom(5, 2, true),
                new AvailableRoom(6, 4, true),
                new AvailableRoom(7, 2, false),
                new AvailableRoom(8, 4, false),
                new AvailableRoom(9, 2, true),
                new AvailableRoom(10, 4, true),
                new AvailableRoom(11, 2, true),
                new AvailableRoom(12, 3, true),
                new AvailableRoom(13, 2, false),
                new AvailableRoom(14, 3, false),
                new AvailableRoom(15, 2, true),
                new AvailableRoom(16, 4, true),
                new AvailableRoom(17, 2, false),
                new AvailableRoom(18, 4, false),
                new AvailableRoom(19, 2, true),
                new AvailableRoom(20, 4, true),
                new AvailableRoom(21, 2, true),
                new AvailableRoom(22, 3, true),
                new AvailableRoom(23, 2, false),
                new AvailableRoom(24, 3, false),
                new AvailableRoom(25, 2, true),
                new AvailableRoom(26, 4, true),
                new AvailableRoom(27, 2, false),
                new AvailableRoom(28, 4, false),
                new AvailableRoom(29, 2, true),
                new AvailableRoom(30, 4, true),
                new AvailableRoom(31, 2, true),
                new AvailableRoom(32, 3, true),
                new AvailableRoom(33, 2, false),
                new AvailableRoom(34, 3, false),
                new AvailableRoom(35, 2, true),
                new AvailableRoom(36, 4, true),
                new AvailableRoom(37, 2, false),
                new AvailableRoom(38, 4, false),
                new AvailableRoom(39, 2, true),
                new AvailableRoom(40, 4, true)
        ).forEach(this.availableRoomRepository::save);
    }
}
