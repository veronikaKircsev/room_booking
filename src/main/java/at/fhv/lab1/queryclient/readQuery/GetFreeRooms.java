package at.fhv.lab1.queryclient.readQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetFreeRooms {

    private LocalDate startDate;
    private LocalDate endDate;
    private int guestNumber;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(int guestNumber) {
        this.guestNumber = guestNumber;
    }

}
