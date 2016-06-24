package com.dietpedia.app.ui.views;

import com.dietpedia.app.domain.model.Diet;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public interface DietView extends MvpView {
    void showDiet(Diet diet);
}
