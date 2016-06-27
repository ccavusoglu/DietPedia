package com.dietpedia.app.presentation.presenters;

import android.app.Application;
import com.dietpedia.app.DietPediaApplication;
import com.dietpedia.app.data.DataManager;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.ui.views.DietView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import javax.inject.Inject;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public class DietPresenter extends BasePresenter<DietView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DietPresenter(Application application) {
        this.mDataManager = ((DietPediaApplication) application).getDataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadDiet(String index) {
        checkViewAttached();

        mSubscription = mDataManager.getDiet(index).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Diet>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Timber.e(e, "There was an error loading.");
            }

            @Override
            public void onNext(Diet diet) {
                if (diet != null) {
                    getMvpView().showDiet(diet);
                } else {
                    //// FIXME: 23.06.2016 => null case handling
                    //                    getMvpView().showCategories(categories);
                }
            }
        });
    }
}
