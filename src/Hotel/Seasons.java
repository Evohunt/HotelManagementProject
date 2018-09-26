package Hotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Seasons implements ISeasons, Serializable {

    private Map<Integer, String> period = new HashMap<Integer, String>() {{
        put(1, "winter");
        put(2, "winter");
        put(3, "spring");
        put(4, "spring");
        put(5, "spring");
        put(6, "summer");
        put(7, "summer");
        put(8, "summer");
        put(9, "autumn");
        put(10, "autumn");
        put(11, "autumn");
        put(12, "winter");
    }};

    @Override
    public String getSeasonFromDate(Date checkInDate) {
        LocalDate date = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return period.get(date.getMonthValue());
    }
}
