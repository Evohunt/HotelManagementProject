package Hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TotalRooms implements Serializable {

    //private ObservableList<IRoom> busyRooms = FXCollections.observableArrayList();

    private List<IRoom> busyRooms = new ArrayList<>();
    private List<IRoom> reservedRooms = new ArrayList<>();
    private int free2BedRooms = 20;
    private int free3BedRooms = 10;
    private int free4BedRooms = 7;


    public List<IRoom> getBusyRooms() {
        return busyRooms;
    }

    public void setBusyRooms(List<IRoom> busyRooms) {
        this.busyRooms = busyRooms;
    }

    public List<IRoom> getReservedRooms() {
        return reservedRooms;
    }

    public void addReservedRoom(Room room) {
        this.reservedRooms.add(room);
        switch (room.getRoomSize()) {
            case 2:
                this.free2BedRooms--;
                break;
            case 3:
                this.free3BedRooms--;
                break;
            case 4:
                this.free4BedRooms--;
                break;
        }
    }

    public void setReservedRooms(List<IRoom> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }

    public int getFree2BedRooms() {
        return free2BedRooms;
    }

    public void setFree2BedRooms(int free2BedRooms) {
        this.free2BedRooms = free2BedRooms;
    }

    public int getFree3BedRooms() {
        return free3BedRooms;
    }

    public void setFree3BedRooms(int free3BedRooms) {
        this.free3BedRooms = free3BedRooms;
    }

    public int getFree4BedRooms() {
        return free4BedRooms;
    }

    public void setFree4BedRooms(int free4BedRooms) {
        this.free4BedRooms = free4BedRooms;
    }
}
