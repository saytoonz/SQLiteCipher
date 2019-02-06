package com.nsromapa.sqlitecipher.Helper;

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private static DBHelper instance;

    private static final int DATABASE_VER =1;
    private static final String DATABASE_NAME = "sayt.db";

    private static final String TABLE_NAME = "CONTACTS";
    private static final String COLUMN_EMAIL = "EMAIL";


    public static final String PASS_PHASE = "11223573";


    private static final String SQL_CREATE_TABLE_QUERY =
            "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_EMAIL+" TEXT PRIMARY KEY)";

    private static final String SQL_DELETE_TABLE_QUERY =
            "DROP TABLE IF EXISTS "+TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    static public synchronized DBHelper getInstance(Context context)
    {
        if (instance == null)
            instance = new DBHelper(context);
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_QUERY);
        onCreate(db);
    }


    public void insertNewMail(String email){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHASE);

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,email);

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void updateEmail(String oldEmail, String newEmail){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHASE);

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,newEmail);

        db.update(TABLE_NAME, values, COLUMN_EMAIL+" = '"+oldEmail+"'",null);
        db.close();
    }

    public void deleteNewMail(String email){
        SQLiteDatabase db = instance.getWritableDatabase(PASS_PHASE);

        db.delete(TABLE_NAME, COLUMN_EMAIL+" = '"+email+"'",null);
        db.close();
    }


    public List<String> getAllEmail(){
        SQLiteDatabase database = instance.getWritableDatabase(PASS_PHASE);

        Cursor cursor = database.rawQuery(String.format("SELECT * FROM '%s';",TABLE_NAME),null);
        List<String> emails = new ArrayList<>();
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                emails.add(email);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();

        return emails;
    }

}
