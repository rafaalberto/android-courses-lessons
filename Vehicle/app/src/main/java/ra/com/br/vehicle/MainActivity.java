package ra.com.br.vehicle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {

    private static final String BLANK_SPACE = " ";
    private static final String EMPTY = "";
    private static final int ZERO = 0;

    private static final String EMAIL_URI_PREFIX = "mailto:";
    private static final String EMAIL_ADDRESS = "rafael.alberto1703@gmail.com";

    EditText editTextDescription;
    EditText editTextYear;
    EditText editTextOwner;
    EditText editTextEmailOwner;
    EditText editTextPrice;
    CheckBox checkBoxAlarm;
    CheckBox checkBoxPowerWindows;
    CheckBox checkBoxAirConditioning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        loadViews();
    }

    public void send(View view) {
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setDescription(StringUtils.trimToEmpty(editTextDescription.getText().toString()));
            vehicle.setYear(StringUtils.trimToEmpty(editTextYear.getText().toString()));
            vehicle.setOwner(StringUtils.trimToEmpty(editTextOwner.getText().toString()));
            vehicle.setEmailOwner(StringUtils.trimToEmpty(editTextEmailOwner.getText().toString()));
            vehicle.setPrice(Double.parseDouble(
                    !editTextPrice.getText().toString().equals(EMPTY) ? editTextPrice.getText().toString()
                            : String.valueOf(ZERO)));
            vehicle.setAlarm(checkBoxAlarm.isChecked());
            vehicle.setPowerWindows(checkBoxPowerWindows.isChecked());
            vehicle.setAirConditioning(checkBoxAirConditioning.isChecked());
            composeEmail(MainService.getMessage(getResources(), vehicle));
        } catch (SystemException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View view) {
        editTextDescription.setText(EMPTY);
        editTextDescription.requestFocus();
        editTextYear.setText(EMPTY);
        editTextOwner.setText(EMPTY);
        editTextEmailOwner.setText(EMPTY);
        editTextPrice.setText(EMPTY);
        checkBoxAlarm.setChecked(false);
        checkBoxPowerWindows.setChecked(false);
        checkBoxAirConditioning.setChecked(false);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setTitle(BLANK_SPACE.concat(getString(R.string.app_name)));
    }

    private void loadViews() {
        editTextDescription = (EditText) findViewById(R.id.edit_text_description);
        editTextYear = (EditText) findViewById(R.id.edit_text_year);
        editTextOwner = (EditText) findViewById(R.id.edit_text_owner);
        editTextEmailOwner = (EditText) findViewById(R.id.edit_text_email_owner);
        editTextPrice = (EditText) findViewById(R.id.edit_text_price);
        checkBoxAlarm = (CheckBox) findViewById(R.id.check_box_alarm);
        checkBoxPowerWindows = (CheckBox) findViewById(R.id.check_box_power_windows);
        checkBoxAirConditioning = (CheckBox) findViewById(R.id.check_box_air_conditioning);
    }

    private void composeEmail(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(EMAIL_URI_PREFIX.concat(EMAIL_ADDRESS)));
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL_ADDRESS);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
