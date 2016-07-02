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
public abstract class Diet {
    public static Func1<Cursor, Diet> MAP = new Func1<Cursor, Diet>() {
        @Override
        public Diet call(final Cursor cursor) {
            long id = Db.getLong(cursor, Db.DietTable.COLUMN_ID);
            String name = Db.getString(cursor, Db.DietTable.COLUMN_NAME);
            String info = Db.getString(cursor, Db.DietTable.COLUMN_INFO);
            long categoryId = Db.getInt(cursor, Db.DietTable.COLUMN_CATEGORYID);


            return new AutoValue_Diet(id, name, info, categoryId);
        }
    };

    public abstract long id();

    public abstract String name();

    public abstract String info();

    public abstract long categoryId();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(Db.DietTable.COLUMN_ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(Db.DietTable.COLUMN_NAME, name);
            return this;
        }

        public Builder info(String info) {
            values.put(Db.DietTable.COLUMN_INFO, info);
            return this;
        }

        public Builder categoryId(long categoryId) {
            values.put(Db.DietTable.COLUMN_CATEGORYID, categoryId);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
