package com.dietpedia.app.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.dietpedia.app.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Çağatay Çavuşoğlu on 03.07.2016.
 */

public class DietPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments      = new ArrayList<>();
    private final List<String>   mFragmentTitles = new ArrayList<>();

    public DietPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    /**
     * This method is only gets called when we invoke {@link #notifyDataSetChanged()} on this adapter.
     * Returns the index of the currently active fragments.
     * There could be minimum two and maximum three active fragments(suppose we have 3 or more  fragments to show).
     * If there is only one fragment to show that will be only active fragment.
     * If there are only two fragments to show, both will be in active state.
     * PagerAdapter keeps left and right fragments of the currently visible fragment in ready/active state so that it could be shown immediate on swiping.
     * Currently Active Fragments means one which is currently visible one is before it and one is after it.
     *
     * @param object Active Fragment reference
     * @return Returns the index of the currently active fragments.
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        List<Fragment> fragmentsList = mFragmentManager.getFragments();
//        if (fragmentsList != null && position <= (fragmentsList.size() - 1)) {
//            SampleFragment sampleFragment = (SampleFragment) fragmentsList.get(position);
//            Utils.DummyItem dummyItem = mDummyItems.get(position);
            //If the current data of the fragment changed, set the new data
//            if (!dummyItem.equals(sampleFragment.getDummyItem())) {
//                sampleFragment.setDummyItem(dummyItem);
//                Log.i(TAG, "********instantiateItem position:" + position + " FragmentDataChanged");
//            }
//        } else {
            //No fragment instance available for this index, create a new fragment by calling getItem() and show the data.
//            Log.i(TAG, "********instantiateItem position:" + position + " NewFragmentCreated");
//        }

        return super.instantiateItem(container, position);
    }
}