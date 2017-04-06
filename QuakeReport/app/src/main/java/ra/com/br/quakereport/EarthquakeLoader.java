package ra.com.br.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String requestUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        requestUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        return EarthquakeUtils.fetchEarthquakes(requestUrl);
    }
}
