package ra.com.br.reportcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ReportCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_card);

        ListView listViewReportCard = (ListView) findViewById(R.id.list_view_report_card);
        listViewReportCard.setAdapter(new ReportCardAdapter(this, QueryUtils.getListReport()));
    }
}
