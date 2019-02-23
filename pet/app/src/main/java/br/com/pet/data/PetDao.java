package br.com.pet.data;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import br.com.pet.Pet;
import br.com.pet.data.PetContract.PetEntry;

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

    public static String[] projectionIndex() {
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED
        };
        return projection;
    }

    public static String[] projectionForm() {
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT};
        return projection;
    }

    public static Pet getData(Cursor cursor) {
        Pet pet = new Pet();
        pet.setName(cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME)));
        pet.setBreed(cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED)));
        pet.setGender(cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER)));
        pet.setWeight(cursor.getInt(cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT)));
        return pet;
    }
}
