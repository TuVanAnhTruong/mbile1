package com.example.tuvananhtruong;

import android.view.View;
import android.widget.Button;

public class CartItem {
    private String name;
    private int price;
    private int imageResId;
    private int quantity;

    public CartItem(String name, int price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = 1; // Mặc định khi tạo là 1
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getImageResId() { return imageResId; }
    public int getQuantity() { return quantity; }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 1) this.quantity--;
    }
}