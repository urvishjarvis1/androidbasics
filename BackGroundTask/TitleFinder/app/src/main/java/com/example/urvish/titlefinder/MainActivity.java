package com.example.urvish.titlefinder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * MainActivity:
 *              - AsyncTask
 *              - InputMehtod Manager
 *              - Connectivity Manager
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditTextBook;
    private TextView mTextViewBookTitle,mTextViewBookAuthor,mTextViewBookDesc;
    private Button mBtnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditTextBook=(EditText)findViewById(R.id.edittxt_book);
        mTextViewBookTitle=(TextView)findViewById(R.id.txt_booktitle);
        mTextViewBookAuthor=(TextView)findViewById(R.id.txt_bookAuthor);
        mTextViewBookDesc=(TextView)findViewById(R.id.txt_bookDesc);
        mBtnSearch=(Button)findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //auto hide keyboard once the button pressed
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        String queryBook=mEditTextBook.getText().toString();

        //checking for internet conncetion
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        //getting information about conncetion
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryBook.length()!=0) {
            //asynctask's anonymous object.
            new FetchBook(mTextViewBookTitle, mTextViewBookAuthor,mTextViewBookDesc).execute(queryBook);
            mTextViewBookTitle.setText("Loading....");
            mTextViewBookAuthor.setText("");
            mTextViewBookDesc.setText("");
        }

        else {
            if (queryBook.length() == 0) {
                mTextViewBookAuthor.setText("");
                mTextViewBookTitle.setText("Please enter a search term");
            } else {
                mTextViewBookAuthor.setText("");
                mTextViewBookTitle.setText("Please check your network connection and try again.");
            }
        }
    }
}
