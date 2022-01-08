package com.networkproject.pricemonitoringapp.ui.AddProduct;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.networkproject.pricemonitoringapp.R;
import com.networkproject.pricemonitoringapp.model.ProductModel;

public class AddProductFragment extends Fragment {


    private Button addImage,addProduct;
    private TextInputLayout productName;
    private TextInputEditText productNameInput;
    private StorageReference storageRef;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ImageView productImage;
    private Uri selectedImage,downloadedImage;
    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_product, container, false);
        addImage = root.findViewById(R.id.addImage);
        addProduct = root.findViewById(R.id.addProduct);
        productNameInput = root.findViewById(R.id.productNameInput);
        productName = root.findViewById(R.id.productName);
        productImage = root.findViewById(R.id.productImage);
        progressDialog=new ProgressDialog(getContext());

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("");
        storageRef = FirebaseStorage.getInstance().getReference();
        databaseReference.keepSynced(true);


        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedImage==null)
                {
                    Toast.makeText(getActivity(),"No Image selected",Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();
                    StorageReference productImageRef = storageRef.child("/product"+String.valueOf(System.currentTimeMillis())+".jpg");
                    productImageRef
                            .putFile(selectedImage)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    productImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            downloadedImage=uri;
                                            String id = String.valueOf(System.currentTimeMillis());
                                            ProductModel product= new ProductModel(productNameInput.getText().toString().trim(),productNameInput.getText().toString().trim().toLowerCase(),downloadedImage.toString());
                                            databaseReference.child("products").child(id).setValue(product);
                                            databaseReference.child(productNameInput.getText().toString().trim().toLowerCase()).child("imageLink").setValue(downloadedImage.toString());
                                            progressDialog.dismiss();
                                            Toast.makeText(getActivity(), "Item is added successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    // ...progressDialog.dismiss();
                                    progressDialog.dismiss();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress=(100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded "+(int)progress+"%");
                                }
                            });
                }
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pick=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pick,1);

            }
        });



        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            selectedImage=data.getData();
            productImage.setImageURI(selectedImage);


        }
    }

}