package com.tdr.app.bakingapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tdr.app.bakingapp.fragments.IngredientsFragment;
import com.tdr.app.bakingapp.fragments.PreparationFragment;

public class DetailAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public DetailAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new IngredientsFragment();
            case 1:
                return new PreparationFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.ingredients_label);

            case 1:
                return mContext.getString(R.string.preparation_label);
        }
        return super.getPageTitle(position);
    }
}
