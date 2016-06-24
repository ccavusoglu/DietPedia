package com.dietpedia.app.ui.views;

import com.dietpedia.app.domain.model.Diet;

import java.util.List;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public interface DietListView extends MvpView {
    void showDietList(List<Diet> dietList);
}
