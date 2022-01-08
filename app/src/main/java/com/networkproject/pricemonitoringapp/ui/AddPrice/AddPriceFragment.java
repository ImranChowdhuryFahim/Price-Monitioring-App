package com.networkproject.pricemonitoringapp.ui.AddPrice;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.networkproject.pricemonitoringapp.R;
import com.networkproject.pricemonitoringapp.model.PriceModel;

import java.util.ArrayList;


public class AddPriceFragment extends Fragment {


    private TextInputLayout region;
    private TextInputEditText farmerPriceInput,wholeSellerPriceInput,sellerPriceInput,marketName,supplyInput;
    private Button addPrice;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReferenceWrite;

    private ArrayList<String> productTypeList;
    private ArrayAdapter<String> productTypeAdapter;
    private AutoCompleteTextView productTypeInput;
    private TextInputLayout productType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_price, container, false);

        farmerPriceInput = root.findViewById(R.id.farmerPriceInput);
        sellerPriceInput = root.findViewById(R.id.sellerPriceInput);
        wholeSellerPriceInput = root.findViewById(R.id.wholeSellerPriceInput);
        marketName = root.findViewById(R.id.marketInput);
        supplyInput = root.findViewById(R.id.supplyInput);

        addPrice = root.findViewById(R.id.addPrice);

        productType = root.findViewById(R.id.productType);
        productTypeInput = root.findViewById(R.id.productTypeInput);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("products");
        databaseReferenceWrite  = firebaseDatabase.getReference("");

        productTypeList = new ArrayList<String>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                                   productTypeList.add(snapshot1.child("searchName").getValue().toString().trim());

                }

                productTypeAdapter = new ArrayAdapter<String>(getActivity(),R.layout.account_type,productTypeList);
                productTypeInput.setAdapter(productTypeAdapter);
                productTypeInput.setThreshold(1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        addPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productType = productTypeInput.getText().toString().trim();
                PriceModel priceModel = new PriceModel(marketName.getText().toString().trim(),
                        farmerPriceInput.getText().toString().trim(),
                        sellerPriceInput.getText().toString().trim(),
                        wholeSellerPriceInput.getText().toString().trim(),
                        supplyInput.getText().toString().trim());

                databaseReferenceWrite.child(productType).child("this_week").child(String.valueOf(System.currentTimeMillis())).setValue(priceModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Price Added",Toast.LENGTH_SHORT).show();
                        farmerPriceInput.setText("");
                        sellerPriceInput.setText("");
                        wholeSellerPriceInput.setText("");
                        supplyInput.setText("");
                    }
                });


            }
        });

        return root;
    }


}