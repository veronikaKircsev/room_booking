package at.fhv.lab1.queryclient;

import at.fhv.lab1.queryclient.projection.GuestProjection;
import at.fhv.lab1.queryclient.projection.RoomProjection;
import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readModell.Booking;
import at.fhv.lab1.queryclient.readModell.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class QueryProjection {

    GuestProjection guestProjection;
    RoomProjection roomProjection;

    @Autowired
    public QueryProjection(GuestProjection guestProjection, RoomProjection roomProjection){
        this.guestProjection = guestProjection;
        this.roomProjection = roomProjection;
    }


    public String getGuests() {
        ArrayList<Guest> guests = guestProjection.getCustomers();
        return arrayToString(guests);
    }

    public String getGuests(String name) {
        ArrayList<Guest> guests = guestProjection.getCustomers(name);
        return arrayToString(guests);
    }


    public String arrayToString(ArrayList<Guest> guests) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Guest guest : guests){
            sb.append(guest.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public String getBookings(LocalDate startDate, LocalDate endDate) {
        ArrayList<Booking> bookingList = (ArrayList<Booking>) roomProjection.getBooking(startDate, endDate);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < bookingList.size(); i++) {
            sb.append(bookingList.get(i).toString());
            if (i < bookingList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();

    }

    public String getFreeRooms(LocalDate start, LocalDate end, int numberOfGuest){
        ArrayList<AvailableRoom> freeRooms = (ArrayList<AvailableRoom>) roomProjection.getFreeRooms(start, end, numberOfGuest);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < freeRooms.size(); i++) {
            sb.append(freeRooms.get(i).toString());
            if (i < freeRooms.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();


    }


}
