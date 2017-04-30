package ra.com.br.pets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PetsFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_form);
        loadSpinner();
    }

    private void loadSpinner() {
        Spinner spinnerGender = (Spinner) findViewById(R.id.spinner_gender);

        ArrayList<String> genders = new ArrayList<String>();
        genders.add(getString(R.string.gender));
        genders.add(getString(R.string.gender_unknown));
        genders.add(getString(R.string.gender_male));
        genders.add(getString(R.string.gender_female));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout_gender, genders) {
            @Override
            public boolean isEnabled(int position) {
                return position == 0 ? false : true;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                textView.setTextColor(isEnabled(position) == true ? Color.BLACK : Color.GRAY);
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerGender.setAdapter(spinnerArrayAdapter);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                textView.setTextColor(spinnerArrayAdapter.isEnabled(position) == true ? Color.BLACK : Color.GRAY);

                /*String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_unknown))) {
                    } else if (selection.equals(getString(R.string.gender_male))) {
                    } else if (selection.equals(getString(R.string.gender_female))) {
                    } else {
                    }
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
