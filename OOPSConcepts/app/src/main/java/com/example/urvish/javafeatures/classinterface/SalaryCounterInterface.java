package com.example.urvish.javafeatures.classinterface;

import com.example.urvish.javafeatures.classinterface.HumanResource;
import com.example.urvish.javafeatures.classinterface.Developer;

/**
 * Created by urvish on 18/1/18.
 */
interface SalaryCounterInterface {
    //this function will call when the object of this Interface has a reference of class object.
    public void salCounter(float increment, float tax);
}
