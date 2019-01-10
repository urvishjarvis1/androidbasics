package com.example.volansys.singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentContract studentContract=StudentContract.getInstance();
        StudentData studentData=studentContract.getStudentInstance();
        studentData.setId("1");
        studentData.setName("urvish");
        TextView tv=findViewById(R.id.textview);
        tv.setText("id"+studentData.getId()+"\nname:"+studentData.getName());
        Log.d("hashcode of singleton",""+studentContract.hashCode());
        StudentContract studentContract1=StudentContract.getInstance();
        Log.d("hashcode of singleton",""+studentContract1.hashCode());
    }
}
