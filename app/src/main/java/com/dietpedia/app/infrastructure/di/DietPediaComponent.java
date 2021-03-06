package com.dietpedia.app.infrastructure.di;

import android.content.Context;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.ui.fragments.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
@Singleton
@Component(modules = DietPediaModule.class)
public interface DietPediaComponent {
    void inject(MainFragment fragment);

    void inject(DietFragment fragment);

    void inject(DietDetailFragment fragment);

    void inject(DietMainFragment fragment);

    void inject(DietListFragment fragment);

    void inject(MainActivity activity);

    @ApplicationContext
    Context context();
}
