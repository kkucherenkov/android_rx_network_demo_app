package com.home.grishnak.filckrexplorer.model;


import com.home.grishnak.filckrexplorer.model.pojo.Brand;
import com.home.grishnak.filckrexplorer.model.pojo.BrandSearchResult;
import com.home.grishnak.filckrexplorer.model.pojo.Camera;
import com.home.grishnak.filckrexplorer.model.pojo.CameraSearchResult;
import com.home.grishnak.filckrexplorer.network.ApiConstants;
import com.home.grishnak.filckrexplorer.network.FlickrApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class FlickrModel {

    @Inject
    FlickrApiService flickrApiService;

    public FlickrModel(FlickrApiService flickrApiService) {
        this.flickrApiService = flickrApiService;
    }

    public Observable<List<Brand>> getBrands() {
        return Observable.create((Subscriber<? super BrandSearchResult> subscriber) -> {
            subscriber.onNext(flickrApiService.getBrands(ApiConstants.FLICKR_KEY));
            subscriber.onCompleted();
        }).doOnNext((searchResult)->{
            if (!searchResult.stat.equals(ApiConstants.OK_STATE)) {
                throw new IllegalStateException("get brands error");
            }
        }).map(BrandSearchResult::getBrands);

    }

    public Observable<List<Camera>> getCamerasOfBrand(String brandId) {
        return Observable.create((Subscriber<? super CameraSearchResult> subscriber) -> {
            subscriber.onNext(flickrApiService.getBrandCameras(ApiConstants.FLICKR_KEY, brandId));
            subscriber.onCompleted();
        }).map(CameraSearchResult::getCameras);
    }
}
