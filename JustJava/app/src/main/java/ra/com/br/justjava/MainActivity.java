package ra.com.br.justjava;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String EMPTY = "";

    private MainService mainService;

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadEditTextName();
        mainService = new MainService();
    }

    public void increment(View view) {
        getTextViewQuantity().setText(String.valueOf(mainService.increment()));
    }

    public void decrement(View view) {
        getTextViewQuantity().setText(String.valueOf(mainService.decrement()));
    }

    public void submitOrder(View view) {
        String name = editTextName.getText().toString();
        if (!name.equals(EMPTY)) {
            getTextViewOrderSummary().setText(
                    mainService.createOrderSummary(name,
                            getCheckBoxWhippedCream().isChecked(),
                            getCheckboxChocolate().isChecked()));
        } else {
            Toast.makeText(this, "You must type your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void resetOrder(View view) {
        mainService.resetOrder();
        editTextName.setText(EMPTY);
        getCheckBoxWhippedCream().setChecked(false);
        getCheckboxChocolate().setChecked(false);
        getTextViewQuantity().setText(String.valueOf(ONE));
        getTextViewOrderSummary().setText(EMPTY);
    }

    private void loadEditTextName() {
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    editTextName.setFocusable(false);
                    editTextName.setFocusableInTouchMode(false);
                    InputMethodManager inputMethodManager =
                            (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, Integer.valueOf(ZERO));
                    return true;
                }
                return false;
            }
        });

        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                editTextName.setFocusable(true);
                editTextName.setFocusableInTouchMode(true);
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                return false;
            }
        });
    }

    private CheckBox getCheckBoxWhippedCream() {
        return (CheckBox) findViewById(R.id.check_box_whipped_cream);
    }

    private CheckBox getCheckboxChocolate() {
        return (CheckBox) findViewById(R.id.check_box_chocolate);
    }

    private TextView getTextViewQuantity() {
        return (TextView) findViewById(R.id.text_view_quantity);
    }

    private TextView getTextViewOrderSummary() {
        return (TextView) findViewById(R.id.text_view_order_summary);
    }
}
