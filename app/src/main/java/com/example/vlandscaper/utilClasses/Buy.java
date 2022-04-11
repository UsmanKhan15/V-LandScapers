package com.example.vlandscaper.utilClasses;

public class Buy {

    String titleHome, category, titlePlantAccessories, imgURL;
    int totalPrice;
    int quantity;
    int price;
    int id;
    public static int idCart = 0;

    String price1, quantity1, totalPrice1;

    public Buy() {
    }

    public Buy(String titleHome, String category, String titlePlantAccessories, String imgURL, int totalPrice, int quantity, int price, int id) {
        this.titleHome = titleHome;
        this.category = category;
        this.titlePlantAccessories = titlePlantAccessories;
        this.imgURL = imgURL;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }

    public Buy(String titlePlantAccessories, int price, int quantity, int totalPrice) {
        this.titlePlantAccessories = titlePlantAccessories;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.price = price;
        idCart++;
    }

    public static String getIdCart() {
        return idCart+"";
    }

    public String getTitleHome() {
        return titleHome;
    }

    public void setTitleHome(String titleHome) {
        this.titleHome = titleHome;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitlePlantAccessories() {
        return titlePlantAccessories;
    }

    public void setTitlePlantAccessories(String titlePlantAccessories) {
        this.titlePlantAccessories = titlePlantAccessories;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int totalPrice(int price, int quantity)
    {
        return price * quantity;
    }
}
