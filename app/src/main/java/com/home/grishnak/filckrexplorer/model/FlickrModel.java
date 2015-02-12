package com.home.grishnak.filckrexplorer.model;


import com.home.grishnak.filckrexplorer.model.pojo.Brand;
import com.home.grishnak.filckrexplorer.model.pojo.BrandSearchResult;
import com.home.grishnak.filckrexplorer.model.pojo.Camera;
import com.home.grishnak.filckrexplorer.model.pojo.CameraSearchResult;
import com.home.grishnak.filckrexplorer.network.ApiConstants;
import com.home.grishnak.filckrexplorer.network.FlickrApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class FlickrModel {

    @Inject
    FlickrApi flickrApi;

    public FlickrModel(FlickrApi flickrApi) {
        this.flickrApi = flickrApi;
    }

    public Observable<List<Brand>> getBrands() {
        return flickrApi.getBrands(ApiConstants.FLICKR_KEY)
                .doOnNext((searchResult) -> {
                    if (!searchResult.stat.equals(ApiConstants.OK_STATE)) {
                        throw new IllegalStateException("get brands error");
                    }
                }).map(BrandSearchResult::getBrands);

    }

    public Observable<List<Camera>> getCamerasOfBrand(String brandId) {
        return flickrApi.getBrandCameras(ApiConstants.FLICKR_KEY, brandId)
                .map(CameraSearchResult::getCameras);
    }
}
