package com.dietpedia.app.domain.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Nullable;
import com.dietpedia.app.data.local.Db;
import com.google.auto.value.AutoValue;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.List;

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
            List<DietDetail> details = new ArrayList<DietDetail>();

            return new AutoValue_Diet(id, name, info, categoryId, details);
        }
    };

    public static Func1<Cursor, Diet> MAP_DETAIL = new Func1<Cursor, Diet>() {
        @Override
        public Diet call(final Cursor cursor) {
            long id = Db.getLong(cursor, Db.DietTable.COLUMN_ID);
            String name = Db.getString(cursor, Db.DietTable.COLUMN_NAME);
            String info = Db.getString(cursor, Db.DietTable.COLUMN_INFO);
            long categoryId = Db.getInt(cursor, Db.DietTable.COLUMN_CATEGORYID);

            List<DietDetail> details = new ArrayList<DietDetail>(cursor.getCount());

            while (!cursor.isAfterLast()) {
                long idD = Db.getLong(cursor, Db.DietDetailTable.COLUMN_ID);
                String nameD = Db.getString(cursor, Db.DietDetailTable.COLUMN_NAME);
                String infoD = Db.getString(cursor, Db.DietDetailTable.COLUMN_INFO);
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

                details.add(new AutoValue_DietDetail(idD, nameD, infoD, dietId, breakfast, lunch, dinner, snack1, snack2, snack3, snack4, snack5, snack6));

                cursor.moveToNext();
            }

            cursor.close();

            return new AutoValue_Diet(id, name, info, categoryId, details);
        }
    };

    public abstract long id();

    public abstract String name();

    @Nullable
    public abstract String info();

    public abstract long categoryId();

    public abstract List<DietDetail> dietDetails();

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
