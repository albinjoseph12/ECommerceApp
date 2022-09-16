package com.example.sylhetjerseyhouse.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.sylhetjerseyhouse.Adapter.LoginSignupTabAdapter;
import com.example.sylhetjerseyhouse.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginSignUpActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    private String[] tabs = new String[]{"Login", "Signup"};
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);


        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.viewPagerID);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Signup"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final LoginSignupTabAdapter adapter = new LoginSignupTabAdapter(getSupportFragmentManager(), getLifecycle(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText(tabs[position])).attach();





    }
}