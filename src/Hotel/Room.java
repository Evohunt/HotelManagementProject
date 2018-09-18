package Hotel;

import java.io.Serializable;

public class Room implements IRoom, Serializable {

    private int roomNumber;
    private int roomSize;
    private Client client;
    private boolean isBusy = false;
    private boolean isReserved = false;

    public Room(int roomNumber, Client client) {
        this.roomNumber = roomNumber;
        this.client = client;
        this.roomSize = client.getRoomSize();
        isReserved = true;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
