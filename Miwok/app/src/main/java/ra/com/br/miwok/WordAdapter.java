package ra.com.br.miwok;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final int ZERO = 0;

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, ZERO, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
