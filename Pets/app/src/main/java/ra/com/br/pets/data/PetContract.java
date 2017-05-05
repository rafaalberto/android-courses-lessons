package ra.com.br.pets.data;

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

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

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
