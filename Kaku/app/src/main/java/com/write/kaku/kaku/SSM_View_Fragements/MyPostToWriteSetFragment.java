package com.write.kaku.kaku.SSM_View_Fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.write.kaku.kaku.R;

public class MyPostToWriteSetFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static MyPostToWriteSetFragment newInstance(int page, String title) {
        MyPostToWriteSetFragment fragmentFirst = new MyPostToWriteSetFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MyPostToWriteSetFragmen","onCreateView start");
        View view = inflater.inflate(R.layout.fragment_my_post_writeset, container, false);
        return view;
    }
}
