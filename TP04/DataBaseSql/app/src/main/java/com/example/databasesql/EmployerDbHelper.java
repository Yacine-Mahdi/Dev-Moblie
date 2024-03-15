package com.example.databasesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class EmployerDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Employer.db";

    public EmployerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Best practice an inner class for each table
    private static class EmployerEntry implements BaseColumns {
        // Table Name
        private static final String TABLE_NAME = "entry";
        // Table Columns: Column nom
        private static final String COLUMN_NAME_NAME = "name";
        // Column email
        private static final String COLUMN_NAME_EMAIL = "email";
        // Column telephone
        private static final String COLUMN_NAME_PHONE = "phone";
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EmployerEntry.TABLE_NAME + " (" +
                    EmployerEntry._ID + " INTEGER PRIMARY KEY," +
                    EmployerEntry.COLUMN_NAME_NAME + " TEXT," +
                    EmployerEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    EmployerEntry.COLUMN_NAME_PHONE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EmployerEntry.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean addEmployer(String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EmployerEntry.COLUMN_NAME_NAME, name);
        values.put(EmployerEntry.COLUMN_NAME_EMAIL, email);
        values.put(EmployerEntry.COLUMN_NAME_PHONE, phone);
        long rowId = db.insert(EmployerEntry.TABLE_NAME, null, values);
        return rowId != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + EmployerEntry.TABLE_NAME, null);
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EmployerEntry.TABLE_NAME, EmployerEntry._ID + "= ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void update(String name, String email, String phone, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(EmployerEntry.COLUMN_NAME_NAME, name);
        newValues.put(EmployerEntry.COLUMN_NAME_EMAIL, email);
        newValues.put(EmployerEntry.COLUMN_NAME_PHONE, phone);
        int count = db.update(EmployerEntry.TABLE_NAME,newValues, EmployerEntry._ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}
