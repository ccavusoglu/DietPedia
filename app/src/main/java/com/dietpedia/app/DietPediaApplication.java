package com.dietpedia.app;

import android.app.Application;
import android.content.Context;
import com.dietpedia.app.data.DataManager;
import com.dietpedia.app.infrastructure.di.DaggerDietPediaComponent;
import com.dietpedia.app.infrastructure.di.DietPediaComponent;
import com.dietpedia.app.infrastructure.di.DietPediaModule;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietPediaApplication extends Application {
    private DataManager mDataManager;
    private DietPediaComponent mainComponent;

    public static DietPediaComponent getComponent(Context context) {
        return ((DietPediaApplication) context.getApplicationContext()).mainComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mainComponent = DaggerDietPediaComponent.builder().dietPediaModule(new DietPediaModule(this)).build();

        mDataManager = new DataManager(this, Schedulers.io());
    }

    public DataManager getDataManager() { return mDataManager; }
}
