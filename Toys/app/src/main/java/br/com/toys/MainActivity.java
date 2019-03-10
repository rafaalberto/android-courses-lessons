package br.com.toys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final int NUMBER_OF_ITEMS = 100;
    private GreenAdapter greenAdapter;
    private RecyclerView numberListView;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberListView = (RecyclerView) findViewById(R.id.recycle_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        numberListView.setLayoutManager(layoutManager);
        numberListView.setHasFixedSize(true);

        greenAdapter = new GreenAdapter(NUMBER_OF_ITEMS, this);
        numberListView.setAdapter(greenAdapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if(toast != null) {
            toast.cancel();
        }

        String toastMessage = "Item #" + clickedItemIndex + " clicked";
        toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
        toast.show();
    }
}
