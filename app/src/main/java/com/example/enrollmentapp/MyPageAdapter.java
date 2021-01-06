package com.example.enrollmentapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//Adapter class for ViewPager
public class MyPageAdapter extends FragmentPagerAdapter {
    Context context;

    public MyPageAdapter(Context context, FragmentManager fm){
        super(fm);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new ListFragment();
        }else{
            return new RegisterFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "User";
        }else{
            return "Enroll";
        }
    }
}
