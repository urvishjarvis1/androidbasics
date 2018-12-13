package com.example.volansys.androidgestures;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

public class DragAndScaleView extends View {
    private Drawable image;
    private float scaleFactor=2.0f;
    private ScaleGestureDetector scaleGestureDetector;

    public DragAndScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        image=context.getResources().getDrawable(R.drawable.ic_launcher_background);
        setFocusable(true);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        scaleGestureDetector=new ScaleGestureDetector(context, new ScaleListener());
    }

    public DragAndScaleView(Context context) {
        super(context);
        image=context.getResources().getDrawable(R.drawable.ic_launcher_background);
        setFocusable(true);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());
        scaleGestureDetector=new ScaleGestureDetector(context, new ScaleListener());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        image.draw(canvas);
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor*=detector.getScaleFactor();
            scaleFactor=Math.max(0.1f, Math.min(scaleFactor, 5.0f));
            invalidate();
            return true;

        }
    }
}
