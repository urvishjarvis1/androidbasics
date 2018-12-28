package com.example.volansys.imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    private Button mbtnSwitcher;
    int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnSwitcher=findViewById(R.id.btnimageswitcher);
        imageSwitcher=findViewById(R.id.imageSwitcher);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                return imageView;
            }
        });

    mbtnSwitcher.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showImage(currentIndex);
            currentIndex++;
        }
    });
    }
    public void showImage(int imgindex){
        if(imgindex>=0&&imgindex<=9){
            int id=getResources().getIdentifier("i_"+imgindex,"drawable",getPackageName());
            imageSwitcher.setImageResource(id);

        }else{
            currentIndex=0;
        }
    }
}
