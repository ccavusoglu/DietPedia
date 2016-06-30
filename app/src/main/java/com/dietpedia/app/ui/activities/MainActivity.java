package com.dietpedia.app.ui.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.ui.fragments.AboutFragment;
import com.dietpedia.app.ui.fragments.DietFragment;
import com.dietpedia.app.ui.fragments.DietListFragment;
import com.dietpedia.app.ui.fragments.MainFragment;
import com.dietpedia.app.util.Utils;
import com.squareup.picasso.Picasso;
import hugo.weaving.DebugLog;

public class MainActivity extends BaseActivity implements MainFragment.Listener, DietListFragment.Listener, DietFragment.Listener, AboutFragment.Listener,
                                                          NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    public static final  String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";
    private static final int    ANIM_DURATION_TOOLBAR    = 300;
    private static final int    ANIM_DURATION_FAB        = 400;

    @Bind(R.id.app_bar)            Toolbar                 mToolbar;
    @Bind(R.id.app_bar_logo)       ImageView               mToolbarLogo;
    @Bind(R.id.main_drawer)        NavigationView          mDrawer;
    @Bind(R.id.activity_main_dl)   DrawerLayout            mDrawerLayout;
    @Bind(R.id.collapsing_image)   ImageView               mImageView;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @Bind(R.id.appbar)             AppBarLayout            mAppBar;
    //    private MenuItem              mSearchMenuItem;
    //    private SearchView            mSearchView;
    private                        ActionBarDrawerToggle   mDrawerToggle;
    private                        boolean                 pendingIntroAnimation;

    public ImageView getToolbarLogo() {
        return mToolbarLogo;
    }

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDietPediaComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mDrawer.setNavigationItemSelectedListener(this);
        //        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        // HACK!
        mDrawer.inflateHeaderView(R.layout.common_drawer_header);
        mDrawer.getHeaderView(0).setVisibility(View.GONE);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;

            mDrawer.setCheckedItem(R.id.navigation_item_0);
            getSupportFragmentManager().beginTransaction().addToBackStack(MainFragment.TAG).replace(R.id.main_content, MainFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //        getMenuInflater().inflate(R.menu.menu_main, menu);
        //
        //        mSearchMenuItem = menu.findItem(R.id.action_search);
        //        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        //        mSearchView.setOnQueryTextListener(this);

        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        mToolbar.setTranslationY(-actionbarSize);
        mToolbarLogo.setTranslationY(-actionbarSize);

        mToolbar.animate().translationY(0).setDuration(ANIM_DURATION_TOOLBAR).setStartDelay(400);
        mToolbarLogo.animate().translationY(0).setDuration(ANIM_DURATION_TOOLBAR).setStartDelay(500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawers();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.navigation_item_0:
                ft.replace(R.id.main_content, MainFragment.newInstance(), MainFragment.TAG);
                ft.addToBackStack(MainFragment.TAG);
                ft.commit();
                break;
            case R.id.navigation_item_1:
            case R.id.navigation_item_2:
            case R.id.navigation_item_3:
            case R.id.navigation_item_4:
                // TODO: read categories onCreate!
                ft.replace(R.id.main_content, DietListFragment.newInstance(item.getTitle().toString(), "INFO HERE"), DietListFragment.TAG);
                ft.addToBackStack(DietListFragment.TAG);
                ft.commit();
                break;
            case R.id.navigation_item_9:
                ft.replace(R.id.main_content, AboutFragment.newInstance(), AboutFragment.TAG);
                ft.addToBackStack(AboutFragment.TAG);
                ft.commit();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_content);

                if (fragment instanceof MainFragment) {
                    disableCollapsing();
                    finish();
                } else if (fragment instanceof DietListFragment) {
                    disableCollapsing();
                    mDrawer.setCheckedItem(R.id.navigation_item_0);

                    getSupportFragmentManager().popBackStack(MainFragment.TAG, 0);
                } else if (fragment instanceof DietFragment) {
                    getSupportFragmentManager().popBackStack(DietListFragment.TAG, 0);
                } else {
                    getSupportFragmentManager().popBackStack();
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void disableCollapsing() {
        mImageView.setVisibility(View.GONE);
        mCollapsingToolbar.setTitleEnabled(false);
        mAppBar.setExpanded(false, false);
    }

    @Override
    public void checkDrawerMenuItem(String name) {
        // TODO: get rid of this hardcoded strings.
        if (name.equals("Shock Diets")) mDrawer.setCheckedItem(R.id.navigation_item_1);
        if (name.equals("Popular Diets")) mDrawer.setCheckedItem(R.id.navigation_item_2);
        if (name.equals("Regional Slimming Diets")) mDrawer.setCheckedItem(R.id.navigation_item_3);
        if (name.equals("Custom Diets")) mDrawer.setCheckedItem(R.id.navigation_item_4);
    }

    @Override
    public void loadBackdrop(int resId) {
        Picasso.with(this).load(resId).resize(512, 512).centerCrop().into(mImageView);
    }

    @Override
    public void enableCollapsing() {
        mImageView.setVisibility(View.VISIBLE);
        mCollapsingToolbar.setTitleEnabled(true);
        mAppBar.setExpanded(true, false);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        query = query.toLowerCase();

        return false;
    }

    public CollapsingToolbarLayout getCollapsingToolbar() {
        return mCollapsingToolbar;
    }
}
