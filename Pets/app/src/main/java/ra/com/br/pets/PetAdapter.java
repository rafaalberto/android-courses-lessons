package ra.com.br.pets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PetAdapter extends ArrayAdapter<Pet> {

    public static final int RESOURCE = 0;

    public PetAdapter(Context context, List<Pet> pets) {
        super(context, RESOURCE, pets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_pet, parent, false);
        }
        Pet pet = getItem(position);

        TextView textViewName = (TextView) convertView.findViewById(R.id.text_view_name);
        textViewName.setText(pet.getName());

        TextView textViewBreed = (TextView) convertView.findViewById(R.id.text_view_breed);
        textViewBreed.setText(pet.getBreed());

        return convertView;
    }
}
