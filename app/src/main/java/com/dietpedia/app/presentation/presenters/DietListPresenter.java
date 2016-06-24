package com.dietpedia.app.presentation.presenters;

import android.app.Application;
import com.dietpedia.app.DietPediaApplication;
import com.dietpedia.app.data.DataManager;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.ui.views.DietListView;
import hugo.weaving.DebugLog;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public class DietListPresenter extends BasePresenter<DietListView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DietListPresenter(Application application) {
        this.mDataManager = ((DietPediaApplication) application).getDataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @DebugLog
    public void loadDietList(String name) {
        checkViewAttached();

        mSubscription = mDataManager.getDietList(name).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<Diet>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "There was an error loading.");
            }

            @Override
            @DebugLog
            public void onNext(List<Diet> diets) {
                if (diets != null) {
                    Timber.d("onNext: " + Thread.currentThread().getName());
                    getMvpView().showDietList(diets);
                } else {
                    //// FIXME: 23.06.2016 => null case handling
                    //                    getMvpView().showCategories(categories);
                }
            }
        });
    }
}
