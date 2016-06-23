package com.dietpedia.app.presentation.presenters;

import android.app.Application;
import com.dietpedia.app.DietPediaApplication;
import com.dietpedia.app.data.DataManager;
import com.dietpedia.app.ui.views.MainView;
import rx.Subscription;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class MainPresenter extends BasePresenter<MainView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(Application application) {
        this.mDataManager = ((DietPediaApplication) application).getDataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadCategories() {
        checkViewAttached();
    }
}
