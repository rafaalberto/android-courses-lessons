package br.com.todolist.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import static br.com.todolist.data.TaskContract.TaskEntry.CONTENT_URI;
import static br.com.todolist.data.TaskContract.TaskEntry.TABLE_NAME;

public class TaskContentProvider extends ContentProvider {

    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS, TASKS);
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.PATH_TASKS + "/#", TASK_WITH_ID);
        return uriMatcher;
    }

    private TaskDbHelper taskDbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        taskDbHelper = new TaskDbHelper(context);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = taskDbHelper.getReadableDatabase();
        int match = uriMatcher.match(uri);

        Cursor returnedCursor = null;
        switch (match) {
            case TASKS:
                returnedCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case TASK_WITH_ID:
                String id = uri.getPathSegments().get(1);
                selection = "_id=?";
                selectionArgs = new String[]{id};
                returnedCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
        }
        returnedCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnedCursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);

        Uri returnedUri = null;

        switch (match) {
            case TASKS:
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnedUri = ContentUris.withAppendedId(CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnedUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int tasksDeleted = 0;

        switch (match) {
            case TASK_WITH_ID:
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
        }

        if(tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = taskDbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        int tasksUpdated = 0;
        switch (match) {
            case TASK_WITH_ID:
                String id = uri.getPathSegments().get(1);
                tasksUpdated = db.update(TABLE_NAME, values, "_id=?", new String[]{id});
                break;
        }
        if(tasksUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return tasksUpdated;
    }
}
