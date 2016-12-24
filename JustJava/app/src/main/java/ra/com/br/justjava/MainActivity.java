package ra.com.br.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String ZERO = "0";
    private static final String EMPTY = "";

    private MainService mainService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainService = new MainService();
    }

    public void increment(View view) {
        getTextViewQuantity().setText(String.valueOf(mainService.increment()));
    }

    public void decrement(View view) {
        getTextViewQuantity().setText(String.valueOf(mainService.decrement()));
    }

    public void submitOrder(View view) {
        getTextViewOrderSummary().setText(mainService.createOrderSummary(getCheckBoxWhippedCream().isChecked()));
    }

    public void resetOrder(View view) {
        mainService.resetOrder();
        getCheckBoxWhippedCream().setChecked(false);
        getTextViewQuantity().setText(String.valueOf(ZERO));
        getTextViewOrderSummary().setText(EMPTY);
    }

    private CheckBox getCheckBoxWhippedCream() {
        return (CheckBox) findViewById(R.id.check_box_whipped_cream);
    }

    private TextView getTextViewQuantity() {
        return (TextView) findViewById(R.id.text_view_quantity);
    }

    private TextView getTextViewOrderSummary() {
        return (TextView) findViewById(R.id.text_view_order_summary);
    }
}
