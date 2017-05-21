package ra.com.br.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {

    public static final String SCHEME_URI = "content://";
    public static final String CONTENT_AUTHORITY_URI = "ra.com.br.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME_URI + CONTENT_AUTHORITY_URI);
    public static final String PATH_PETS = "pets";

    private PetContract() {

    }

    public static final class PetEntry implements BaseColumns {

        //uri - content://ra.com.br.pets/pets
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        //vnd.android.cursor.dir/ra.com.br.pets/pets
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY_URI + "/" + PATH_PETS;

        //vnd.android.cursor.item/ra.com.br.pets/pets
        public static final String CONTENT_ITEM_BASE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY_URI + "/" + PATH_PETS;

        public static final String TABLE_NAME = "pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        public static final int GENDER_UNKNOWN = 1;
        public static final int GENDER_MALE = 2;
        public static final int GENDER_FEMALE = 3;
    }
}
