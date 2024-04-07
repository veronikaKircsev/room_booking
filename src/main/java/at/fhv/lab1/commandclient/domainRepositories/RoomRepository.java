package at.fhv.lab1.commandclient.domainRepositories;

import at.fhv.lab1.commandclient.writeModell.Room;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoomRepository {

    private Map<Integer, Room> rooms = new HashMap<>();

    public void save(Room room) {
        rooms.put(room.getRoomNumber(), room);
    }

    public boolean remove(int id) {
        if (id != 0 && rooms.containsKey(id)) {
            rooms.remove(id);
            return true;
        }
        return false;
    }


    public Room getRoomByNumber(int number) {
        if (number != 0 && rooms.containsKey(number)) {
            return rooms.get(number);
        }
        return new Room();
    }

    @Override
    public String toString() {
        return "RoomRepository{" +
                "rooms=" + rooms +
                '}';
    }
}
