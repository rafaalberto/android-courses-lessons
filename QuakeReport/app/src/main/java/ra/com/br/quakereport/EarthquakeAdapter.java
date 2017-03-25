package ra.com.br.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public static final int RESOURCE = 0;

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, RESOURCE, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_earthquake, parent, false);
        }
        Earthquake earthquake = getItem(position);

        TextView textViewMagnitude = (TextView) listItemView.findViewById(R.id.text_view_magnitude);
        textViewMagnitude.setText(earthquake.getMagnitude());

        TextView textViewLocation = (TextView) listItemView.findViewById(R.id.text_view_location);
        textViewLocation.setText(earthquake.getLocation());

        TextView textViewDate = (TextView) listItemView.findViewById(R.id.text_view_date);
        textViewDate.setText(earthquake.getDate());

        return listItemView;
    }
}
