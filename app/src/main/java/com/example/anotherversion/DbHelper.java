package com.example.anotherversion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    /* Main Settings */
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "main.db";
    public static final int DB_VERSION = 1;
    /* ============= */

    /* Tables */
    // Pass anasteisha
    public static final String USER_ANS ="ans";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANS ="answers";
    public static final String COLUMN_QUES ="questions";
    public static final String CREATE_TABLE_ANS = "CREATE TABLE " + USER_ANS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ANS + " TEXT,"
            + COLUMN_QUES + " TEXT);";

    // Categories romchonsh
    public static final String TABLE_CAT ="cat";
    public static final String COLUMN_CAT_ID = "_id";
    public static final String COLUMN_CAT_NAME ="cat_name";
    public static final String CREATE_TABLE_CAT = "CREATE TABLE `" + TABLE_CAT + "` (`"
            + COLUMN_CAT_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `"
            + COLUMN_CAT_NAME + "` TEXT);";
    /* ================== */

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ANS);
        db.execSQL(CREATE_TABLE_CAT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_ANS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CAT);
        onCreate(db);
    }

    /* Categories romchonsh */

    public void addCat(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (COLUMN_CAT_NAME, name);
        db.insert (TABLE_CAT, null, values);
        db.close();
    }

    public boolean checkExistsCat(String name)
    {
        String selectQuery = "SELECT * FROM `" + TABLE_CAT + "` WHERE `" + COLUMN_CAT_NAME + "` = '" + name + "'";

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount()>0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
            db.close();
        }

        return  false;
    }

    /* ================== */

    public void addAnswers (String questions, String answers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (COLUMN_QUES, questions);
        values.put(COLUMN_ANS, answers);
        long id = db.insert (USER_ANS, null, values);
        db.close();
        Log.d (TAG, "answered questions - "+id+answers);
    }

    public boolean getAnswers (String answers){
        String selectQuery = "select * from  " + USER_ANS + " where "+ COLUMN_ANS + " = " +
                "'"+answers+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
}
