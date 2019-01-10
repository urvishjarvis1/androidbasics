package com.example.urvish.sharedprefrencelogin.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.urvish.sharedprefrencelogin.data.ListHelper;
import com.example.urvish.sharedprefrencelogin.R;

/**
 * get data from user and set it to database
 */

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{
    private ListHelper mListHelper;
    private EditText mEditTextAddItem;
    private Button mButtonAdd;


    public static final String RETURN_REPLY="com.example.urvish.sharedprefrencelogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        this.setTitle("Add Item");
        mListHelper=new ListHelper(this);
        mEditTextAddItem=(EditText)findViewById(R.id.editxt_todoitem);
        mButtonAdd=(Button) findViewById(R.id.btn_additem);
        mButtonAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
            String data;
            long id;
            Intent intent=new Intent();
            data=mEditTextAddItem.getText().toString();
            if(data.isEmpty()){
                mEditTextAddItem.setError("EnterData");
                mEditTextAddItem.setFocusable(true);
            }else{

                id=mListHelper.insertData(data);
                intent.putExtra(RETURN_REPLY,id);
                setResult(RESULT_OK,intent);
                finish();
            }


    }

}
