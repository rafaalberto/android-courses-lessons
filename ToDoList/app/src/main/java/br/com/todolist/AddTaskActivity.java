package br.com.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.todolist.data.TaskContract;

import static br.com.todolist.data.TaskContract.*;
import static br.com.todolist.data.TaskContract.TaskEntry.*;

public class AddTaskActivity extends AppCompatActivity {

    private int id;
    private int mPriority;
    private Cursor mCursor;
    private Uri uri;

    private EditText editTextDescription;
    private RadioButton radButton1;
    private RadioButton radButton2;
    private RadioButton radButton3;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;

        editTextDescription = findViewById(R.id.editTextTaskDescription);
        radButton1 = findViewById(R.id.radButton1);
        radButton2 = findViewById(R.id.radButton2);
        radButton3 = findViewById(R.id.radButton3);
        buttonAdd = findViewById(R.id.addButton);

        Intent intent = getIntent();
        uri = intent.getData();

        if(uri != null) {
            mCursor = getContentResolver().query(uri, null, null, null, COLUMN_PRIORITY);
            if(mCursor.moveToFirst()){
                if(mCursor != null) {
                    id = mCursor.getInt(mCursor.getColumnIndex(TaskEntry._ID));
                    String description = mCursor.getString(mCursor.getColumnIndex(TaskEntry.COLUMN_DESCRIPTION));
                    int priority = mCursor.getInt(mCursor.getColumnIndex(TaskEntry.COLUMN_PRIORITY));

                    editTextDescription.setText(description);
                    switch (priority) {
                        case 1: radButton1.setChecked(true); break;
                        case 2: radButton2.setChecked(true); break;
                        case 3: radButton3.setChecked(true); break;
                    }
                    buttonAdd.setText("Save");
                }
            }
        }

    }

    public void onClickAddTask(View view) {
        String description = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (description.length() == 0) return;

        ContentValues contentValues = new ContentValues();

        if(id > 0) {
            contentValues.put(_ID, id);
        }

        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRIORITY, mPriority);

        if(id == 0) {
            Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);
            if(uri != null) {
                Toast.makeText(getBaseContext(), "Task adicionada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }else {
            int rowsUpdated = getContentResolver().update(uri, contentValues, null, null);
            if(rowsUpdated > 0) {
                Toast.makeText(getBaseContext(), "Task atualizada com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void onPrioritySelected(View view) {
        if(((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        }else if(((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        }else if(((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }
}
