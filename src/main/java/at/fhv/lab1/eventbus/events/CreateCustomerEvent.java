package at.fhv.lab1.eventbus.events;

public class CreateCustomerEvent extends Event{

    private String Customer;


    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    @Override
    public String toString() {
        return "CreateCustomerEvent{" +
                "Customer='" + Customer + '\'' +
                ", id=" + id +
                ", created=" + created +
                '}';
    }
}
