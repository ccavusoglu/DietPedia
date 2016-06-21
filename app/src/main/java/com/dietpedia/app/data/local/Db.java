package com.dietpedia.app.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.dietpedia.app.domain.model.Category;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class Db {
    public Db() { }

    public static abstract class DietTable {
        public static final String TABLE_NAME = "diet";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_CATEGORYID = "catId";
        public static final String COLUMN_BREAKFAST = "breakfast";
        public static final String COLUMN_LUNCH = "lunch";
        public static final String COLUMN_DINNER = "dinner";
        public static final String COLUMN_SNACK1 = "snack1";
        public static final String COLUMN_SNACK2 = "snack2";
        public static final String COLUMN_SNACK3 = "snack3";
        public static final String COLUMN_SNACK4 = "snack4";
        public static final String COLUMN_SNACK5 = "snack5";
        public static final String COLUMN_SNACK6 = "snack6";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INT PRIMARY KEY NOT NULL," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_INFO + " TEXT NOT NULL," +
                COLUMN_CATEGORYID + " INT NOT NULL," +
                COLUMN_BREAKFAST + " TEXT NOT NULL," +
                COLUMN_LUNCH + " TEXT NOT NULL," +
                COLUMN_DINNER + " TEXT NOT NULL," +
                COLUMN_SNACK1 + " TEXT NOT NULL," +
                COLUMN_SNACK2 + " TEXT NOT NULL," +
                COLUMN_SNACK3 + " TEXT NOT NULL," +
                COLUMN_SNACK4 + " TEXT NOT NULL," +
                COLUMN_SNACK5 + " TEXT NOT NULL," +
                COLUMN_SNACK6 + " TEXT NOT NULL," +
                " ); ";

        public static ContentValues toContentValues(Category category) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, category.name);
            return values;
        }

        public static Category parseCursor(Cursor cursor) {
            Category person = new Category();
            person.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            return person;
        }

        public static void onUpdate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + Db.CategoryTable.TABLE_NAME);
            onCreate(db);
        }

        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(Db.CategoryTable.CREATE);
        }
    }

    public static abstract class CategoryTable {
        public static final String TABLE_NAME = "category";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ORDER = "order";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INT PRIMARY KEY NOT NULL," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_ORDER + " INT NOT NULL" +
                " ); ";

        public static ContentValues toContentValues(Category category) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, category.id);
            values.put(COLUMN_NAME, category.name);
            values.put(COLUMN_ORDER, category.order);
            return values;
        }

        public static Category parseCursor(Cursor cursor) {
            Category person = new Category();
            person.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
            return person;
        }

        public static void onUpdate(SQLiteDatabase db) {
            db.execSQL("DROP TABLE IF EXISTS " + Db.CategoryTable.TABLE_NAME);
            onCreate(db);
        }

        public static void onCreate(SQLiteDatabase db) {
            db.execSQL(Db.CategoryTable.CREATE);
        }
    }
}
