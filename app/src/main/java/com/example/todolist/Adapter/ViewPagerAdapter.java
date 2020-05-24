package com.example.todolist.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListItems= new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {

        return fragmentListItems.size();
    }


    public void AddFragment(Fragment fragment , String title){

        fragmentList.add(fragment);
        fragmentListItems.add(title);
    }

    public CharSequence getPageTitle(int position){

        return fragmentListItems.get(position);
    }
}