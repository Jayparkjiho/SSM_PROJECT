package com.write.kaku.kaku.SSM_View_Util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.write.kaku.kaku.SSM_View_Fragements.MyPostToMyWriteFragment;
import com.write.kaku.kaku.SSM_View_Fragements.MyPostToWriteSetFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 2;

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("PagerAdapter","getItem-> position: "+position);
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MyPostToMyWriteFragment.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MyPostToWriteSetFragment.newInstance(1, "Page # 2");
                /*case 2: // Fragment # 1 - This will show SecondFragment
//                    return SecondFragment.newInstance(2, "Page # 3");
                    return MyPostToMyWriteFragment.newInstance(2, "Page # 3");*/
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tabName = null;
        switch (position){
            case 0:
                tabName = "私の書く";
                break;
            case 1:
                tabName = "セット";
                break;
            default:
                break;
        }
        return tabName;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public int getItemPosition(Object object) {
        Log.d("PagerAdapter","getItemPosition");

//        return super.getItemPosition(object);
        return POSITION_NONE;
    }
}
