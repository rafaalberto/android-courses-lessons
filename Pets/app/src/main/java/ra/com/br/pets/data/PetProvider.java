package ra.com.br.pets.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import ra.com.br.pets.Constants;
import ra.com.br.pets.R;
import ra.com.br.pets.data.PetContract.PetEntry;

public class PetProvider extends ContentProvider {

    public static final String LOG_TAG = PetProvider.class.getSimpleName();
    private static final int ERROR_INSERT_CODE = -1;

    private static final int PETS = 100;
    private static final int PET_ID = 101;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY_URI, PetContract.PATH_PETS, PETS);
        uriMatcher.addURI(PetContract.CONTENT_AUTHORITY_URI, PetContract.PATH_PETS + "/#", PET_ID);
    }

    private PetDbHelper petDbHelper;

    @Override
    public boolean onCreate() {
        petDbHelper = new PetDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = petDbHelper.getReadableDatabase();
        Cursor cursor;
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                cursor = sqLiteDatabase.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PET_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return PetEntry.CONTENT_LIST_TYPE;
            case PET_ID:
                return PetEntry.CONTENT_ITEM_BASE_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return insertPet(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return sqLiteDatabase.delete(PetEntry.TABLE_NAME, selection, selectionArgs);
            case PET_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return sqLiteDatabase.delete(PetEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Delete is not supported for " + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = uriMatcher.match(uri);
        switch (match) {
            case PETS:
                return updatePet(values, selection, selectionArgs);
            case PET_ID:
                selection = PetEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private Uri insertPet(Uri uri, ContentValues contentValues) {
        checkData(contentValues);
        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        long id = sqLiteDatabase.insert(PetEntry.TABLE_NAME, null, contentValues);
        if (id == ERROR_INSERT_CODE) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        return ContentUris.withAppendedId(uri, id);
    }

    private int updatePet(ContentValues values, String selection, String[] selectionArgs) {
        checkData(values);
        if (values.size() == Constants.ZERO) {
            return Constants.ZERO;
        }
        SQLiteDatabase sqLiteDatabase = petDbHelper.getWritableDatabase();
        return sqLiteDatabase.update(PetEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    private void checkData(ContentValues contentValues) {
        String name = contentValues.getAsString(PetEntry.COLUMN_PET_NAME);
        if (name == null || name.equals(Constants.EMPTY)) {
            throw new IllegalArgumentException(getContext().getString(R.string.pet_requires_name));
        }
        String breed = contentValues.getAsString(PetEntry.COLUMN_PET_BREED);
        if (breed == null || breed.equals(Constants.EMPTY)) {
            throw new IllegalArgumentException(getContext().getString(R.string.pet_requires_breed));
        }
        int gender = contentValues.getAsInteger(PetEntry.COLUMN_PET_GENDER);
        if (gender == Constants.ZERO) {
            throw new IllegalArgumentException(getContext().getString(R.string.pet_requires_gender));
        }
    }
}
