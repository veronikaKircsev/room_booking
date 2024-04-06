package at.fhv.lab1.queryclient.readRepository;

import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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


    public void setRoomBooked(int roomNumber, LocalDate startDate, int duration){
        setRoomAvailability(roomNumber, startDate, duration, false);
    }

    public void setRoomFree(int roomNumber, LocalDate startDate, int duration) {
        setRoomAvailability(roomNumber, startDate, duration, true);
    }

    public void setRoomAvailability(int roomNumber, LocalDate startDate, int duration, boolean availability){
        AvailableRoom room = rooms.get(roomNumber);
        HashMap<LocalDate,Boolean> dates = room.getAvailable();
        for (int i = 0; i <= duration; i++){
            dates.put(startDate.plusDays(i), availability);
        }
        room.setAvailable(dates);
        rooms.put(room.getRoomNumber(), room);
    }
}
