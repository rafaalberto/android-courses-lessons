package br.com.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private TextView textViewUrlDisplay;
    private TextView textViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        textViewUrlDisplay = (TextView) findViewById(R.id.text_view_url_display);
        textViewSearchResults = (TextView) findViewById(R.id.text_view_search_results);

        String[] repositories = {
                "Java",
                "Android",
                "Mobile",
                "VueJS",
                "Node",
                "IOS",
                "Kanban",
                "Scrum",
                "TDD",
                "AWS",
                "Unit Tests",
                "English",
                "Clean Code",
                "Refactoring"
        };

        for(String repository : repositories) {
            textViewSearchResults.append(repository + "\n\n");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();
        if(menuItemSelected == R.id.action_menu_search) {
            Toast.makeText(MainActivity.this, "Search clicked!", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
