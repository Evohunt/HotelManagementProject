package Hotel;

import DataSaving.RoomsSerializer;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TotalRooms implements Serializable {

    private List<Room> busyRooms = new ArrayList<>();
    private List<Room> reservedRooms = new ArrayList<>();
    private List<Room> freeRooms = new ArrayList<Room>() {{
        add(new Room(201));
        add(new Room(202));
        add(new Room(203));
        add(new Room(204));
        add(new Room(205));
        add(new Room(301));
        add(new Room(302));
        add(new Room(303));
        add(new Room(304));
        add(new Room(305));
        add(new Room(401));
        //add(new Room(402));
        //add(new Room(403));
    }};
    private RoomsSerializer ser = new RoomsSerializer();

    public TotalRooms() {

        File f = new File("rooms.ser");
        if(f.exists() && !f.isDirectory()) {
            this.reservedRooms = ser.deserialize("rooms.ser").getReservedRooms();
            this.freeRooms = ser.deserialize("rooms.ser").getFreeRooms();
        }

    }

    public boolean areFreeRoomsAvailable(int roomSize) {
        if (this.freeRooms.size() != 0) {
            for (Room iterator : freeRooms) {
                String string = Integer.toString(iterator.getRoomNumber());
                if (string.startsWith(Integer.toString(roomSize))) {
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    public void assignRoomNumber(Room room, int roomSize) {
        for (Room iterator : freeRooms) {
            String string = Integer.toString(iterator.getRoomNumber());
            if (string.startsWith(Integer.toString(roomSize))) {
                room.setRoomNumber(iterator.getRoomNumber());
                break;
            }
        }
    }

    public void removeFreeRoomWithNumber(int roomNumber) {
        for (Room iterator : freeRooms) {
            String string = Integer.toString(iterator.getRoomNumber());
            if (string.equals(Integer.toString(roomNumber))) {
                freeRooms.remove(iterator);
                break;
            }
        }
    }

    public void addReservedRoom(Room room) {
        this.reservedRooms.add(room);
        ser.serialize(this, "rooms.ser");
    }

    public void addReservedRoom(Room room, int roomSize) {
        assignRoomNumber(room, roomSize);
        removeFreeRoomWithNumber(room.getRoomNumber());
        this.reservedRooms.add(room);
        ser.serialize(this, "rooms.ser");
    }

    public String processRoomNumber(String string) {
        return string.substring(1, 4);
    }

    public void cancelReservedRoom(int roomNumber) {
        for (Room iterator : reservedRooms) {
            String string = Integer.toString(iterator.getRoomNumber());
            if (string.equals(Integer.toString(roomNumber))) {
                freeRooms.add(iterator);
                reservedRooms.remove(iterator);
                break;
            }
        }
    }

    public void setReservedRooms(List<Room> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public List<Room> getReservedRooms() {
        return reservedRooms;
    }

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public List<Room> getBusyRooms() {
        return busyRooms;
    }

    public void setBusyRooms(List<Room> busyRooms) {
        this.busyRooms = busyRooms;
    }

}
