package br.com.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCities;
    private String[] cities = {
            "New York", "Los Angeles", "Las Vegas", "San Francisco",
            "Washington", "Paris", "London", "Manchester", "Lisbon",
            "Tokyo", "São Paulo", "Rio de Janeiro", "Porto", "Rome", "Madrid"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCities = findViewById(R.id.list_view_cities);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1
        );

        arrayAdapter.addAll(cities);

        listViewCities.setAdapter(arrayAdapter);

        listViewCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = listViewCities.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), value, LENGTH_SHORT).show();
            }
        });
    }
}
