package com.example.tuvananhtruong;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cart = new ArrayList<>();

    public static void addToCart(CartItem newItem) {
        for (CartItem item : cart) {
            if (item.getName().equals(newItem.getName())) {
                item.increaseQuantity();  // Nếu trùng tên, tăng số lượng
                return;
            }
        }
        cart.add(newItem); // Nếu chưa có, thêm mới
    }

    public static List<CartItem> getCart() {
        return cart;
    }

    public static void clearCart() {
        cart.clear();
    }
    public static void removeItem(int index) {
        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
    }
}