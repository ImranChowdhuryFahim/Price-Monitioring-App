package com.networkproject.pricemonitoringapp.ui.PendingComplaints;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.networkproject.pricemonitoringapp.R;

public class PendingComplaintsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_pending_complaints, container, false);

        return root;
    }
}