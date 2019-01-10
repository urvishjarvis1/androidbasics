package com.example.urvish.sharedprefrencelogin.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.urvish.sharedprefrencelogin.fragments.FragmentCommunicator;
import com.example.urvish.sharedprefrencelogin.fragments.LoginFragment;
import com.example.urvish.sharedprefrencelogin.fragments.PinLoginFragment;
import com.example.urvish.sharedprefrencelogin.R;

import java.io.FileOutputStream;

/**
 * Main Activity
 * 2 fragments - Login,PinLogin.
 * Sharedprefrence will holds users data.
 *
 */
public class MainActivity extends AppCompatActivity implements FragmentCommunicator {
    private final String UNAME_KEY = "uname";
    private final String PASS_KEY = "pass";
    private final String PIN_KEY = "pin";
    private final String mpref = "com.example.urvish.sharedprefrencelogin";
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private LoginFragment mLoginFragment;
    private PinLoginFragment mPinLoginFragment;
    private SharedPreferences mSharedPreferences;
    private Intent mIntent;
    private SharedPreferences.Editor prefrenceEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileInsert();
        //sharedprefrence for storing user's data
        checkUser();
        }

    @Override
    protected void onResume() {
        super.onResume();
        checkUser();
    }

    /**
     * overriden method for fragment communication
     *
     * @param uname=Username from fragment
     * @param pass=password from fragment
     * @param pin=pin from fragment
     */

    @Override
    public void sendData(String uname, String pass,int pin) {
        prefrenceEdit.putString(UNAME_KEY, uname);
        prefrenceEdit.putString(PASS_KEY, pass);
        prefrenceEdit.putInt(PIN_KEY, pin);
        prefrenceEdit.apply();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mPinLoginFragment = new PinLoginFragment();
        mFragmentTransaction.replace(R.id.frag_login, mPinLoginFragment);
        mFragmentTransaction.commit();

    }

    /**
     * overridden method for fragment communication
     * @param PIN=pin from fragment
     */

    @Override
    public void sendData(int PIN) {

        if (PIN == mSharedPreferences.getInt(PIN_KEY,0)) {
            mIntent=new Intent(this, TodoListActivity.class);
            startActivity(mIntent);
        } else {
            Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * file insertion method
     */
    private void fileInsert(){
        String filename = "News";
        String string = getString(R.string.news);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void checkUser(){
        mSharedPreferences = getSharedPreferences(mpref, MODE_PRIVATE);
        mFragmentManager = getSupportFragmentManager();
        prefrenceEdit=mSharedPreferences.edit();

        if (!mSharedPreferences.contains(UNAME_KEY) && !mSharedPreferences.contains(PASS_KEY)) {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mLoginFragment = new LoginFragment();
            mFragmentTransaction.replace(R.id.frag_login, mLoginFragment);
            mFragmentTransaction.commit();
        } else {
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mPinLoginFragment = new PinLoginFragment();
            mFragmentTransaction.replace(R.id.frag_login, mPinLoginFragment);
            mFragmentTransaction.commit();
        }
    }
}
