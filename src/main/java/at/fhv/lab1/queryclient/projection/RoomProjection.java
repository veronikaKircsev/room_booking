package at.fhv.lab1.queryclient.projection;

import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readModell.Booking;
import at.fhv.lab1.queryclient.readQuery.GetBookings;
import at.fhv.lab1.queryclient.readQuery.GetFreeRooms;
import at.fhv.lab1.queryclient.readRepository.AvailableRoomRepository;
import at.fhv.lab1.queryclient.readRepository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Booking> handle(GetBookings query) {
            return bookingRepository.getBookings(query.getStartDate(), query.getEndDate());
    }

    public List<AvailableRoom> handle(GetFreeRooms query) {
        return availableRoomRepository.getFreeRooms(query.getStartDate(), query.getEndDate(), query.getGuestNumber());
    }
}
