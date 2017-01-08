package ra.com.br.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ContinentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);
        loadViews();
    }

    private void loadViews(){
        ArrayList<String> arrayListContinents = new ArrayList<String>();
        arrayListContinents.add("America");
        arrayListContinents.add("Africa");
        arrayListContinents.add("Asia");
        arrayListContinents.add("Europa");
        arrayListContinents.add("Oceania");

        Collections.sort(arrayListContinents);

        ListView listViewContinent = (ListView) findViewById(R.id.list_view_continent);
        listViewContinent.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListContinents));
    }
}
