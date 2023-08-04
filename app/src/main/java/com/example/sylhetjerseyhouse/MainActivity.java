package com.example.sylhetjerseyhouse;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sylhetjerseyhouse.Fragments.FragmentCart;
import com.example.sylhetjerseyhouse.Fragments.FragmentCategory;
import com.example.sylhetjerseyhouse.Fragments.FragmentHome;
import com.example.sylhetjerseyhouse.Fragments.Fragment_Settings;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.id_bottomNavigationbar);
        ReplaceFragment(new FragmentHome());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_home:
                        ReplaceFragment(new FragmentHome());
                        break;
                    case R.id.id_category:
                        ReplaceFragment(new FragmentCategory());
                        break;
                    case R.id.id_cart:
                        ReplaceFragment(new FragmentCart());
                        break;
                    case R.id.id_settings:
                        ReplaceFragment(new Fragment_Settings());
                        break;
                }
                return true;
            }
        });


    }


    public void ReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_frameLayout, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit this application?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


}