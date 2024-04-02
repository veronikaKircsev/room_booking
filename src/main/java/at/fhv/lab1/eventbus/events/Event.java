package at.fhv.lab1.eventbus.events;


import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Event{

    private long timestamp;
    private String content;
    public final UUID id = UUID.randomUUID();
    public final LocalDateTime created = LocalDateTime.now();


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

    @Override
    public String toString() {
        return "Event{" +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
