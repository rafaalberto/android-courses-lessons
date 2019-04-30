package br.com.waitlist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.waitlist.R;
import br.com.waitlist.adapter.GuestListAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewAllGuests = findViewById(R.id.recycler_view_all_guests);
        recyclerViewAllGuests.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAllGuests.setAdapter(new GuestListAdapter(this));
    }
}
