package com.iiitd.apurupa.mcassignment.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NB VENKATESHWARULU on 11/2/2016.
 */
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>  {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView;
        public LinearLayout l1;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);
           mTitleTextView = (TextView) itemView.findViewById(R.id.item_title);
            l1=(LinearLayout)itemView.findViewById(R.id.item_linearlayout);

           l1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Log.d("CLICKED",String.valueOf(getAdapterPosition()));

                    Intent I =new Intent(context.getApplicationContext(),ShowItemDetailsActivity.class);
                    I.putExtra("position",getAdapterPosition());

                   context.startActivity(I);
                  //  ((MainActivity) context).userItemClick(getAdapterPosition());
                }
            });
            }


    }

    private List<ToDoList> mToDoList;

    private Context mContext;


    public ItemListAdapter(Context context, List<ToDoList> toDoLists) {
        mToDoList = toDoLists;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View todoitemView = inflater.inflate(R.layout.item_todolist, parent, false);

        ViewHolder viewHolder = new ViewHolder(todoitemView,context);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ItemListAdapter.ViewHolder holder, int position) {

        ToDoList toDolist = mToDoList.get(position);

        // Set item views based on your views and data model
        TextView textView =holder.mTitleTextView;
        Log.d("Title",toDolist.getMtitle());
        textView.setText(toDolist.getMtitle());



    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }
}
