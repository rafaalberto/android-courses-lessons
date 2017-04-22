package ra.com.br.quakereport;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeUtils {

    public static final String LOG_TAG = EarthquakeUtils.class.getSimpleName();
    public static final int ZERO = 0;

    private static final String URL_USGS_EARTHQUAKE = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    private static final String FORMAT = "format";
    private static final String LIMIT = "limit";
    private static final String MINIMUM_MAGNITUDE = "minmag";
    private static final String ORDER_BY = "orderby";

    private static final String FEATURES = "features";
    private static final String PROPERTIES = "properties";
    private static final String MAGNITUDE = "mag";
    private static final String PLACE = "place";
    private static final String TIME = "time";
    private static final String URL = "url";

    public static String createURL(String... parameters) {
        Uri baseUri = Uri.parse(URL_USGS_EARTHQUAKE);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter(FORMAT, "geojson");
        uriBuilder.appendQueryParameter(LIMIT, "10");
        uriBuilder.appendQueryParameter(MINIMUM_MAGNITUDE, parameters[0]);
        uriBuilder.appendQueryParameter(ORDER_BY, parameters[1]);
        return uriBuilder.toString();
    }

    public static List<Earthquake> fetchEarthquakes(String requestUrl) {
        List<Earthquake> earthquakes = new ArrayList<Earthquake>();
        try {
            JSONObject jsonResponse = new JSONObject(EarthquakeConnection.makeHttpRequest(requestUrl));
            JSONArray features = jsonResponse.getJSONArray(FEATURES);
            if (features.length() > ZERO) {
                for (int index = ZERO; index < features.length(); index++) {
                    JSONObject feature = features.getJSONObject(index);
                    JSONObject properties = feature.getJSONObject(PROPERTIES);
                    earthquakes.add(new Earthquake(properties.getDouble(MAGNITUDE), properties.getString(PLACE), properties.getLong(TIME), properties.getString(URL)));
                }
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream: ", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSON: " + e.getMessage());
        }
        return earthquakes;
    }
}
