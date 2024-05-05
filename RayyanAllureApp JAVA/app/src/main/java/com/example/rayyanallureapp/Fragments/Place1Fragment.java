package com.example.rayyanallureapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rayyanallureapp.R;

public class Place1Fragment extends Fragment {

    public Place1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place1, container, false);
        TextView tv = view.findViewById(R.id.txtFrag);
        ImageView iv = view.findViewById(R.id.imgFrag);

        return view;
    }
}