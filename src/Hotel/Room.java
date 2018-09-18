package Hotel;

public class Room implements IRoom {

    private int roomNumber;
    private int roomSize;

    public Room(int roomNumber, int roomSize) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }
}
