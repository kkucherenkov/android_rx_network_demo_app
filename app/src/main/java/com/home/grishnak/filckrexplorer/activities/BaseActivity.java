package com.home.grishnak.filckrexplorer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.home.grishnak.filckrexplorer.App;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) getApplication()).getObjectGraph().inject(this);
    }
}
