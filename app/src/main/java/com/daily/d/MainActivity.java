package com.daily.d;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.daily.d.db.DatabaseHandler;
import com.daily.d.helper.utils.Helper;
import com.daily.d.tasks.Tasks;
import com.daily.d.tasks.TasksAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Tasks> tasksList=null;
    private TasksAdapter tasksAdapter=null;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.deleteall)
                {
//                    db.deleteTable();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.delete_task)
                            .setMessage(R.string.delete_task_desc)
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
