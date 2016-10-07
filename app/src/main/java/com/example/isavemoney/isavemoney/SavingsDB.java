package com.example.isavemoney.isavemoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class SavingsDB{

    static final String TB_NAME = "Savings";
    static final String COLUMN0_NAME = "Date";
    static final String COLUMN1_NAME = "Saving";

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Savings (Date DATETIME DEFAULT CURRENT_DATE, Saving INT(7),)";
    final Context c;
    SQLiteDatabase db;
    DBHelper helper;
    public SavingsDB(Context ctx) {
        this.c = ctx;
        helper = new DBHelper(c);
    }



    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, TB_NAME, null, '1');
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);


            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
            onCreate(db);
        }
    }

    public SavingsDB openDB() {
        try {
            db = helper.getWritableDatabase();

        } catch (SQLException e) {
            Toast.makeText(c, e.getMessage(), Toast.LENGTH_LONG).show();

        }
        return this;
    }

    public void close() {

        helper.close();
    }
    public long addSaving(int saving) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, saving);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Cursor getAllData() {
        db = helper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TB_NAME,null);
        return res;
    }


}

