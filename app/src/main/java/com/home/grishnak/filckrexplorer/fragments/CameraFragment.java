package com.home.grishnak.filckrexplorer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.home.grishnak.filckrexplorer.fragments.base.BaseListFragment;
import com.home.grishnak.filckrexplorer.model.FlickrModel;
import com.home.grishnak.filckrexplorer.model.pojo.Camera;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CameraFragment extends BaseListFragment {

    private static final String BRAND_PARAM = "brand_fragment_parameter";
    @Inject
    FlickrModel flickrModel;
    private String mBrand;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CameraFragment() {
    }

    public static CameraFragment newInstance(String brand) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(BRAND_PARAM, brand);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBrand = getArguments().getString(BRAND_PARAM);
        }

        flickrModel.getCamerasOfBrand(mBrand)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Camera>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Camera> list) {
                        setListAdapter(new ArrayAdapter<>(
                                getActivity(),
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1,
                                list));
                    }
                });

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }
}
