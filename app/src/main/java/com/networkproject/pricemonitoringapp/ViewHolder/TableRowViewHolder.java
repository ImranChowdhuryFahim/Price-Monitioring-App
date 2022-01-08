package com.networkproject.pricemonitoringapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.networkproject.pricemonitoringapp.R;

public class TableRowViewHolder extends RecyclerView.ViewHolder {
    public TextView marketName,farmerPrice,wholesSellerPrice,sellerPrice,supply;

    public TableRowViewHolder(@NonNull View itemView){

        super(itemView);

        marketName = itemView.findViewById(R.id.marketName);
        farmerPrice = itemView.findViewById(R.id.farmerPrice);
        wholesSellerPrice = itemView.findViewById(R.id.wholeSellerPrice);
        sellerPrice = itemView.findViewById(R.id.sellerPrice);
        supply = itemView.findViewById(R.id.supply);

    }
}
