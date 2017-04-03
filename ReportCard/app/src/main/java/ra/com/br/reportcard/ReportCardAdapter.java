package ra.com.br.reportcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportCardAdapter extends ArrayAdapter<ReportCard> {

    private static final int ZERO = 0;

    public ReportCardAdapter(Context context, ArrayList<ReportCard> reportCards) {
        super(context, ZERO, reportCards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_report_card, parent, false);
        }
        ReportCard reportCard = getItem(position);
        TextView textViewSubject = (TextView) listItemView.findViewById(R.id.text_view_subject);
        textViewSubject.setText(reportCard.getSubject());
        return listItemView;
    }
}
