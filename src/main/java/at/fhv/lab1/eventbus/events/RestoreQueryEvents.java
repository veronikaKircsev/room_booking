package at.fhv.lab1.eventbus.events;

public class RestoreQueryEvents extends Event{
    private boolean update = true;
    public boolean getUpdate() {
        return update;
    }
}
