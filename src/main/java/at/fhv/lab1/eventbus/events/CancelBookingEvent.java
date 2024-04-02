package at.fhv.lab1.eventbus.events;

public class CancelBookingEvent extends Event{

    private int reservationNumber;


    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String toString() {
        return "CancelBookingEvent{" +
                "reservationNumber=" + reservationNumber +
                ", id=" + id +
                ", created=" + created +
                '}';
    }
}
