package at.fhv.lab1.commandclient.aggregate;


import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.domainRepositories.GuestRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;;

@Service
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
       this.guestRepository = guestRepository;
    }


    public Guest createCustomer(CreateCustomer query) {
        Guest guest = new Guest(query.getName(), query.getAddress(),
                query.getBirthDate());
        if (!guestRepository.existsGuest(guest)) {
            guestRepository.save(guest);
            return guest;
        }
        return null;
    }
}
