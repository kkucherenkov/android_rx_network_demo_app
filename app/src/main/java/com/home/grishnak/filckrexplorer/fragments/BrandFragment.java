package com.home.grishnak.filckrexplorer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.home.grishnak.filckrexplorer.R;
import com.home.grishnak.filckrexplorer.model.FlickrModel;
import com.home.grishnak.filckrexplorer.model.pojo.Brand;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BrandFragment extends BaseFragment {
    @Inject
    FlickrModel flickrModel;

    public BrandFragment() {

    }

    // TODO: Rename and change types of parameters
    public static BrandFragment newInstance() {
        return new BrandFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flickrModel.getBrands()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Brand>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Brand> list) {
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
        Brand brand = (Brand) l.getAdapter().getItem(position);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, CameraFragment.newInstance(brand.getId()))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

}
