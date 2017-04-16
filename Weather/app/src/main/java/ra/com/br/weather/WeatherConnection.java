package ra.com.br.weather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class WeatherConnection {

    public static final int CONNECTION_STATUS_OK = 200;
    public static final int READ_TIMEOUT = 10000;
    public static final int CONNECT_TIMEOUT = 15000;

    protected static String makeHttpRequest(String requestURL) throws IOException {
        URL url = createURL(requestURL);
        if (url == null) {
            return "";
        }
        return createHttpConnection(url);
    }

    private static URL createURL(String requestURL) {
        URL url = null;
        try {
            return new URL(requestURL);
        } catch (MalformedURLException e) {
            Log.e(Utils.LOG_TAG, "Error with creating URL", e);
        }
        return null;
    }

    private static String createHttpConnection(URL url) throws IOException {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String response = "";
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(CONNECT_TIMEOUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == CONNECTION_STATUS_OK) {
                inputStream = httpURLConnection.getInputStream();
                response = readFromStream(inputStream);
            } else {
                Log.e(Utils.LOG_TAG, "Error response code: " + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(Utils.LOG_TAG, "Error: " + e.getMessage());
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder response = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                response.append(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            inputStreamReader.close();
        }
        inputStream.close();
        return response.toString();
    }
}
