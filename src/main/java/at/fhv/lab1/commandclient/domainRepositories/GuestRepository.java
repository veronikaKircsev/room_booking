package at.fhv.lab1.commandclient.domainRepositories;

import at.fhv.lab1.commandclient.writeModell.Guest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GuestRepository {

    private Map<Integer,Guest> guestList = new HashMap();
    private int counter;

    public boolean save(Guest guest) {
        guest.setId(++counter);
        System.out.println(guest.getId());
        guestList.put(counter, guest);
        return true;
    }

    public Guest getGuestById(int id) {
        if (id != 0 && guestList.containsKey(id)) {
            return guestList.get(id);
        }
        return new Guest();
    }


    public boolean removeGuestById(int id) {
        if (id != 0 && guestList.containsKey(id)) {
            guestList.remove(id);
            return true;
        }
        return false;
    }

    public Guest existsGuest(Guest guest) {
        for(Guest g: guestList.values()) {
            if(g.equals(guest)) {
                return g;
            }
        }
        return null;
    }


}
