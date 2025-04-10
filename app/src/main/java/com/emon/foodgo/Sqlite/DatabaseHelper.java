package com.emon.foodgo.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Foodgo_database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_favourite = "favourite";
    private static final String COLUMN_ID = "id";
    private static final boolean COLUMN_favourite = false;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String CREATE_SMS_TABLE = "CREATE TABLE " + TABLE_favourite + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_favourite + " TEXT,"
                + COLUMN_MESSAGE + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_STATUS + " TEXT)";
        db.execSQL(CREATE_SMS_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      /*  db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS);
        onCreate(db);*/
    }

}
