package com.example.vlandscaper.utilClasses;

public class Home {
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
