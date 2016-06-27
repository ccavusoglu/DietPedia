package com.dietpedia.app.data;

import android.content.Context;
import com.dietpedia.app.data.local.DatabaseHelper;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.domain.model.Diet;
import hugo.weaving.DebugLog;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DataManager {
    private DatabaseHelper mDatabaseHelper;
    private Scheduler mScheduler;

    @DebugLog
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

    public Observable<List<Diet>> getDietList(String name) {
        return mDatabaseHelper.getDietList(name);
    }

    public Observable<List<Diet>> getCustomDietList(String query) {
        return mDatabaseHelper.getCustomDietList(query);
    }

    public Observable<Diet> getDiet(String index) {
        return mDatabaseHelper.getDiet(index);
    }
}
