package com.example.urvish.localservicedemo;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by urvish on 7/2/18.
 * Service:
 *          Downloading image file from url and put into file.
 */

public class DownloadService extends IntentService {
    private int mResult= Activity.RESULT_CANCELED;
    public static final String URLPATH="url";
    public static final String FILENAME="file";
    public static final String FILEPATH="filepath";
    public static final String RESULT="result";
    public static final String NOTIFICATION="com.example.urvish.localservicedemo.receiver";
    private Bitmap mBitmap;
    private String mFileName;
    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String urlPath = intent.getStringExtra(URLPATH);
        mFileName = intent.getStringExtra(FILENAME);
        File foutput=new File(Environment.getExternalStorageDirectory(),mFileName);

        if(foutput.exists()){
            foutput.delete();
        }
        InputStream stream=null;
        FileOutputStream foutstream=null;
        try{
            URL url= new URL(urlPath);
            stream=url.openConnection().getInputStream();
            mBitmap= BitmapFactory.decodeStream(stream);
            stream.close();
            foutstream=new FileOutputStream(foutput.getPath());
            mBitmap.compress(Bitmap.CompressFormat.JPEG,100,foutstream);

            mResult=Activity.RESULT_OK;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(foutstream!=null){
                try{
                    foutstream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            sendBackResult(foutput.getAbsolutePath(),mResult);

        }
    }
    void sendBackResult(String outputfile,int mResult){
        Intent intent=new Intent(NOTIFICATION);
            intent.putExtra(FILEPATH,outputfile);
            intent.putExtra(RESULT,mResult);
            intent.putExtra(FILENAME,mFileName);

            sendBroadcast(intent);
            
    }

}
