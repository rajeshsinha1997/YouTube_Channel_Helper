package helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {

    /**
     * function to get current local date time as String
     * @return current local date time as String
     */
    public static String getCurrentLocalDateTimeString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }
}
