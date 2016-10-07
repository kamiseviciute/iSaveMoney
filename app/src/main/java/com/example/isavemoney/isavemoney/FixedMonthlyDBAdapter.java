package com.example.isavemoney.isavemoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class FixedMonthlyDBAdapter {
    static final String TB_NAME = "Salaries";
    static final String COLUMN1_NAME = "monthlySalary";
    static final String COLUMN2_NAME = "weeklySalary";
    static final String COLUMN3_NAME = "weeklyHourlyPay";
    static final String COLUMN5_NAME = "monthlyHourlyPay";
    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Salaries (monthlySalary INT(7), weeklySalary INT(7),weeklyHourlyPay DOUBLE, monthlyHourlyPay DOUBLE)";
    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public FixedMonthlyDBAdapter(Context ctx) {
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

    public FixedMonthlyDBAdapter openDB() {
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

    public long addFixedMonthlySalary(int monthlySalary) {
        try {
            db.execSQL("DELETE FROM " + TB_NAME);
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, monthlySalary);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addWeeklyHourlyPay(double weeklyHourlyPay) {
        try {
            db.execSQL("DELETE FROM " + TB_NAME);
            ContentValues cv = new ContentValues();
            cv.put(COLUMN3_NAME, weeklyHourlyPay);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addMonthlyHourlyPay(double monthlyHourlyPay) {
        try {
            db.execSQL("DELETE FROM " + TB_NAME);
            ContentValues cv = new ContentValues();
            cv.put(COLUMN5_NAME, monthlyHourlyPay);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addFixedWeeklySalary(int weeklySalary) {
        try {
            db.execSQL("DELETE FROM " + TB_NAME);
            ContentValues cv = new ContentValues();
            cv.put(COLUMN2_NAME, weeklySalary);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean monthlySalaryIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN1_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }

    public boolean weeklyHourlyPayIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN3_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }
    public boolean monthlyHourlyPayIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN5_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }
    public boolean weeklySalaryIsEmpty(){
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN2_NAME+" FROM " + TB_NAME, null);
        int count = mCursor.getCount();
        if(count ==0){
            return true;
        }
        mCursor.close();
        return false;
    }
    public String getLastInsertFromMonthlySalary(){
        String mySalary="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN1_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToFirst();
            mySalary = mCursor.getString(0);
        }
        return mySalary;
    }
    public String getLastInsertFromWeeklySalary(){
        String mySalary="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN2_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToFirst();
            mySalary = mCursor.getString(0);
        }
        return mySalary;
    }
    public String getLastInsertFromWeeklyHourlyPay(){
        String hourlyPay="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN3_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToFirst();
            hourlyPay = mCursor.getString(0);
        }
        return hourlyPay;
    }
    public String getLastInsertFromMonthlyHourlyPay(){
        String hourlyPay="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT "+COLUMN5_NAME+" FROM " + TB_NAME, null);
        if (mCursor!=null){
            mCursor.moveToFirst();
            hourlyPay = mCursor.getString(0);
        }
        return hourlyPay;

    }

}


