package com.dietpedia.app.infrastructure.di;

import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.ui.fragments.CategoryFragment;
import com.dietpedia.app.ui.fragments.DietFragment;
import com.dietpedia.app.ui.fragments.MainFragment;
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
        void inject(CategoryFragment fragment);
        void inject(MainActivity activity);
}
