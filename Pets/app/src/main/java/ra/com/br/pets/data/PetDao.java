package ra.com.br.pets.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import ra.com.br.pets.Pet;
import ra.com.br.pets.data.PetContract.PetEntry;

public abstract class PetDao {

    public static Uri insert(ContentResolver contentResolver, Pet pet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, pet.getName());
        contentValues.put(PetEntry.COLUMN_PET_BREED, pet.getBreed());
        contentValues.put(PetEntry.COLUMN_PET_GENDER, pet.getGender());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, pet.getWeight());
        return contentResolver.insert(PetEntry.CONTENT_URI, contentValues);
    }

    public static int update(ContentResolver contentResolver, Uri uri, Pet pet) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, pet.getName());
        contentValues.put(PetEntry.COLUMN_PET_BREED, pet.getBreed());
        contentValues.put(PetEntry.COLUMN_PET_GENDER, pet.getGender());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, pet.getWeight());
        return contentResolver.update(uri, contentValues, null, null);
    }

    public static int delete(ContentResolver contentResolver, Uri uri) {
        return contentResolver.delete(uri, null, null);
    }
}
