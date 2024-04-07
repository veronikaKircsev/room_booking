package at.fhv.lab1.commandclient.aggregate;


import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.domainRepositories.GuestRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class GuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
       this.guestRepository = guestRepository;
    }


    public Guest createCustomer(CreateCustomer command) {
        Guest guest = new Guest(command.getName(), command.getAddress(),
                command.getBirthDate());
        if (guestRepository.existsGuest(guest) == null) {
            guestRepository.save(guest);
            return guest;
        }
        return guestRepository.existsGuest(guest);
    }

    public boolean isExists(BookRoom command) {
        Guest guest = new Guest(command.getName(), command.getAddress(),
                command.getBirthDate());
        return guestRepository.existsGuest(guest) == null;
    }

}
