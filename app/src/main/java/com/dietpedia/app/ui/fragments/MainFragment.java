package com.dietpedia.app.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import com.dietpedia.app.DietPediaApplication;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class MainFragment extends Fragment {
    public interface Listener {
        void onListClicked(long id);
        void onNewListClicked();
    }

    @Inject
    BriteDatabase db;

    @Override
    public void onAttach(Activity activity) {
        if (!(activity instanceof Listener)) {
            throw new IllegalStateException("Activity must implement fragment Listener.");
        }

        super.onAttach(activity);
        DietPediaApplication.getComponent(activity).inject(this);
        setHasOptionsMenu(true);
    }
}
