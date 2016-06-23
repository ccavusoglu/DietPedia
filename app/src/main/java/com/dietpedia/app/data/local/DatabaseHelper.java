package com.dietpedia.app.data.local;

import android.content.Context;
import android.database.Cursor;
import com.dietpedia.app.domain.model.Category;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import com.squareup.sqlbrite.SqlBrite;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DatabaseHelper {
    private BriteDatabase db;

    public DatabaseHelper(Context context) {
        SqlBrite mSqlBrite = SqlBrite.create();
        // FIXME: 23.06.2016 => before going production
        context.deleteDatabase(DbOpenHelper.DATABASE_NAME);
        db = mSqlBrite.wrapDatabaseHelper(new DbOpenHelper(context), Schedulers.io());
    }

    public Observable<List<Category>> getCategories() {
        final String QUERY =
                "" + "SELECT " + Db.CategoryTable.COLUMN_ID + ", " + Db.CategoryTable.COLUMN_NAME + ", " + Db.CategoryTable.COLUMN_INFO + ", " + Db.CategoryTable
                        .COLUMN_SORT + " FROM " + Db.CategoryTable.TABLE_NAME;

        QueryObservable a = db.createQuery(Db.CategoryTable.TABLE_NAME, QUERY);

        return db.createQuery(Db.CategoryTable.TABLE_NAME, QUERY).mapToList(Category.MAP);
    }

    public Observable<Category> saveCategory(final Category person) {
        return null;
    }
}
