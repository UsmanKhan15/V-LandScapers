package com.example.vlandscaper.utilClasses;

import java.io.Serializable;

public class Design implements Serializable {
    String titleDesign, descriptionDesign, imgUrlDesign;

    public Design(String titleDesign, String descriptionDesign, String imgUrlDesign) {
        this.titleDesign = titleDesign;
        this.descriptionDesign = descriptionDesign;
        this.imgUrlDesign = imgUrlDesign;
    }

    public Design() {
    }

    public String getTitleDesign() {
        return titleDesign;
    }

    public void setTitleDesign(String titleDesign) {
        this.titleDesign = titleDesign;
    }

    public String getDescriptionDesign() {
        return descriptionDesign;
    }

    public void setDescriptionDesign(String descriptionDesign) {
        this.descriptionDesign = descriptionDesign;
    }

    public String getImgUrlDesign() {
        return imgUrlDesign;
    }

    public void setImgUrlDesign(String imgUrlDesign) {
        this.imgUrlDesign = imgUrlDesign;
    }
}
