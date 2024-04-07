package at.fhv.lab1.queryclient.readModell;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class AvailableRoom {

    private int roomNumber;
    private int maxCapacity;
    private boolean withBalcony;
    private HashMap<LocalDate, Boolean> available = new HashMap<>();



    public AvailableRoom(int roomNumber, int maxCapacity, boolean withBalcony) {
        LocalDate currentDate = LocalDate.now();
        this.roomNumber = roomNumber;
        this.maxCapacity = maxCapacity;
        this.withBalcony = withBalcony;
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
        Integer nights = Math.toIntExact(ChronoUnit.DAYS.between(start, end)) + 1;
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            Boolean availability = available.get(date);
            if (availability) {
                free.add(true);
            }
        }
        if (free.size() == nights)
            return true;
        else
            return false;
    }

    public HashMap<LocalDate, Boolean> getAvailable() {
        return available;
    }

    public void setAvailable(HashMap<LocalDate, Boolean> available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "AvailableRoom{" +
                "roomNumber=" + roomNumber +
                ", maxCapacity=" + maxCapacity +
                ", withBalcony=" + withBalcony +
                '}';
    }
}
