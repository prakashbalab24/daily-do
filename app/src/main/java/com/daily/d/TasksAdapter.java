package com.daily.d;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

/**
 * Created by prakash-bala on 30/1/17.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {

    private Context mContext;
    private List<Tasks> tasksList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date;
        public LinearLayout linearLayout;
        public CheckBox checkBox;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            linearLayout = (LinearLayout) view.findViewById(R.id.linearview);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        }
    }


    public TasksAdapter(Context mContext, List<Tasks> tasksList) {
        this.mContext = mContext;
        this.tasksList = tasksList;
        Log.i("offerTitle","here");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Tasks tasks = tasksList.get(position);
        holder.title.setText(tasks.getTaskName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(false);
                }
                else {
                    holder.checkBox.setChecked(true);
                }

            }
        });



        // loading album cover using Glide library
        //.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
         return tasksList.size();
    }
}