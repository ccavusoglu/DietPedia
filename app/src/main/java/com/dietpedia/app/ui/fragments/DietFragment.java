package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.dietpedia.app.ui.views.DietView;
import com.dietpedia.app.util.Utils;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietFragment extends Fragment implements DietView {
    public static final String TAG = "DietFragment";
    public static final String KEY_INDEX = "index";

    @Bind(R.id.fragment_diet_layout) LinearLayout mLayout;
    @Inject DietPresenter mPresenter;

    private Listener mListener;
    private String mTitle;

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
        // TODO: custom diet images
        mListener.loadBackdrop(R.drawable.diet1);
    }

    // TODO: set title when diet loaded!
    @Override
    @DebugLog
    public void onResume() {
        super.onResume();
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
        mTitle = diet.name();

        LayoutInflater inflater = LayoutInflater.from((Context) mListener);
        CardView cardViewBreakfast = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);

        TextView breakfast = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal);
        breakfast.setText("Breakfast");
        breakfast.setTypeface(Utils.typeBold);

        TextView breakfastDesc = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_desc);
        breakfastDesc.setText(diet.breakfast());
        breakfastDesc.setTypeface(Utils.typeRegular);

        TextView breakfastSnack1 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack1_desc);
        breakfastSnack1.setText(diet.snack1());
        breakfastSnack1.setTypeface(Utils.typeRegular);

        TextView breakfastSnack2 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack2_desc);
        breakfastSnack2.setText(diet.snack2());
        breakfastSnack2.setTypeface(Utils.typeRegular);

        mLayout.addView(cardViewBreakfast, 0);

        CardView cardViewLunch = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);

        TextView lunch = (TextView) cardViewLunch.findViewById(R.id.cv_meal);
        lunch.setText("Lunch");
        lunch.setTypeface(Utils.typeBold);

        TextView lunchDesc = (TextView) cardViewLunch.findViewById(R.id.cv_meal_desc);
        lunchDesc.setText(diet.lunch());
        lunchDesc.setTypeface(Utils.typeRegular);

        TextView lunchSnack1 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack1_desc);
        lunchSnack1.setText(diet.snack3());
        lunchSnack1.setTypeface(Utils.typeRegular);

        TextView lunchSnack2 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack2_desc);
        lunchSnack2.setText(diet.snack4());
        lunchSnack2.setTypeface(Utils.typeRegular);

        mLayout.addView(cardViewLunch, 1);

        CardView cardViewDinner = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);

        TextView dinner = (TextView) cardViewDinner.findViewById(R.id.cv_meal);
        dinner.setText("Dinner");
        dinner.setTypeface(Utils.typeBold);

        TextView dinnerDesc = (TextView) cardViewDinner.findViewById(R.id.cv_meal_desc);
        dinnerDesc.setText(diet.dinner());
        dinnerDesc.setTypeface(Utils.typeRegular);

        TextView dinnerSnack1 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack1_desc);
        dinnerSnack1.setText(diet.snack5());
        dinnerSnack1.setTypeface(Utils.typeRegular);

        TextView dinnerSnack2 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack2_desc);
        dinnerSnack2.setText(diet.snack6());
        dinnerSnack2.setTypeface(Utils.typeRegular);

        mLayout.addView(cardViewDinner, 2);
    }

    public interface Listener {
        void loadBackdrop(int resId);

        void enableCollapsing();
    }
}
