package ra.com.br.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int MINIMUM_QUANTITY = 0;
    private static final int UNITY_PRICE = 5;
    private static final int ONE = 1;
    private static final String EMPTY = "";

    int quantity = MINIMUM_QUANTITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity = quantity + ONE;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > MINIMUM_QUANTITY) {
            quantity = quantity - ONE;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {
        displayMessage(createOrderSummary(hasWhippedCream()));
    }

    public void resetOrder(View view) {
        quantity = MINIMUM_QUANTITY;
        displayQuantity(MINIMUM_QUANTITY);
        displayMessage(EMPTY);
        unCheckWhippedCream();
    }

    private boolean hasWhippedCream() {
        CheckBox checkboxWhippedCream = (CheckBox) findViewById(R.id.check_box_whipped_cream);
        return checkboxWhippedCream.isChecked();
    }

    private String createOrderSummary(boolean hasWhippedCream) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: Rafael Alberto")
                .append("\nQuantity: " + quantity)
                .append("\nTotal: $ " + calculatePrice(quantity))
                .append("\nAdd Whipped Cream? " + (hasWhippedCream == true ? "Yes" : "No"))
                .append("\nThank you!");
        return stringBuilder.toString();
    }

    private int calculatePrice(int quantity) {
        return quantity * UNITY_PRICE;
    }

    private void displayQuantity(int number) {
        TextView textViewQuantity = (TextView) findViewById(R.id.text_view_quantity);
        textViewQuantity.setText(String.valueOf(number));
    }

    private void displayMessage(String message) {
        TextView textViewPrice = (TextView) findViewById(R.id.text_view_order_summary);
        textViewPrice.setText(message);
    }

    private void unCheckWhippedCream() {
        CheckBox checkboxWhippedCream = (CheckBox) findViewById(R.id.check_box_whipped_cream);
        checkboxWhippedCream.setChecked(false);
    }
}
