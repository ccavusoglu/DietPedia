package com.dietpedia.app.data.local;

import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.util.Log;
import com.dietpedia.app.domain.model.Category;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DatabaseHelper {
    private BriteDatabase db;

    public DatabaseHelper(Context context) {
        SqlBrite mSqlBrite = SqlBrite.create();
        db = mSqlBrite.wrapDatabaseHelper(new DbOpenHelper(context), Schedulers.io());
    }

    public Observable<Category> getCategory() {
        return null;
    }

    public Observable<Category> saveCategory(final Category person) {
        return null;
    }
}
