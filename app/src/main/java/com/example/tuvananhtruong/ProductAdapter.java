package com.example.tuvananhtruong;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private String[] names, prices;
    private int[] images;
    private String[] descriptions;

    public ProductAdapter(Context context, String[] names, String[] prices, int[] images,String[] descriptions) {
        this.context = context;
        this.names = names;
        this.prices = prices;
        this.images = images;
        this.descriptions = descriptions;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        ImageView img;
        TextView name, price;
        Button btnDetail;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.imgProduct);
            holder.name = convertView.findViewById(R.id.txtName);
            holder.price = convertView.findViewById(R.id.txtPrice);
            holder.btnDetail = convertView.findViewById(R.id.btnDetails);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setImageResource(images[i]);
        holder.name.setText(names[i]);
        holder.price.setText(prices[i]);

        holder.btnDetail.setOnClickListener(v -> {
            Toast.makeText(context, "Chi tiết: " + names[i], Toast.LENGTH_SHORT).show();
        });

        // Sự kiện khi nhấn nút Chi tiết
        holder.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", names[i]);
            intent.putExtra("price", "Giá: " + prices[i]);
            intent.putExtra("description", descriptions[i]);
            intent.putExtra("image", images[i]);
            context.startActivity(intent);
        });

        return convertView;
    }
}
