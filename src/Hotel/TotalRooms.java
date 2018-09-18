package Hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TotalRooms {

    private ObservableList<IRoom> busyRooms = FXCollections.observableArrayList();
    private ObservableList<IRoom> reservedRooms = FXCollections.observableArrayList();
    private int free2BedRooms = 20;
    private int free3BedRooms = 10;
    private int free4BedRooms = 7;

    public ObservableList<IRoom> getBusyRooms() {
        return busyRooms;
    }

    public void setBusyRooms(ObservableList<IRoom> busyRooms) {
        this.busyRooms = busyRooms;
    }

    public ObservableList<IRoom> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(ObservableList<IRoom> reservedRooms) {
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
