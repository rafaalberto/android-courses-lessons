package ra.com.br.didyoufeelit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
    }

    private void updateUI(Event earthquake) {
        TextView titleTextView = (TextView) findViewById(R.id.text_view_title);
        titleTextView.setText(earthquake.title);

        TextView tsunamiTextView = (TextView) findViewById(R.id.text_view_number_of_people);
        tsunamiTextView.setText(getString(R.string.number_of_people_felt_it, earthquake.numberOfPeople));

        TextView magnitudeTextView = (TextView) findViewById(R.id.text_view_perceived_magnitude);
        magnitudeTextView.setText(earthquake.perceivedStrength);
    }

    private class EarthquakeAsyncTask extends AsyncTask<String, Void, Event> {

        @Override
        protected Event doInBackground(String... urls) {
            Event result = Utils.fetchEarthquakeData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(Event result) {
            updateUI(result);
        }
    }
}
