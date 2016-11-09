package com.iiitd.apurupa.mcassignment.todolist;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mTaskTitle;
    private EditText mTaskDescr;
    private Button msaveTaskButton;
    private Button mcancelTaskButton;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        init();
    }

    private void init() {
        mTaskTitle=(EditText)findViewById(R.id.taskTitleEditText);
        mTaskDescr=(EditText)findViewById(R.id.taskDescrEditTet);
        msaveTaskButton=(Button)findViewById(R.id.saveTaskButton);
        mcancelTaskButton=(Button)findViewById(R.id.cancelTaskButton);
        msaveTaskButton.setOnClickListener(this);
        mcancelTaskButton.setOnClickListener(this);
        db=new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=getIntent();
        switch(view.getId())
        {
            case R.id.saveTaskButton:
                ToDoList td=new ToDoList();
                String title=mTaskTitle.getText().toString();
                String descr=mTaskDescr.getText().toString();
                if(title.equals("")||descr.equals("")) {
                    Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
                    break;
                }
                Log.d(title,descr);

                td.setMtitle(title);
                td.setmDescription(descr);
                db.addTask(td);
                intent.putExtra("TASK","TASK ADDED");
                setResult(RESULT_OK,intent);
                finish();
                 break;
            case R.id.cancelTaskButton:
                List<ToDoList> list=db.getAllTasks();

                intent.putExtra("TASK","TASK NOT ADDED");
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
