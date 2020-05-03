package me.tandeneck.blogdemo.entity;

import androidx.annotation.DrawableRes;

/**
 * author:tandeneck
 * time:2020/5/3
 * description:
 */
public class HomeEntity {
    private String title;
    private @DrawableRes int res;
    private Class<?> clazz;

    public HomeEntity(String title, int res, Class<?> clazz) {
        this.title = title;
        this.res = res;
        this.clazz = clazz;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
