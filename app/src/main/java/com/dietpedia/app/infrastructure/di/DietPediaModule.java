package com.dietpedia.app.infrastructure.di;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */

import android.app.Application;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(includes = {DbModule.class,})
public final class DietPediaModule {
    private final Application application;

    public DietPediaModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
