package at.fhv.lab1.eventbus.events;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("deleteEvents")
public class DeleteEvents extends Event{

    private Boolean delete = true;
    public Boolean getDelete() {
        return delete;
    }
}
