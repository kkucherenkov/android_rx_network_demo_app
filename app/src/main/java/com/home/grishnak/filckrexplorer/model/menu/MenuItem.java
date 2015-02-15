package com.home.grishnak.filckrexplorer.model.menu;

import android.support.annotation.StringRes;


public class MenuItem {
    public int parentId;
    public int text;
    public int id;
    public int color = -1;

    public MenuItem(int id, int parentId, @StringRes int text) {
        this.text = text;
        this.id = id;
        this.parentId = parentId;
    }

    public MenuItem(int id, int parentId, @StringRes int text, int color) {
        this(id, parentId, text);
        this.color = color;
    }
}