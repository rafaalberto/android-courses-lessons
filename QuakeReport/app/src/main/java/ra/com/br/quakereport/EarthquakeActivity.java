package ra.com.br.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    private static final int ZERO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();
        ListView listViewEathquakes = (ListView) findViewById(R.id.list_view_earthquake);
        listViewEathquakes.setAdapter(new EarthquakeAdapter(this, earthquakes));
    }
}
