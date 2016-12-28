package ra.com.br.justjava;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {

    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String EMPTY = "";

    private static final String PREFIX_EMAIL = "mailto:";
    private static final String DEFAULT_EMAIL_ADDRESS = "rafael.alberto1703@gmail.com";
    private static final String DEFAULT_EMAIL_SUBJECT = "Just Java Order for";

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
        try {
            String name = StringUtils.trimToEmpty(editTextName.getText().toString());
            String message = mainService.createOrderSummary(name, getCheckBoxWhippedCream().isChecked(), getCheckboxChocolate().isChecked());
            composeEmail(name, message);
        } catch (SystemException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void resetOrder(View view) {
        mainService.resetOrder();
        editTextName.setText(EMPTY);
        getCheckBoxWhippedCream().setChecked(false);
        getCheckboxChocolate().setChecked(false);
        getTextViewQuantity().setText(String.valueOf(ONE));
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

    private void composeEmail(String name, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(PREFIX_EMAIL.concat(DEFAULT_EMAIL_ADDRESS)));
        intent.putExtra(Intent.EXTRA_EMAIL, DEFAULT_EMAIL_ADDRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT, DEFAULT_EMAIL_SUBJECT.concat(" ").concat(name));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
