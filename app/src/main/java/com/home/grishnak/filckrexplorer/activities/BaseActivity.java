package com.home.grishnak.filckrexplorer.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.home.grishnak.filckrexplorer.Application;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((Application) getApplication()).getObjectGraph().inject(this);
    }
}
