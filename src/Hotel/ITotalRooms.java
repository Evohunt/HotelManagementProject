package Hotel;

public interface ITotalRooms {

    void configRoomsFromFile(String path);
    boolean areFreeRoomsAvailable(int roomSize);
    void assignRoomNumber(Room room, int roomSize);
    void removeFreeRoomWithNumber(int roomNumber);
    void addReservedRoom(Room room, int roomSize);
    String processRoomNumber(String string);
    void cancelReservedRoom(int roomNumber);
    void moveRoomFromReservedToBusy(int roomNumber);
    void addExtraCostForRoom(int roomNumber, String roomType, int extraCost);
    void checkOutRoom(int roomNumber);

}
