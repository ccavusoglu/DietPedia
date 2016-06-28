package com.dietpedia.app.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.dietpedia.app.R;
import com.dietpedia.app.ui.adapters.SimpleAdapter;
import com.dietpedia.app.ui.behaviours.SlidingBehavior;

/**
 * Created by Çağatay Çavuşoğlu on 29.06.2016.
 */

@CoordinatorLayout.DefaultBehavior(SlidingBehavior.class)
public class SlidingLayout extends FrameLayout {

    private int mHeaderViewHeight;

    public SlidingLayout(Context context) {
        this(context, null);
    }

    public SlidingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.widget_card, this);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        TextView header = (TextView) findViewById(R.id.header);

        SimpleAdapter adapter = new SimpleAdapter(list);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardLayout, defStyleAttr, 0);
        header.setBackgroundColor(a.getColor(R.styleable.SlidingCardLayout_android_colorBackground, Color.BLACK));
        header.setText(a.getText(R.styleable.SlidingCardLayout_android_text));
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mHeaderViewHeight = findViewById(R.id.header).getMeasuredHeight();
        }
    }

    public int getHeaderHeight() {
        return mHeaderViewHeight;
    }
}
