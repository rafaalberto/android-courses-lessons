package ra.com.br.trip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("Rio de Janeiro", "Brazil", R.drawable.rio));
        cities.add(new City("São Paulo", "Brazil", R.drawable.sao_paulo));
        cities.add(new City("Buenos Aires", "Argentina", R.drawable.buenos_aires));
        cities.add(new City("New York", "United States", R.drawable.new_york));
        cities.add(new City("Mexico City", "Mexico", R.drawable.mexico_city));
        cities.add(new City("Toronto", "Canada", R.drawable.toronto));
        cities.add(new City("Miami", "United States", R.drawable.miami));
        cities.add(new City("Vancouver", "Canada", R.drawable.vancouver));

        ListView listViewTrip = (ListView) findViewById(R.id.list_view_trip);
        listViewTrip.setAdapter(new CityAdapter(this, cities));
    }
}
