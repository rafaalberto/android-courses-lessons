package br.com.todolist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static br.com.todolist.data.TaskContract.TaskEntry;

public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.TaskViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private ListItemClickListener onClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    CustomCursorAdapter(Context mContext, ListItemClickListener listItemClickListener) {
        this.mContext = mContext;
        this.onClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(mCursor.getColumnIndex(TaskEntry._ID));
        String description = mCursor.getString(mCursor.getColumnIndex(TaskEntry.COLUMN_DESCRIPTION));
        int priority = mCursor.getInt(mCursor.getColumnIndex(TaskEntry.COLUMN_PRIORITY));

        taskViewHolder.itemView.setTag(id);
        taskViewHolder.taskDescriptionView.setText(description);
        taskViewHolder.priorityView.setText(String.valueOf(priority));

        GradientDrawable priorityCircle = (GradientDrawable) taskViewHolder.priorityView.getBackground();
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }

    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch(priority) {
            case 1: priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2: priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3: priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default: break;
        }
        return priorityColor;
    }

    @Override
    public int getItemCount() {
        if(mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor cursor) {
        if(mCursor == cursor) {
            return null;
        }
        Cursor temp = mCursor;
        this.mCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView taskDescriptionView;
        TextView priorityView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            priorityView = itemView.findViewById(R.id.priorityTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mCursor.moveToPosition(getAdapterPosition());
            int id = mCursor.getInt(mCursor.getColumnIndex(TaskEntry._ID));
            onClickListener.onListItemClick(id);
        }
    }

}
