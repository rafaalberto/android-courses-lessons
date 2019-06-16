package br.com.pet.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import br.com.pet.R;

public class PetCursorAdapter extends CursorAdapter {

    public static final int FLAGS = 0;

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, FLAGS);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_view_item_pet, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.text_view_name);
        TextView textViewBreed = (TextView) view.findViewById(R.id.text_view_breed);

        textViewName.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME)));
        textViewBreed.setText(cursor.getString(cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED)));
    }
}
