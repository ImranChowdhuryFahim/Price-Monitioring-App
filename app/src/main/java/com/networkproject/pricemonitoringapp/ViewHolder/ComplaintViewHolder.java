package com.networkproject.pricemonitoringapp.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.networkproject.pricemonitoringapp.R;

public class ComplaintViewHolder extends RecyclerView.ViewHolder {

    public TextView shopNo,shopName,marketName,region,complaintDetails;
    public ComplaintViewHolder(@NonNull View itemView) {
        super(itemView);

        shopNo = itemView.findViewById(R.id.shopNo);
        shopName = itemView.findViewById(R.id.shopName);
        marketName = itemView.findViewById(R.id.marketName);
        region = itemView.findViewById(R.id.region);
        complaintDetails = itemView.findViewById(R.id.complainDetails);
    }
}
