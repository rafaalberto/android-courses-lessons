package br.com.waitlist.data;

import android.provider.BaseColumns;

public class WaitlistContract {

    public class WaitlistEntry implements BaseColumns {
        public static final String TABLE_NAME = "waitlist";
        public static final String COLUMN_GUEST_NAME = "guest_name";
        public static final String COLUMN_PARTY_SIZE = "party_size";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
