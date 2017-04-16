package ra.com.br.weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {

    public static final int ZERO = 0;
    public static final String LOG_TAG = "weatherLog";

    public static final String FORMAT_DATE_TIME = "MM/yy/yyyy HH:mm";
    public static final String FORMAT_TIME = "HH:mm";
    public static final long MILLISECONDS = 1000L;

    public static final String URL_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather?q=LencoisPaulista&lang=pt&units=metric&appid=87f46dab64701a6795eb56aa61196334";

    public static String getFormattedDateTime(Long dateTime, String formatDateTime) {
        Date date = new Date(dateTime * MILLISECONDS);
        return new SimpleDateFormat(formatDateTime).format(date);
    }
}
