package ra.com.br.pets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ra.com.br.pets.data.PetContract;
import ra.com.br.pets.data.PetDao;

public class PetsIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_index);

        FloatingActionButton buttonAddPet = (FloatingActionButton) findViewById(R.id.floating_button_add_pet);
        buttonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsIndexActivity.this, PetsFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView textViewDisplayPets = (TextView) findViewById(R.id.text_view_display_pets);

        PetDao petDao = new PetDao(PetsIndexActivity.this);
        List<Pet> pets = petDao.selectAll();
        if(!pets.isEmpty()){
            textViewDisplayPets.setText("The pets table contains " + pets.size() + " pets.\n\n");
            textViewDisplayPets.append(PetContract.PetEntry._ID + " - " +
                    PetContract.PetEntry.COLUMN_PET_NAME + "\n\n");

            for(Pet pet : pets){
                textViewDisplayPets.append(pet.getId() + " - " + pet.getName() + "\n");
            }
        }else{
            textViewDisplayPets.setText("No pets");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pets_index, menu);
        return true;
    }
}
