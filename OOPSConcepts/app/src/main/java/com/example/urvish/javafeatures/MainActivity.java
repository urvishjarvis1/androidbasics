package com.example.urvish.javafeatures;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.urvish.javafeatures.classinterface.ClassInterfaceActivity;
import com.example.urvish.javafeatures.controlflow.ControlFlowActivity;
import com.example.urvish.javafeatures.inheritancepolymorphism.InheritanceActivity;
import com.example.urvish.javafeatures.structdata.StructDataActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPress(View v) {
        Intent intent;
        Button mBtn;
        mBtn = (Button) v;
        switch (mBtn.getId()) {
            case R.id.btn_controlflow:
                intent = new Intent(this, ControlFlowActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_structdata:
                intent = new Intent(this, StructDataActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_classinterface:
                intent = new Intent(this, ClassInterfaceActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_inheritance:
                intent = new Intent(this, InheritanceActivity.class);
                startActivity(intent);
                break;
        }
    }
}
