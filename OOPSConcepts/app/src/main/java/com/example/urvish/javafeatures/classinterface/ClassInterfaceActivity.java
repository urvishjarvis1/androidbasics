package com.example.urvish.javafeatures.classinterface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.urvish.javafeatures.classinterface.HumanResource;
import com.example.urvish.javafeatures.R;
import com.example.urvish.javafeatures.classinterface.Developer;
import com.example.urvish.javafeatures.classinterface.SalaryCounterInterface;

/**
 * Class interface
 * created class Employee,Developer,HumanResource
 * implemented SalaryCounterInterface
 * Method Overriding - salCounter
 */

public class ClassInterfaceActivity extends AppCompatActivity  {
    protected Developer mDev = new Developer(101, "Devloper", 5000);
    protected Developer mDev2 = new Developer(102, "Devloper1", 35000);
    protected HumanResource mEmp2 = new HumanResource(103, "HR1", 50000);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_interface);
        TextView mDevTxt;
        TextView mDev2Txt;
        TextView mEmp2Txt;
        SalaryCounterInterface mSalIncDev=mDev;
        SalaryCounterInterface mSalIncDev2=mDev2;
        SalaryCounterInterface mSalIncEmp2=mEmp2;
        mDevTxt = (TextView) findViewById(R.id.txt_before_dev);
        mDevTxt.setText(mDev.toString());

        mDev2Txt = (TextView) findViewById(R.id.txt_before_dev2);
        mDev2Txt.setText(mDev2.toString());
        mEmp2Txt = (TextView) findViewById(R.id.txt_before_hr);
        mEmp2Txt.setText(mEmp2.toString());
        mSalIncDev.salCounter(.50f,400);
        mSalIncDev2.salCounter(.60f,400);
        mSalIncEmp2.salCounter(.70f,4355);
        mDevTxt = (TextView) findViewById(R.id.txt_after_dev);
        mDevTxt.setText(mDev.toString());
        mDev2Txt = (TextView) findViewById(R.id.txt_after_dev2);
        mDev2Txt.setText(mDev2.toString());
        mEmp2Txt = (TextView) findViewById(R.id.txt_after_hr);
        mEmp2Txt.setText(mEmp2.toString());

    }

}
