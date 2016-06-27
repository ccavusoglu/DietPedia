package com.dietpedia.app.data.local;

import android.content.Context;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.domain.model.Diet;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import hugo.weaving.DebugLog;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DatabaseHelper {
    private BriteDatabase db;
    private static String ALIAS_CATEGORY = "c";
    private static String ALIAS_DIET = "d";

    @DebugLog
    public DatabaseHelper(Context context) {
        SqlBrite mSqlBrite = SqlBrite.create();
        // FIXME: 23.06.2016 => before going production
        context.deleteDatabase(DbOpenHelper.DATABASE_NAME);
        db = mSqlBrite.wrapDatabaseHelper(new DbOpenHelper(context), Schedulers.io());
    }

    public Observable<List<Category>> getCategories() {
        final String QUERY =
                "" + "SELECT " + Db.CategoryTable.COLUMN_ID + ", " + Db.CategoryTable.COLUMN_NAME + ", " + Db.CategoryTable.COLUMN_INFO + ", " + Db
                        .CategoryTable.COLUMN_SORT + " FROM " + Db.CategoryTable.TABLE_NAME;

        return db.createQuery(Db.CategoryTable.TABLE_NAME, QUERY).mapToList(Category.MAP);
    }

    public Observable<List<Diet>> getDietList(String name) {
        final String QUERY =
                "" + "SELECT "
                + ALIAS_DIET + "." + Db.DietTable.COLUMN_ID + ", "
                + ALIAS_DIET + "." + Db.DietTable.COLUMN_NAME + ", "
                + ALIAS_DIET + "." + Db.DietTable.COLUMN_INFO + ""
                + " FROM " + Db.DietTable.TABLE_NAME + " AS " + ALIAS_DIET
                + " LEFT OUTER JOIN " + Db.CategoryTable.TABLE_NAME + " AS " + ALIAS_CATEGORY
                        + " ON " + ALIAS_DIET + "." + Db.DietTable.COLUMN_CATEGORYID + " = "
                        + ALIAS_CATEGORY + "." + Db.CategoryTable.COLUMN_ID
                + " WHERE " + ALIAS_CATEGORY + "." + Db.CategoryTable.COLUMN_NAME + " = '" + name + "'";

        return db.createQuery(Arrays.asList(Db.DietTable.TABLE_NAME, Db.CategoryTable.TABLE_NAME), QUERY).mapToList(Diet.MAP);
    }

    public Observable<Diet> getDiet(String name) {
        final String QUERY =
                "" + "SELECT * FROM " + Db.DietTable.TABLE_NAME + " WHERE " + Db.DietTable.COLUMN_NAME + " = '" + name + "'";

        return db.createQuery(Db.DietTable.TABLE_NAME, QUERY).mapToOne(Diet.MAP);
    }

    public Observable<Category> saveCategory(final Category category) {
        return null;
    }
}
