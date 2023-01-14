package com.example.sylhetjerseyhouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sylhetjerseyhouse.Fragments.FragmentAccount;
import com.example.sylhetjerseyhouse.Fragments.FragmentCart;
import com.example.sylhetjerseyhouse.Fragments.FragmentCategory;
import com.example.sylhetjerseyhouse.Fragments.FragmentHome;
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
                switch (item.getItemId())
                {
                    case R.id.id_home:
                        ReplaceFragment(new FragmentHome());
                        break;
                    case R.id.id_category:
                        ReplaceFragment(new FragmentCategory());
                        break;
                    case R.id.id_cart:
                        ReplaceFragment(new FragmentCart());
                        break;
                    case R.id.id_account:
                        ReplaceFragment(new FragmentAccount());
                        break;
                }
                return true;
            }
        });




    }



    public void ReplaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_frameLayout, fragment);
        fragmentTransaction.commit();
    }




}