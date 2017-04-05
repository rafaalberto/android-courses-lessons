package ra.com.br.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final int RESOURCE = 0;
    private static final String FORMAT_DATE = "LLL dd, yyyy";
    private static final String FORMAT_TIME = "h:mm a";
    private static final String FORMAT_MAGNITUDE = "0.0";
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
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
        textViewMagnitude.setText(formatMagnitude(earthquake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) textViewMagnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = earthquake.getLocation();
        String offsetLocation;
        String primaryLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String parts[] = originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation = parts[0].concat(LOCATION_SEPARATOR);
            primaryLocation = parts[1];
        } else {
            offsetLocation = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView textViewOffsetLocation = (TextView) listItemView.findViewById(R.id.text_view_offset_location);
        textViewOffsetLocation.setText(offsetLocation);

        TextView textViewPrimaryLocation = (TextView) listItemView.findViewById(R.id.text_view_primary_location);
        textViewPrimaryLocation.setText(primaryLocation);

        Date date = new Date(earthquake.getTimeInMilliseconds());

        TextView textViewDate = (TextView) listItemView.findViewById(R.id.text_view_date);
        textViewDate.setText(getFormattedDateTime(date, FORMAT_DATE));

        TextView textViewTime = (TextView) listItemView.findViewById(R.id.text_view_time);
        textViewTime.setText(getFormattedDateTime(date, FORMAT_TIME));

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }

    private String getFormattedDateTime(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    private String formatMagnitude(double magnitude) {
        return new DecimalFormat(FORMAT_MAGNITUDE).format(magnitude);
    }
}
