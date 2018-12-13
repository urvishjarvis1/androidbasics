package com.example.volansys.androidgestures;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    private GestureDetectorCompat mGesturs;
    private Button mBtnNextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGesturs = new GestureDetectorCompat(this, this);
        mGesturs.setOnDoubleTapListener(this);
        mBtnNextActivity=findViewById(R.id.nextactivity);
        mBtnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),TrackerActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);
        if (this.mGesturs.onTouchEvent(event)) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("gesture", "down");

                break;
            case MotionEvent.ACTION_UP:
                Log.d("gesture", "Up");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("gesture", "Move");

                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("gesture", "cancel");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d("gesture", "gesture happend outside of screen bound");
                break;



        }
        return true;

    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("gestures", "onSingleTapConfirmed" + e.toString());
        return true;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("gestures", "DoubleTap" + e.toString());
        return true;
    }


    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("gestures", "onDoubleTapEvent" + e.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("gestures", "onDown" + e.toString());

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("gestures", "onShowprees" + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("gestures", "onSingleTapUp" + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("gestures", "onScroll" + e1.toString() + " " + e2.toString());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("gestures", "onLongPress" + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("gestures", "onFling" + e1.toString() + " " + e2.toString());
        return true;
    }
}
