package com.networkproject.pricemonitoringapp.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.networkproject.pricemonitoringapp.R;


public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView productName;
    public ImageView productImage;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.productName);
        productImage = itemView.findViewById(R.id.productImage);
    }
}

