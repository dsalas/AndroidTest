package com.test.android.database.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
/**
 * Created by Android on 25/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db) {
        db.execSQL(DataSource.CREATE_USER);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        //Drop tables and recreate
    }

}
