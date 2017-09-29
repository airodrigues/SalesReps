package br.com.rmit.salesreps.activity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.rmit.salesreps.activity.fragments.HeaderFragment;
import br.com.rmit.salesreps.activity.fragments.ItemFragment;

/**
 * Created by Alexandre on 30/08/17.
 */

public class CustomAdapter extends FragmentPagerAdapter {

    private String fragments [] = {"Fragment 1","Fragment 2"};

    public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HeaderFragment();
            case 1:
                return new ItemFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position];
    }
}