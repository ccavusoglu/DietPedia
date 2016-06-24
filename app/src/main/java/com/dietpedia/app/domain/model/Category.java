package com.dietpedia.app.domain.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.dietpedia.app.data.local.Db;
import com.google.auto.value.AutoValue;
import rx.functions.Func1;

/**
 * Created by Çağatay Çavuşoğlu on 21.06.2016.
 */

@AutoValue
public abstract class Category {
    public static Func1<Cursor, Category> MAP = new Func1<Cursor, Category>() {
        @Override
        public Category call(final Cursor cursor) {
            long id = Db.getLong(cursor, Db.CategoryTable.COLUMN_ID);
            String name = Db.getString(cursor, Db.CategoryTable.COLUMN_NAME);
            String info = Db.getString(cursor, Db.CategoryTable.COLUMN_INFO);
            int order = Db.getInt(cursor, Db.CategoryTable.COLUMN_SORT);
            return new AutoValue_Category(id, name, info, order);
        }
    };

    public abstract long id();

    public abstract String name();

    public abstract String info();

    public abstract int order();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(Db.CategoryTable.COLUMN_ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(Db.CategoryTable.COLUMN_NAME, name);
            return this;
        }

        public Builder info(String info) {
            values.put(Db.CategoryTable.COLUMN_INFO, info);
            return this;
        }

        public Builder order(int order) {
            values.put(Db.CategoryTable.COLUMN_SORT, order);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
