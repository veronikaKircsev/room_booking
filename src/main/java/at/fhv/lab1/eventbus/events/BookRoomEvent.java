package at.fhv.lab1.eventbus.events;

import java.time.LocalDate;

public class BookRoomEvent extends Event{

    private int bookingID;
    private String customer;
    private int roomNumber;
    private LocalDate startDate;
    private int duration;
    private int totalNumberOfGuest;


    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getTotalNumberOfGuest() {
        return totalNumberOfGuest;
    }

    public void setTotalNumberOfGuest(int totalNumberOfGuest) {
        this.totalNumberOfGuest = totalNumberOfGuest;
    }

    @Override
    public String toString() {
        return "BookRoomEvent{" +
                "bookingID=" + bookingID +
                ", customer='" + customer + '\'' +
                ", roomNumber=" + roomNumber +
                ", duration=" + duration +
                ", id=" + id +
                ", created=" + created +
                '}';
    }
}
