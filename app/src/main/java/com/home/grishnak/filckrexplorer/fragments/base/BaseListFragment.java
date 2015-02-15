package com.home.grishnak.filckrexplorer.fragments.base;

import android.app.ListFragment;
import android.os.Bundle;

import com.home.grishnak.filckrexplorer.Application;

public abstract class BaseListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getActivity().getApplication()).getObjectGraph().inject(this);
    }
}
