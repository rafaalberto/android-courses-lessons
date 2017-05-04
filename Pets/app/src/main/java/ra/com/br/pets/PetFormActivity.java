package ra.com.br.pets;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ra.com.br.pets.data.PetContract.PetEntry;
import ra.com.br.pets.data.PetDao;

public class PetFormActivity extends AppCompatActivity {

    private int gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_form);
        loadSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pet_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertPets();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPets() {
        Pet pet = new Pet();
        EditText editTextName = (EditText) findViewById(R.id.edit_text_name);
        pet.setName(editTextName.getText().toString().trim());

        EditText editTextBreed = (EditText) findViewById(R.id.edit_text_breed);
        pet.setBreed(editTextBreed.getText().toString().trim());

        pet.setGender(gender);

        EditText editTextWeight = (EditText) findViewById(R.id.edit_text_weight);
        pet.setWeight(Integer.valueOf(editTextWeight.getText().toString()));

        PetDao petDao = new PetDao(this);
        long newRowId = petDao.insert(pet);

        if (newRowId == -1) {
            Toast.makeText(this, "Erro saving pet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Inserted pet id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSpinner() {
        Spinner spinnerGender = (Spinner) findViewById(R.id.spinner_gender);

        ArrayList<String> genders = new ArrayList<String>();
        genders.add(getString(R.string.gender));
        genders.add(getString(R.string.gender_unknown));
        genders.add(getString(R.string.gender_male));
        genders.add(getString(R.string.gender_female));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_gender, genders) {
            @Override
            public boolean isEnabled(int position) {
                return position == Constants.ZERO ? false : true;
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

                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_unknown))) {
                        gender = PetEntry.GENDER_UNKNOWN;
                    } else if (selection.equals(getString(R.string.gender_male))) {
                        gender = PetEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        gender = PetEntry.GENDER_FEMALE;
                    } else {
                        gender = Constants.ZERO;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = Constants.ZERO;
            }
        });
    }
}
