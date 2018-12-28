package com.example.urvish.datetimepicker;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Two fragment for taking input from user
 * the date fragment and time fragment was type of dialog fragment
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTxtDateTime;
    private Button mBtnShow;
    private String month_str,year_str,day_str,hr,mn;
    private String am="am";
    private DialogFragment newFragmentDate;
    private DialogFragment newFragmentTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtDateTime=(TextView) findViewById(R.id.dateandtime);
        mBtnShow=(Button) findViewById(R.id.btn_show);
        mBtnShow.setClickable(false);
         newFragmentTime= new TimePickerFragment();
         newFragmentDate = new DatePickerFragment();

    }

    public void takeTime(View view) {
       
        newFragmentTime.show(getSupportFragmentManager(),getString(R.string.time_picker));
    }

    public void takeDate(View view) {
        
        newFragmentDate.show(getSupportFragmentManager(),getString(R.string.date_picker));
        if(hr!=null||mn!=null)
            mBtnShow.setClickable(true);

    }
    public void processDatePicker(int year,int month,int day){
         month_str=Integer.toString(month+1);
        day_str=Integer.toString(day);
        year_str=Integer.toString(year);
        Toast.makeText(getApplicationContext(),
                day_str+"/"+month_str+"/"+year_str, Toast.LENGTH_SHORT).show();

    }
    public void processTimePicker(int hour,int min){

        if(hour>=12){hour=hour%12;am="pm";}
        if(hour==0){hour=12;}
         hr=Integer.toString(hour);
         mn=Integer.toString(min);
        Toast.makeText(getApplicationContext(),
                hr+":"+mn+" "+am,Toast.LENGTH_SHORT).show();
    }
    public void show(View v){
        mTxtDateTime.setVisibility(View.VISIBLE);
        String text="Date:"+day_str+"/"+month_str+"/"+year_str+"\nTime: "+hr+":"+mn+" "+am;
        mTxtDateTime.setText(text);
    }
}
