package com.dietpedia.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.infrastructure.di.ApplicationContext;
import com.dietpedia.app.ui.fragments.DietListFragment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class DietListAdapter extends RecyclerView.Adapter<DietListAdapter.DietListViewHolder> {
    @Inject @ApplicationContext Context context;

    private List<Diet> mDiets;
    private DietListFragment mFragment;

    @Inject
    public DietListAdapter() {
        mDiets = new ArrayList<>();
    }

    public void setDiets(List<Diet> diets) {
        mDiets = diets;
    }

    @Override
    public DietListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_diet, parent, false);
        return new DietListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DietListViewHolder holder, int position) {
        Diet diet = mDiets.get(position);
        //        Picasso.with(context).load("").into(holder.coverImage);
        //        holder.titleTextView.setText(headline.getTitle());
        //        holder.descTextView.setText(headline.getDesc());
        //        holder.dateTextView.setText(headline.getDate());
    }

    @Override
    public int getItemCount() {
        return mDiets.size();
    }

    public void attachAdapter(DietListFragment fragment) {
        mFragment = fragment;
    }

    class DietListViewHolder extends RecyclerView.ViewHolder {
        //        @Bind(R.id.cv_headline) TextView titleTextView;
        //        @Bind(R.id.cv_partial) TextView descTextView;
        //        @Bind(R.id.cv_date) TextView dateTextView;
        //        @Bind(R.id.cv_headline_cont) TextView mContTextView;
        //        @Bind(R.id.cv_headline_share) ImageButton mShareButton;
        //
        public DietListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //
            //            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
            //            final Typeface type1 = Typeface.createFromAsset(context.getAssets(), Utils.LIGHT_FONT);
            //            titleTextView.setTypeface(type);
            //            descTextView.setTypeface(type1);
            //            dateTextView.setTypeface(type);
            //            mContTextView.setTypeface(type);
            //            mContTextView.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) {
            //                    mFragment.bringDetail(mHeadlines.get(getLayoutPosition()));
            //                }
            //            });
            //
            //            mShareButton.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View v) {
            //                    mFragment.bringShareSheet(mHeadlines.get(getLayoutPosition()));
            //                }
            //            });
            //        }
        }
    }
}
