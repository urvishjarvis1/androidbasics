package com.example.urvish.assignment7;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urvish.assignment7.presenter.RetrofitPresenterImpl;
import com.example.urvish.assignment7.view.RetrofitInterface;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Retrofit activity
 * implements RetrofitInterface
 * getting data from localhost using help of presenter
 * setting data from localhost using help of presenter
 */
public class RetrofitActivity extends AppCompatActivity implements RetrofitInterface, View.OnClickListener {
    private static final String TAG = RetrofitActivity.class.getSimpleName();
    private TextView mTextViewdata;
    private Button mBtngetData, mBtnPostData, mBtnSendAnalytics;
    private EditText mEdittxtName, mEdittextEmail, mEdittxtId;
    private ProgressDialog mProgressDialog;
    private RetrofitPresenterImpl retrofitPresenter;
    private String value;
    private Tracker mTracker;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mProgressDialog.show();
                    break;
                case 2:
                    mProgressDialog.hide();
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        mTracker.setScreenName("RetrofitActivity");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        AnalyticsApplication application = (AnalyticsApplication) getApplicationContext();
        mTracker = application.getDefaultTracker();
        mTextViewdata = findViewById(R.id.txt_data);
        mBtngetData = findViewById(R.id.btn_getdata);
        mEdittxtId = findViewById(R.id.edit_id);
        mEdittxtName = findViewById(R.id.edittxtname);
        mEdittextEmail = findViewById(R.id.edittxtemail);
        String result = emailCheck();
        mBtnSendAnalytics = findViewById(R.id.sendanalytics);
        mBtnSendAnalytics.setOnClickListener(this);
        retrofitPresenter = new RetrofitPresenterImpl(this);
        mProgressDialog = new ProgressDialog(this);
        mBtngetData.setOnClickListener(this);
        mBtnPostData = findViewById(R.id.btn_postdata);
        mBtnPostData.setOnClickListener(this);

    }


    @Override
    public void showLoading() {
        Message msg = new Message();
        msg.what = 1;
        mHandler.sendMessage(msg);
    }

    @Override
    public void hideLoading() {
        Message msg1 = new Message();
        msg1.what = 2;
        mHandler.sendMessage(msg1);
    }

    @Override
    public void showContent(String data) {
        mTextViewdata.setVisibility(View.VISIBLE);
        mTextViewdata.setText(data);
    }

    /**
     * for validation of email
     *
     * @return string of character
     */
    public String emailCheck() {


        mEdittextEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                // TODO Auto-generated method stub
                Is_Valid_Email(mEdittextEmail); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (!isEmailValid(edt.getText().toString())) {
                    edt.setError("Invalid Email Address");
                    value = null;
                } else {
                    value = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence mEdittextEmail) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(mEdittextEmail)
                        .matches();
            } // end of TextWatcher (mEdittextEmail)
        });

        return value;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getdata:
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mBtngetData.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (mEdittxtId.getText().toString().isEmpty()) {
                    mEdittxtId.setError("Enter Data");
                } else {
                    retrofitPresenter.getData(mEdittxtId.getText().toString());
                }
                break;
            case R.id.btn_postdata:
                inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mBtnPostData.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (mEdittxtName.getText().toString().isEmpty() || mEdittextEmail.getText().toString().isEmpty()) {
                    if (mEdittxtName.getText().toString().isEmpty())
                        mEdittxtName.setError("enter data");
                    if (mEdittextEmail.getText().toString().isEmpty())
                        mEdittextEmail.setError("enter data");
                } else {
                    //To validate user's email input
                    String result = emailCheck();
                    if (result != null) {
                        retrofitPresenter.setData(mEdittxtName.getText().toString(), mEdittextEmail.getText().toString());
                    } else {
                        mEdittextEmail.setError("enter proper mail address");
                    }
                }
                break;
            case R.id.sendanalytics:
                mTracker.setScreenName("MainActivity");
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("main")
                        .setAction("button pressed")
                        .build());
                break;
            default:
                Toast.makeText(this, "please press the button", Toast.LENGTH_SHORT).show();
        }
    }
}

