package com.iiitd.apurupa.mcassignment.todolist;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NB VENKATESHWARULU on 11/5/2016.
 */
public class DetailViewAdapter extends PagerAdapter{
    private DBHelper db;
    ArrayList<ToDoList> list;
    private Context context;
    private LayoutInflater lf;

    public DetailViewAdapter(Context context)
    {
        this.context=context;
        db=new DBHelper(context);
        list=db.getAllTasks();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View detail_view = lf.inflate(R.layout.detailitem_layout,container,false);
        TextView titleview= (TextView) detail_view.findViewById(R.id.TitleDetailtextView);
        TextView descrview=(TextView)detail_view.findViewById(R.id.DescrDetailtextView);
        Log.d("POS",String.valueOf(position));
        titleview.setText(list.get(position).getMtitle());
        descrview.setText(list.get(position).getmDescription());
        container.addView(detail_view);
        return detail_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
