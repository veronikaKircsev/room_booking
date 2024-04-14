package at.fhv.lab1.eventbus.events;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event{

    private String content;
    private long timestamp;
    public UUID id = UUID.randomUUID();
    public LocalDateTime created = LocalDateTime.now();


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id =id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "{" +
                "content:'" + content + '\'' +
                ", timestamp:" + timestamp +
                ", id:" + id +
                ", created:" + created +
                '}';
    }
}
