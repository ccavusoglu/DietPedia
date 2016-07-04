package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.DietDetail;
import com.dietpedia.app.presentation.presenters.DietPresenter;
import com.dietpedia.app.ui.activities.BaseActivity;
import com.dietpedia.app.ui.activities.MainActivity;
import hugo.weaving.DebugLog;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietDetailFragment extends Fragment {
    public static final String TAG       = "DietDetailFragment";
    public static final String KEY_INDEX = "index";

    @Bind(R.id.fragment_diet_detail_layout) LinearLayout  mLayout;
    @Inject                          DietPresenter mPresenter;

    private String   mTitle;
    private DietDetail mContent;

    public static DietDetailFragment newInstance(DietDetail detail) {
        DietDetailFragment fragment = new DietDetailFragment();
        fragment.setContent(detail);
        return fragment;
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

    /*
    public void showDiet(Diet diet) {
//        setupViewPager(mViewPager);

        mTitle = diet.name();
        ((MainActivity) getActivity()).getCollapsingToolbar().setTitle(mTitle);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = Utils.dpToPx(8);
        params.setMargins(margin, margin, margin, margin);

        CardView infoCv = (CardView) mLayout.findViewById(R.id.cv_diet_info);
        TextView infoTxt = (TextView) infoCv.findViewById(R.id.diet_info);
        infoTxt.setText(diet.info());
        infoTxt.setTypeface(Utils.FONTTYPE_REGULAR);

        LayoutInflater inflater = LayoutInflater.from((Context) mContext);
        CardView cardViewBreakfast = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
        cardViewBreakfast.setLayoutParams(params);

        TextView breakfast = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal);
        breakfast.setText("Breakfast");
        breakfast.setTypeface(Utils.FONTTYPE_BOLD);

        TextView breakfastDesc = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_desc);
        breakfastDesc.setText(diet.dietDetails().get(0).breakfast());
        breakfastDesc.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView breakfastSnack1 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack1_desc);
        breakfastSnack1.setText(diet.dietDetails().get(0).snack1());
        breakfastSnack1.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView breakfastSnack2 = (TextView) cardViewBreakfast.findViewById(R.id.cv_meal_snack2_desc);
        breakfastSnack2.setText(diet.dietDetails().get(0).snack2());
        breakfastSnack2.setTypeface(Utils.FONTTYPE_LIGHT);

        mLayout.addView(cardViewBreakfast, 1);

        CardView cardViewLunch = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
        cardViewLunch.setLayoutParams(params);

        TextView lunch = (TextView) cardViewLunch.findViewById(R.id.cv_meal);
        lunch.setText("Lunch");
        lunch.setTypeface(Utils.FONTTYPE_BOLD);

        TextView lunchDesc = (TextView) cardViewLunch.findViewById(R.id.cv_meal_desc);
        lunchDesc.setText(diet.dietDetails().get(0).lunch());
        lunchDesc.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView lunchSnack1 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack1_desc);
        lunchSnack1.setText(diet.dietDetails().get(0).snack3());
        lunchSnack1.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView lunchSnack2 = (TextView) cardViewLunch.findViewById(R.id.cv_meal_snack2_desc);
        lunchSnack2.setText(diet.dietDetails().get(0).snack4());
        lunchSnack2.setTypeface(Utils.FONTTYPE_LIGHT);

        mLayout.addView(cardViewLunch, 2);

        CardView cardViewDinner = (CardView) inflater.inflate(R.layout.cardview_meal, mLayout, false);
        cardViewDinner.setLayoutParams(params);

        TextView dinner = (TextView) cardViewDinner.findViewById(R.id.cv_meal);
        dinner.setText("Dinner");
        dinner.setTypeface(Utils.FONTTYPE_BOLD);

        TextView dinnerDesc = (TextView) cardViewDinner.findViewById(R.id.cv_meal_desc);
        dinnerDesc.setText(diet.dietDetails().get(0).dinner());
        dinnerDesc.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView dinnerSnack1 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack1_desc);
        dinnerSnack1.setText(diet.dietDetails().get(0).snack5());
        dinnerSnack1.setTypeface(Utils.FONTTYPE_LIGHT);

        TextView dinnerSnack2 = (TextView) cardViewDinner.findViewById(R.id.cv_meal_snack2_desc);
        dinnerSnack2.setText(diet.dietDetails().get(0).snack6());
        dinnerSnack2.setTypeface(Utils.FONTTYPE_LIGHT);

        mLayout.addView(cardViewDinner, 3);
    }
*/
    public void setContent(DietDetail content) {
        mContent = content;
    }
}
