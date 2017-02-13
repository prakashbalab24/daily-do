package com.daily.d.helper.utils;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daily.d.R;
import com.daily.d.tasks.Tasks;
import com.daily.d.tasks.TasksAdapter;
import com.daily.d.db.DatabaseHandler;

import java.util.List;

/**
 * Created by prakash-bala on 9/2/17.
 */

public class Helper {
    private static List<Tasks> tasksList;
    private static DatabaseHandler db;
    private static TasksAdapter tasksAdapter;
    private static RecyclerView recyclerView;
    private static Activity activity;
    public Helper(List<Tasks> tasksList,DatabaseHandler db,TasksAdapter tasksAdapter,RecyclerView recyclerView,Activity activity){

        this.tasksList = tasksList;
        this.db = db;
        this.tasksAdapter = tasksAdapter;
        this.recyclerView = recyclerView;
        this.activity = activity;
    }
    public static void showDialog(){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.setCancelable(false);
        Button done = (Button) dialog.findViewById(R.id.done);
        Button cancel =(Button) dialog.findViewById(R.id.cancel);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newTask = (EditText) dialog.findViewById(R.id.editText);
                String addTask = newTask.getText().toString();
                if (addTask!=null&&!addTask.isEmpty()) {
                    db.addTasks(new Tasks(addTask));
                    Helper.fetchIntoView();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(activity,"Field is Empty!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static void fetchIntoView(){
        tasksList = db.getAllTasksList();

        tasksAdapter = new TasksAdapter(activity,tasksList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tasksAdapter);

    }
}
