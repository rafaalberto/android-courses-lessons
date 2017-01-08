package ra.com.br.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class CountryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        loadViews();
    }

    private void loadViews() {
        ArrayList<Country> arrayListCountries = new ArrayList<Country>();
        arrayListCountries.add(new Country("Brazil", "Brasilia"));
        arrayListCountries.add(new Country("United States", "Washington"));
        arrayListCountries.add(new Country("Argentina", "Buenos Aires"));
        arrayListCountries.add(new Country("Canada", "Ottawa"));
        arrayListCountries.add(new Country("Mexico", "Mexico City"));
        arrayListCountries.add(new Country("Uruguay", "Montevideo"));
        arrayListCountries.add(new Country("Colombia", "Bogota"));
        arrayListCountries.add(new Country("Portugal", "Lisbon"));
        arrayListCountries.add(new Country("Chile", "Santiago"));
        arrayListCountries.add(new Country("Spain", "Madrid"));
        arrayListCountries.add(new Country("France", "Paris"));
        arrayListCountries.add(new Country("United Kingdom", "London"));
        arrayListCountries.add(new Country("Italy", "Rome"));
        arrayListCountries.add(new Country("Germany", "Berlin"));
        arrayListCountries.add(new Country("Ireland", "Dublin"));

        Collections.sort(arrayListCountries);

        ListView listViewCountries = (ListView) findViewById(R.id.list_view_country);
        listViewCountries.setAdapter(new CountryAdapter(this, arrayListCountries));
    }
}
