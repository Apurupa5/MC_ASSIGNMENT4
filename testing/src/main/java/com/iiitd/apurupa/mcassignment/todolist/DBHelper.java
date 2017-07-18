package com.iiitd.apurupa.mcassignment.todolist;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NB VENKATESHWARULU on 11/4/2016.
 */



public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="ToDoListDB";
    private static final String TABLE_TASKS ="Tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TASKTITLE = "taskTitle";
    private static final String KEY_DESCR = "taskDescr";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_TASKTITLE+ " TEXT, "
        + KEY_DESCR + " INTEGER)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Create tables again
        onCreate(db);
    }
    public void addTask(ToDoList task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASKTITLE, task.getMtitle());
        values.put(KEY_DESCR, task.getmDescription());

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }


    public ArrayList<ToDoList> getAllTasks() {
        ArrayList<ToDoList> taskList = new ArrayList<ToDoList>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ToDoList taskitem = new ToDoList();
                taskitem.setId(cursor.getInt(0));
                taskitem.setMtitle(cursor.getString(1));
                taskitem.setmDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCR)));
                taskList.add(taskitem);
            } while (cursor.moveToNext());
        }

        return taskList;
    }
}
