package com.example.volansys.androidgestures;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

public class TrackerActivity extends AppCompatActivity {
    private VelocityTracker mVelocityTracker;
    private Button mBtnMouseTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        mBtnMouseTracker=findViewById(R.id.btnnextactivity);
        mBtnMouseTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MultipleTouchActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("gesture", "down");
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("gesture", "Up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("gesture", "Move");
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                Log.d("velocity", "X velocity" + mVelocityTracker.getXVelocity(pointerId));
                Log.d("velocity", "Y velocity" + mVelocityTracker.getYVelocity(pointerId));
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("gesture", "cancel");
                mVelocityTracker.recycle();
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("gesture", "gesture happend outside of screen bound");
                break;



        }
        return true;
    }
}
