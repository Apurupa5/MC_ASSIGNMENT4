package com.iiitd.apurupa.mcassignment.todolist;

import java.util.ArrayList;

/**
 * Created by NB VENKATESHWARULU on 11/2/2016.
 */
public class ToDoList {


    private String mtitle;
    private String mDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public ToDoList(String s) {

    }

    public ToDoList(String s, String s1) {
        this.mtitle=s;
        this.mDescription=s1;
    }

    public ToDoList() {
       this.mDescription=null;
        this.mtitle=null;
    }

    public void ToDoList(String title,String des)
    {
        this.mtitle=title;
        this.mDescription=mDescription;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public static ArrayList<ToDoList> createContactsList(int numContacts) {
        ArrayList<ToDoList> contacts = new ArrayList<ToDoList>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new ToDoList("Person " ,String.valueOf(i)));
        }

        return contacts;
    }
}
