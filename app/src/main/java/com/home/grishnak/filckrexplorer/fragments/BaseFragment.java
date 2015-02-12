package com.home.grishnak.filckrexplorer.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.home.grishnak.filckrexplorer.Application;

public abstract class BaseFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getActivity().getApplication()).getObjectGraph().inject(this);
    }
}
