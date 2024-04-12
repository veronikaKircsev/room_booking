package at.fhv.lab1.eventbus.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonAutoDetect
public class CancelBookingEvent extends Event{

    private String content;
    private long timestamp;
    private int reservationNumber;


    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "content\n" + content + "\n" +
                "timestamp\n" + timestamp + "\n" +
                "reservationNumber\n" + reservationNumber + "\n" +
                "id\n" + id + "\n" +
                "created\n" + created + "\n";
    }
}
