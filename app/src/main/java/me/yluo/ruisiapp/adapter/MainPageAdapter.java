package me.yluo.ruisiapp.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import me.yluo.ruisiapp.fragment.BaseLazyFragment;

public class MainPageAdapter extends FragmentStatePagerAdapter {

    private List<BaseLazyFragment> fragments;

    public MainPageAdapter(FragmentManager fm, List<BaseLazyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
