package com.dietpedia.app.ui.activities;

import android.support.v7.app.AppCompatActivity;
import com.dietpedia.app.DietPediaApplication;
import com.dietpedia.app.infrastructure.di.DietPediaComponent;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected DietPediaComponent mDietPediaComponent;

    public DietPediaComponent getDietPediaComponent() {
        if (mDietPediaComponent == null) {
            mDietPediaComponent = DietPediaApplication.getComponent(this);
        }
        return mDietPediaComponent;
    }
}
