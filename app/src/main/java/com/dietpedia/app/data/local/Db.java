package com.dietpedia.app.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import hugo.weaving.DebugLog;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class Db {
    public static final int BOOLEAN_FALSE = 0;
    public static final int BOOLEAN_TRUE = 1;

    private Db() {
        throw new AssertionError("No instances.");
    }

    public static String getString(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index == -1) return "";

        return cursor.getString(index);
    }

    public static boolean getBoolean(Cursor cursor, String columnName) {
        return getInt(cursor, columnName) == BOOLEAN_TRUE;
    }

    public static int getInt(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index == -1) return 0;

        return cursor.getInt(index);
    }

    public static long getLong(Cursor cursor, String columnName) {
        int index = cursor.getColumnIndex(columnName);
        if (index == -1) return 0;

        return cursor.getLong(index);
    }

    public static abstract class DietTable {
        public static final String TABLE_NAME = "diet";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_CATEGORYID = "catId";

        public static final String CREATE = "" + "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_INFO + " TEXT NOT NULL," +
                COLUMN_CATEGORYID + " INT NOT NULL" +
                ")";

        @DebugLog
        public static void onUpdate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + Db.DietTable.TABLE_NAME);
            onCreate(db);
        }

        @DebugLog
        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(Db.DietTable.CREATE);
        }
    }


    public static abstract class DietDetailTable {
        public static final String TABLE_NAME = "dietDetail";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_DIETID = "dietId";
        public static final String COLUMN_BREAKFAST = "breakfast";
        public static final String COLUMN_LUNCH = "lunch";
        public static final String COLUMN_DINNER = "dinner";
        public static final String COLUMN_SNACK1 = "snack1";
        public static final String COLUMN_SNACK2 = "snack2";
        public static final String COLUMN_SNACK3 = "snack3";
        public static final String COLUMN_SNACK4 = "snack4";
        public static final String COLUMN_SNACK5 = "snack5";
        public static final String COLUMN_SNACK6 = "snack6";

        public static final String CREATE = "" + "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_INFO + " TEXT NULL," +
                COLUMN_DIETID + " INT NOT NULL," +
                COLUMN_BREAKFAST + " TEXT NULL," +
                COLUMN_LUNCH + " TEXT NULL," +
                COLUMN_DINNER + " TEXT NULL," +
                COLUMN_SNACK1 + " TEXT NULL," +
                COLUMN_SNACK2 + " TEXT NULL," +
                COLUMN_SNACK3 + " TEXT NULL," +
                COLUMN_SNACK4 + " TEXT NULL," +
                COLUMN_SNACK5 + " TEXT NULL," +
                COLUMN_SNACK6 + " TEXT NULL" +
                ")";

        @DebugLog
        public static void onUpdate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + Db.DietDetailTable.TABLE_NAME);
            onCreate(db);
        }

        @DebugLog
        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(Db.DietDetailTable.CREATE);
        }
    }

    public static abstract class CategoryTable {
        public static final String TABLE_NAME = "category";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_SORT = "sort";

        public static final String CREATE =
                "" + "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT NOT " +
                        "NULL," + COLUMN_INFO + " TEXT NOT NULL," + COLUMN_SORT + " INTEGER NOT NULL" +
                        ")";

        @DebugLog
        public static void onUpdate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + Db.CategoryTable.TABLE_NAME);
            onCreate(db);
        }

        @DebugLog
        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(Db.CategoryTable.CREATE);
        }
    }
}
