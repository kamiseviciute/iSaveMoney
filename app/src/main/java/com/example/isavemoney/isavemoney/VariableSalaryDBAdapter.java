package com.example.isavemoney.isavemoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class VariableSalaryDBAdapter {
    static final String TB_NAME = "variable_salaries";
    static final String COLUMN1_NAME = "weeklyVariableSalary";
    static final String COLUMN2_NAME = "monthlyVariableSalary";
    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS variable_salaries (Date DATETIME DEFAULT CURRENT_DATE, weeklyVariableSalary INT(7), monthlyVariableSalary INT(7))";
    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public VariableSalaryDBAdapter(Context ctx) {
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

    public VariableSalaryDBAdapter openDB() {
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
    public long addVariableMonthlySalary(int monthlyVariableSalary) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN2_NAME, monthlyVariableSalary);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addVariableWeeklySalary(int weeklyVariableSalary) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, weeklyVariableSalary);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean monthlyVariableSalaryIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN2_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }
    public boolean weeklyVariableSalaryIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN1_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }
    public String getLastInsertFromWeeklyVariableSalary(){
        String mySalary="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN1_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            mySalary = mCursor.getString(0);
            mCursor.close();
        }
        return mySalary;
    }
    public String getLastInsertFromMonthlyVariableSalary(){
        String mySalary="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN2_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            mySalary = mCursor.getString(0);
            mCursor.close();
        }
        return mySalary;
    }

    public Cursor getCurrentSalary(int month, int year) {
        db = helper.getWritableDatabase();
        String query = "SELECT * FROM variable_salaries WHERE strftime('%m', Date)='0" + month + "'" + " AND strftime('%Y', Date)='" + year +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }
    public Cursor getCurrentSalaryUpper(int month, int year) {
        db = helper.getWritableDatabase();
        String query = "SELECT * FROM variable_salaries WHERE strftime('%m', Date)='" + month + "'" + " AND strftime('%Y', Date)='" + year +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }


}


