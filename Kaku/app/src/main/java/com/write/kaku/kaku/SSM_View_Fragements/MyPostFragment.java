package com.write.kaku.kaku.SSM_View_Fragements;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.write.kaku.kaku.MainViewActivity;
import com.write.kaku.kaku.R;
import com.write.kaku.kaku.WriteActivity;

import org.w3c.dom.Text;


public class MyPostFragment extends Fragment {

    private FragmentActivity myContext;
    private FragmentManager fragManager;
    private FragmentPagerAdapter adapterViewPager;

    @Override
    public void onAttach(Context context) {
        myContext=(FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("MyPost");

        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_post, container, false);

        /** ViewPager */
        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);

        ViewPager test = new ViewPager(view.getContext());
        adapterViewPager = new MyPagerAdapter(myContext.getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        TextView userName = (TextView)view.findViewById(R.id.userName);
        TextView userIntro = (TextView)view.findViewById(R.id.userIntro);

        userName.setText("李様");
        userIntro.setText("ギモティ");

        /*Button logout_button = (Button)view.findViewById(R.id.logout_button);
        ImageButton write_button = (ImageButton)view.findViewById(R.id.Writing);

        write_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainViewActivity)getActivity(), WriteActivity.class);
                startActivity(intent);

            }
        });*/

        // Inflate the layout for this fragment
        return view;
    }

    /** viewpager*/
    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            Log.d("MyPostFragment","getItem-> position: "+position);
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

        // Returns the page title for the top indicator
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



    }

}
