package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.DietDetail;
import com.dietpedia.app.presentation.presenters.DietPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.util.Utils;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietDetailFragment extends Fragment {
    public static final String TAG = "DietDetailFragment";
    public static final String KEY_INDEX = "index";

    @Bind(R.id.fragment_diet_detail_layout) LinearLayout mLayout;
    @Inject DietPresenter mPresenter;

    private DietDetail mContent;

    public static DietDetailFragment newInstance(DietDetail detail) {
        DietDetailFragment fragment = new DietDetailFragment();
        fragment.setContent(detail);
        return fragment;
    }

    public void setContent(DietDetail content) {
        mContent = content;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet_detail, container, false);
        ButterKnife.bind(this, view);

        showContent();
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

    @DebugLog
    public void showContent() {
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = Utils.dpToPx(8);
        params.setMargins(margin, margin, margin, margin);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        CardView infoCv = (CardView) mLayout.findViewById(R.id.cv_diet_info);
        TextView infoTxt = (TextView) infoCv.findViewById(R.id.diet_info);
        infoTxt.setText(mContent.info());
        infoTxt.setTypeface(Utils.FONTTYPE_REGULAR);

        if (mContent.info() == null) {
            infoCv.setVisibility(View.GONE);
        }

        int index = 1;
        if (mContent.breakfast() != null) {
            CardView cardViewBreakfast = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
            cardViewBreakfast.setLayoutParams(params);

            TextView breakfast = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal);
            breakfast.setText("Breakfast");
            breakfast.setTypeface(Utils.FONTTYPE_BOLD);

            TextView breakfastDesc = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_desc);
            breakfastDesc.setText(mContent.breakfast());
            breakfastDesc.setTypeface(Utils.FONTTYPE_LIGHT);

            TextView breakfastSnack1 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack1_desc);
            if (mContent.snack1() != null) {
                breakfastSnack1.setText(mContent.snack1());
                breakfastSnack1.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                breakfastSnack1.setVisibility(View.GONE);
                cardViewBreakfast.findViewById(R.id.cv_meal_snack1).setVisibility(View.GONE);
            }

            TextView breakfastSnack2 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack2_desc);
            if (mContent.snack2() != null) {
                breakfastSnack2.setText(mContent.snack2());
                breakfastSnack2.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                breakfastSnack2.setVisibility(View.GONE);
                cardViewBreakfast.findViewById(R.id.cv_meal_snack2).setVisibility(View.GONE);
            }

            mLayout.addView(cardViewBreakfast, index++);
        }

        if (mContent.lunch() != null) {
            CardView cardViewLunch = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
            cardViewLunch.setLayoutParams(params);

            TextView lunch = (TextView) cardViewLunch.findViewById(R.id.cv_meal);
            lunch.setText("Lunch");
            lunch.setTypeface(Utils.FONTTYPE_BOLD);

            TextView lunchDesc = (TextView) cardViewLunch.findViewById(R.id.cv_meal_desc);
            lunchDesc.setText(mContent.lunch());
            lunchDesc.setTypeface(Utils.FONTTYPE_LIGHT);

            TextView lunchSnack1 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack1_desc);
            if (mContent.snack3() != null) {
                lunchSnack1.setText(mContent.snack3());
                lunchSnack1.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                lunchSnack1.setVisibility(View.GONE);
                cardViewLunch.findViewById(R.id.cv_meal_snack1).setVisibility(View.GONE);
            }

            TextView lunchSnack2 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack2_desc);
            if (mContent.snack4() != null) {
                lunchSnack2.setText(mContent.snack4());
                lunchSnack2.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                lunchSnack2.setVisibility(View.GONE);
                cardViewLunch.findViewById(R.id.cv_meal_snack2).setVisibility(View.GONE);
            }

            mLayout.addView(cardViewLunch, index++);
        }

        if (mContent.lunch() != null) {
            CardView cardViewDinner = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
            cardViewDinner.setLayoutParams(params);

            TextView dinner = (TextView) cardViewDinner.findViewById(R.id.cv_meal);
            dinner.setText("Dinner");
            dinner.setTypeface(Utils.FONTTYPE_BOLD);

            TextView dinnerDesc = (TextView) cardViewDinner.findViewById(R.id.cv_meal_desc);
            dinnerDesc.setText(mContent.dinner());
            dinnerDesc.setTypeface(Utils.FONTTYPE_LIGHT);

            TextView dinnerSnack1 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack1_desc);
            if (mContent.snack5() != null) {
                dinnerSnack1.setText(mContent.snack5());
                dinnerSnack1.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                dinnerSnack1.setVisibility(View.GONE);
                cardViewDinner.findViewById(R.id.cv_meal_snack1).setVisibility(View.GONE);
            }


            TextView dinnerSnack2 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack2_desc);
            if (mContent.snack6() != null) {
                dinnerSnack2.setText(mContent.snack6());
                dinnerSnack2.setTypeface(Utils.FONTTYPE_LIGHT);
            } else {
                dinnerSnack2.setVisibility(View.GONE);
                cardViewDinner.findViewById(R.id.cv_meal_snack2).setVisibility(View.GONE);
            }

            mLayout.addView(cardViewDinner, index++);
        }
    }
}
