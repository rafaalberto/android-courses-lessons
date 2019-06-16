package br.com.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.net.URL;

import br.com.sunshine.data.WeatherContract;
import br.com.sunshine.utilities.NetworkUtils;
import br.com.sunshine.utilities.OpenWeatherJsonUtils;

public class SunshineSyncTask {

    synchronized public static void syncWeather(Context context) {
        try {
            Log.i("sunshine", "Data sync");
            URL weatherRequestUrl = NetworkUtils.getUrl(context);
            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
            ContentValues[] weatherValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);
            if (weatherValues != null && weatherValues.length != 0) {
                ContentResolver sunshineContentResolver = context.getContentResolver();
                sunshineContentResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null);
                sunshineContentResolver.bulkInsert(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherValues);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
