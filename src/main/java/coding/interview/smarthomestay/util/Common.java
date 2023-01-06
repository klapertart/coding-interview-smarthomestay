package coding.interview.smarthomestay.util;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */


public class Common {

    public static String getReservationCode(){
        return "RSV" + String.format("%02d", LocalTime.now().getHour()) +
                String.format("%02d",LocalTime.now().getMinute()) +
                String.format("%02d", LocalDate.now().getDayOfMonth()) +
                String.format("%02d", LocalDate.now().getMonthValue()) +
                LocalDate.now().getYear();
    }
}
