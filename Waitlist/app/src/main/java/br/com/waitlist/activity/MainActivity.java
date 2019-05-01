package br.com.waitlist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.waitlist.R;
import br.com.waitlist.adapter.GuestListAdapter;
import br.com.waitlist.data.Waitlist;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAllGuests;
    private EditText editTextPersonName;
    private EditText editTextPartySize;
    private Button buttonAddToWaitlist;

    private List<Waitlist> waitlists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        waitlists = new ArrayList<>();

        recyclerViewAllGuests = findViewById(R.id.recycler_view_all_guests);
        recyclerViewAllGuests.setHasFixedSize(true);
        recyclerViewAllGuests.setLayoutManager(new LinearLayoutManager(this));
        final GuestListAdapter guestListAdapter = new GuestListAdapter(waitlists);
        recyclerViewAllGuests.setAdapter(guestListAdapter);

        editTextPersonName = findViewById(R.id.edit_text_person_name);
        editTextPartySize = findViewById(R.id.edit_text_party_count);

        buttonAddToWaitlist = findViewById(R.id.button_add_to_waitlist);

        buttonAddToWaitlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waitlist waitlist = new Waitlist(editTextPersonName.getText().toString(),
                        Integer.valueOf(editTextPartySize.getText().toString()));
                waitlists.add(waitlist);
                guestListAdapter.notifyDataSetChanged();
                editTextPersonName.setText("");
                editTextPartySize.setText("");
                editTextPersonName.requestFocus();
            }
        });
    }

}
