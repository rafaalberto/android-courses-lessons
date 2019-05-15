package br.com.architecture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "br.com.architecture.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "br.com.architecture.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "br.com.architecture.EXTRA_PRIORITY";

    public static final int PRIORITY_MIN_VALUE = 1;
    public static final int PRIORITY_MAX_VALUE = 10;

    EditText editTextTitle;
    EditText editTextDescription;
    NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);

        numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(PRIORITY_MIN_VALUE);
        numberPickerPriority.setMaxValue(PRIORITY_MAX_VALUE);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intentData = new Intent();
        intentData.putExtra(EXTRA_TITLE, title);
        intentData.putExtra(EXTRA_DESCRIPTION, description);
        intentData.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, intentData);
        finish();
    }
}
