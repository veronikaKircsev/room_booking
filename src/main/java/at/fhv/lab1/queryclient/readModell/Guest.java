package at.fhv.lab1.queryclient.readModell;

import java.time.LocalDateTime;

public class Guest {

    private int id;
    private String name;
    private String address;
    private LocalDateTime birthDate;

    public Guest(String name, String address, LocalDateTime birthday) {
        this.name = name;
        this.address = address;
        this.birthDate = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }
}
