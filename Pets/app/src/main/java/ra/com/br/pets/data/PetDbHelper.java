package ra.com.br.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ra.com.br.pets.data.PetContract.PetEntry;

public class PetDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shelter.db";

    //If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sqlCreatePetsTable = new StringBuilder();
        sqlCreatePetsTable.append("CREATE TABLE " + PetEntry.TABLE_NAME + " (")
                .append(PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, ")
                .append(PetEntry.COLUMN_PET_BREED + " TEXT, ")
                .append(PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, ")
                .append(PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);");
        db.execSQL(sqlCreatePetsTable.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
