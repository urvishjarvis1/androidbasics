package com.example.urvish.localservicedemo;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

/**
 * MainActivity:
 *              - LocalService
 *              - service will download an image from web and put into file and set as ImageView
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTextviewStatus;
    private Button mBtnDownload,mBtnStop;
    private ImageView mImgView;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent INTENT) {
            Bundle bundle=INTENT.getExtras();
            if(bundle!=null){
                String filepath=bundle.getString(DownloadService.FILEPATH);
                int resultCode=bundle.getInt(DownloadService.RESULT);
                if(resultCode==RESULT_OK){
                    Toast.makeText(context, "Download done:"+filepath, Toast.LENGTH_LONG).show();
                    mTextviewStatus.setText(R.string.successful);
                    try{
                        File imgFile=new File(Environment.getExternalStorageDirectory(),bundle.getString(DownloadService.FILENAME));
                        Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile));
                        mImgView.setImageBitmap(bitmap);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Download failed",
                        Toast.LENGTH_LONG).show();
                    mTextviewStatus.setText(R.string.failed);
                }
            }else{
                Toast.makeText(context, "Bundle error", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextviewStatus=(TextView) findViewById(R.id.status);
        mBtnDownload=(Button) findViewById(R.id.btn_download);
        mImgView=(ImageView) findViewById(R.id.img);
        final Intent INTENT=new Intent(this,DownloadService.class);
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                INTENT.putExtra(DownloadService.URLPATH,"https://as01.epimg.net/futbol/imagenes/2016/10/31/champions/1477897663_181226_1477897758_noticia_normal.jpg");
                INTENT.putExtra(DownloadService.FILENAME,"TEST.jpg");
                startService(INTENT);
                mTextviewStatus.setText("service Started");
            }
        });
        mBtnStop=findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(INTENT);
                Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver,new IntentFilter(DownloadService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }
}
