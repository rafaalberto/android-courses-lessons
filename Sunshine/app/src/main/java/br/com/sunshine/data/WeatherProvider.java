package br.com.sunshine.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;

import static br.com.sunshine.data.WeatherContract.CONTENT_AUTHORITY;
import static br.com.sunshine.data.WeatherContract.PATH_WEATHER;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.COLUMN_DATE;
import static br.com.sunshine.data.WeatherContract.WeatherEntry.TABLE_NAME;

public class WeatherProvider extends ContentProvider {

    public static final int CODE_WEATHER = 100;
    public static final int CODE_WEATHER_WITH_DATE = 101;

    public static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, PATH_WEATHER, CODE_WEATHER);
        uriMatcher.addURI(authority, PATH_WEATHER + "/#", CODE_WEATHER_WITH_DATE);
        return uriMatcher;
    }

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private WeatherDbHelper weatherDbHelper;

    @Override
    public boolean onCreate() {
        weatherDbHelper = new WeatherDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @NonNull String[] projection, @NonNull String selection, @NonNull String[] selectionArgs, @NonNull String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case CODE_WEATHER_WITH_DATE: {
                String normalizedUtcDateString = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{normalizedUtcDateString};
                cursor = weatherDbHelper.getReadableDatabase().query(
                        TABLE_NAME,
                        projection,
                        COLUMN_DATE + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);
                break;
            }
            case CODE_WEATHER: {
                cursor = weatherDbHelper.getReadableDatabase().query(
                        TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            }
            default: throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase database = weatherDbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case CODE_WEATHER:
                database.beginTransaction();
                int rowsInserted = 0;
                try {
                    for(ContentValues value: values) {
                        long id = database.insert(TABLE_NAME, null, value);
                        if(id != -1) {
                            rowsInserted ++;
                        }
                    }
                    database.setTransactionSuccessful();
                } finally {
                    database.endTransaction();
                }

                if(rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return rowsInserted;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @NonNull String selection, @NonNull String[] selectionArgs) {
        int numRowsDeleted;
        if (null == selection) selection = "1";
        switch (uriMatcher.match(uri)) {
            case CODE_WEATHER:
                numRowsDeleted = weatherDbHelper.getWritableDatabase().delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @NonNull ContentValues values, @NonNull String selection, @NonNull String[] selectionArgs) {
        return 0;
    }
}
