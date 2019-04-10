package br.com.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterViewHolder> {

    private List<String> countries;

    public class CountryAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewCityItem;

        public CountryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCityItem = itemView.findViewById(R.id.text_view_city_item);
        }
    }

    @NonNull
    @Override
    public CountryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdListItem = R.layout.country_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdListItem, viewGroup, false);
        return new CountryAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapterViewHolder countryAdapterViewHolder, int i) {
        String city = this.countries.get(i);
        countryAdapterViewHolder.textViewCityItem.setText(city);
    }

    @Override
    public int getItemCount() {
        if(this.countries.size() == 0) return 0;
        return this.countries.size();
    }

    public void setCities(List<String> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

}
