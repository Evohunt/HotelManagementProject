package DataSaving;

import Hotel.TotalRooms;

public interface IRoomsSerializer {

    public void serialize(TotalRooms rooms, String path);
    public TotalRooms deserialize(String path);

}
