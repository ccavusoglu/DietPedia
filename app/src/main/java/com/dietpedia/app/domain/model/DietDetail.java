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
public abstract class DietDetail {
    public static Func1<Cursor, DietDetail> MAP = new Func1<Cursor, DietDetail>() {
        @Override
        public DietDetail call(final Cursor cursor) {
            long id = Db.getLong(cursor, Db.DietDetailTable.COLUMN_ID);
            String name = Db.getString(cursor, Db.DietDetailTable.COLUMN_NAME);
            String info = Db.getString(cursor, Db.DietDetailTable.COLUMN_INFO);
            long dietId = Db.getInt(cursor, Db.DietDetailTable.COLUMN_DIETID);

            String breakfast = Db.getString(cursor, Db.DietDetailTable.COLUMN_BREAKFAST);
            String lunch = Db.getString(cursor, Db.DietDetailTable.COLUMN_LUNCH);
            String dinner = Db.getString(cursor, Db.DietDetailTable.COLUMN_DINNER);
            String snack1 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK1);
            String snack2 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK2);
            String snack3 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK3);
            String snack4 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK4);
            String snack5 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK5);
            String snack6 = Db.getString(cursor, Db.DietDetailTable.COLUMN_SNACK6);

            return new AutoValue_DietDetail(id, name, info, dietId, breakfast, lunch, dinner, snack1, snack2, snack3, snack4, snack5, snack6);
        }
    };

    public abstract long id();

    public abstract String name();

    public abstract String info();

    public abstract long dietId();

    public abstract String breakfast();

    public abstract String lunch();

    public abstract String dinner();

    public abstract String snack1();

    public abstract String snack2();

    public abstract String snack3();

    public abstract String snack4();

    public abstract String snack5();

    public abstract String snack6();

    public static final class Builder {
        private final ContentValues values = new ContentValues();

        public Builder id(long id) {
            values.put(Db.DietDetailTable.COLUMN_ID, id);
            return this;
        }

        public Builder name(String name) {
            values.put(Db.DietDetailTable.COLUMN_NAME, name);
            return this;
        }

        public Builder info(String info) {
            values.put(Db.DietDetailTable.COLUMN_INFO, info);
            return this;
        }

        public Builder dietId(long dietId) {
            values.put(Db.DietDetailTable.COLUMN_DIETID, dietId);
            return this;
        }

        public Builder breakfast(String breakfast) {
            values.put(Db.DietDetailTable.COLUMN_BREAKFAST, breakfast);
            return this;
        }

        public Builder lunch(String lunch) {
            values.put(Db.DietDetailTable.COLUMN_LUNCH, lunch);
            return this;
        }

        public Builder dinner(String dinner) {
            values.put(Db.DietDetailTable.COLUMN_DINNER, dinner);
            return this;
        }

        public Builder snack1(String snack1) {
            values.put(Db.DietDetailTable.COLUMN_SNACK1, snack1);
            return this;
        }

        public Builder snack2(String info) {
            values.put(Db.DietDetailTable.COLUMN_SNACK2, info);
            return this;
        }

        public Builder snack3(String snack3) {
            values.put(Db.DietDetailTable.COLUMN_SNACK3, snack3);
            return this;
        }

        public Builder snack4(String snack4) {
            values.put(Db.DietDetailTable.COLUMN_SNACK4, snack4);
            return this;
        }

        public Builder snack5(String snack5) {
            values.put(Db.DietDetailTable.COLUMN_SNACK5, snack5);
            return this;
        }

        public Builder snack6(String snack6) {
            values.put(Db.DietDetailTable.COLUMN_SNACK6, snack6);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
