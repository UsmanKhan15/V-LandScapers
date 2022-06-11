package com.example.vlandscaper.utilClasses;

import java.io.Serializable;

public class Home implements Serializable {
    String title, description, imgURL;

    public Home() {
    }

    public Home(String title, String description, String imgURL) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgURL() {
        return imgURL;
    }
}
