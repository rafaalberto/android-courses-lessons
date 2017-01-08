package ra.com.br.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class AmericaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_america);
        loadViews();
    }

    private void loadViews(){
        ArrayList<String> arrayListCountries = new ArrayList<String>();
        arrayListCountries.add("Brazil");
        arrayListCountries.add("Argentina");
        arrayListCountries.add("Uruguay");
        arrayListCountries.add("Paraguay");
        arrayListCountries.add("Colombia");
        arrayListCountries.add("Chile");
        arrayListCountries.add("Mexico");
        arrayListCountries.add("Panama");
        arrayListCountries.add("United States");
        arrayListCountries.add("Costa Rica");
        arrayListCountries.add("Canada");

        Collections.sort(arrayListCountries);
        
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListCountries);

        ListView listViewAmerica = (ListView) findViewById(R.id.list_view_america);
        listViewAmerica.setAdapter(arrayAdapter);
    }
}
