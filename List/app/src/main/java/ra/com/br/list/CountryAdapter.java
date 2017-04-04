package ra.com.br.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Country> {

    private static final int ZERO = 0;

    public CountryAdapter(Activity context, ArrayList<Country> countries) {
        super(context, ZERO, countries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_country, parent, false);
        }

        Country currentCountry = getItem(position);

        TextView textViewName = (TextView) listItemView.findViewById(R.id.text_view_name);
        textViewName.setText(currentCountry.getName());

        TextView textViewCapital = (TextView) listItemView.findViewById(R.id.text_view_capital);
        textViewCapital.setText(currentCountry.getCapital());

        ImageView imageViewFlag = (ImageView) listItemView.findViewById(R.id.image_view_flag);
        imageViewFlag.setImageResource(currentCountry.getIdImageFlag());

        return listItemView;
    }
}
