package com.home.grishnak.filckrexplorer.model.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Camera {
    private NameValue name;
    private String id;
    private Image imgUrl;


    public Camera(String name, String id) {
        this.name.set(name);
        this.id = id;
    }

    public Camera() {}

    @Override
    public String toString() {
        return name.toString();
    }


    public String getName() {
        return name.toString();
    }

    public void setName(NameValue name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl.small.toString();
    }

    public void setImgUrl(Image imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static class Image {
        public NameValue small;
        public NameValue large;
    }
}
