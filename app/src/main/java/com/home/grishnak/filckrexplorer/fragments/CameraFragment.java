package com.home.grishnak.filckrexplorer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.home.grishnak.filckrexplorer.model.FlickrModel;
import com.home.grishnak.filckrexplorer.model.pojo.Brand;
import com.home.grishnak.filckrexplorer.model.pojo.Camera;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class CameraFragment extends ListFragment {

    private static final String BRAND_PARAM = "brand_fragment_parameter";

    private String mBrand;

    private BrandFragment.OnFragmentInteractionListener mListener;

    private FlickrModel flickrModel;

    public static CameraFragment newInstance(String brand) {
        CameraFragment fragment = new CameraFragment();
        Bundle args = new Bundle();
        args.putString(BRAND_PARAM, brand);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CameraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mBrand = getArguments().getString(BRAND_PARAM);
        }

        flickrModel = FlickrModel.getInstance();
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
                        setListAdapter(new ArrayAdapter(
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
        try {
            mListener = (BrandFragment.OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


}
