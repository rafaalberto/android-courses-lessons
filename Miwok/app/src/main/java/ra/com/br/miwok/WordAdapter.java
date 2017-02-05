package ra.com.br.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final int ZERO = 0;
    private int resourceColorId;

    public WordAdapter(Context context, ArrayList<Word> words, int resourceColorId) {
        super(context, ZERO, words);
        this.resourceColorId = resourceColorId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word word = getItem(position);
        TextView textViewMiwok = (TextView) listItemView.findViewById(R.id.text_view_miwok);
        textViewMiwok.setText(word.getMiwokTranslation());

        TextView textViewDefault = (TextView) listItemView.findViewById(R.id.text_view_default);
        textViewDefault.setText(word.getDefaultTranslation());

        setImageViewIcon(listItemView, word);
        setBackgroundColor(listItemView);

        return listItemView;
    }

    private void setImageViewIcon(View listItemView, Word word) {
        ImageView imageViewIcon = (ImageView) listItemView.findViewById(R.id.image_view_icon);

        if (word.hasImage()) {
            imageViewIcon.setImageResource(word.getImageResourceId());
        } else {
            imageViewIcon.setVisibility(View.GONE);
        }
    }

    private void setBackgroundColor(View listItemView) {
        View linearLayoutContainer = listItemView.findViewById(R.id.linear_layout_container);
        int color = ContextCompat.getColor(getContext(), resourceColorId);
        linearLayoutContainer.setBackgroundColor(color);
    }
}
