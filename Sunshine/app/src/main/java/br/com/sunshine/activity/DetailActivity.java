package br.com.sunshine.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.sunshine.R;
import br.com.sunshine.utilities.SunshineDateUtils;
import br.com.sunshine.utilities.SunshineWeatherUtils;

import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_DATE;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_DEGREES;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_HUMIDITY;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_MAX_TEMP;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_MIN_TEMP;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_PRESSURE;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_WEATHER_ID;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_WIND_SPEED;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_DETAIL_LOADER = 353;

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    public static final String[] WEATHER_DETAIL_PROJECTION = {
            COLUMN_DATE,
            COLUMN_MAX_TEMP,
            COLUMN_MIN_TEMP,
            COLUMN_HUMIDITY,
            COLUMN_PRESSURE,
            COLUMN_WIND_SPEED,
            COLUMN_DEGREES,
            COLUMN_WEATHER_ID
    };

    public static final int INDEX_WEATHER_DATE = 0;
    public static final int INDEX_WEATHER_MAX_TEMP = 1;
    public static final int INDEX_WEATHER_MIN_TEMP = 2;
    public static final int INDEX_WEATHER_HUMIDITY = 3;
    public static final int INDEX_WEATHER_PRESSURE = 4;
    public static final int INDEX_WEATHER_WIND_SPEED = 5;
    public static final int INDEX_WEATHER_DEGREES = 6;
    public static final int INDEX_WEATHER_CONDITION_ID = 7;

    private TextView textViewDate;
    private TextView textViewDescription;
    private TextView textViewHighTemperature;
    private TextView textViewLowTemperature;
    private TextView textViewHumidity;
    private TextView textViewWind;
    private TextView textViewPressure;

    private Uri uri;
    private String forecastSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewDate = findViewById(R.id.text_view_date);
        textViewDescription = findViewById(R.id.text_view_weather_description);
        textViewHighTemperature = findViewById(R.id.text_view_high_temperature);
        textViewLowTemperature = findViewById(R.id.text_view_low_temperature);
        textViewHumidity = findViewById(R.id.text_view_humidity);
        textViewWind = findViewById(R.id.text_view_wind);
        textViewPressure = findViewById(R.id.text_view_pressure);

        uri = getIntent().getData();

        LoaderManager.getInstance(this).initLoader(ID_DETAIL_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, @Nullable Bundle bundle) {
        switch (loaderId) {
            case ID_DETAIL_LOADER:
                return new CursorLoader(this,
                        uri,
                        WEATHER_DETAIL_PROJECTION,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader not implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        boolean cursorHasValidData = false;
        if(cursor != null && cursor.moveToFirst()) {
            cursorHasValidData = true;
        }

        if(!cursorHasValidData) {
            return;
        }

        long localDateMidnightGmt = cursor.getLong(INDEX_WEATHER_DATE);
        String dateText = SunshineDateUtils.getFriendlyDateString(this, localDateMidnightGmt, true);
        textViewDate.setText(dateText);

        int weatherId = cursor.getInt(INDEX_WEATHER_CONDITION_ID);
        String description = SunshineWeatherUtils.getStringForWeatherCondition(this, weatherId);
        textViewDescription.setText(description);

        double highInCelsius = cursor.getDouble(INDEX_WEATHER_MAX_TEMP);
        String high = SunshineWeatherUtils.formatTemperature(this, highInCelsius);
        textViewHighTemperature.setText(high);

        double lowInCelsius = cursor.getDouble(INDEX_WEATHER_MIN_TEMP);
        String low = SunshineWeatherUtils.formatTemperature(this, lowInCelsius);
        textViewLowTemperature.setText(low);

        String humidity = getString(R.string.format_humidity, cursor.getFloat(INDEX_WEATHER_HUMIDITY));
        textViewHumidity.setText(humidity);

        float windSpeed = cursor.getFloat(INDEX_WEATHER_WIND_SPEED);
        float windDirection = cursor.getFloat(INDEX_WEATHER_DEGREES);
        String windString = SunshineWeatherUtils.getFormattedWind(this, windSpeed, windDirection);
        textViewWind.setText(windString);

        String pressure = getString(R.string.format_pressure, cursor.getFloat(INDEX_WEATHER_PRESSURE));
        textViewPressure.setText(pressure);

        forecastSummary = String.format("%s - %s - %s/%s", dateText, description, high, low);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForestIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if (menuItemSelected == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent createShareForestIntent() {
        return ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(forecastSummary)
                .getIntent();
    }

}
