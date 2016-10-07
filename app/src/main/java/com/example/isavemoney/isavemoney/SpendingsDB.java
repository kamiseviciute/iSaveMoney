package com.example.isavemoney.isavemoney;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SpendingsDB {
    static final String TB_NAME = "Savings";
    static final String COLUMN1_NAME = "Rent";
    static final String COLUMN2_NAME = "Bills_Utilities";
    static final String COLUMN3_NAME = "Traveling";
    static final String COLUMN4_NAME = "Clothing";
    static final String COLUMN5_NAME = "Hobbies";
    static final String COLUMN6_NAME = "Personal_Care";
    static final String COLUMN7_NAME = "Food_Dining";
    static final String COLUMN8_NAME = "Entertainment";
    static final String COLUMN9_NAME = "Other";

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Savings (Rent INT(7), Bills_Utilities INT(7), Traveling INT(7), Clothing INT(7), Hobbies INT(7), Personal_Care INT(7),Food_Dining INT(7),Entertainment INT(7), Other INT(7))";
    final Context c;
    SQLiteDatabase db;
    DBHelper helper;
    public SpendingsDB(Context ctx) {
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

    public SpendingsDB openDB() {
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
    public long addRent(int rent) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, rent);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addBills(int bills) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN2_NAME, bills);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long addTraveling(int traveling) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, traveling);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addClothing(int clothing) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, clothing);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addHobbies(int hobbies) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, hobbies);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addPersonalCare(int personalCare) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, personalCare);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addFoodDining(int foodDining) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, foodDining);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addEntertainment(int entertainment) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, entertainment);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public long addOther(int other) {
        try {
            db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN1_NAME, other);
            return db.insert(TB_NAME, null, cv);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getLastInsertFromRent(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String rentPrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            rentPrice = mCursor.getString(mCursor.getColumnIndex(COLUMN1_NAME));
        }
        return rentPrice;
    }
    public String getLastInsertFromBills(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String billPrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            billPrice = mCursor.getString(mCursor.getColumnIndex(COLUMN2_NAME));
        }
        return billPrice;
    }
    public String getLastInsertFromTraveling(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String traveling="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            traveling = mCursor.getString(mCursor.getColumnIndex(COLUMN3_NAME));
        }
        return traveling;
    }
    public String getLastInsertFromClothing(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String clothingPrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            clothingPrice = mCursor.getString(mCursor.getColumnIndex(COLUMN4_NAME));
        }
        return clothingPrice;
    }
    public String getLastInsertFromHobbies(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String hobbyPrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            hobbyPrice = mCursor.getString(mCursor.getColumnIndex(COLUMN5_NAME));
        }
        return hobbyPrice;
    }
    public String getLastInsertFromPersonalCare(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String personalCarePrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            personalCarePrice = mCursor.getString(mCursor.getColumnIndex(COLUMN6_NAME));
        }
        return personalCarePrice;
    }
    public String getLastInsertFromFoodDining(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String foodPrice="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            foodPrice = mCursor.getString(mCursor.getColumnIndex(COLUMN7_NAME));
        }
        return foodPrice;
    }
    public String getLastInsertFromEntertainment(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String priceEntertainment="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            priceEntertainment = mCursor.getString(mCursor.getColumnIndex(COLUMN8_NAME));
        }
        return priceEntertainment;
    }
    public String getLastInsertFromOther(){
        String[] columns = {COLUMN1_NAME, COLUMN2_NAME, COLUMN3_NAME, COLUMN4_NAME, COLUMN5_NAME, COLUMN6_NAME, COLUMN7_NAME, COLUMN8_NAME, COLUMN9_NAME};
        String priceOther="";
        db=helper.getReadableDatabase();
        Cursor mCursor = db.query(TB_NAME, columns, null, null, null, null, null);
        if (mCursor!=null){
            mCursor.moveToLast();
            priceOther = mCursor.getString(mCursor.getColumnIndex(COLUMN9_NAME));
        }
        return priceOther;
    }

    public Cursor getAllData() {
        db = helper.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TB_NAME,null);
        db.close();
        return res;
    }


}


