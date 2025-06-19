package com.example.tuvananhtruong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Button quay lại
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        // ListView với CustomAdapter
        ListView listView = findViewById(R.id.listViewItems);
        String[] items = {"Xe đạp thể thao", "Xe đạp trẻ em", "Xe đạp leo núi", "Xe đạp đường phố"};
        int[] images = {
                R.drawable.xe_dap_1,
                R.drawable.xe_dap_2,
                R.drawable.xe_dap_3,
                R.drawable.xe_dap_4
        };

        CustomAdapter adapter = new CustomAdapter(this, items, images);
        listView.setAdapter(adapter);
    }

    // ✅ CustomAdapter nên đặt ngoài onCreate như thế này
    public class CustomAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final String[] titles;
        private final int[] images;

        public CustomAdapter(Context context, String[] titles, int[] images) {
            super(context, R.layout.list_item_with_image, titles);
            this.context = context;
            this.titles = titles;
            this.images = images;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View rowView = LayoutInflater.from(context).inflate(R.layout.list_item_with_image, parent, false);
            TextView textView = rowView.findViewById(R.id.itemText);
            ImageView imageView = rowView.findViewById(R.id.itemImage);
            textView.setText(titles[position]);
            imageView.setImageResource(images[position]);
            return rowView;
        }
    }
}
