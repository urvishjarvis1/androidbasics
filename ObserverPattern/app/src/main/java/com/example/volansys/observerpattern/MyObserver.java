package com.example.volansys.observerpattern;

import android.content.Context;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyObserver implements PropertyChangeListener {
    Context context;
    ShowData showData;
    public MyObserver(Person person,Context context,ShowData showData) {

        this.context=context;
        this.showData=showData;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        showData.showchangedata(evt.getOldValue().toString(),evt.getNewValue().toString());
    }
    void subscribe(Person person){
        person.addListener(this);
    }
    void unsubscribe(Person person){
        person.removeListener(this);
    }

}
