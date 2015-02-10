package com.home.grishnak.filckrexplorer.model;


import com.home.grishnak.filckrexplorer.model.pojo.Brand;
import com.home.grishnak.filckrexplorer.model.pojo.BrandSearchResult;
import com.home.grishnak.filckrexplorer.model.pojo.Camera;
import com.home.grishnak.filckrexplorer.model.pojo.CameraSearchResult;
import com.home.grishnak.filckrexplorer.network.ApiConstants;
import com.home.grishnak.filckrexplorer.network.FlickrApi;

import java.util.IllegalFormatException;
import java.util.List;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;

public class FlickrModel {
    private static FlickrModel instance;

    public static FlickrModel getInstance() {
        if (instance == null) {
            synchronized (FlickrModel.class) {
                if (instance == null) {
                    instance = new FlickrModel();
                }
            }
        }
        return instance;
    }

    private FlickrModel() {}



    public Observable<List<Brand>> getBrands() {
        return Observable.create((Subscriber<? super BrandSearchResult> subscriber) -> {
            subscriber.onNext(FlickrApi.getInstance().getBrands());
            subscriber.onCompleted();
        }).doOnNext((searchResult)->{
            if (!searchResult.stat.equals(ApiConstants.OK_STATE)) {
                throw new IllegalStateException("get brands error");
            }
        }).map(BrandSearchResult::getBrands);

    }

    public Observable<List<Camera>> getCamerasOfBrand(String brandId) {
        return Observable.create((Subscriber<? super CameraSearchResult> subscriber) -> {
            subscriber.onNext(FlickrApi.getInstance().getCameras(brandId));
            subscriber.onCompleted();
        }).map(CameraSearchResult::getCameras);
    }
}
