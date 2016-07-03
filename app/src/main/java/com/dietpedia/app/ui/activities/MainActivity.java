package com.dietpedia.app.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.domain.model.Diet;
import com.dietpedia.app.presentation.presenters.MainPresenter;
import com.dietpedia.app.ui.adapters.SearchAdapter;
import com.dietpedia.app.ui.fragments.AboutFragment;
import com.dietpedia.app.ui.fragments.DietFragment;
import com.dietpedia.app.ui.fragments.DietListFragment;
import com.dietpedia.app.ui.fragments.MainFragment;
import com.dietpedia.app.util.Utils;
import com.jakewharton.rxbinding.widget.RxSearchView;
import com.squareup.picasso.Picasso;
import hugo.weaving.DebugLog;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Bind(R.id.main_tabs) TabLayout mTabLayout;
    @Inject MainPresenter mMainPresenter;
    //    private MenuItem              mSearchMenuItem;
    //    private SearchView            mSearchView;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean               pendingIntroAnimation;
    private MenuItem      mSearchMenuItem;
    private SearchView    mSearchView;
    private SearchAdapter mSearchAdapter;

    public TabLayout getTabLayout() {
        return mTabLayout;
    }

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

        // Search
        mSearchAdapter = new SearchAdapter(this);

        if (savedInstanceState == null) {
            pendingIntroAnimation = true;

            mDrawer.setCheckedItem(R.id.navigation_item_0);
            getSupportFragmentManager().beginTransaction().addToBackStack(MainFragment.TAG).replace(R.id.main_content, MainFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        mSearchMenuItem = menu.findItem(R.id.action_search);

        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

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
                    getSupportFragmentManager().popBackStack(MainFragment.TAG, 0);
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
        mTabLayout.setVisibility(View.GONE);
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
        mTabLayout.setVisibility(View.VISIBLE);
        mCollapsingToolbar.setTitleEnabled(true);
        mAppBar.setExpanded(true, false);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        //        query = query.toLowerCase();
        Timber.d(query);

        // TODO: split rx chain to use presenter
        RxSearchView.queryTextChanges(mSearchView).throttleLast(100, TimeUnit.MILLISECONDS).debounce(200, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread()).doOnNext(new Action1<CharSequence>() {
            @Override
            public void call(CharSequence charSequence) {
                Timber.d("searching: " + charSequence);
            }
        }).flatMap(new Func1<CharSequence, Observable<List<Diet>>>() {
            @Override
            public Observable<List<Diet>> call(CharSequence charSequence) {
                Timber.d("flatMap: " + charSequence);
                return mMainPresenter.searchDiets(charSequence.toString());
            }
        }).map(new Func1<List<Diet>, List<String>>() {
            @Override
            public List<String> call(List<Diet> diets) {
                Timber.d("map: " + diets.size());
                List<String> res = new ArrayList<String>();

                for (Diet diet : diets) {
                    res.add(diet.name());
                }

                return res;
            }
        }).filter(new Func1<List<String>, Boolean>() {
            @Override
            public Boolean call(List<String> results) {
                final boolean empty = results.isEmpty();
                if (empty) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mSearchAdapter.clear();
                        }
                    });
                }
                return !empty;
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<String>>() {
            @Override
            public void call(final List<String> results) {
                // Cursor
                String[] columns = new String[]{"_id", "search_item"};
                Object[] temp = new Object[]{0, "default"};

                final MatrixCursor cursor = new MatrixCursor(columns);

                for (int i = 0; i < results.size(); i++) {
                    temp[0] = i;
                    temp[1] = results.get(i);
                    cursor.addRow(temp);
                }

                // SearchView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSearchAdapter.changeCursor(cursor);
                        mSearchAdapter.setItems(results);

                        mSearchView.setSuggestionsAdapter(mSearchAdapter);
                    }
                });

                //                mSearchAdapter.addAll(results);
                //                mSearchAdapter.notifyDataSetChanged();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                // nothing
            }
        });
        return false;
    }

    public CollapsingToolbarLayout getCollapsingToolbar() {
        return mCollapsingToolbar;
    }

    public void searchResultClick(String s) {
        mSearchView.clearFocus();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, DietFragment.newInstance(s), DietFragment.TAG);
        ft.addToBackStack(DietFragment.TAG);

        ft.commit();
    }
}
