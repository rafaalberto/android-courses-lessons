package br.com.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static br.com.todolist.data.TaskContract.TaskEntry.COLUMN_PRIORITY;
import static br.com.todolist.data.TaskContract.TaskEntry.CONTENT_URI;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, CustomCursorAdapter.ListItemClickListener{

    private static final int TASK_LOADER_ID = 0;

    private CustomCursorAdapter mAdapter;
    RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setHasFixedSize(true);

        mAdapter = new CustomCursorAdapter(this, this);
        recyclerViewTasks.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int id = (int) viewHolder.itemView.getTag();
                Uri uri = CONTENT_URI;
                uri = uri.buildUpon().appendPath(String.valueOf(id)).build();
                getContentResolver().delete(uri, null, null);
                LoaderManager.getInstance(MainActivity.this).restartLoader(TASK_LOADER_ID, null, MainActivity.this);
            }
        }).attachToRecyclerView(recyclerViewTasks);

        FloatingActionButton fabButton = findViewById(R.id.foating_button);
        fabButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        LoaderManager.getInstance(this).initLoader(TASK_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoaderManager.getInstance(this).restartLoader(TASK_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {

        return new AsyncTaskLoader<Cursor>(this) {

            Cursor mTaskData = null;

            @Override
            protected void onStartLoading() {
                if (mTaskData != null) {
                    deliverResult(mTaskData);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public Cursor loadInBackground() {
                try {
                    return getContentResolver().query(CONTENT_URI,
                            null,
                            null,
                            null,
                            COLUMN_PRIORITY);
                } catch (Exception e) {
                    Log.e("error-main-activity", "Failed to load data");
                    return null;
                }
            }

            @Override
            public void deliverResult(@Nullable Cursor data) {
                mTaskData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(int id) {
        Uri uri = CONTENT_URI;
        uri = uri.buildUpon().appendPath(String.valueOf(id)).build();
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }
}
