package com.home.grishnak.filckrexplorer.Modules;

import android.content.Context;
import android.util.Log;

import com.home.grishnak.filckrexplorer.App;
import com.home.grishnak.filckrexplorer.activities.MainActivity;
import com.home.grishnak.filckrexplorer.fragments.BrandFragment;
import com.home.grishnak.filckrexplorer.fragments.CameraFragment;
import com.home.grishnak.filckrexplorer.model.FlickrModel;
import com.home.grishnak.filckrexplorer.network.ApiConstants;
import com.home.grishnak.filckrexplorer.network.FlickrApiService;
import com.home.grishnak.filckrexplorer.network.JacksonConverter;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

@Module(
        library = true,
        injects = {MainActivity.class, BrandFragment.class, CameraFragment.class}
)
public class ApplicationModule {

    private Context application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public FlickrApiService providesFlickrApiService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        try {
            File cacheDir = new File(application.getCacheDir(), "http-cache");
            Cache cache = new Cache(cacheDir, 1024 * 1024 * 5l); // 5 MB HTTP Cache

            okHttpClient.setCache(cache);
        } catch (IOException e) {
            Log.e("ApplicationModule", "Could not create cache directory for HTTP client: " + e.getMessage(), e);
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.BASE_URL)
                .setConverter(new JacksonConverter())
                .setClient(new OkClient(okHttpClient))
                .build();
        return restAdapter.create(FlickrApiService.class);

    }

    @Provides
    @Singleton
    public FlickrModel providesFlickrModel(FlickrApiService service) {
        return new FlickrModel(service);
    }
}
