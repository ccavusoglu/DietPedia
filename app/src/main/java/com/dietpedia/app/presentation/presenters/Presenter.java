package com.dietpedia.app.presentation.presenters;

import com.dietpedia.app.ui.views.MvpView;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public interface Presenter<V extends MvpView> {
    void attachView(V mvpView);
    void detachView();
}

