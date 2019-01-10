package com.example.volansys.observerpattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Person {
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
    List<PropertyChangeListener> listeners=new ArrayList<>();
    private String fname;
    private String lname;

    public Person() {
    }

    public Person(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        notifyListeners(this,FIRSTNAME,this.fname,this.fname=fname);
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        notifyListeners(this,LASTNAME,this.lname,this.lname=lname);
    }
    private void notifyListeners(Object object,String property,String oldValue,String newValue){
        for (PropertyChangeListener name:listeners) {
            name.propertyChange(new PropertyChangeEvent(this,property,oldValue,newValue));
        }
    }
    public void addListener(PropertyChangeListener listener){
        if(!listeners.contains(listener))
        listeners.add(listener);
    }
    public void removeListener(PropertyChangeListener listener){
        if(listeners.contains(listener))
            listeners.remove(listener);
    }
}

