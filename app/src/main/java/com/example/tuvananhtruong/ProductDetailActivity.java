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
        // Nút quay lại
        ImageView cart = findViewById(R.id.Back);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        // 🔸 Ánh xạ view từ XML
        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productDescription = findViewById(R.id.productDescription);
        productPrice = findViewById(R.id.productPrice);

        // 🔸 Nhận dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String price = getIntent().getStringExtra("price");
        int imageResId = getIntent().getIntExtra("image", R.drawable.xe_dap_1);

        // 🔸 Gán dữ liệu
        productName.setText(name);
        productDescription.setText(description);
        productPrice.setText(price);
        productImage.setImageResource(imageResId);

        // Thêm vào giỏ hàng
        Button btnAddToCart = findViewById(R.id.addToCartBtn);
        btnAddToCart.setOnClickListener(v -> {
            if (price == null || price.isEmpty()) {
                Toast.makeText(this, "Giá sản phẩm không hợp lệ!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                String rawPrice = price.replaceAll("[^0-9]", ""); // bỏ dấu chấm, ₫
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
