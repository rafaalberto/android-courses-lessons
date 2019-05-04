package br.com.todolist;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.com.todolist.data.TaskContract;

import static br.com.todolist.data.TaskContract.*;
import static br.com.todolist.data.TaskContract.TaskEntry.*;

public class AddTaskActivity extends AppCompatActivity {

    int mPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }

    public void onClickAddTask(View view) {
        String description = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (description.length() == 0) return;

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRIORITY, mPriority);

        Uri uri = getContentResolver().insert(CONTENT_URI, contentValues);
        if(uri != null) {
            Toast.makeText(getBaseContext(), "Task adicionada com sucesso!", Toast.LENGTH_SHORT).show();
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
