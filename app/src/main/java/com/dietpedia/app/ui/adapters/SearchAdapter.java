package com.dietpedia.app.ui.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.dietpedia.app.R;
import com.dietpedia.app.ui.activities.MainActivity;
import com.dietpedia.app.util.Utils;

import java.util.List;

/**
 * Created by ccavusoglu on 01.07.2016.
 */
public class SearchAdapter extends CursorAdapter {
    private final MainActivity mActivity;
    private       List<String> mItems;
    private       TextView     mText;

    public SearchAdapter(Context context) {
        super(context, null, false);

        // TODO: abstract this
        mActivity = (MainActivity) context;
    }

    public void setItems(List<String> items) {
        mItems = items;
    }

    @Override
    public View newView(Context context, final Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.search_dropdown_item, parent, false);
        mText = (TextView) view.findViewById(R.id.search_item);

        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.searchResultClick(((TextView) view.findViewById(R.id.search_item)).getText().toString());
            }
        });
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (mItems.isEmpty()) {
            // TODO: no result show
            mText.setText("No result");
            view.setClickable(false);
            return;
        }

        view.setClickable(true);
        mText.setTypeface(Utils.FONTTYPE_REGULAR);
        mText.setText(mItems.get(cursor.getPosition()));
    }

    public void clear() {
        if (mItems != null) mItems.clear();
        if (getCursor() != null) getCursor().close();
    }
}
