package com.example.vlandscaper.utilClasses;

public class OrderPlace {

    String totalAmount;
    String totalItems;
    String itemNames;
    String userName;
    String pushId;

    public OrderPlace(String totalAmount, String totalItems, String itemNames, String userName, String pushId) {
        this.totalAmount = totalAmount;
        this.totalItems = totalItems;
        this.itemNames = itemNames;
        this.userName = userName;
        this.pushId = pushId;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
