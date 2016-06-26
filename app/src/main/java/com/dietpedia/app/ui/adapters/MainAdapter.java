package com.dietpedia.app.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Category;
import com.dietpedia.app.infrastructure.di.ApplicationContext;
import com.dietpedia.app.ui.fragments.MainFragment;
import com.dietpedia.app.util.Utils;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.06.2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    @Inject @ApplicationContext Context context;

    private List<Category> mCategories;
    private MainFragment   mFragment;

    @Inject
    public MainAdapter() {
        mCategories = new ArrayList<>();
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Category category = mCategories.get(position);
        //        Picasso.with(context).load("").into(holder.coverImage);
        holder.titleTextView.setText(category.name());
        holder.descTextView.setText(category.info());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    public void attachAdapter(MainFragment mainFragment) {
        mFragment = mainFragment;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cv_category_title) TextView titleTextView;
        @Bind(R.id.cv_category_desc)  TextView descTextView;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // TODO: use singleton types
            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
            final Typeface type1 = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
            titleTextView.setTypeface(type);
            descTextView.setTypeface(type1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Timber.d(v.toString());
                }
            });
        }
    }
}

