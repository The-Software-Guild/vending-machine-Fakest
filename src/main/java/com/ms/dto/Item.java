package com.ms.dto;

import java.math.BigDecimal;

public class Item {
    public final String ITEM_ID;
    public String title;
    public BigDecimal price;
    public int quantity;

    public Item(String ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public Item(String ITEM_ID, String title, BigDecimal price, int quantity) {
        this.ITEM_ID = ITEM_ID;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getITEM_ID() {
        return ITEM_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ITEM_ID='" + ITEM_ID + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
