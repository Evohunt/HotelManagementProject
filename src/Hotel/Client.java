package Hotel;

import InputValidator.DateValidator;
import javafx.scene.control.DatePicker;
import java.io.Serializable;
import java.util.Date;

public class Client implements IClient, Serializable {

    private String name;
    private String cnp;
    private String phone;
    private String email;
    private Date checkInDate;
    private Date checkOutDate;

    public void setCheckInDate(DatePicker checkInDate) {
        this.checkInDate = new DateValidator().convertToDate(checkInDate);
    }

    public void setCheckOutDate(DatePicker checkOutDate) {
        this.checkOutDate = new DateValidator().convertToDate(checkOutDate);
    }

    public String getStringCheckInDate() {
        return new DateValidator().convertDateToString(checkInDate);
    }

    public String getStringCheckOutDate() {
        return new DateValidator().convertDateToString(checkOutDate);
    }

    public void reset() {
        this.name = "";
        this.cnp = "";
        this.phone = "";
        this.email = "";
        this.checkInDate = null;
        this.checkOutDate = null;
    }

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

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

}
