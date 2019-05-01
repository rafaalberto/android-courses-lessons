package br.com.waitlist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.waitlist.R;
import br.com.waitlist.data.Waitlist;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.GuestListAdapterViewHolder> {

    private List<Waitlist> waitlists;

    public GuestListAdapter(List<Waitlist> waitlists) {
        this.waitlists = waitlists;
    }

    @NonNull
    @Override
    public GuestListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View listItemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_guest, viewGroup, false);
        return new GuestListAdapterViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestListAdapterViewHolder guestListAdapterViewHolder, int i) {
        Waitlist waitlist = waitlists.get(i);
        guestListAdapterViewHolder.textViewName.setText(waitlist.getPersonName());
        guestListAdapterViewHolder.textViewPartySize.setText(String.valueOf(waitlist.getPartyCount()));
    }

    @Override
    public int getItemCount() {
        return waitlists.size();
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
