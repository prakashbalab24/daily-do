package com.daily.d;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "taskmanager";

	// Contacts table name
	private static final String TABLE_TASKS = "tasks";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TASK_NAME = "taskname";
//	private static final String KEY_PH_NO = "phone_number";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TASK_NAME + " TEXT"
				+ ")";
		db.execSQL(CREATE_TASKS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addTasks(Tasks tasks) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TASK_NAME, tasks.getTaskName()); // Contact Name

		// Inserting Row
		db.insert(TABLE_TASKS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Tasks getTasks(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
				KEY_TASK_NAME }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Tasks tasks = new Tasks(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1));
		// return contact
		return tasks;
	}
	
	// Getting All Contacts
	public List<Tasks> getAllTasksList() {
		List<Tasks> tasksList = new ArrayList<Tasks>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TASKS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Tasks tasks = new Tasks();
				tasks.setId(Integer.parseInt(cursor.getString(0)));
				tasks.setTaskName(cursor.getString(1));
				// Adding contact to list
				tasksList.add(tasks);
			} while (cursor.moveToNext());
		}

		// return contact list
		return tasksList;
	}

	// Updating single contact
	public int updateContact(Tasks tasks) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TASK_NAME, tasks.getTaskName());

		// updating row
		return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(tasks.getId()) });
	}

	// Deleting single contact
	public void deleteTasks(Tasks tasks) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASKS, KEY_ID + " = ?",
				new String[] { String.valueOf(tasks.getId()) });
		db.close();
	}


	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_TASKS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

	public void deleteTable(){
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(TABLE_TASKS, null, null);
		Helper.fetchIntoView();
	}

}
