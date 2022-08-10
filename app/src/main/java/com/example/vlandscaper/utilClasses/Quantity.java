package com.example.vlandscaper.utilClasses;

public class Quantity {
    String itemName;
    int quantityQ;

    public Quantity() {
    }

    public Quantity(String itemName, int quantityQ) {
        this.itemName = itemName;
        this.quantityQ = quantityQ;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantityQ() {
        return quantityQ;
    }

    public void setQuantityQ(int quantity) {
        this.quantityQ = quantity;
    }
}
