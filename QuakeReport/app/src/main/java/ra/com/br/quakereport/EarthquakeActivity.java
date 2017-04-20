package ra.com.br.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final int EARTHQUAKE_LOADER_ID = 1;
    private static final int ZERO = 0;
    private static final String URL_USGS_EARTHQUAKE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=10";

    private EarthquakeAdapter earthquakeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        final ListView listViewEathquake = (ListView) findViewById(R.id.list_view_earthquake);
        loadListViewEarthquake(listViewEathquake);
        checkConnectionAndGetLoader(listViewEathquake);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_action_settings){
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this, URL_USGS_EARTHQUAKE);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        View progressBarLoading = findViewById(R.id.progress_bar_loading);
        progressBarLoading.setVisibility(View.GONE);

        earthquakeAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            earthquakeAdapter.addAll(earthquakes);
        } else {
            TextView textViewEmpty = (TextView) findViewById(R.id.text_view_empty);
            textViewEmpty.setText(R.string.no_earthquakes_found);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        earthquakeAdapter.clear();
    }

    private void loadListViewEarthquake(final ListView listViewEathquake) {
        earthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        listViewEathquake.setAdapter(earthquakeAdapter);

        listViewEathquake.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = earthquakeAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(earthquake.getUrl()));
                startActivity(intent);
            }
        });

        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        checkConnectionAndGetLoader(listViewEathquake);
                        swipeRefresh.setRefreshing(false);
                    }
                }
        );

        listViewEathquake.setOnScrollListener(new AbsListView.OnScrollListener() {
            private boolean scrollEnabled;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition = (listViewEathquake == null || listViewEathquake.getChildCount() == ZERO) ?
                        ZERO : listViewEathquake.getChildAt(ZERO).getTop();

                boolean newScrollEnabled = (firstVisibleItem == ZERO && topRowVerticalPosition >= ZERO) ? true : false;

                if (null != swipeRefresh && scrollEnabled != newScrollEnabled) {
                    swipeRefresh.setEnabled(newScrollEnabled);
                    scrollEnabled = newScrollEnabled;
                }
            }
        });
    }

    private void checkConnectionAndGetLoader(ListView listViewEarthquake) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            earthquakeAdapter.clear();
            View progressBarLoading = findViewById(R.id.progress_bar_loading);
            progressBarLoading.setVisibility(View.GONE);

            TextView textViewEmpty = (TextView) findViewById(R.id.text_view_empty);
            textViewEmpty.setText(R.string.no_internet_connection);
            listViewEarthquake.setEmptyView(textViewEmpty);
        }
    }
}
