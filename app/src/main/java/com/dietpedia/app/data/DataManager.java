package com.dietpedia.app.data;

import android.content.Context;
import com.dietpedia.app.data.local.DatabaseHelper;
import com.dietpedia.app.domain.model.Category;
import rx.Observable;
import rx.Scheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DataManager {
    private DatabaseHelper mDatabaseHelper;
    private Scheduler mScheduler;

    public DataManager(Context context, Scheduler scheduler) {
        mDatabaseHelper = new DatabaseHelper(context);
        mScheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return mScheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        mScheduler = scheduler;
    }

    public Observable<Category> saveCategory(Category person) {
        return mDatabaseHelper.saveCategory(person);
    }

    public Observable<List<Category>> getCategories() {
        return mDatabaseHelper.getCategories();
    }
}
