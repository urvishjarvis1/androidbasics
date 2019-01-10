package com.example.volansys.observerpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ShowData{
    private TextView mTextViewResult;
    private Button mBtnChangeName;
    private MyObserver myObserver;
    private ToggleButton mtoogleSub;
    private Person person;
    private ScrollView scrollView;
    private int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView=findViewById(R.id.scrlview);
        mTextViewResult=findViewById(R.id.txtviewresult);
        mBtnChangeName=findViewById(R.id.btnchangename);
        person=new Person("urvish","rana");
        myObserver=new MyObserver(person,this,this);
        mBtnChangeName.setOnClickListener(this);
        mtoogleSub=findViewById(R.id.btn_sub);
        mtoogleSub.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnchangename:
                id++;
                if(id%2==0){

                    person.setLname("Rana");

                } else {

                    person.setLname("Jarvis");


                }
                break;
            case R.id.btn_sub:
                if(mtoogleSub.isChecked()){
                   myObserver.subscribe(person);
                }else{
                    myObserver.unsubscribe(person);
                }
        }
    }

    @Override
    public void showchangedata(String oldValue, String newvalue) {
        mTextViewResult.append("Old Value:"+oldValue+"->"+"New Value:"+newvalue+"\n");
        scrollView.scrollBy(0,100);

    }
}
