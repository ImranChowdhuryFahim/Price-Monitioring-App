package com.networkproject.pricemonitoringapp.ui.ProductList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.networkproject.pricemonitoringapp.ProductTableActivity;
import com.networkproject.pricemonitoringapp.R;
import com.networkproject.pricemonitoringapp.ViewHolder.ProductViewHolder;
import com.networkproject.pricemonitoringapp.model.ProductModel;
import com.squareup.picasso.Picasso;

public class ProductListFragment extends Fragment {


    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SearchView productSearch;
    private SharedPreferences sharedPref;

    private FirebaseRecyclerAdapter<ProductModel, ProductViewHolder> adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_product_list, container, false);
        recyclerView = root.findViewById(R.id.productList);
        productSearch = root.findViewById(R.id.productSearch);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        productSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                FirebaseSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FirebaseSearch(s);
                return false;
            }
        });




        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(databaseReference ,ProductModel.class).build();

        adapter = new FirebaseRecyclerAdapter<ProductModel, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {
                holder.productName.setText(model.getName());
                Picasso.get().load(model.getImageLink()).into(holder.productImage);
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getActivity(),ProductTableActivity.class));
                    }
                });
            }


            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_row,parent,false);
                ProductViewHolder productViewHolder = new ProductViewHolder(view);
                return productViewHolder;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        return root;
    }

    private void FirebaseSearch(String searchText) {
        String query=searchText.toLowerCase();
        Query firebaseSearchQuery=databaseReference.orderByChild("searchName").startAt(query).endAt(query + "\uf8ff");
        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<ProductModel>().setQuery(firebaseSearchQuery,ProductModel.class).build();

        adapter = new FirebaseRecyclerAdapter<ProductModel, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {
                holder.productName.setText(model.getName());
                Picasso.get().load(model.getImageLink()).into(holder.productImage);
                startActivity(new Intent(getActivity(),ProductTableActivity.class));
            }


            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.product_row,parent,false);
                ProductViewHolder productViewHolder = new ProductViewHolder(view);
                return productViewHolder;
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

}