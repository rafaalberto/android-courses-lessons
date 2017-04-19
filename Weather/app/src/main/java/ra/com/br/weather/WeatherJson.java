package ra.com.br.weather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public abstract class WeatherJson {

    public static Weather fetchCurrentWeather(String urlWeatherApi){
        Weather currentWeather = new Weather();
        try {
            JSONObject root = new JSONObject(WeatherConnection.makeHttpRequest(urlWeatherApi));
            JSONArray weather = root.getJSONArray("weather");
            JSONObject main = root.getJSONObject("main");
            JSONObject sys = root.getJSONObject("sys");

            currentWeather.setLocation(root.getString("name").concat(", ").concat(sys.getString("country")));
            currentWeather.setDescription(weather.getJSONObject(Utils.ZERO).getString("main"));
            currentWeather.setIcon(weather.getJSONObject(Utils.ZERO).getString("icon"));
            currentWeather.setTemperature(main.getInt("temp"));
            currentWeather.setLastUpdate(root.getLong("dt"));
        } catch (JSONException e) {
            Log.e(Utils.LOG_TAG, "Error parsing JSON: " + e.getMessage());
        } catch (IOException e) {
            Log.e(Utils.LOG_TAG, "Error IOException: " + e.getMessage());
        }
        return currentWeather;
    }
}
