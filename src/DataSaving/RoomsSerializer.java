package DataSaving;

import Hotel.TotalRooms;

import java.io.*;

public class RoomsSerializer implements IRoomsSerializer, Serializable {
    @Override
    public void serialize(TotalRooms rooms, String path) {
        try {

            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rooms);
            out.close();
            fileOut.close();

        } catch (IOException i) {

            i.printStackTrace();

        }
    }

    @Override
    public TotalRooms deserialize(String path) {
        TotalRooms rooms = null;

        try {

            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            rooms = (TotalRooms) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException i) {

            i.printStackTrace();

        }

        return rooms;
    }
}
