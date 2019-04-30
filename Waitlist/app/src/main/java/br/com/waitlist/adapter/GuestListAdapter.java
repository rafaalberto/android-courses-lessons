package br.com.waitlist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.waitlist.R;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestViewHolder> {

    private Context context;

    public GuestListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.guest_list_item, viewGroup, false);
        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder guestViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GuestViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewPartySize;

        public GuestViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPartySize = itemView.findViewById(R.id.text_view_party_size);
        }
    }

}
