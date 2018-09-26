package DataSaving;

import Hotel.Price;

public interface IPricesSerializer {

    void serialize(Price price, String path);
    Price deserialize(String path);

}
