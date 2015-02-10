package com.home.grishnak.filckrexplorer.utils;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.home.grishnak.filckrexplorer.model.pojo.Brand;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SubscriptionUtils {
    private SubscriptionUtils() { }

    public static Subscription subscribeTextViewText(final Observable<String> observable,
                                                     final TextView textView) {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((String s) -> {
                    textView.setText(s);
                });
    }
}
