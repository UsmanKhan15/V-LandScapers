package com.example.vlandscaper.utilClasses;

public class OrderPlace {

    String totalAmount;
    String totalItems;
    String itemNames;
    String userEmail;

    public OrderPlace(String totalAmount, String totalItems, String itemNames, String userEmail) {
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.itemNames = itemNames;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }
}
