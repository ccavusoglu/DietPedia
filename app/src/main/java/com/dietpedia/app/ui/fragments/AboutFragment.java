package com.dietpedia.app.ui.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dietpedia.app.R;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.util.Utils;

/**
 * Created by Çağatay Çavuşoğlu on 22.06.2016.
 */
public class AboutFragment extends Fragment {
    public static final String TAG = "AboutFragment";

    private Listener mListener;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        setHasOptionsMenu(true);

        mListener = (Listener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        final Typeface type = Typeface.createFromAsset(getContext().getAssets(), Utils.REGULAR_FONT);
        ((TextView) view.findViewById(R.id.about_disclaimer)).setTypeface(type);

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

    public interface Listener {
        void disableCollapsing();
    }
}
