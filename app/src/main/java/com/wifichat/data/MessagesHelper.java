package com.wifichat.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Divya on 11/28/2016.
 */

public class MessagesHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MessagesHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "messages.db";
    private static final int DATABASE_VERSION = 1;

    public MessagesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_MESSAGES_TABLE = "CREATE TABLE " +
                MessagesContract.MessagesEntry.TABLE_MESSAGES + "(" +
                MessagesContract.MessagesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                MessagesContract.MessagesEntry.COLUMN_MESSAGE + " TEXT NOT NULL, " +
                MessagesContract.MessagesEntry.COLUMN_SENDER + " TEXT NOT NULL, " +
                MessagesContract.MessagesEntry.COLUMN_RECEIVER + " TEXT NOT NULL, " +
                MessagesContract.MessagesEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                MessagesContract.MessagesEntry.COLUMN_STATUS + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_MESSAGES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
