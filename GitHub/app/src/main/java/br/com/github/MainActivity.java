package br.com.github;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private TextView textViewUrlDisplay;
    private TextView textViewSearchResults;
    private TextView textViewErrorMessage;
    private ProgressBar progressBarLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = (EditText) findViewById(R.id.edit_text_search);
        textViewUrlDisplay = (TextView) findViewById(R.id.text_view_url_display);
        textViewSearchResults = (TextView) findViewById(R.id.text_view_search_results);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_message);
        progressBarLoading = (ProgressBar) findViewById(R.id.progress_bar_loading);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtil.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String result) {
            progressBarLoading.setVisibility(View.INVISIBLE);
            if (result != null && !result.equals("")) {
                showJsonDataView();
                textViewSearchResults.setText(result);
            }else{
                showErrorMessage();
            }
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
            makeGitHubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeGitHubSearchQuery() {
        String githubQuery = editTextSearch.getText().toString();
        URL gitHubSearchUrl = NetworkUtil.buildUrl(githubQuery);
        textViewUrlDisplay.setText(gitHubSearchUrl.toString());
        new GithubQueryTask().execute(gitHubSearchUrl);
    }

    private void showJsonDataView() {
        textViewErrorMessage.setVisibility(View.INVISIBLE);
        textViewSearchResults.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        textViewSearchResults.setVisibility(View.INVISIBLE);
        textViewErrorMessage.setVisibility(View.VISIBLE);
    }
}
