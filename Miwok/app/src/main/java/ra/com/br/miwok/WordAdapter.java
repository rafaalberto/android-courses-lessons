package ra.com.br.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final int ZERO = 0;

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, ZERO, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentyWord = getItem(position);
        TextView textViewMiwok = (TextView) listItemView.findViewById(R.id.text_view_miwok);
        textViewMiwok.setText(currentyWord.getMiwokTranslation());

        TextView textViewDefault = (TextView) listItemView.findViewById(R.id.text_view_default);
        textViewDefault.setText(currentyWord.getDefaultTranslation());

        return listItemView;
    }
}
