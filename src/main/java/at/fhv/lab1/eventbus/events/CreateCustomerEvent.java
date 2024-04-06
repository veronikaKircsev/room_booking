package at.fhv.lab1.eventbus.events;


import java.time.LocalDate;

public class CreateCustomerEvent extends Event{

    private String name;
    private String address;
    private LocalDate birthDate;
    private int id;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateCustomerEvent{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthDate=" + birthDate +
                ", id=" + id +
                ", id=" + id +
                ", created=" + created +
                '}';
    }
}
