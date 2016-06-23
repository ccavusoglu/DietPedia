package com.dietpedia.app.presentation.presenters;

import android.app.Application;
import com.dietpedia.app.DietPediaApplication;
import com.dietpedia.app.data.DataManager;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.ui.views.MainView;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

        mSubscription = mDataManager.getCategories().subscribe(new Subscriber<List<Category>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "There was an error loading.");
            }

            @Override
            public void onNext(List<Category> categories) {
                if (categories != null) {
                    getMvpView().showCategories(categories);
                } else {
                    //// FIXME: 23.06.2016 => null case handling
                    //                    getMvpView().showCategories(categories);
                }
            }
        });
    }
}
