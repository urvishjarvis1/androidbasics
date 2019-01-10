package com.example.volansys.roomdatabase;

import android.arch.persistence.room.Room;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.volansys.roomdatabase.model.User;
import com.example.volansys.roomdatabase.model.UserDataBase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditTextName, mEditTextId, mEditTextRead;
    private Button mBtnInsert, mBtnRead;
    private UserDataBase userDataBase;
    private RadioButton mRadioButtonId,mRadioButtonName;
    private User user;
    private  String res="";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(MainActivity.this, "Id already exists", Toast.LENGTH_SHORT).show();
                    mEditTextId.setError("Change id");
                    break;

                case 2:
                    Toast.makeText(MainActivity.this, "User inserted", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Response")
                            .setMessage("Data:\n"+res)
                            .create().show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    void init(){
        mEditTextId = findViewById(R.id.edittxtid);
        mEditTextName = findViewById(R.id.edittxtname);
        mEditTextRead = (EditText)findViewById(R.id.edittxtread);
        mBtnInsert = findViewById(R.id.btninssubmit);
        mBtnRead = findViewById(R.id.btnreadsubmit);
        mBtnRead.setOnClickListener(this);
        mBtnInsert.setOnClickListener(this);
        mRadioButtonId=findViewById(R.id.byid);
        mRadioButtonName=findViewById(R.id.byname);
        userDataBase= Room.databaseBuilder(this,UserDataBase.class,"Users.db").build();

    }
    void insert(final User user){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                userDataBase.userDao().insertToDatabase(user);
                Message msg=new Message();
                msg.what=2;
                handler.sendMessage(msg);
                }catch (SQLiteConstraintException e){
                    Message msg=new Message();
                    msg.what=1;
                    handler.sendMessage(msg);

            }
        }}).start();


    }
    void getById(final String id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                User users=userDataBase.userDao().getUserById(id);
                res="";

                    res=res+"\nId:"+users.getUId()+"\nName:"+users.getUserName();

                Message msg=new Message();
                msg.what=3;
                handler.sendMessage(msg);
            }
        }).start();
    }
    void getByName(final String name){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users=userDataBase.userDao().getUserByName(name);
                res="";
                for (User u:users) {
                    res=res+"\nId:"+u.getUId()+"\nName:"+u.getUserName();
                    Log.d("datani",u.getUserName()+u.getUId());
                }
                Message msg=new Message();
                msg.what=3;
                handler.sendMessage(msg);

            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btninssubmit:
                user=new User();
                if(!mEditTextId.getText().toString().isEmpty())
                    user.setUId(mEditTextId.getText().toString());
                else
                    mEditTextId.setError("Enter id");
                if(!mEditTextName.getText().toString().isEmpty())
                    user.setUserName(mEditTextName.getText().toString());
                else
                    mEditTextName.setError("Enter Name");
                insert(user);
                break;
            case R.id.btnreadsubmit:
                if(!mRadioButtonName.isChecked()||!mRadioButtonId.isChecked()){
                    if(mRadioButtonId.isChecked()){
                        mEditTextRead.setInputType(InputType.TYPE_CLASS_NUMBER);
                        if(!mEditTextRead.getText().toString().isEmpty()){
                            String id=mEditTextRead.getText().toString();
                            getById(id);
                        }else{
                            mEditTextRead.setError("Enter Id");
                        }
                    }else if(mRadioButtonName.isChecked()){
                        mEditTextRead.setInputType(InputType.TYPE_CLASS_TEXT);
                        if(!mEditTextRead.getText().toString().isEmpty()){
                            String name=mEditTextRead.getText().toString();
                            getByName(name);
                        }else{
                            mEditTextRead.setError("Enter name");
                        }
                    }
                }else{
                    Toast.makeText(this, "Please select the Method byid or byname", Toast.LENGTH_SHORT).show();
                }
            default:
                //todo

        }
    }
}
