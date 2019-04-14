package br.com.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEntry;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEntry = (EditText) findViewById(R.id.edit_text_entry);
        button = (Button) findViewById(R.id.button_click);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEntered = editTextEntry.getText().toString();
                Intent childActivityIntent = new Intent(MainActivity.this, ChildActivity.class);
                childActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
                startActivity(childActivityIntent);
            }
        });
    }
}
