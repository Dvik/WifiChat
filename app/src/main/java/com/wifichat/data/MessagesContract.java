package com.wifichat.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Divya on 11/28/2016.
 */

public class MessagesContract {

    public static final String CONTENT_AUTHORITY = "com.wifichat.data.MessagesProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MessagesEntry implements BaseColumns {

        public static final String TABLE_MESSAGES = "messages";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_MESSAGE = "message";
        public static final String COLUMN_SENDER = "sender";
        public static final String COLUMN_RECEIVER = "receiver";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_TIME = "time";

        public static final String[] SITUATION_PROJECTION = {COLUMN_ID, COLUMN_MESSAGE, COLUMN_SENDER,
                COLUMN_SENDER, COLUMN_RECEIVER, COLUMN_STATUS,COLUMN_TIME};

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_MESSAGES).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MESSAGES;

        public static Uri buildFavoritesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}