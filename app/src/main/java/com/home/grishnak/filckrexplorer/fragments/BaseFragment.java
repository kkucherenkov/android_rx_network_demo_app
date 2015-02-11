package com.home.grishnak.filckrexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.home.grishnak.filckrexplorer.App;

public abstract class BaseFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getObjectGraph().inject(this);
    }
}
