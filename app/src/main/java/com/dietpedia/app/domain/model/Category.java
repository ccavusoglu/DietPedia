package com.dietpedia.app.domain.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import com.dietpedia.app.data.local.Db;
import com.google.auto.value.AutoValue;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 21.06.2016.
 */

@AutoValue
public abstract class Category {
    public static Func1<Cursor, List<Category>> MAP = new Func1<Cursor, List<Category>>() {
        @Override
        public List<Category> call(final Cursor cursor) {
            try {
                List<Category> values = new ArrayList<>(cursor.getCount());

                while (cursor.moveToNext()) {
                    long id = Db.getLong(cursor, Db.CategoryTable.COLUMN_ID);
                    String name = Db.getString(cursor, Db.CategoryTable.COLUMN_NAME);
                    String info = Db.getString(cursor, Db.CategoryTable.COLUMN_INFO);
                    int order = Db.getInt(cursor, Db.CategoryTable.COLUMN_ORDER);
                    values.add(new AutoValue_Category(id, name, info, order));
                }
                return values;
            } finally {
                cursor.close();
            }
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
            values.put(Db.CategoryTable.COLUMN_ORDER, order);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
