package br.com.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryAdapterViewHolder> implements Filterable {

    private List<String> countries;
    private List<String> countriesFull;
    public List<String> filteredList;

    public class CountryAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewCityItem;

        public CountryAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCityItem = itemView.findViewById(R.id.text_view_city_item);
        }
    }

    CountryAdapter(List<String> countries) {
        this.countries = countries;
        countriesFull = new ArrayList<>(countries);
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
    public void onBindViewHolder(@NonNull CountryAdapterViewHolder countryAdapterViewHolder, int index) {
        String city = countries.get(index);
        countryAdapterViewHolder.textViewCityItem.setText(city);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public Filter getFilter() {
        return countriesFilter;
    }

    private Filter countriesFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(countriesFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for(String item : countriesFull) {
                    if(item.toLowerCase().contains(filter)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
