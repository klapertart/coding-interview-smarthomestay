package coding.interview.smarthomestay.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author kurakuraninja
 * @since 05/01/23
 */
public class DateUtil {
    public static Date plusDay(String sDate, int days) throws ParseException {
        if (days > 0){
            Date date = strToDate(sDate);

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);

            return cal.getTime();
        }

        return null;
    }

    public static Date strToDate(String sDate) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    }
}
