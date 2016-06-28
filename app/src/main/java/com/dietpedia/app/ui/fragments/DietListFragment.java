package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.presentation.presenters.DietListPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.ui.adapters.DietListAdapter;
import com.dietpedia.app.ui.views.DietListView;
import com.google.gson.Gson;
import hugo.weaving.DebugLog;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietListFragment extends Fragment implements DietListView {
    public static final  String TAG       = "DietListFragment";
    private static final String KEY_NAME  = "category_name";
    private static final String KEY_INFO  = "category_info";
    private static final String KEY_QUERY = "query";
    @Inject DietListPresenter mPresenter;
    @Inject DietListAdapter   mAdapter;
    @Bind(R.id.dietlist_rv) RecyclerView mRecyclerView;

    private Listener mListener;

    public static DietListFragment newInstance(String title, String info, String query) {
        Bundle arguments = new Bundle();
        arguments.putString(KEY_NAME, title);
        arguments.putString(KEY_INFO, info);
        arguments.putString(KEY_QUERY, query);

        DietListFragment fragment = new DietListFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((BaseActivity) getActivity()).getDietPediaComponent().inject(this);
        setHasOptionsMenu(true);

        mListener = (Listener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter.attachAdapter(this);

        mPresenter.attachView(this);

        if (getArguments().getString(KEY_QUERY) != null) mPresenter.loadCustomDietList(getArguments().getString(KEY_QUERY));
        else mPresenter.loadDietList(getArguments().getString(KEY_NAME));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietlist, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_news);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        //        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.CENTER);
        //
        //        ((BaseActivity) getActivity()).addShareAction(false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageView a = ((MainActivity) getActivity()).getToolbarLogo();
        a.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.disableCollapsing();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDietList(List<Diet> dietList) {
        mAdapter.setDiets(dietList);
        mAdapter.notifyDataSetChanged();
    }

    public void dietClicked(Diet diet) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, DietFragment.newInstance(diet.name()), DietFragment.TAG);
        ft.addToBackStack(DietFragment.TAG);

        ft.commit();
    }

    public interface Listener {
        void disableCollapsing();
    }
}
