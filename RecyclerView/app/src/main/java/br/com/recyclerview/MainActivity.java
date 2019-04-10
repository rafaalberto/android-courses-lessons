package br.com.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        countryAdapter = new CountryAdapter();
        recyclerView.setAdapter(countryAdapter);

        List<String> countries = new ArrayList<>();
        countries.add("Brazil");
        countries.add("United States");
        countries.add("Germany");
        countries.add("Portugal");
        countries.add("Canada");
        countries.add("Chile");
        countries.add("France");
        countries.add("England");
        countries.add("China");
        countries.add("Japan");
        countries.add("Colombia");
        countries.add("Italy");
        countries.add("Spain");
        countries.add("Mexico");
        countries.add("Russia");
        countries.add("South Africa");
        countries.add("Australia");

        Collections.sort(countries, (item1, item2) -> item1.compareTo(item2));

        countryAdapter.setCities(countries);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
