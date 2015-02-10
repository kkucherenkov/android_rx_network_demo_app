package com.home.grishnak.filckrexplorer.network;

import com.home.grishnak.filckrexplorer.model.pojo.BrandSearchResult;
import com.home.grishnak.filckrexplorer.model.pojo.CameraSearchResult;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

public class FlickrApi {
    private static FlickrApi instance;
    private FlickrApiService flickrApiService;

    public static FlickrApi getInstance() {
        if (instance == null) {
            synchronized (FlickrApi.class) {
                if (instance == null) {
                    instance = new FlickrApi();
                }
            }
        }
        return instance;
    }

    private FlickrApi() {
        RestAdapter restAdapter = buildRestAdapter();
        this.flickrApiService = restAdapter.create(FlickrApiService.class);
    }

    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(ApiConstants.BASE_URL)
                .setConverter(new JacksonConverter())
                .setClient(getHttpClient())
                .build();
    }

    private Client getHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setConnectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.setReadTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        return new OkClient(httpClient);
    }

    public BrandSearchResult getBrands() {
        return this.flickrApiService.getBrands(ApiConstants.FLICKR_KEY);
    }

    public CameraSearchResult getCameras(String brandId) {
        CameraSearchResult result = this.flickrApiService.getBrandCameras(ApiConstants.FLICKR_KEY, brandId);
        return result;
    }

}
