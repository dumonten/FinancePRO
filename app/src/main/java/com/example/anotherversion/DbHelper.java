package com.example.anotherversion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.anotherversion.model.Category;
import com.example.anotherversion.model.CategoryItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DbHelper extends SQLiteOpenHelper {

    /* Main Settings */
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "main.db";
    public static final int DB_VERSION = 3;
    /* ============= */

    /* Tables */
    // Pass anasteisha 50 оттенков grey (⌒‿⌒)
    public static final String USER_ANS ="ans";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANS ="answers";
    public static final String COLUMN_QUES ="questions";
    public static final String CREATE_TABLE_ANS = "CREATE TABLE `" + USER_ANS + "` (`"
            + COLUMN_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `"
            + COLUMN_ANS + "` TEXT, `"
            + COLUMN_QUES + "` TEXT);";

    // Categories romchonsh
    public static final String TABLE_CAT ="cat";
    public static final String COLUMN_CAT_ID = "_id";
    public static final String COLUMN_CAT_NAME ="cat_name";
    public static final String CREATE_TABLE_CAT = "CREATE TABLE `" + TABLE_CAT + "` (`"
            + COLUMN_CAT_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `"
            + COLUMN_CAT_NAME + "` TEXT);";

    public static final String TABLE_CAT_ITEMS ="cat_items";
    public static final String COLUMN_CAT_ITEMS_ID = "_id";
    public static final String COLUMN_CAT_ITEMS_CAT_ID = "cat_id";
    public static final String COLUMN_CAT_ITEMS_NAME ="cat_item_name";
    public static final String COLUMN_CAT_ITEMS_COST ="cat_item_cost";
    public static final String COLUMN_CAT_ITEMS_DATE ="cat_item_date";
    public static final String COLUMN_CAT_ITEMS_DATESEC ="cat_item_date_sec";
    public static final String CREATE_TABLE_CAT_ITEMS = "CREATE TABLE `" + TABLE_CAT_ITEMS + "` (`"
            + COLUMN_CAT_ITEMS_ID + "` INTEGER PRIMARY KEY AUTOINCREMENT, `"
            + COLUMN_CAT_ITEMS_NAME + "` TEXT, `"
            + COLUMN_CAT_ITEMS_CAT_ID + "` INTEGER, `"
            + COLUMN_CAT_ITEMS_COST + "` REAL, `"
            + COLUMN_CAT_ITEMS_DATE + "` TEXT, `"
            + COLUMN_CAT_ITEMS_DATESEC + "` INTEGER);";

    /* ================== */

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ANS);
        db.execSQL(CREATE_TABLE_CAT);
        db.execSQL(CREATE_TABLE_CAT_ITEMS);

        addCatHardcoded(db, "Доходы");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_ANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT_ITEMS);

        onCreate(db);
    }

    /* Categories romchonsh */

    public  void addCatHardcoded(SQLiteDatabase db, String name)
    {
        ContentValues values = new ContentValues();
        values.put (COLUMN_CAT_NAME, name);
        db.insert (TABLE_CAT, null, values);
    }

    public void addCat(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (COLUMN_CAT_NAME, name);
        db.insert (TABLE_CAT, null, values);
        db.close();
    }

    public void deleteCat(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery;
        selectQuery = "DELETE FROM `" + TABLE_CAT + "` WHERE `" + COLUMN_CAT_ID + "` = '" + id + "'";
        db.execSQL(selectQuery);
        selectQuery = "DELETE FROM `" + TABLE_CAT_ITEMS + "` WHERE `" + COLUMN_CAT_ITEMS_CAT_ID + "` = '" + id + "'";
        db.execSQL(selectQuery);
        db.close();
    }

    public void addCatItem(String name, float cost, int id, long curDateMilsec)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());
        values.put (COLUMN_CAT_ITEMS_NAME, name);
        values.put (COLUMN_CAT_ITEMS_CAT_ID, id);
        values.put (COLUMN_CAT_ITEMS_COST, cost);
        values.put (COLUMN_CAT_ITEMS_DATE, currentDateAndTime);
        values.put (COLUMN_CAT_ITEMS_DATESEC, curDateMilsec);
        db.insert (TABLE_CAT_ITEMS, null, values);
        db.close();
    }

    public void deleteCatItem(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "DELETE FROM `" + TABLE_CAT_ITEMS + "` WHERE `" + COLUMN_CAT_ITEMS_ID + "` = '" + id + "'";
        db.execSQL(selectQuery);
        db.close();
    }

    public boolean checkExistsCat(String name)
    {
        String selectQuery = "SELECT * FROM `" + TABLE_CAT + "` WHERE `" + COLUMN_CAT_NAME + "` = '" + name + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            return true;
        }

        cursor.close();
        db.close();
        return  false;
    }

    public List getCategories()
    {
        List<Category> categoryList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `" + TABLE_CAT + "`";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(0);
            String currentName = cursor.getString(1);
            categoryList.add(new Category(currentID, currentName));
        }

        cursor.close();
        db.close();

        return categoryList;
    }

    public List getCategoriesItems(int id_search)
    {
        List<CategoryItem> categoryItemsList = new ArrayList<>();
        String selectQuery = "SELECT * FROM `" + TABLE_CAT_ITEMS + "` WHERE `" + COLUMN_CAT_ITEMS_CAT_ID + "` = '" + id_search + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(0);
            String currentName = cursor.getString(1);
            float currentCost = cursor.getFloat(3);
            String currentDate = cursor.getString(4);
            long currentDateSec = cursor.getInt(5);
            categoryItemsList.add(new CategoryItem(currentID, currentName, currentCost, currentDate, currentDateSec));
        }

        cursor.close();
        db.close();

        return categoryItemsList;
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
