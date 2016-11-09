package com.iiitd.apurupa.mcassignment.todolist;

import android.content.Intent;
import android.graphics.Rect;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static int ct=10;
    ArrayList<ToDoList> tdlist;
    RecyclerView rvtasks;
    private static final int VERTICAL_ITEM_SPACE =20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       rvtasks= (RecyclerView) findViewById(R.id.todolist_recyclerview);


       DBHelper db= new DBHelper(this);
        tdlist=db.getAllTasks();
        Log.d("Hello","Hi");
        Log.d("ArrayList:",String.valueOf(tdlist.size()));

        ItemListAdapter adapter = new ItemListAdapter(this,tdlist);

        rvtasks.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        rvtasks.setAdapter(adapter);

       rvtasks.setLayoutManager(new LinearLayoutManager(this));
        ct=ct+5;

        Log.d("Value",String.valueOf(ct));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.clear();
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.setGroupVisible(R.id.main_menu_group,true);
        menu.setGroupVisible(R.id.detail_menu_group,false);
        Log.d("TAG", "Insideoncreateoptionsmenu");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("TAG","onoptionsItemsselected");
        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_additem)
        {
            Log.d("TAG","inside");
          //  Toast.makeText(this,"Hello from add",Toast.LENGTH_LONG).show();
            Intent I=new Intent(this,AddTaskActivity.class);
            startActivityForResult(I,1);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request it is that we're responding to

        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("TASK");
                Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

                if(message.equals("TASK ADDED"))
                {
                    refreshtaskslist();
                }
            }
        }

        }

    private void refreshtaskslist() {
        DBHelper db= new DBHelper(this);
        tdlist=db.getAllTasks();
        Log.d("Hello","Hi");
        ItemListAdapter adapter = new ItemListAdapter(this,tdlist);
        rvtasks.setAdapter(adapter);
        rvtasks.setLayoutManager(new LinearLayoutManager(this));
    }

    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}
