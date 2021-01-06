package com.example.enrollmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.viewpager);
        tabLayout=findViewById(R.id.sliding_tabs);
        //Attaching Adapter for ViewPager
        MyPageAdapter mypageadapter=new MyPageAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(mypageadapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}