package com.home.grishnak.filckrexplorer.fragments.base;

import android.app.Fragment;
import android.os.Bundle;

import com.home.grishnak.filckrexplorer.Application;

public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Application) getActivity().getApplication()).getObjectGraph().inject(this);
    }
}
