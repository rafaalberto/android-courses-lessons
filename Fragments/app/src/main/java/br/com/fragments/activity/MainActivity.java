package br.com.fragments.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.fragments.R;
import br.com.fragments.fragment.ChatsFragment;
import br.com.fragments.fragment.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    private Button buttonChats;
    private Button buttonContacts;
    private ChatsFragment chatsFragment;
    private ContactsFragment contactsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);

        buttonChats = findViewById(R.id.buttonChats);
        buttonContacts = findViewById(R.id.buttonContacts);

        chatsFragment = new ChatsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutContent, chatsFragment);
        transaction.commit();

        buttonChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutContent, chatsFragment);
                transaction.commit();
            }
        });

        buttonContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsFragment = new ContactsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayoutContent, contactsFragment);
                transaction.commit();
            }
        });

    }
}
