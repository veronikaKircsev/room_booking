package at.fhv.lab1.queryclient.projection;

import at.fhv.lab1.queryclient.readModell.Guest;
import at.fhv.lab1.queryclient.readQuery.GetCustomers;
import at.fhv.lab1.queryclient.readRepository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GuestProjection {
    private GuestRepository repository;

    @Autowired
    public GuestProjection(GuestRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Guest> getCustomers(GetCustomers command) {
        return repository.getGuestList(command.getName());
    }

    public ArrayList<Guest> getCustomers() {
        return repository.getGuestList();
    }

}

