package at.fhv.lab1.commandclient.writeModell;


import java.time.LocalDate;
import java.util.Objects;

public class Guest {

    private int id;
    private String name;
    private String address;
    private LocalDate birthDate;

    public Guest(){

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest guest)) return false;
        return getName().equals(guest.getName()) && getAddress().equals(guest.getAddress()) && getBirthDate().equals(guest.getBirthDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress(), getBirthDate());
    }
}
