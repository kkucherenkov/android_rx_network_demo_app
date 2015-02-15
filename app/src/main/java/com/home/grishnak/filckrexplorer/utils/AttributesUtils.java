package com.home.grishnak.filckrexplorer.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StyleRes;

import com.home.grishnak.filckrexplorer.R;

public class AttributesUtils {


    public static int getPrimaryLightColor(Context context, @StyleRes int style) {
        return context.getResources().getColor(getAttributeId(context, style, R.attr.colorPrimary));
    }

    public static int getPrimaryDarkColor(Context context, @StyleRes int style) {
        return context.getResources().getColor(getAttributeId(context, style, R.attr.colorPrimaryDark));
    }

    public static int getPrimaryColor(Context context, @StyleRes int style) {
        return context.getResources().getColor(getAttributeId(context, style, R.attr.colorPrimary));
    }

    public static int getAccentColor(Context context, @StyleRes int style) {
        return context.getResources().getColor(getAttributeId(context, style, R.attr.colorAccent));
    }

    public static int getAttributeId(Context context, int style, int attr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(style, new int[]{attr});
        return a.getResourceId(0, 0);
    }

}