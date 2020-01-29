package com.example.muayad.maxhit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter extends SQLiteOpenHelper{

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "history.db";

    // DB Fields
    public static final String KEY_ROWID = "_id";
    public static final int COL_ROWID = 0;

    //fields
    public static final String TABLE_MAX = "history";
    public static final String KEY_MAX = "max";
    public static final String KEY_DATE = "date";
    public static final String KEY_WEIGHT = "weight";

    //Columns
    public static final int COL_MAX = 1;
    public static final int COL_DATE = 2;
    public static final int COL_WEIGHT = 3;


    public static final int DATABASE_VERSION = 1;


    private SQLiteDatabase db;

    public DBAdapter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_MAX
                + " (" + KEY_ROWID + " integer primary key autoincrement, "

                + KEY_MAX + " INTEGER, "
                + KEY_DATE + " TEXT, "
                + KEY_WEIGHT + " INTEGER "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAX);
        onCreate(db);

    }
    //methods

    // Add a new set of values to the database.
    public void insertRow(int max, String date, int weight) {
        /*
         * CHANGE 3:
         */
        // TODO: Update data in the row with new fields.
        // TODO: Also change the function's arguments to be what you need!
        // Create row's data:
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_MAX, max);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_WEIGHT, weight);
        SQLiteDatabase db = getWritableDatabase();

        // Insert it into the database.
        db.insert(TABLE_MAX, null, initialValues);
        db.close();
    }
    // Delete a row from the database, by rowId (primary key)

    public void deleteRow(int max) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM" + TABLE_MAX + "WHERE" + COL_MAX + "=\"" + max + "\";");
    }


    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_MAX + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if(c.getString(c.getColumnIndex("max"))!=null) {
                dbString += c.getString(c.getColumnIndex("max")) + " " + c.getString(c.getColumnIndex("date")) + " " + c.getString(c.getColumnIndex("weight"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_MAX;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
