package at.fhv.lab1.queryclient.readModell;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

public class Booking {

    private int bookingId;
    private int roomNumber;
    private LocalDate startDate;
    private int nights;
    private int totalNumberOfGuests;
    private String guestName;

    public int getTotalNumberOfGuests() {
        return totalNumberOfGuests;
    }

    public void setTotalNumberOfGuests(int totalNumberOfGuests) {
        this.totalNumberOfGuests = totalNumberOfGuests;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }


    public Booking() {

    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public LocalDate getEndDate() {
        return startDate.plusDays(nights);
    }

    public boolean isBooked(LocalDateTime day){
        if(startDate.isBefore(ChronoLocalDate.from(day)) && startDate.plusDays(nights).isAfter(ChronoLocalDate.from(day))){
            return true;
        }
        return false;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"bookingId\":" + bookingId +
                ", \"roomNumber\":" + roomNumber +
                ", \"startDate\":\"" + startDate + "\"" +
                ", \"nights\":" + nights +
                ", \"totalNumberOfGuests\":" + totalNumberOfGuests +
                ", \"guestName\":\"" + guestName + "\"" +
                '}';
    }

}
