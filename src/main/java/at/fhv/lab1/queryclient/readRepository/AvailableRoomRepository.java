package at.fhv.lab1.queryclient.readRepository;

import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AvailableRoomRepository {

    private Map<Integer, AvailableRoom>rooms = new HashMap<>();

    public ArrayList<AvailableRoom> getFreeRooms(LocalDate start, LocalDate end, Integer guestNumber) {
        ArrayList<AvailableRoom> freeRoomNumbers = new ArrayList<>();
        for (AvailableRoom room : rooms.values()) {
            if (room.isAvailable(start, end) && room.getMaxCapacity() >= guestNumber) {
                freeRoomNumbers.add(room);
            }

        }
        return freeRoomNumbers;
    }

}
