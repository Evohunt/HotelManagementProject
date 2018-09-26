package Hotel;

import DataSaving.PricesSerializer;
import InputValidator.DateValidator;

import java.io.File;
import java.io.Serializable;

public class Price implements IPrice, Serializable {

    private int summerPrice = 0;
    private int springPrice = 0;
    private int autumnPrice = 0;
    private int winterPrice = 0;

    public Price() {
        File pricesFile = new File("prices.ser");
        if(pricesFile.exists() && !pricesFile.isDirectory()) {
            PricesSerializer ser = new PricesSerializer();
            this.summerPrice = ser.deserialize("prices.ser").getSummerPrice();
            this.springPrice = ser.deserialize("prices.ser").getSpringPrice();
            this.autumnPrice = ser.deserialize("prices.ser").getAutumnPrice();
            this.winterPrice = ser.deserialize("prices.ser").getWinterPrice();
        }
    }

    @Override
    public long calculatePriceForRoom(Room room) {
        ISeasons seasons = new Seasons();
        switch (seasons.getSeasonFromDate(room.getClient().getCheckInDate())) {
            case "winter":
                return winterPrice * room.getRoomSize() * (new DateValidator().calculateNumberFromDateInterval(room.getClient().getCheckInDate(), room.getClient().getCheckOutDate()));
            case "spring":
                return springPrice * room.getRoomSize() * (new DateValidator().calculateNumberFromDateInterval(room.getClient().getCheckInDate(), room.getClient().getCheckOutDate()));
            case "summer":
                return summerPrice * room.getRoomSize() * (new DateValidator().calculateNumberFromDateInterval(room.getClient().getCheckInDate(), room.getClient().getCheckOutDate()));
            case "autumn":
                return autumnPrice * room.getRoomSize() * (new DateValidator().calculateNumberFromDateInterval(room.getClient().getCheckInDate(), room.getClient().getCheckOutDate()));
        }
        return 0;
    }

    public int getSummerPrice() {
        return summerPrice;
    }

    public void setSummerPrice(int summerPrice) {
        this.summerPrice = summerPrice;
    }

    public int getSpringPrice() {
        return springPrice;
    }

    public void setSpringPrice(int springPrice) {
        this.springPrice = springPrice;
    }

    public int getAutumnPrice() {
        return autumnPrice;
    }

    public void setAutumnPrice(int autumnPrice) {
        this.autumnPrice = autumnPrice;
    }

    public int getWinterPrice() {
        return winterPrice;
    }

    public void setWinterPrice(int winterPrice) {
        this.winterPrice = winterPrice;
    }
}
