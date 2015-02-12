package com.home.grishnak.filckrexplorer.network;

import com.home.grishnak.filckrexplorer.model.pojo.BrandSearchResult;
import com.home.grishnak.filckrexplorer.model.pojo.CameraSearchResult;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface FlickrApi {
    @GET("/?method=flickr.cameras.getBrands&format=json&nojsoncallback=1")
    public Observable<BrandSearchResult> getBrands(@Query("api_key") String apiKey);

    @GET("/?method=flickr.cameras.getBrandModels&format=json&nojsoncallback=1")
    public Observable<CameraSearchResult> getBrandCameras(@Query("api_key") String apiKey, @Query("brand") String brandId);

}
