package com.home.grishnak.filckrexplorer.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.home.grishnak.filckrexplorer.Application;
import com.home.grishnak.filckrexplorer.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public abstract class BaseActivity extends ActionBarActivity {
    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.inject(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ((Application) getApplication()).getObjectGraph().inject(this);
    }

    protected abstract int getLayoutResource();

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }
}
