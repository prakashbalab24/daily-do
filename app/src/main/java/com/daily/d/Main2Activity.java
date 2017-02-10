package com.daily.d;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Tasks> tasksList;
    private TasksAdapter tasksAdapter;
    private  DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.deleteall)
                {
//                    db.deleteTable();
                    new AlertDialog.Builder(Main2Activity.this)
                            .setTitle("Delete Tasks")
                            .setMessage("Are you sure you want to delete all tasks?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    db.deleteTable();
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
                return false;
            }
        });
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        db = new DatabaseHandler(this);
        new Helper(tasksList,db,tasksAdapter,recyclerView,this);

//        db.addContact(new Tasks(1,"balaji"));
//        db.addContact(new Tasks(2,"manikandan"));

        Helper.fetchIntoView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Helper.showDialog();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
