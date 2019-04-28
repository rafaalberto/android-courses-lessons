package br.com.sunshine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.sunshine.R;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

    private String[] weatherData;

    private final ForecastAdapterOnClickHandler onClickHandler;

    public interface ForecastAdapterOnClickHandler {
        void onClick(String weatherItem);
    }

    public ForecastAdapter(ForecastAdapterOnClickHandler onClickHandler) {
        this.onClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public ForecastAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdListItem = R.layout.forecast_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdListItem, viewGroup, false);
        return new ForecastAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapterViewHolder forecastAdapterViewHolder, int position) {
        String weatherItem = this.weatherData[position];
        forecastAdapterViewHolder.weatherTextView.setText(weatherItem);
    }

    @Override
    public int getItemCount() {
        if(this.weatherData == null) return 0;
        return this.weatherData.length;
    }

    public void setWeatherData(String[] weatherData) {
        this.weatherData = weatherData;
        notifyDataSetChanged();
    }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView weatherTextView;

        public ForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherTextView = itemView.findViewById(R.id.text_view_weather_data);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String weatherItem = weatherData[adapterPosition];
            onClickHandler.onClick(weatherItem);
        }
    }

}
