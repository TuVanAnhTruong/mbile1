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

public class ProductDetailActivity extends AppCompatActivity {

    ImageView productImage;
    TextView productName, productDescription, productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ⬅️ Nút quay lại về trang Home
        ImageView btnBack = findViewById(R.id.Back);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ProductDetailActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // 📌 Ánh xạ view
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);
        Button btnAddToCart = findViewById(R.id.addToCartBtn);

        // 📥 Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String price = getIntent().getStringExtra("price");
        int imageResId = getIntent().getIntExtra("image", R.drawable.xe_dap_1);

        // 📤 Hiển thị lên giao diện
        productName.setText(name);
        productDescription.setText(description);
        productPrice.setText(price);
        productImage.setImageResource(imageResId);

        // 🛒 Xử lý nút thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            if (price == null || price.isEmpty()) {
                Toast.makeText(this, "Giá sản phẩm không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Lấy số từ chuỗi "Giá: 2.500.000đ" → 2500000
                String rawPrice = price.replaceAll("[^0-9]", "");
                int priceInt = Integer.parseInt(rawPrice);

                CartItem item = new CartItem(name, priceInt, imageResId);
                CartManager.addToCart(item);

                Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Lỗi khi thêm sản phẩm: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
