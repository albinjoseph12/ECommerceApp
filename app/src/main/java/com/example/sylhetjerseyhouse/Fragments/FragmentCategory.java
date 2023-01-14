package com.example.sylhetjerseyhouse.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sylhetjerseyhouse.Activities.ClubsJersey;
import com.example.sylhetjerseyhouse.Activities.ClubsKeyRing;
import com.example.sylhetjerseyhouse.Activities.CustomizedJersey;
import com.example.sylhetjerseyhouse.Activities.Flags;
import com.example.sylhetjerseyhouse.Activities.Jogers;
import com.example.sylhetjerseyhouse.Activities.NationalJersey;
import com.example.sylhetjerseyhouse.Activities.Snakers;
import com.example.sylhetjerseyhouse.Activities.WinterClothes;
import com.example.sylhetjerseyhouse.ItemView;
import com.example.sylhetjerseyhouse.R;

public class FragmentCategory extends Fragment implements View.OnClickListener {

    LinearLayout clubsJersey, nationalJersey, customizedJersey, jogers, snakers, clubsKeyRing, flags, winterClothes;


    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        clubsJersey = view.findViewById(R.id.id_clubsJersey);
        nationalJersey = view.findViewById(R.id.id_nationalJersey);
        customizedJersey = view.findViewById(R.id.id_customizedJersey);
        jogers = view.findViewById(R.id.id_jogers);
        snakers = view.findViewById(R.id.id_snakers);
        clubsKeyRing = view.findViewById(R.id.id_clubsKeyRing);
        flags = view.findViewById(R.id.id_flags);
        winterClothes = view.findViewById(R.id.id_winterClothes);


        clubsJersey.setOnClickListener(this::onClick);
        nationalJersey.setOnClickListener(this::onClick);
        customizedJersey.setOnClickListener(this::onClick);
        jogers.setOnClickListener(this::onClick);
        snakers.setOnClickListener(this::onClick);
        clubsKeyRing.setOnClickListener(this::onClick);
        flags.setOnClickListener(this::onClick);
        winterClothes.setOnClickListener(this::onClick);



        goToActivity();
        return view;
    }



    private void goToActivity() {

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_clubsJersey:
            {
                Intent i = new Intent(getContext(), ClubsJersey.class);
                startActivity(i);
                break;
            }
            case R.id.id_nationalJersey:
            {
                Intent i = new Intent(getContext(), NationalJersey.class);
                startActivity(i);
                break;
            }
            case R.id.id_customizedJersey:
            {
                Intent i = new Intent(getContext(), CustomizedJersey.class);
                startActivity(i);
                break;
            }
            case R.id.id_jogers:
            {
                Intent i = new Intent(getContext(), Jogers.class);
                startActivity(i);
                break;
            }
            case R.id.id_snakers:
            {
                Intent i = new Intent(getContext(), Snakers.class);
                startActivity(i);
                break;
            }
            case R.id.id_clubsKeyRing:
            {
                Intent i = new Intent(getContext(), ClubsKeyRing.class);
                startActivity(i);
                break;
            }
            case R.id.id_flags:
            {
                Intent i = new Intent(getContext(), Flags.class);
                startActivity(i);
                break;
            }
            case R.id.id_winterClothes:
            {
                Intent i = new Intent(getContext(), WinterClothes.class);
                startActivity(i);
                break;
            }
        }
    }







}