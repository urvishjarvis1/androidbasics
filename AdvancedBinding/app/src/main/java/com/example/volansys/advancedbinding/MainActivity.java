package com.example.volansys.advancedbinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.volansys.advancedbinding.databinding.MyLayoutBinding;


public class MainActivity extends AppCompatActivity {
    private MyLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        User user=new User("urvish","21");
        binding.setUser(user);


    }
}
