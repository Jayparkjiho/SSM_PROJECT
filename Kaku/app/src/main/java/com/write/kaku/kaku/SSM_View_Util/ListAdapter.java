package com.write.kaku.kaku.SSM_View_Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.write.kaku.kaku.R;


public class ListAdapter extends BaseAdapter {

    private String[] title;
    private Context context;

    public ListAdapter(Context context, String[] title){
        super();
        this.title = title;
        this.context = context;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        TextView text1 = (TextView) rowView.findViewById(R.id.listText);

        text1.setText(title[position]);

        return rowView;
    }


}
