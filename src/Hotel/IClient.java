package Hotel;

import javafx.scene.control.DatePicker;

public interface IClient {

    void setCheckInDate(DatePicker checkInDate);
    void setCheckOutDate(DatePicker checkInDate);
    String getStringCheckInDate();
    String getStringCheckOutDate();
    void reset();
}
