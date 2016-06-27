package com.dietpedia.app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.domain.model.Diet;
import hugo.weaving.DebugLog;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dietpedia.db";
    public static final int DATABASE_VERSION = 1;

    @DebugLog
    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    @DebugLog
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            Db.CategoryTable.onCreate(db);
            Db.DietTable.onCreate(db);

            // TODO: read all from json
            long categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Shock Diets")
                    .info("INFO HERE")
                    .order(1)
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 1")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 2")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 3")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 4")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 5")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 6")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 7")
                    .categoryId(categoryId)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());

            long categoryId1 = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Popular Diets")
                    .info("INFO HERE")
                    .order(2)
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 2")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 21")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 22")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 23")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 24")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 25")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 26")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 27")
                    .categoryId(categoryId1)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());

            long categoryId2 = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Regional Slimming Diets")
                    .info("INFO HERE")
                    .order(3)
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 31")
                    .categoryId(categoryId2)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 32")
                    .categoryId(categoryId2)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 33")
                    .categoryId(categoryId2)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 34")
                    .categoryId(categoryId2)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 35")
                    .categoryId(categoryId2)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());

            long categoryId3 = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Custom Diets")
                    .info("INFO HERE")
                    .order(4)
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 41")
                    .categoryId(categoryId3)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 42")
                    .categoryId(categoryId3)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());
            db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 43")
                    .categoryId(categoryId3)
                    .info("INFO HERE")
                    .breakfast("breakfast")
                    .lunch("lunch")
                    .dinner("dinner")
                    .snack1("snack1")
                    .snack2("snack2")
                    .snack3("snack3")
                    .snack4("snack4")
                    .snack5("snack5")
                    .snack6("snack6")
                    .build());

            final String QUERY =
                    "" + "SELECT " + Db.CategoryTable.COLUMN_ID + ", " + Db.CategoryTable.COLUMN_NAME + ", " + Db.CategoryTable.COLUMN_INFO + ", " + Db.CategoryTable
                            .COLUMN_SORT + " FROM " + Db.CategoryTable.TABLE_NAME;

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Db.CategoryTable.onUpdate(db);
        Db.DietTable.onUpdate(db);
    }
}

