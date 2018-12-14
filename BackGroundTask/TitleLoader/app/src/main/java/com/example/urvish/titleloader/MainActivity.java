package com.example.urvish.titleloader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * MainActivity:
 *              - AsyncLoader
 *              - InputMethod Manager
 *              - Connectivity Manager
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoaderManager.LoaderCallbacks<String> {
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
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
    }

    @Override
    public void onClick(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        String queryBook=mEditTextBook.getText().toString();
        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryBook", queryBook);
        getSupportLoaderManager().restartLoader(0, queryBundle,this);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryBook.length()!=0) {
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

    /**
     * calling loader
     * @param id=unique id for loader
     * @param args=arguments
     * @return bookloader object
     */

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this,args.getString("queryBook"));
    }

    /**
     * after finishing loading
     * @param loader=return by loader
     * @param data=data
     */
    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        try {

            JSONObject jsonObject = new JSONObject(data);

            JSONArray itemsArray = jsonObject.getJSONArray("items");


            int i = 0;
            String title = null;
            String authors = null;


            while (i < itemsArray.length() || (authors == null && title == null)) {

                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");


                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }


                i++;
            }


            if (title != null && authors != null){
                mTextViewBookTitle.setText(title);
                mTextViewBookAuthor.setText(authors);
                mEditTextBook.setText("");
            } else {

                mTextViewBookTitle.setText("Not Found");
                mTextViewBookAuthor.setText("");
            }

        } catch (Exception e){

            mTextViewBookTitle.setText("Not Found");
            mTextViewBookAuthor.setText("");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
