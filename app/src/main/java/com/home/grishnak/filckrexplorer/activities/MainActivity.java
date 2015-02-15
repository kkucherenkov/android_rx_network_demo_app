package com.home.grishnak.filckrexplorer.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;

import com.home.grishnak.filckrexplorer.R;
import com.home.grishnak.filckrexplorer.activities.base.BaseActivity;
import com.home.grishnak.filckrexplorer.fragments.BrandFragment;
import com.home.grishnak.filckrexplorer.fragments.MenuFragment;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MenuFragment.OnMenuItemSelectedListener {

    private MenuFragment menuFragment;
    private BrandFragment brandFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        menuFragment = MenuFragment.newInstance();
        menuFragment.setOnMenuItemSelectedListener(this);
        ft.replace(R.id.menuContent, menuFragment);
        ft.commit();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if (getToolbar() != null) {
            getToolbar().inflateMenu(R.menu.menu_main);
        }

        return true;
    }

    private void setFragment(Fragment fragment) {
        setFragment(fragment, false);
    }


    private void setFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    @Override
    public boolean onProfileSelected() {

        return false;
    }

    @Override
    public boolean onReposSelected() {
        if (brandFragment == null) {
            brandFragment = BrandFragment.newInstance();
        }

        setFragment(brandFragment);
        return true;
    }

    @Override
    public boolean onStarredSelected() {
        return true;
    }

    @Override
    public boolean onWatchedSelected() {
        return true;
    }

    @Override
    public boolean onPeopleSelected() {
        return false;
    }

    @Override
    public void onMenuItemSelected(@NonNull com.home.grishnak.filckrexplorer.model.menu.MenuItem item, boolean changeTitle) {
        if (changeTitle) {
            setTitle(item.text);
        }
        closeMenu();
    }

    @Override
    public boolean onUserEventsSelected() {
        return false;
    }

    @Override
    public boolean onSettingsSelected() {
        return false;
    }

    @Override
    public boolean onAboutSelected() {
        return false;
    }

}
