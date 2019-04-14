package br.com.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        textViewDisplay = (TextView) findViewById(R.id.text_view_display);
        Intent intentStartedThisActivity = getIntent();
        if(intentStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            String textEntered = intentStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            textViewDisplay.setText(textEntered);
        }
    }
}
