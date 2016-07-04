package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.presentation.presenters.DietPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.util.Utils;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietMainFragment extends Fragment {
    public static final String TAG = "DietMainFragment";

    @Bind(R.id.fragment_diet_main_layout) LinearLayout mLayout;
    @Inject DietPresenter mPresenter;

    private Diet mContent;

    public static DietMainFragment newInstance(Diet diet) {
        DietMainFragment fragment = new DietMainFragment();
        fragment.setContent(diet);
        return fragment;
    }

    public void setContent(Diet diet) {
        mContent = diet;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((BaseActivity) getActivity()).getDietPediaComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_main, container, false);
        ButterKnife.bind(this, view);

        if (mContent != null) showContent();
        return view;
    }

    // TODO: set title when diet loaded!
    @Override
    @DebugLog
    public void onResume() {
        super.onResume();

        ImageView a = ((MainActivity) getActivity()).getToolbarLogo();
        a.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void showContent() {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = Utils.dpToPx(8);
        params.setMargins(margin, margin, margin, margin);

        CardView infoCv = (CardView) mLayout.findViewById(R.id.cv_diet_info);
        TextView infoTxt = (TextView) infoCv.findViewById(R.id.diet_info);
        infoTxt.setText(mContent.info());
        infoTxt.setTypeface(Utils.FONTTYPE_REGULAR);
    }
}
