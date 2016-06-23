package com.dietpedia.app.ui.views;

import com.dietpedia.app.domain.model.Category;

import java.util.List;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public interface MainView extends MvpView {
    void showCategories(List<Category> categories);
}

