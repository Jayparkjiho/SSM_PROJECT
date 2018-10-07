package com.write.kaku.kaku.SSM_View_Fragements;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
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
import com.write.kaku.kaku.SSM_View_Util.PagerAdapter;
import com.write.kaku.kaku.WriteActivity;

import org.w3c.dom.Text;


public class MyPostFragment extends Fragment {

    private FragmentActivity myContext;
    private FragmentManager fragManager;
//    private FragmentPagerAdapter adapterViewPager;
    private FragmentStatePagerAdapter adapterViewPager;

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
        Log.d("MyPostFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_post, container, false);

        /** ViewPager */
        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);

        adapterViewPager = new PagerAdapter(myContext.getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        TextView userName = (TextView)view.findViewById(R.id.userName);
        TextView userIntro = (TextView)view.findViewById(R.id.userIntro);

        userName.setText("李様");
        userIntro.setText("ギモティ");

        Log.d("MyPostFragment","onCreateView");

        // Inflate the layout for this fragment
        return view;
    }

}
