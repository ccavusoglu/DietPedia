package com.dietpedia.app.ui.fragments;

import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.dietpedia.app.R;
import com.dietpedia.app.presentation.presenters.MainPresenter;
import com.dietpedia.app.ui.adapters.CategoryAdapter;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class CategoryFragment extends android.support.v4.app.Fragment {
    public interface Listener {
        void onListClicked(long id);
        void onNewListClicked();
    }

    public static String TAG = "CategoryFragment";
    @Inject
    public MainPresenter mMainPresenter;
    @Inject
    public CategoryAdapter mHeadlinesAdapter;

    @Bind(R.id.categories_rv)
    RecyclerView mRecyclerView;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }


}
