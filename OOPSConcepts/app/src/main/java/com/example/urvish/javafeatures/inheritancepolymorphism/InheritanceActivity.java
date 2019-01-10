package com.example.urvish.javafeatures.inheritancepolymorphism;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.urvish.javafeatures.R;

/**
 * inheritance
 * Superclass:Person Subclass:EmployeeInfo
 * method overloading- setData()
 * method overriding -toString()
 * constructor overloading
 */
public class InheritanceActivity extends AppCompatActivity {
    protected Person mPerson = new Person("Person1", 21);
    protected EmployeeInfo mEmployee = new EmployeeInfo("Person2", 21, 101);
    protected EmployeeInfo mEmployee1=new EmployeeInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inheritance);
        TextView mTxtSup= (TextView) findViewById(R.id.txt_super);
        TextView mTxtSub= (TextView) findViewById(R.id.txt_sub);
        TextView mTxtMethodOvr=(TextView) findViewById(R.id.txt_methodovrloding);
        mEmployee1.setData(103);//mehtodoverloading
        mEmployee1.setData("Person3",22,103);//mehtodoverloading
        mTxtSup.setText(mPerson.toString());
        mTxtSub.setText(mEmployee.toString());
        mTxtMethodOvr.setText(mEmployee1.toString());
    }
}
