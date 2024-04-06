package at.fhv.lab1.commandclient.writeModell;


public class Room {

    private int roomNumber;
    private int maxGuestCapacity;
    private boolean withBalcony;

    public Room(int roomNumber, int maxGuestCapacity, boolean withBalcony) {
        this.roomNumber = roomNumber;
        this.maxGuestCapacity = maxGuestCapacity;
        this.withBalcony = withBalcony;
    }

    public Room(){

    }




    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getMaxGuestCapacity() {
        return maxGuestCapacity;
    }

    public void setMaxGuestCapacity(int maxGuestCapacity) {
        this.maxGuestCapacity = maxGuestCapacity;
    }

    public boolean isWithBalcony() {
        return withBalcony;
    }

    public void setWithBalcony(boolean withBalcony) {
        this.withBalcony = withBalcony;
    }


}
