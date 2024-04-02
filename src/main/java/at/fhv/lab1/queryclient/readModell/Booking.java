package at.fhv.lab1.queryclient.readModell;

import java.time.LocalDateTime;

public class Booking {

    private int roomNumber;
    private LocalDateTime startDate;
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


    public Booking(int roomNumber, LocalDateTime startDate, int nights, int totalNumberOfGuests, String guestName) {
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.nights = nights;
        this.totalNumberOfGuests = totalNumberOfGuests;
        this.guestName = guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public LocalDateTime getEndDate() {
        return startDate.plusDays(nights);
    }

    public boolean isBooked(LocalDateTime day){
        if(startDate.isBefore(day) && startDate.plusDays(nights).isAfter(day)){
            return true;
        }
        return false;
    }

}
