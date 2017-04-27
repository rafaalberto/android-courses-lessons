package ra.com.br.weather;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utils {

    public static final int ZERO = 0;
    public static final String LOG_TAG = "weatherLog";

    public static final String FORMAT_DATE_TIME = "EEE, MMM dd HH:mm";
    public static final long MILLISECONDS = 1000L;

    public static final String URL_PREFIX_WEATHER_API = "http://api.openweathermap.org/data/2.5/weather";

    public static String createURL(Context context, String... parameters){
        Uri uri = Uri.parse(URL_PREFIX_WEATHER_API);
        Uri.Builder uribuilder = uri.buildUpon();
        uribuilder.appendQueryParameter("q", parameters[0]);
        uribuilder.appendQueryParameter("lang", "en");
        uribuilder.appendQueryParameter("units", parameters[1].equals(context.getResources().getString((R.string.settings_unit_celsius_value))) ? "metric" : "imperial");
        uribuilder.appendQueryParameter("appid", "87f46dab64701a6795eb56aa61196334");
        return uribuilder.toString();
    }

    public static String getFormattedDateTime(Long dateTime, String formatDateTime) {
        Date date = new Date(dateTime * MILLISECONDS);
        return new SimpleDateFormat(formatDateTime).format(date);
    }

    public static int getIconId(String icon) {
        switch (icon) {
            case "01d":
                return R.drawable.sunny;
            case "01n":
                return R.drawable.moon;
            case "02d":
                return R.drawable.cloud;
            case "02n":
                return R.drawable.night_cloud;
            case "03d":
                return R.drawable.partially_cloud;
            case "03n":
                return R.drawable.partially_cloud;
            case "04d":
                return R.drawable.partially_cloud;
            case "04n":
                return R.drawable.partially_cloud;
            case "09d":
                return R.drawable.showers;
            case "09n":
                return R.drawable.showers;
            case "10d":
                return R.drawable.rain;
            case "10n":
                return R.drawable.rain;
            case "11d":
                return R.drawable.thunderstorm;
            case "11n":
                return R.drawable.thunderstorm;
            case "13d":
                return R.drawable.snow;
            case "13n":
                return R.drawable.snow;
            case "50d":
                return R.drawable.twister;
            case "50n":
                return R.drawable.twister;
            default:
                return R.drawable.cloud;
        }
    }
}
