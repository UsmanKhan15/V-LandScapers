package com.example.vlandscaper.utilClasses;

public class CartData {

    String titlePlantA;
    int price;
    int totalPrice;
    int quantity;


    public CartData(String titlePlantA, int price, int totalPrice, int quantity) {
        this.titlePlantA = titlePlantA;
        this.price = price;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public CartData(String titlePlantA, int price, int quantity) {
        this.titlePlantA = titlePlantA;
        this.price = price;
        this.quantity = quantity;
    }

    public CartData() {
    }

    public String getTitlePlantA() {
        return titlePlantA;
    }

    public void setTitlePlantA(String titlePlantA) {
        this.titlePlantA = titlePlantA;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public int totalPrice(int price, int quantity)
    {
        return price * quantity;
    }

}
