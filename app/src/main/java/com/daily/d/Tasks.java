package com.daily.d;

/**
 * Created by prakash-bala on 9/2/17.
 */

public class Tasks {
    private int id;
    private String taskName;
    private int done;

    public Tasks(){

    }
    public Tasks(int id,String taskName){
        this.id = id;
        this.taskName = taskName;
    }
    public Tasks(String taskName){
        this.taskName = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
