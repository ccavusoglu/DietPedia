package com.dietpedia.app.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.domain.model.DietDetail;
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
            Db.DietDetailTable.onCreate(db);

            // TODO: read all from json
            long categoryId = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Shock Diets")
                    .info("INFO HEREE")
                    .order(1)
                    .build());
            long dietId = db.insert(Db.DietTable.TABLE_NAME, null, new Diet.Builder()
                    .name("Diet 1")
                    .categoryId(categoryId)
                    .info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEINFO HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEINFO HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
                    .build());

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 1")
                    .dietId(dietId)
                    .info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEINFO HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEINFO HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
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

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 2")
                    .dietId(dietId)
                    .info("INFO")
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

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 3")
                    .dietId(dietId)
                    .info("INFO3")
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

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 4")
                    .dietId(dietId)
                    .info("INFO")
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

            db.insert(Db.DietDetailTable.TABLE_NAME, null, new DietDetail.Builder()
                    .name("Day 5")
                    .dietId(dietId)
                    .info("INFO")
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

            long categoryId2 = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Regional Slimming Diets")
                    .info("INFO HERE")
                    .order(3)
                    .build());

            long categoryId3 = db.insert(Db.CategoryTable.TABLE_NAME, null, new Category.Builder()
                    .name("Custom Diets")
                    .info("INFO HERE")
                    .order(4)
                    .build());

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Db.CategoryTable.onUpdate(db);
        Db.DietTable.onUpdate(db);
        Db.DietDetailTable.onUpdate(db);
    }
}

