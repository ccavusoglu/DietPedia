package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.presentation.presenters.DietPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.views.DietView;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietFragment extends Fragment implements DietView {
    public static final String TAG = "DietFragment";

    private final String mIndex;

    @Inject DietPresenter mPresenter;

    private DietFragment(String index) {
        mIndex = index;
    }

    public static DietFragment newInstance(String index) {
        return new DietFragment(index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((BaseActivity) getActivity()).getDietPediaComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.attachView(this);
        mPresenter.loadDiet(mIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietlist, container, false);
        ButterKnife.bind(this, view);

        //        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_news);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        //        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.CENTER);
        //
        //        ((BaseActivity) getActivity()).addShareAction(false);

        return view;
    }

    @Override
    public void showDiet(Diet diet) {

    }

    public interface Listener {
        void onListClicked(long id);

        void onNewListClicked();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
