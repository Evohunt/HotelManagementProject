package Hotel;

import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.util.Date;

public class Client implements IClient, Serializable {

    private String name;
    private String cnp;
    private String phone;
    private String email;
    private int roomSize;
    private Date checkInDate;
    private Date checkOutDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(DatePicker checkInDate) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(checkInDate.getValue());
        Date date = new Date(sqlDate.getTime());
        this.checkInDate = date;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(DatePicker checkOutDate) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(checkOutDate.getValue());
        Date date = new Date(sqlDate.getTime());
        this.checkOutDate = date;
    }

}
