package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.presentation.presenters.MainPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.ui.adapters.MainAdapter;
import com.dietpedia.app.ui.views.MainView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class MainFragment extends Fragment implements MainView {
    public static final String TAG = "MainFragment";
    @Inject MainPresenter mMainPresenter;
    @Inject MainAdapter   mMainAdapter;

    @Bind(R.id.main_rv) RecyclerView mRecyclerView;

    private Listener mListener;

    public static MainFragment newInstance() {
        return new MainFragment();
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

        mMainAdapter.attachAdapter(this);

        mMainPresenter.attachView(this);
        mMainPresenter.loadCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_news);
        //        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        //        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.CENTER);
        //
        //        ((BaseActivity) getActivity()).addShareAction(false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListener.disableCollapsing();
    }

    @Override
    public void onResume() {
        super.onResume();

        ImageView a = ((MainActivity) getActivity()).getToolbarLogo();
        a.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCategories(List<Category> categories) {
        mMainAdapter.setCategories(categories);
        mMainAdapter.notifyDataSetChanged();
    }

    public void categoryClicked(Category category) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, DietListFragment.newInstance(category.name(), category.info()), DietListFragment.TAG);
        ft.addToBackStack(DietListFragment.TAG);

        mListener.checkDrawerMenuItem(category.name());

        ft.commit();
    }

    public interface Listener {
        void disableCollapsing();

        void checkDrawerMenuItem(String name);
    }
}
