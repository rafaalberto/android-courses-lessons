package br.com.pet;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.pet.data.PetContract.PetEntry;
import br.com.pet.data.PetDao;

public class PetFormActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_PET_LOADER = Constants.ZERO;

    private EditText editTextName;
    private EditText editTextBreed;
    private Spinner spinnerGender;
    private EditText editTextWeight;

    private int gender;
    private Uri currentPetUri;
    private boolean petHasChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_form);

        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextBreed = (EditText) findViewById(R.id.edit_text_breed);
        spinnerGender = (Spinner) findViewById(R.id.spinner_gender);
        editTextWeight = (EditText) findViewById(R.id.edit_text_weight);

        editTextName.setOnTouchListener(touchListener);
        editTextBreed.setOnTouchListener(touchListener);
        spinnerGender.setOnTouchListener(touchListener);
        editTextWeight.setOnTouchListener(touchListener);

        loadSpinner();

        Intent intent = getIntent();
        currentPetUri = intent.getData();

        if (currentPetUri == null) {
            setTitle(R.string.title_activity_add_new_pet);
            invalidateOptionsMenu();
        } else {
            setTitle(R.string.title_activity_edit_pet);
            getLoaderManager().initLoader(ID_PET_LOADER, null, this);
        }
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
                savePet();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if (!petHasChanged) {
                    NavUtils.navigateUpFromSameTask(PetFormActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NavUtils.navigateUpFromSameTask(PetFormActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentPetUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, currentPetUri, PetDao.projectionForm(), null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor.moveToFirst()) {
            Pet pet = PetDao.getData(cursor);
            editTextName.setText(pet.getName());
            editTextBreed.setText(pet.getBreed());
            editTextWeight.setText(Integer.toString(pet.getWeight()));

            switch (pet.getGender()) {
                case PetEntry.GENDER_MALE:
                    spinnerGender.setSelection(PetEntry.GENDER_MALE);
                    break;
                case PetEntry.GENDER_FEMALE:
                    spinnerGender.setSelection(PetEntry.GENDER_FEMALE);
                    break;
                default:
                    spinnerGender.setSelection(PetEntry.GENDER_UNKNOWN);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onBackPressed() {
        if (!petHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            petHasChanged = true;
            return false;
        }
    };

    private void loadSpinner() {
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

    private void savePet() {
        Pet pet = new Pet();
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        pet.setName(editTextName.getText().toString().trim());

        editTextBreed = (EditText) findViewById(R.id.edit_text_breed);
        pet.setBreed(editTextBreed.getText().toString().trim());

        pet.setGender(gender);

        editTextWeight = (EditText) findViewById(R.id.edit_text_weight);
        pet.setWeight(!editTextWeight.getText().toString().equals(Constants.EMPTY) ? Integer.valueOf(editTextWeight.getText().toString()) : Constants.ZERO);

        try {
            if (currentPetUri == null) {
                Uri uri = PetDao.insert(getContentResolver(), pet);
                Toast.makeText(this, uri != null ? getString(R.string.pet_insert_successful) : getString(R.string.pet_insert_failed), Toast.LENGTH_SHORT).show();
            } else {
                int rowsUpdated = PetDao.update(getContentResolver(), currentPetUri, pet);
                if (rowsUpdated != Constants.ZERO) {
                    Toast.makeText(this, getString(R.string.pet_update_successful), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getString(R.string.pet_update_failed), Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deletePet();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deletePet() {
        if (currentPetUri != null) {
            int rowsDeleted = PetDao.delete(getContentResolver(), currentPetUri);
            if (rowsDeleted != Constants.ZERO) {
                Toast.makeText(this, getString(R.string.editor_delete_pet_successful), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_delete_pet_failed), Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }
}
