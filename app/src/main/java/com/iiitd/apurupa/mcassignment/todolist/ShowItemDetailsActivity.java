package com.iiitd.apurupa.mcassignment.todolist;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ShowItemDetailsActivity extends AppCompatActivity {

    ViewPager viewpager;
    DetailViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item_details);
        Intent I=getIntent();
        int pos=I.getIntExtra("position",0);


        viewpager=(ViewPager)findViewById(R.id.view_pager);
        adapter=new DetailViewAdapter(this);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(pos);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.clear();

        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.setGroupVisible(R.id.main_menu_group,false);
        menu.setGroupVisible(R.id.detail_menu_group,true);
        Log.d("TAG", "Insideoncreateoptionsmenuin show ");
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }
*/
}
