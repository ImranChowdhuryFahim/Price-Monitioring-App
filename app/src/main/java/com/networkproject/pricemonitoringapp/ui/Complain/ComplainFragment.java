package com.networkproject.pricemonitoringapp.ui.Complain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.networkproject.pricemonitoringapp.R;
import com.networkproject.pricemonitoringapp.model.ComplaintModel;

public class ComplainFragment extends Fragment {



    private TextInputEditText shopNo,shopName,marketName,region,complaintDetails;
    private Button addComplaint;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_complain, container, false);
        shopNo = root.findViewById(R.id.shopNoInput);
        shopName = root.findViewById(R.id.shopNameInput);
        marketName = root.findViewById(R.id.marketNameInput);
        region = root.findViewById(R.id.regionInput);
        complaintDetails = root.findViewById(R.id.complaintDetailsInput);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");

        addComplaint = root.findViewById(R.id.addComplaint);


        addComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComplaintModel complaintModel = new ComplaintModel(shopNo.getText().toString().trim(),
                        shopName.getText().toString().trim(),
                        marketName.getText().toString().trim(),
                        region.getText().toString().trim(),
                        complaintDetails.getText().toString().trim());

                databaseReference.child("complaints").child(String.valueOf(System.currentTimeMillis())).setValue(complaintModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        shopNo.setText("");
                        marketName.setText("");
                        region.setText("");
                        shopName.setText("");
                        complaintDetails.setText("");
                        Toast.makeText(getActivity(),"Complaint Added",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        return root;
    }


}