package br.com.sunshine;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import br.com.sunshine.data.SunshinePreferences;
import br.com.sunshine.utilities.NetworkUtils;
import br.com.sunshine.utilities.OpenWeatherJsonUtils;

public class MainActivity extends AppCompatActivity {

    TextView textViewWeatherData;
    TextView textViewErrorMessage;
    ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        textViewWeatherData = (TextView) findViewById(R.id.text_view_weather_data);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_message);
        progressBarLoading = (ProgressBar) findViewById(R.id.progressbar_loading);
        loadWeatherData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if (menuItemSelected == R.id.action_menu_refresh) {
            textViewWeatherData.setText("");
            loadWeatherData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadWeatherData() {
        String location = SunshinePreferences.getPreferredWeatherLocation(this);
        new FetchWeatherTask().execute(location);
    }

    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(location);

            try {
                String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
                String[] simpleJsonWeatherData = OpenWeatherJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
                return simpleJsonWeatherData;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] weatherData) {
            progressBarLoading.setVisibility(View.INVISIBLE);
            if (weatherData != null) {
                for (String weatherString : weatherData) {
                    textViewWeatherData.append((weatherString) + "\n\n");
                }
            } else {
                textViewWeatherData.setVisibility(View.INVISIBLE);
                textViewErrorMessage.setVisibility(View.VISIBLE);
            }
        }
    }
}
