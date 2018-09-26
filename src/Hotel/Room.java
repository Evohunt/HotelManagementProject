package Hotel;

import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Room implements IRoom, Serializable {

    private int roomNumber;
    private int roomSize;
    private Client client;
    private int extraCost = 0;
    private boolean isBusy = false;
    private boolean isReserved = false;

    public Room(Client client, int roomSize) {
        this.client = client;
        this.roomSize = roomSize;
        isReserved = true;
    }

    @Override
    public long calculateRoomPrice() {
        long differenceInMillies = client.getCheckOutDate().getTime() - client.getCheckInDate().getTime();
        return TimeUnit.DAYS.convert(differenceInMillies, TimeUnit.MILLISECONDS) * 50 * roomSize;
    }

    @Override
    public long calculateTotalDays() {
        long differenceInMillies = client.getCheckOutDate().getTime() - client.getCheckInDate().getTime();
        return TimeUnit.DAYS.convert(differenceInMillies, TimeUnit.MILLISECONDS);
    }

    @Override
    public void reset() {
        this.extraCost = 0;
        client.reset();
    }

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public int getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(int extraCost) {
        this.extraCost = extraCost;
    }


}
