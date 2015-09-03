package com.example.geolabedu.testn2.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.geolabedu.testn2.R;
import com.example.geolabedu.testn2.TestDescription;

import java.util.ArrayList;

/**
 * Created by Geolabedu on 8/3/15.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList list;

    public CustomAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        View item;
        TestView textView;

        if(view==null){
            item=View.inflate(context, R.layout.listview_item,null);
            textView=new TestView();

            item.setTag(textView);
        }else {
            item=view;
            textView= (TestView) item.getTag();
        }

        TestDescription testDescription= (TestDescription) getItem(position);

        textView.textView.setText(testDescription.getModeli());

        textView.view.setImageURI(testDescription.getUri());
        return item;
    }
    private class TestView{

        ImageView view;
        TextView textView;
    }
}
