package DataSaving;

import Hotel.TotalRooms;

import java.io.Serializable;

public class RoomsSerializer implements IRoomsSerializer, Serializable {
    @Override
    public void serialize(TotalRooms rooms, String path) {

    }

    @Override
    public TotalRooms deserialize(String path) {
        return null;
    }
}
