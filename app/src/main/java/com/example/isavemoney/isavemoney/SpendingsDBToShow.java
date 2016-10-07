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

public class SpendingsDBToShow {

    static final String TB_NAME = "Spendings_Other";
    static final String COLUMN0_NAME = "Date";
    static final String COLUMN1_NAME = "Type_Of_Spending";
    static final String COLUMN2_NAME = "Cost";

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Spendings_Other (Date DATETIME DEFAULT CURRENT_DATE, Type_Of_Spending VARCHAR, Cost INT(7))";
    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public SpendingsDBToShow(Context ctx) {
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

    public SpendingsDBToShow openDB() {
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

    public long addRent(String rent_text, int rent) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, rent_text);
            cv.put(COLUMN2_NAME, rent);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addBills(String bills_text, int bills) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, bills_text);
            cv.put(COLUMN2_NAME, bills);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addTraveling(String traveling_text, int traveling) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, traveling_text);
            cv.put(COLUMN2_NAME, traveling);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addClothing(String clothing_text, int clothing) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, clothing_text);
            cv.put(COLUMN2_NAME, clothing);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addHobbies(String hobbies_text, int hobbies) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, hobbies_text);
            cv.put(COLUMN2_NAME, hobbies);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addPersonalCare(String personalCare_text, int personalCare) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, personalCare_text);
            cv.put(COLUMN2_NAME, personalCare);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addFoodDining(String foodDining_text, int foodDining) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, foodDining_text);
            cv.put(COLUMN2_NAME, foodDining);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addEntertainment(String entertainment_text, int entertainment) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, entertainment_text);
            cv.put(COLUMN2_NAME, entertainment);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addOther(String other_text, int other) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, other_text);
            cv.put(COLUMN2_NAME, other);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public Cursor getAllData() {
        db = helper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TB_NAME, null);
        return res;
    }


    public Cursor getSpendingOnMonth(int month, int year) {
        db = helper.getWritableDatabase();
        String query = "SELECT * FROM Spendings_Other WHERE strftime('%m', Date)='0" + month + "'" + " AND strftime('%Y', Date)='" + year +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }

    public Cursor getSpendingOnMonth2(int month, int year) {
        db = helper.getWritableDatabase();
        String query = "SELECT * FROM Spendings_Other WHERE strftime('%m', Date)='" + month + "'" + " AND strftime('%Y', Date)='" + year + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }
}
