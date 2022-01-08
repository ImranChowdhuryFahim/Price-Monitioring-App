package com.networkproject.pricemonitoringapp;

import static com.networkproject.pricemonitoringapp.LoginActivity.ACCOUNT_TYPE;
import static com.networkproject.pricemonitoringapp.LoginActivity.SHARED_PREFS;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.networkproject.pricemonitoringapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView userName,userEmail,type;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private SharedPreferences sharedPref;
    private String accountType;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");


        View headerView = navigationView.getHeaderView(0);
        userName = headerView.findViewById(R.id.userName);
        userEmail = headerView.findViewById(R.id.userEmail);
        type = headerView.findViewById(R.id.accountType);





        sharedPref = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        accountType = sharedPref.getString(ACCOUNT_TYPE,"Customer");
        type.setText(accountType+" Account");

//        Toast.makeText(HomeActivity.this,accountType,Toast.LENGTH_SHORT).show();
//        userName.setText(name);
//        userEmail.setText(email);

        if(user!=null)
        {
            databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    userEmail.setText(snapshot.child("email").getValue().toString());
                    userName.setText(snapshot.child("name").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        Menu menu = navigationView.getMenu();


        if(accountType.equals("Customer")){
            menu.findItem(R.id.nav_add_product).setVisible(false);
            menu.findItem(R.id.nav_add_price).setVisible(false);
            menu.findItem(R.id.nav_pending_complaints).setVisible(false);
        }

        else if(accountType.equals("Authority"))
        {
            menu.findItem(R.id.nav_add_product).setVisible(false);
            menu.findItem(R.id.nav_add_price).setVisible(false);
        }





        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_add_product,R.id.nav_add_price, R.id.nav_product_list, R.id.nav_complain,R.id.nav_pending_complaints,R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}