package DataSaving;

import Hotel.TotalRooms;

public interface IRoomsSerializer {

    void serialize(TotalRooms rooms, String path);
    TotalRooms deserialize(String path);

}
