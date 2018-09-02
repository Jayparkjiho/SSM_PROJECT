package com.write.kaku.kaku.SSM_View_Fragements;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.write.kaku.kaku.MainActivity;
import com.write.kaku.kaku.ManualActivity;
import com.write.kaku.kaku.R;



public class WriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Write");
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        Log.d("WriteFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_write, container, false);


        mAuth = FirebaseAuth.getInstance();

        Button logout_button = (Button)view.findViewById(R.id.logout_button);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent((MainActivity)getActivity(), ManualActivity.class);
                startActivity(intent);

                Log.d("WriteFragment","click");
            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_write, container, false);
        return view;
    }

}
