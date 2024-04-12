package at.fhv.lab1.queryclient;

import at.fhv.lab1.eventbus.events.BookRoomEvent;
import at.fhv.lab1.eventbus.events.CancelBookingEvent;
import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import at.fhv.lab1.eventbus.events.DeleteEvents;
import at.fhv.lab1.queryclient.projection.GuestProjection;
import at.fhv.lab1.queryclient.projection.RoomProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventProjection {

    private final GuestProjection guestProjection;
    private final RoomProjection roomProjection;

    @Autowired
    public EventProjection(GuestProjection guestProjection, RoomProjection roomProjection){
        this.guestProjection = guestProjection;
        this.roomProjection = roomProjection;
    }

    public void handle(BookRoomEvent event){
        roomProjection.handle(event);
    }

    public void handle(CancelBookingEvent event) {
        roomProjection.handle(event);

    }

    public void handle(CreateCustomerEvent event) {
        guestProjection.handle(event);
    }

    public void handle(DeleteEvents event) {
        guestProjection.delete();
        roomProjection.delete();

    }

}
