package ra.com.br.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake("San Francisco", "7.2", "Feb 2, 2016"));
        earthquakes.add(new Earthquake("London", "6.1", "July 20, 2015"));
        earthquakes.add(new Earthquake("Tokyo", "3.9", "Nov 10, 2014"));
        earthquakes.add(new Earthquake("Mexico City", "5.4", "May 3, 2014"));
        earthquakes.add(new Earthquake("Moscow", "2.8", "Jan 31, 2013"));
        earthquakes.add(new Earthquake("Rio de Janeiro", "4.9", "Aug 19, 2012"));
        earthquakes.add(new Earthquake("Paris", "1.6", "Oct 31, 2011"));

        ListView listViewEathquakes = (ListView) findViewById(R.id.list_view_earthquake);
        listViewEathquakes.setAdapter(new EarthquakeAdapter(this, earthquakes));
    }
}
