package com.write.kaku.kaku.SSM_View_Fragements;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.write.kaku.kaku.SSM_View_Util.ListAdapter;
import com.write.kaku.kaku.R;

import java.util.ArrayList;

public class MyPostToMyWriteFragment extends Fragment {

    // Store instance variables
    private String title;
    private int page;

    //listview
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ListView listView;
    TextView testtextView;

    // newInstance constructor for creating fragment with arguments
    public static MyPostToMyWriteFragment newInstance(int page, String title) {
        MyPostToMyWriteFragment fragmentFirst = new MyPostToMyWriteFragment();
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
        Log.d("MyPostToMyWriteFragment","onCreateView start");
        View view = inflater.inflate(R.layout.fragment_my_post_mywrite, container, false);

        /*Items = new ArrayList<String>();
        Items.add("First");
        Items.add("Second");
        Items.add("Third");

        //ArrayList객체와 ListView 객체 연결 위해 ArrayAdapter객체를 사용
        Adapter = new ArrayAdapter<String>(view.getContext(), R.layout.list_item, Items);
        listView = (ListView) view.findViewById(R.id.myWriteListView);

        listView.setAdapter(Adapter);*/

        String[] title = {"Title1", "Title2", "Title3", "Title4"};

        ListAdapter listAdapter = new ListAdapter(view.getContext(), title);

        listView = (ListView) view.findViewById(R.id.myWriteListView);

        listView.setAdapter(listAdapter);


        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });*/
        /*listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Adapter.notifyDataSetChanged();*/



        return view;
    }
}
