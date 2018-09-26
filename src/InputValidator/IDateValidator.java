package InputValidator;

import javafx.scene.control.DatePicker;

import java.util.Date;

public interface IDateValidator {

    Date convertToDate(DatePicker datepicker);
    boolean isDateIntervalValid(Date checkInDate, Date checkOutDate);
    String convertDateToString(Date date);

}
