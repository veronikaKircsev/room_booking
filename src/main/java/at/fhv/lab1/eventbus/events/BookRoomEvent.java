package at.fhv.lab1.eventbus.events;

public class BookRoomEvent extends Event{

    private int bookingID;
    private String customer;
    private int roomNumber;
    private int duration;


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

    public int getRoomNumber(int roomNumber) {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getDuration(int nights) {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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
