package com.networkproject.pricemonitoringapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String UID = "uid";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "userName";

    private TextView signUp;
    private Button login;
    private TextInputEditText passInput,emailInput;
    private TextInputLayout password,email,accountType;
    private ArrayList<String> accountTypeList;
    private ArrayAdapter<String> accountTypeAdapter;
    private AutoCompleteTextView accountTypeInput;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,databaseReferenceWrite;
    private ProgressDialog progressDialog;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_login);

        signUp = findViewById(R.id.reg);
        login = findViewById(R.id.login);
        accountTypeInput = findViewById(R.id.accountTypeInput);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        emailInput = findViewById(R.id.emailInput);
        password = findViewById(R.id.password);
        passInput = findViewById(R.id.passInput);
        accountType = findViewById(R.id.accountType);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("");
        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);


        accountTypeList = new ArrayList<String>();
        accountTypeList.add("Customer");
        accountTypeList.add("Admin");
        accountTypeList.add("Authority");

        accountTypeAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.account_type,accountTypeList);
        accountTypeInput.setAdapter(accountTypeAdapter);
        accountTypeInput.setThreshold(1);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (checkConnectivity()){
//                } else {
//                    nointernetp();
//                    return;
//                }
//
//                if(!validateEmail()  |  !validatePass() | !validateAccountType())
//                {
//                    return;
//                }
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }
        });

    }


    private boolean validateAccountType() {
        String val = accountTypeInput.getText().toString();

        if(val.isEmpty())
        {
            accountType.setError("Field cannot be empty");
            return false;
        }
        else {
            accountType.setError(null);
            return true;
        }
    }

    private boolean validatePass() {
        String val = passInput.getText().toString();

        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            return false;
        }
        else {
            password.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = emailInput.getText().toString();
        String emailPattern = "^(.+)@(.+)$";

        if(val.isEmpty())
        {
            email.setError("Field cannot be empty");
            return false;
        }
        if(!val.matches(emailPattern))
        {
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            return true;
        }
    }
    private void nointernetp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.ic_baseline_network_check_24);
        builder.setTitle("Bad Connection");
        builder.setMessage("No internet access, please activate the internet to use the app!");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Close",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Reload",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){

                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if ((info == null || !info.isConnected() || !info.isAvailable())) {

            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onBackPressed()
    {
        exit();
    }
    private void exit() {
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(true);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to leave the application?");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();
                moveTaskToBack(true);

            }
        });

        builder.setNegativeButton("No",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }
}