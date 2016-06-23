package com.dietpedia.app.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import com.dietpedia.app.R;
import com.dietpedia.app.presentation.presenters.MainPresenter;
import com.dietpedia.app.ui.adapters.DietListAdapter;
import com.dietpedia.app.ui.views.DietListView;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietListFragment extends Fragment implements DietListView {
    public static final String TAG = "DietListFragment";

    @Inject MainPresenter mMainPresenter;
    @Bind(R.id.dietlist_rv) RecyclerView mRecyclerView;
    private DietListAdapter mDietListAdapter;

    public static DietListFragment newInstance() {
        return new DietListFragment();
    }

    @Override
    public void showDietList() {

    }

    public interface Listener {
        void onListClicked(long id);

        void onNewListClicked();
    }
}
