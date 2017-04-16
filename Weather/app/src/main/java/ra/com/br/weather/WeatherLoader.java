package ra.com.br.weather;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class WeatherLoader extends AsyncTaskLoader<Weather> {

    private String urlWeatherApi;

    public WeatherLoader(Context context, String urlWeatherApi) {
        super(context);
        this.urlWeatherApi = urlWeatherApi;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Weather loadInBackground() {
        return WeatherJson.fetchCurrentWeather(urlWeatherApi);
    }
}
