package at.fhv.lab1.commandclient.writeModell;


import java.time.LocalDate;

public class Reservation {

    private int id;
    private int roomNumber;
    private int guestId;
    private LocalDate start;
    private int nights;



    public Reservation(int roomNumber, int guestId, LocalDate start, int nights) {
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.start = start;
        this.nights = nights;
    }

    public Reservation() {
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public boolean exists(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        LocalDate startThat = (LocalDate) that.getStart();
        int nightThat = (int) that.getNights();
        LocalDate endThat = startThat.plusDays(nightThat);
        LocalDate end = start.plusDays(nights);
        boolean booked1 = startThat.isAfter(start) && startThat.isBefore(end);
        boolean booked2 = endThat.isBefore(end) && endThat.isAfter(end);
        boolean booked3 = startThat.isBefore(start) && endThat.isAfter(end);

        return getRoomNumber() == that.getRoomNumber() && ((getNights() == that.getNights()
                && getStart().equals(that.getStart())) || booked1 || booked2 || booked3) ;
    }


}
