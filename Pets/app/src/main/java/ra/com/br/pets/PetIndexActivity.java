package ra.com.br.pets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import ra.com.br.pets.data.PetDao;

public class PetIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_index);

        FloatingActionButton buttonAddPet = (FloatingActionButton) findViewById(R.id.floating_button_add_pet);
        buttonAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetIndexActivity.this, PetFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ListView listViewPets = (ListView) findViewById(R.id.list_view_pets);
        PetAdapter petAdapter = new PetAdapter(this, PetDao.selectAll(getContentResolver()));
        listViewPets.setAdapter(petAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pet_index, menu);
        return true;
    }
}
