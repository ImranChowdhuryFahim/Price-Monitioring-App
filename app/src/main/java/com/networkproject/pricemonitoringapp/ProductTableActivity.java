package com.networkproject.pricemonitoringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.networkproject.pricemonitoringapp.ViewHolder.ProductViewHolder;
import com.networkproject.pricemonitoringapp.ViewHolder.TableRowViewHolder;
import com.networkproject.pricemonitoringapp.model.PriceModel;
import com.networkproject.pricemonitoringapp.model.ProductModel;
import com.squareup.picasso.Picasso;

public class ProductTableActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<PriceModel, TableRowViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_table);

        recyclerView = findViewById(R.id.table_recycler_view);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("rice").child("this_week");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ProductTableActivity.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);



        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<PriceModel>().setQuery(databaseReference ,PriceModel.class).build();

        adapter = new FirebaseRecyclerAdapter<PriceModel, TableRowViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TableRowViewHolder holder, int position, @NonNull PriceModel model) {
                holder.marketName.setText(model.getMarketName());
                holder.farmerPrice.setText(model.getFarmerPrice());
                holder.wholesSellerPrice.setText(model.getWholeSellerPrice());
                holder.supply.setText(model.getSupply());
                holder.sellerPrice.setText(model.getSellerPrice());

            }



            @NonNull
            @Override
            public TableRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_table,parent,false);
                TableRowViewHolder tableRowViewHolder = new TableRowViewHolder(view);
                return tableRowViewHolder;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }
}