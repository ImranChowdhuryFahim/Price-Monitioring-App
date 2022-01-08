package com.networkproject.pricemonitoringapp.ui.PendingComplaints;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.networkproject.pricemonitoringapp.R;
import com.networkproject.pricemonitoringapp.ViewHolder.ComplaintViewHolder;
import com.networkproject.pricemonitoringapp.model.ComplaintModel;
import com.networkproject.pricemonitoringapp.model.ProductModel;

public class PendingComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SearchView productSearch;
    private FirebaseRecyclerAdapter<ComplaintModel, ComplaintViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pending_complaints, container, false);
        recyclerView = root.findViewById(R.id.complaintList);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("complaints");
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions options=new FirebaseRecyclerOptions.Builder<ComplaintModel>().setQuery(databaseReference ,ComplaintModel.class).build();

        adapter = new FirebaseRecyclerAdapter<ComplaintModel, ComplaintViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position, @NonNull ComplaintModel model) {
                holder.shopNo.setText(model.getShopNo());
                holder.shopName.setText(model.getShopName());
                holder.marketName.setText(model.getMarketName());
                holder.region.setText(model.getRegion());
                holder.complaintDetails.setText(model.getComplaintDetails());
            }

            @NonNull
            @Override
            public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.complaint_row,parent,false);
                ComplaintViewHolder complaintViewHolder = new ComplaintViewHolder(view);
                return complaintViewHolder;
            }


        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return root;
    }
}