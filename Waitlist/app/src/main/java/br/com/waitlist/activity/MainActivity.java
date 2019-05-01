package br.com.waitlist.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.waitlist.R;
import br.com.waitlist.adapter.GuestListAdapter;
import br.com.waitlist.data.WaitlistContract;
import br.com.waitlist.data.WaitlistDbHelper;

import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME;
import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE;
import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP;
import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPersonName;
    private EditText editTextPartySize;
    private Button buttonAdd;
    private RecyclerView recyclerViewAllGuests;

    private SQLiteDatabase db;

    private GuestListAdapter guestListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPersonName = findViewById(R.id.edit_text_person_name);
        editTextPartySize = findViewById(R.id.edit_text_party_size);
        buttonAdd = findViewById(R.id.button_add_to_waitlist);

        recyclerViewAllGuests = findViewById(R.id.recycler_view_all_guests);
        recyclerViewAllGuests.setHasFixedSize(true);
        recyclerViewAllGuests.setLayoutManager(new LinearLayoutManager(this));

        WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);
        db = dbHelper.getWritableDatabase();
        Cursor cursor = getAllGuests();

        guestListAdapter = new GuestListAdapter(this, cursor);
        recyclerViewAllGuests.setAdapter(guestListAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWaitlist();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                long id = (long) viewHolder.itemView.getTag();
                removeGuest(id);
                guestListAdapter.swapCursor(getAllGuests());
            }
        }).attachToRecyclerView(recyclerViewAllGuests);
    }

    private Cursor getAllGuests() {
        return db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_TIMESTAMP);

    }

    private void addToWaitlist() {
        if (editTextPersonName.getText().length() == 0 || editTextPartySize.getText().length() == 0) {
            return;
        }

        String personName = editTextPersonName.getText().toString();
        int partySize = Integer.parseInt(editTextPartySize.getText().toString());

        addNewGuest(personName, partySize);
        guestListAdapter.swapCursor(getAllGuests());

        editTextPersonName.clearFocus();
        editTextPersonName.getText().clear();
        editTextPartySize.getText().clear();
    }

    private long addNewGuest(String personName, int partySize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_GUEST_NAME, personName);
        contentValues.put(COLUMN_PARTY_SIZE, partySize);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    private boolean removeGuest(long id) {
        return db.delete(TABLE_NAME,
                WaitlistContract.WaitlistEntry._ID + " = " + id,
                null) > 0;
    }
}
