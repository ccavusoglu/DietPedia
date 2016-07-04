package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.domain.model.DietDetail;
import com.dietpedia.app.presentation.presenters.DietPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.ui.adapters.DietPagerAdapter;
import com.dietpedia.app.ui.views.DietView;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietFragment extends Fragment implements DietView {
    public static final String TAG       = "DietFragment";
    public static final String KEY_INDEX = "index";

    @Bind(R.id.fragment_diet_vp) ViewPager     mViewPager;
    @Inject                      DietPresenter mPresenter;

    private Listener         mListener;
    private String           mTitle;
    private DietPagerAdapter mAdapter;

    public static DietFragment newInstance(String index) {
        DietFragment fragment = new DietFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_INDEX, index);
        fragment.setArguments(bundle);

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

        mPresenter.attachView(this);
        mPresenter.loadDiet(getArguments().getString(KEY_INDEX));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mListener.enableCollapsing();
        mListener.loadBackdrop();

        setupViewPager(mViewPager);
        ((MainActivity) getActivity()).getTabLayout().setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new DietPagerAdapter(getChildFragmentManager());
        mAdapter.addFragment(new DietMainFragment(), "INFO");
        viewPager.setAdapter(mAdapter);
    }

    // TODO: set title when diet loaded!
    @Override
    @DebugLog
    public void onResume() {
        super.onResume();
        // FIXME: 03.07.2016
        ((MainActivity) getActivity()).getCollapsingToolbar().setTitle(mTitle);

        ImageView a = ((MainActivity) getActivity()).getToolbarLogo();
        a.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showDiet(Diet diet) {
        //        setupViewPager(mViewPager);

        mTitle = diet.name();
        ((MainActivity) getActivity()).getCollapsingToolbar().setTitle(mTitle);

        // setup pager etc.
        if (diet.dietDetails().size() < 2) {
            ((MainActivity) getActivity()).getTabLayout().setVisibility(View.GONE);
        } else {
            for (DietDetail detail : diet.dietDetails()) {
                ((MainActivity) getActivity()).getTabLayout().setVisibility(View.VISIBLE);
                mAdapter.addFragment(DietDetailFragment.newInstance(detail), detail.name());
            }
            mAdapter.notifyDataSetChanged();
            ((MainActivity) getActivity()).getTabLayout().setupWithViewPager(mViewPager);
        }
    }

    public interface Listener {
        void loadBackdrop();

        void enableCollapsing();
    }
}
