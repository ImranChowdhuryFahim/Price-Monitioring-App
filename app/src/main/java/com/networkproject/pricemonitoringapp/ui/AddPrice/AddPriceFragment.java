package com.networkproject.pricemonitoringapp.ui.AddPrice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.networkproject.pricemonitoringapp.R;


public class AddPriceFragment extends Fragment {


    private TextInputLayout region;
    private TextInputEditText regionInput;
    private Button addRegion;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_price, container, false);
        region = root.findViewById(R.id.region);
        regionInput = root.findViewById(R.id.regionInput);
        addRegion = root.findViewById(R.id.addRegion);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");

        addRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()) return;

                databaseReference.child("regions").child(String.valueOf(System.currentTimeMillis())).child("name").setValue(regionInput.getText().toString().trim());
                databaseReference.child(regionInput.getText().toString().trim()).child("status").setValue("active");


            }
        });

        return root;
    }

    private boolean validate() {
        String val = regionInput.getText().toString();
        String emailPattern = "^(.+)@(.+)$";

        if(val.isEmpty())
        {
            region.setError("Field cannot be empty");
            return false;
        }

        else {
            region.setError(null);
            return true;
        }
    }
}