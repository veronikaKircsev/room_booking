package at.fhv.lab1.eventbus.events;

import java.time.LocalDate;

public class CreateCustomerEvent extends Event{

    private String name;
    private String address;
    private LocalDate birthDate;
    private int customerId;
    private String content;
    private long timestamp;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
        return
                "content\n" + content + "\n" +
                "name\n" + name + "\n" +
                " address\n" + address + "\n" +
                "birthDate\n" + birthDate + "\n" +
                "customerId\n" + customerId + "\n" +
                "id\n" + id + "\n" +
                "created\n" + created + "\n" +
                "timestamp\n" + timestamp + "\n";
    }

}
