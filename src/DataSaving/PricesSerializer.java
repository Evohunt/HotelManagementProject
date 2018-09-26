package DataSaving;

import Hotel.Price;
import Hotel.TotalRooms;

import java.io.*;

public class PricesSerializer implements IPricesSerializer {
    @Override
    public void serialize(Price price, String path) {
        try {

            FileOutputStream fileOut;
            fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(price);
            out.close();
            fileOut.close();

        } catch (IOException i) {

            i.printStackTrace();

        }
    }

    @Override
    public Price deserialize(String path) {
        Price price = null;

        try {

            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            price = (Price) in.readObject();
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException i) {

            i.printStackTrace();

        }

        return price;
    }
}
