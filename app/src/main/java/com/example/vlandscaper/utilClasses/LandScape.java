package com.example.vlandscaper.utilClasses;

public class LandScape {

    String name, imgUrl, modelUrl;

    public LandScape(String name, String imgUrl, String modelUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.modelUrl = modelUrl;
    }

    public LandScape() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }
}
