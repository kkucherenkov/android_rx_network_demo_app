package com.home.grishnak.filckrexplorer.activities.base;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;

import com.home.grishnak.filckrexplorer.Application;
import com.home.grishnak.filckrexplorer.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public abstract class BaseActivity extends ActionBarActivity {
    @InjectView(R.id.toolbar) Toolbar toolbar;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.inject(this);
        if (isToolbarEnabled()) {
            if (toolbar != null) {
                toolbar.setTitle(R.string.app_name);
                setSupportActionBar(toolbar);

                if (mDrawerLayout != null) {
                    actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0);
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
                    mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
                }
            }
        }
        ((Application) getApplication()).getObjectGraph().inject(this);
    }


    protected abstract int getLayoutResource();

    public boolean isToolbarEnabled() {
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (actionBarDrawerToggle != null) {
            actionBarDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout != null) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                    mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                    mDrawerLayout.openDrawer(Gravity.START);
                }
            }
        }
        return false;
    }

    @Override
    public void setTitle(CharSequence title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        if (toolbar != null) {
            toolbar.setTitle(titleId);
        } else {
            super.setTitle(titleId);
        }
    }


    @Override
    public void onBackPressed() {
        if ((mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START))) {
            closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    public void closeMenu() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }
    }

    public void reload() {
        getContent();
    }

    protected void getContent() {

    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }
}
