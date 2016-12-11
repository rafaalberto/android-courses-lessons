package ra.com.br.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int MINIMUM_QUANTITY = 0;

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if(quantity > MINIMUM_QUANTITY){
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    public void submitOrder(View view) {
        int price = quantity * 5;
        String priceMessage = "Total: $ " + price;
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);
    }

    private void displayQuantity(int number) {
        TextView textViewQuantity = (TextView) findViewById(R.id.text_view_quantity);
        textViewQuantity.setText(String.valueOf(number));
    }

    private void displayMessage(String message){
        TextView textViewPrice = (TextView) findViewById(R.id.text_view_price);
        textViewPrice.setText(message);
    }
}
