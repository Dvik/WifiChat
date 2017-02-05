package com.wifichat.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Divya on 11/28/2016.
 */

public class MessagesHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = MessagesHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "situations.db";
    private static final int DATABASE_VERSION = 3;

    public MessagesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_SITUATION_TABLE = "CREATE TABLE " +
                MessagesContract.SituationEntry.TABLE_SITUATIONS + "(" +
                MessagesContract.SituationEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                MessagesContract.SituationEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_HEADPHONE_STATE + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_WEATHER_STATE + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_LATITUDE + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_LONGITUDE + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_PLACE + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_ACTIVITY + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_ACTION + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_ACTION_NAME + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_IS_OPEN_APP + " TEXT NOT NULL, " +
                MessagesContract.SituationEntry.COLUMN_CHECKED + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_SITUATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        switch (oldVersion) {
            case 1:
            case 2:  sqLiteDatabase.execSQL("ALTER TABLE " + MessagesContract.SituationEntry.TABLE_SITUATIONS +
                    " ADD COLUMN "+ MessagesContract.SituationEntry.COLUMN_IS_OPEN_APP +" TEXT NULL");
            case 3:
        }
    }
}
