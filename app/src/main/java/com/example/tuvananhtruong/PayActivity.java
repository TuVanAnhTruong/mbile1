package com.example.tuvananhtruong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {
    TextView totalAmount, productList;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pay);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        totalAmount = findViewById(R.id.totalAmount);
        productList = findViewById(R.id.productList);
        btnPay = findViewById(R.id.btnPay);
        ImageView cart = findViewById(R.id.Back);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        ArrayList<String> names = intent.getStringArrayListExtra("names");
        ArrayList<Integer> prices = intent.getIntegerArrayListExtra("prices");
        ArrayList<Integer> quantities = intent.getIntegerArrayListExtra("quantities");
        int total = intent.getIntExtra("total", 0);

        // Hiển thị danh sách sản phẩm
        if (names != null && prices != null && quantities != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < names.size(); i++) {
                builder.append(i + 1)
                        .append(". ")
                        .append(names.get(i))
                        .append(" - ")
                        .append(prices.get(i))
                        .append("₫ x ")
                        .append(quantities.get(i))
                        .append("\n");
            }
            productList.setText(builder.toString());
        }

        // Hiển thị tổng tiền
        totalAmount.setText("Tổng: " + total + "₫");

        // Nút thanh toán
        btnPay.setOnClickListener(v -> {
            if (CartManager.getCart().isEmpty()) {
                // ❌ Nếu giỏ hàng rỗng → không cho thanh toán
                Toast.makeText(PayActivity.this, "Giỏ hàng trống! Không thể thanh toán.", Toast.LENGTH_SHORT).show();
            } else {
                // ✅ Có hàng → cho thanh toán
                Toast.makeText(PayActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                CartManager.clearCart(); // Xoá giỏ hàng
                finish(); // Ở lại hoặc quay về
            }
        });

        // Nút quay lại
        cart.setOnClickListener(v -> {
            Intent intentBack = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intentBack);
        });
    }
}
