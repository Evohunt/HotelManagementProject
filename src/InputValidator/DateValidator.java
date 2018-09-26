package InputValidator;

import javafx.scene.control.DatePicker;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateValidator implements IDateValidator {
    @Override
    public Date convertToDate(DatePicker datepicker) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(datepicker.getValue());
        return new Date(sqlDate.getTime());
    }

    @Override
    public boolean isDateIntervalValid(Date checkInDate, Date checkOutDate) {
        if (checkOutDate.before(checkInDate) || checkInDate.equals(checkOutDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String convertDateToString(Date date) {
        Format formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }

    @Override
    public long calculateNumberFromDateInterval(Date checkInDate, Date checkOutDate) {
        long differenceInMillies = checkOutDate.getTime() - checkInDate.getTime();
        return TimeUnit.DAYS.convert(differenceInMillies, TimeUnit.MILLISECONDS);
    }
}
