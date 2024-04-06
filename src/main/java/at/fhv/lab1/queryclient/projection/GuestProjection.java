package at.fhv.lab1.queryclient.projection;

import at.fhv.lab1.eventbus.events.CreateCustomerEvent;
import at.fhv.lab1.queryclient.readModell.Guest;
import at.fhv.lab1.queryclient.readQuery.GetCustomers;
import at.fhv.lab1.queryclient.readRepository.GuestReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuestProjection {
    private GuestReadRepository repository;

    @Autowired
    public GuestProjection(GuestReadRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Guest> getCustomers(String name) {
        return repository.getGuestList(name);
    }

    public ArrayList<Guest> getCustomers() {
        return repository.getGuestList();
    }

    public void handle(CreateCustomerEvent event){
        Guest guest = new Guest();
        guest.setName(event.getName());
        guest.setBirthDate(event.getBirthDate());
        guest.setId(event.getId());
        guest.setAddress(event.getAddress());
        repository.save(guest);
    }

}

