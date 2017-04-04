package ra.com.br.trip;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    public static final int RESOURCE = 0;

    public CityAdapter(Activity context, ArrayList<City> cities) {
        super(context, RESOURCE, cities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_trip, parent, false);
        }

        City city = getItem(position);

        TextView textViewName = (TextView) convertView.findViewById(R.id.text_view_name);
        textViewName.setText(city.getName());

        TextView textViewCountry = (TextView) convertView.findViewById(R.id.text_view_country);
        textViewCountry.setText(city.getCountry());

        ImageView imageViewPhoto = (ImageView) convertView.findViewById(R.id.image_view_photo);
        imageViewPhoto.setImageResource(city.getIdPhoto());

        return convertView;
    }
}
