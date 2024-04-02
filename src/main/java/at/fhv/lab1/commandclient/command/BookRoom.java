package at.fhv.lab1.commandclient.command;

import at.fhv.lab1.commandclient.writeModell.Guest;

import java.time.LocalDate;

public class BookRoom {

    private int roomNumber;
    private LocalDate start;
    private int nights;
    private Guest guest;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}
