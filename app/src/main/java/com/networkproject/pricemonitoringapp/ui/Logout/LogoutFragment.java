package com.networkproject.pricemonitoringapp.ui.Logout;

import static com.networkproject.pricemonitoringapp.LoginActivity.SHARED_PREFS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.networkproject.pricemonitoringapp.LoginActivity;
import com.networkproject.pricemonitoringapp.R;

public class LogoutFragment extends Fragment {


    private Button logout;
    private SharedPreferences sharedPref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_logout, container, false);
        logout = root.findViewById(R.id.logoutButton);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                sharedPref = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                sharedPref.edit().clear().apply();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}