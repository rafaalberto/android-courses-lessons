package ra.com.br.weather;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Weather> {

    public static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        checkConnectionAndGetLoader();
    }

    @Override
    public Loader<Weather> onCreateLoader(int id, Bundle args) {
        return new WeatherLoader(this, Utils.URL_WEATHER_API);
    }

    @Override
    public void onLoadFinished(Loader<Weather> loader, Weather data) {
        View progressBarLoading = findViewById(R.id.progress_bar_loading);
        progressBarLoading.setVisibility(View.GONE);
        loadViews(data);
    }

    @Override
    public void onLoaderReset(Loader<Weather> loader) {
        loadViews(new Weather());
    }

    private void checkConnectionAndGetLoader() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            TextView textViewEmpty = (TextView) findViewById(R.id.text_view_empty);
            textViewEmpty.setText(R.string.no_internet_connection);
        }
    }

    private void loadViews(Weather currentWeather) {
        TextView textViewLocation = (TextView) findViewById(R.id.text_view_location);
        textViewLocation.setText(currentWeather.getLocation());

        TextView textViewDescription = (TextView) findViewById(R.id.text_view_description);
        textViewDescription.setText(currentWeather.getDescription());

        TextView textViewTemperature = (TextView) findViewById(R.id.text_view_temperature);
        textViewTemperature.setText(String.valueOf(currentWeather.getTemperature()));

        TextView textViewHumidity = (TextView) findViewById(R.id.text_view_humidity);
        textViewHumidity.setText(String.valueOf(currentWeather.getHumidity()));

        TextView textViewLastUpdate = (TextView) findViewById(R.id.text_view_last_update);
        textViewLastUpdate.setText(Utils.getFormattedDateTime(currentWeather.getLastUpdate(), Utils.FORMAT_DATE_TIME));

        TextView textViewSunrise = (TextView) findViewById(R.id.text_view_sunrise);
        textViewSunrise.setText(Utils.getFormattedDateTime(currentWeather.getSunrise(), Utils.FORMAT_TIME));

        TextView textViewSunset = (TextView) findViewById(R.id.text_view_sunset);
        textViewSunset.setText(Utils.getFormattedDateTime(currentWeather.getSunset(), Utils.FORMAT_TIME));
    }
}
