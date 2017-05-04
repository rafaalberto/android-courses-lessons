package ra.com.br.pets.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ra.com.br.pets.Pet;
import ra.com.br.pets.data.PetContract.PetEntry;

public class PetDao {

    private PetDbHelper petDbHelper;
    private Context context;

    public PetDao(Context context) {
        this.context = context;
        petDbHelper = new PetDbHelper(context);
    }

    public List<Pet> selectAll() {
        List<Pet> pets = new ArrayList<Pet>();
        SQLiteDatabase sqLiteDatabase = petDbHelper.getReadableDatabase();
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED
        };
        Cursor cursor = sqLiteDatabase.query(PetEntry.TABLE_NAME, projection, null, null, null, null, null);
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

    public long insert(Pet pet) {
        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetEntry.COLUMN_PET_NAME, pet.getName());
        contentValues.put(PetEntry.COLUMN_PET_BREED, pet.getBreed());
        contentValues.put(PetEntry.COLUMN_PET_GENDER, pet.getGender());
        contentValues.put(PetEntry.COLUMN_PET_WEIGHT, pet.getWeight());
        long newRowId = sqLiteDatabase.insert(PetEntry.TABLE_NAME, null, contentValues);
        return newRowId;
    }
}
