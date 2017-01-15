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
        arrayListCountries.add(new Country("Brazil", "Brasilia", R.drawable.brazil));
        arrayListCountries.add(new Country("United States", "Washington", R.drawable.united_states));
        arrayListCountries.add(new Country("Argentina", "Buenos Aires", R.drawable.argentina));
        arrayListCountries.add(new Country("Canada", "Ottawa", R.drawable.canada));
        arrayListCountries.add(new Country("Mexico", "Mexico City", R.drawable.mexico));
        arrayListCountries.add(new Country("Uruguay", "Montevideo", R.drawable.uruguay));
        arrayListCountries.add(new Country("Colombia", "Bogota", R.drawable.colombia));
        arrayListCountries.add(new Country("Portugal", "Lisbon", R.drawable.portugal));
        arrayListCountries.add(new Country("Chile", "Santiago", R.drawable.chile));
        arrayListCountries.add(new Country("Spain", "Madrid", R.drawable.spain));
        arrayListCountries.add(new Country("France", "Paris", R.drawable.france));
        arrayListCountries.add(new Country("United Kingdom", "London", R.drawable.united_kingdom));
        arrayListCountries.add(new Country("Italy", "Rome", R.drawable.italy));
        arrayListCountries.add(new Country("Germany", "Berlin", R.drawable.germany));
        arrayListCountries.add(new Country("Ireland", "Dublin", R.drawable.ireland));

        Collections.sort(arrayListCountries);

        ListView listViewCountries = (ListView) findViewById(R.id.list_view_country);
        listViewCountries.setAdapter(new CountryAdapter(this, arrayListCountries));
    }
}
