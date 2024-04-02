package at.fhv.lab1.queryclient.readModell;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AvailableRoom {

    private int roomNumber;
    private int maxCapacity;
    private HashMap<LocalDate, Boolean> available = new HashMap<>();



    public AvailableRoom(int roomNumber, int maxCapacity) {
        LocalDate currentDate = LocalDate.now();
        this.roomNumber = roomNumber;
        this.maxCapacity = maxCapacity;
        for (int i = 0; i < 1000; i++) {
            available.put(currentDate.plusDays(i), true);

        }
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void booking(LocalDate start, int nights) {
        for (int i = 0; i <nights; i++) {
            available.put(start.plusDays(i), false);
        }
    }

    public boolean isAvailable(LocalDate start, LocalDate end){
        ArrayList<Boolean> free = new ArrayList<>();
        Integer nights = Math.toIntExact(ChronoUnit.DAYS.between(start, end));
        for (Map.Entry<LocalDate, Boolean> entry : available.entrySet()) {
            LocalDate date = entry.getKey();
            boolean value = entry.getValue();
            if (date.isAfter(start) && date.isBefore(end)) {
                free.add(true);
            }
        }
        if (free.size() == nights)
            return true;
        else
            return false;
    }



}
