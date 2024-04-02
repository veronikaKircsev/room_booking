package at.fhv.lab1.queryclient.readRepository;

import at.fhv.lab1.queryclient.readModell.Guest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class GuestRepository {

    private Map<Integer, Guest> guests =    new HashMap<>();

    @Bean
    public GuestRepository GuestRepository() {
        return new GuestRepository();
    }
    private void saveGuests(Guest guest) {
        guests.put(guest.getId(), guest);
    }

    public ArrayList<Guest> getGuestList() {
        ArrayList<Guest> list = new ArrayList<>();
        for (Guest guest : guests.values()) {
            list.add(guest);
        }
        return list;
    }

    public ArrayList<Guest> getGuestList(String name) {
        ArrayList<Guest> list = new ArrayList<>();
        for (Guest guest : guests.values()) {
            if (guest.getName().equals(name)) {
                list.add(guest);
            }
        }
        return list;
    }
}
