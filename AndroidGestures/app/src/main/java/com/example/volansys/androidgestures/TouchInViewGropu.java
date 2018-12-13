package com.example.volansys.androidgestures;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class TouchInViewGropu extends AppCompatActivity {
    private View parentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_in_view_gropu);
        parentView=findViewById(R.id.parentview);
        parentView.post(new Runnable() {
            @Override
            public void run() {
                Rect rect=new Rect();
                ImageButton mBtnImg=findViewById(R.id.btnImg);
                mBtnImg.setEnabled(true);
                mBtnImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TouchInViewGropu.this,
                                "Touch occured inside the ImageButton touch region",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                mBtnImg.getHitRect(rect);
                rect.right+=100;
                rect.bottom+=100;
                TouchDelegate touchDelegate=new TouchDelegate(rect,mBtnImg);
                if(View.class.isInstance(mBtnImg.getParent())){
                    ((View)mBtnImg.getParent()).setTouchDelegate(touchDelegate);
                }
            }

        });

    }
}
