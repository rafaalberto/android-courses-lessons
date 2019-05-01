package br.com.waitlist.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.waitlist.R;

import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME;
import static br.com.waitlist.data.WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestListAdapterViewHolder> {

    private Context context;
    private Cursor cursor;

    public GuestListAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public GuestListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {
        View listItemView = LayoutInflater.from(context)
                .inflate(R.layout.list_item_guest, viewGroup, false);
        return new GuestListAdapterViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapterViewHolder guestListAdapterViewHolder, int index) {
        if(!cursor.moveToPosition(index)) return;
        String name = cursor.getString(cursor.getColumnIndex(COLUMN_GUEST_NAME));
        int partySize = cursor.getInt(cursor.getColumnIndex(COLUMN_PARTY_SIZE));

        guestListAdapterViewHolder.textViewName.setText(name);
        guestListAdapterViewHolder.textViewPartySize.setText(String.valueOf(partySize));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if(newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    public class GuestListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPartySize;

        public GuestListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPartySize = itemView.findViewById(R.id.text_view_party_size);
        }
    }

}
