package com.tianzhi.shop520.entity.store;

/**
 * Created by thinkpad on 2017/10/23.
 */

public class StoreItemBag {
    private Class<?> activity;
    private int imageResource;
    private String title;

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
