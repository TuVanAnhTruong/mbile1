package com.example.tuvananhtruong;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    LinearLayout cartItemsContainer;
    TextView txtTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartItemsContainer = findViewById(R.id.cartItemsContainer);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);

        ImageView back = findViewById(R.id.Back);
        back.setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        loadCartItems();
        //Nut thanh toan
        Button checkout = findViewById(R.id.btnCheckout);
        checkout.setOnClickListener(v -> {
            List<CartItem> cart = CartManager.getCart();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<Integer> prices = new ArrayList<>();
            ArrayList<Integer> quantities = new ArrayList<>();
            int total = 0;

            for (CartItem item : cart) {
                names.add(item.getName());
                prices.add(item.getPrice());
                quantities.add(item.getQuantity());
                total += item.getPrice() * item.getQuantity();
            }

            Intent intent = new Intent(getApplicationContext(), PayActivity.class);
            intent.putStringArrayListExtra("names", names);
            intent.putIntegerArrayListExtra("prices", prices);
            intent.putIntegerArrayListExtra("quantities", quantities);
            intent.putExtra("total", total);
            startActivity(intent);
        });

    }

    private void loadCartItems() {
        List<CartItem> items = CartManager.getCart();
        int total = 0;

        cartItemsContainer.removeAllViews(); // Xóa nếu có cũ

        for (CartItem item : items) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.activity_cartitem, null);

            ImageView img = itemView.findViewById(R.id.itemImage);
            TextView name = itemView.findViewById(R.id.itemName);
            TextView price = itemView.findViewById(R.id.itemPrice);
            TextView quantity = itemView.findViewById(R.id.itemQuantity); // ✅ thêm đúng chỗ

            img.setImageResource(item.getImageResId());
            name.setText(item.getName());
            price.setText("Giá: " + item.getPrice() + "₫");
            quantity.setText("Số lượng: " + item.getQuantity()); // ✅ đặt giá trị số lượng

            total += item.getPrice() * item.getQuantity(); // ✅ tính theo số lượng

            cartItemsContainer.addView(itemView);
        }

        txtTotalPrice.setText("Tổng tiền: " + total + "₫");

    }
    @Override
    protected void onResume() {
        super.onResume();
        loadCartItems(); // Tải lại giỏ hàng mỗi khi quay lại màn này
    }
}
