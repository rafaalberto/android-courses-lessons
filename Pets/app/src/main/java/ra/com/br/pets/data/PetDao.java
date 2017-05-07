package ra.com.br.pets.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import ra.com.br.pets.Pet;
import ra.com.br.pets.data.PetContract.PetEntry;

public abstract class PetDao {

    public static List<Pet> selectAll(ContentResolver contentResolver) {
        List<Pet> pets = new ArrayList<>();
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED
        };
        //content://ra.com.br.pets/pets/
        Cursor cursor = contentResolver.query(PetEntry.CONTENT_URI, projection, null, null, null);
        try {
            while (cursor.moveToNext()) {
                Pet pet = new Pet();
                pet.setId(cursor.getLong(cursor.getColumnIndex(PetEntry._ID)));
                pet.setName(cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME)));
                pet.setBreed(cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED)));
                pets.add(pet);
            }
        } finally {
            cursor.close();
        }
        return pets;
    }

    public static Uri insert(ContentResolver contentResolver, Pet pet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, pet.getName());
        contentValues.put(PetEntry.COLUMN_PET_BREED, pet.getBreed());
        contentValues.put(PetEntry.COLUMN_PET_GENDER, pet.getGender());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, pet.getWeight());
        return contentResolver.insert(PetEntry.CONTENT_URI, contentValues);
    }
}
