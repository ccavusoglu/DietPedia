package com.dietpedia.app.ui.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.dietpedia.app.R;
import com.dietpedia.app.ui.fragments.DietFragment;
import com.dietpedia.app.ui.fragments.DietListFragment;
import com.dietpedia.app.ui.fragments.MainFragment;
import hugo.weaving.DebugLog;

public class MainActivity extends BaseActivity implements MainFragment.Listener, DietListFragment.Listener, DietFragment.Listener,
                                                          NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    @Bind(R.id.app_bar)          Toolbar        mToolbar;
    @Bind(R.id.main_drawer)      NavigationView mDrawer;
    @Bind(R.id.activity_main_dl) DrawerLayout   mDrawerLayout;

    private MenuItem              mSearchMenuItem;
    private SearchView            mSearchView;
    private ActionBarDrawerToggle mDrawerToggle;

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
            getSupportFragmentManager().beginTransaction().addToBackStack("Main").replace(R.id.main_content, MainFragment.newInstance()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(mSearchMenuItem);
        mSearchView.setOnQueryTextListener(this);

        return true;
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
    public void onListClicked(long id) {

    }

    @Override
    public void onNewListClicked() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawers();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        switch (item.getItemId()) {
            case R.id.navigation_item_1:
            case R.id.navigation_item_2:
            case R.id.navigation_item_3:
            case R.id.navigation_item_4:
                // TODO: read categories onCreate!
                ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right);
                ft.replace(R.id.main_content, DietListFragment.newInstance(item.getTitle().toString(), "INFO HERE", null), DietListFragment.TAG);
                ft.addToBackStack(item.getTitle().toString());
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
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else super.onBackPressed();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        query = query.toLowerCase();

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        //        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right);
        ft.replace(R.id.main_content, DietListFragment.newInstance("", "", query), DietListFragment.TAG);
        ft.addToBackStack(MainFragment.TAG);
        ft.commit();

        return false;
    }
}
