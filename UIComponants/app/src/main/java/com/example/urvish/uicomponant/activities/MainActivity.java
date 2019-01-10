package com.example.urvish.uicomponant.activities;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.urvish.uicomponant.R;

/**
 * EditText,seekbar,Radio Group,mSpinner,Button
 * In uname and password enter uname as "urivsh" and password as "1234"
 * Toggle Button, Switch,Checkbox
 * the checkbox status will be diplayed when user clicks the Login Button.
 * The progressdialog will show when user press the Progress Button.
 * An AlertBox will show when user press the Exit button
 */

public class MainActivity extends AppCompatActivity {


    private Spinner mSpinner;
    private ArrayAdapter<CharSequence> mAdapter;
    private SeekBar mSeekBar;
    private Button mBtnLogin;
    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSpinner();
        setSeekbar();
        login();
        setToggle();
        setSwitch();

    }

    /**
     *  this method will set adapter to spinner.
     */
    private void setSpinner() {
        mSpinner = (Spinner) findViewById(R.id.spinner);

        if (mSpinner != null) {
            mSpinner.setOnItemSelectedListener(Listener);
        }
        //set the data to mAdapter
        mAdapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        //set data to view
        mAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        if (mSpinner != null) {
            mSpinner.setAdapter(mAdapter);
        }
    }


    /**
     * this method will set the setOnSeekBarChanegeListerner
     */
    private void setSeekbar() {

        mSeekBar = (SeekBar) findViewById(R.id.seekbar_age);
        mSeekBar.setOnSeekBarChangeListener(SeekbarLister);
    }

    /**
     * methode sets the edittext view and get data from it and validate it
     * it also Toast message for Checkbox view
     */
    private void login() {
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CheckBox mCheckBox = (CheckBox) findViewById(R.id.check);
                EditText mUsername = (EditText) findViewById(R.id.uname);
                EditText mPass = (EditText) findViewById(R.id.password);
                String mUser = mUsername.getText().toString(), mPassact = mPass.getText().toString();
                //for checkbutton state
                if (mCheckBox.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Check state:" + mCheckBox.isChecked(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Check state:" + mCheckBox.isChecked(),
                            Toast.LENGTH_SHORT).show();
                }
                if (!mUsername.getText().toString().isEmpty() && !mPass.getText().toString().isEmpty()) {
                    //for user authentication

                    Intent mIntent = new Intent(MainActivity.this,
                            ListViewActivity.class);
                    startActivity(mIntent);


                } else if ((!mUsername.getText().toString().isEmpty())) {
                    mPass.setError("please enter password");
                    mPass.setFocusable(true);
                } else if (!mPass.getText().toString().isEmpty()) {
                    mUsername.setError("please enter username");
                    mUsername.setFocusable(true);
                } else {
                    mPass.setError("please enter password");
                    mUsername.setError("please enter username");
                }
            }
        });
    }

    /**
     * This will set the toggleButton view
     */
    private void setToggle() {
        final ToggleButton mToggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for chekcing state of toggle button
                if (mToggleButton.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Toggle on!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Toggle off!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * this will set the Switch View
     */
    private void setSwitch() {
        mSwitch = (Switch) findViewById(R.id.switch_ex);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //for chekcing state of Swtich
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getApplicationContext(), "Switch Stage: " + b,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * RadioButtonClicked method will Toast upon user's choice
     * @param view=it holds the view object.
     */
    public void onRadioButtonClicked(View view) {
        //for chekcing state of Swtich
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Male",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.female:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Female",
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * it will set the progress bar and show it
     * @param view
     */
    public void showProgress(View view) {
        final ProgressDialog progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDoalog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDoalog.getProgress() <= progressDoalog
                            .getMax()) {
                        Thread.sleep(100);
                        progressDoalog.incrementProgressBy(1);
                        if (progressDoalog.getProgress() == progressDoalog
                                .getMax()) {
                            progressDoalog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * it will set the alert view and shows it
     * @param view
     */
    public void showAlert(View view) {

        AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
        build.setTitle(R.string.alert);
        build.setMessage(R.string.exit);
        build.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.ok, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        build.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), R.string.cancel, Toast.LENGTH_SHORT).show();
            }
        });
        build.show();

    }

    /**
     * Spinner Methods for itemSelected
     */
    AdapterView.OnItemSelectedListener Listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
            Toast.makeText(getApplicationContext(), "you selected " +
                    adapterView.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    /**
     * seekbar method for progress changed
     */
    SeekBar.OnSeekBarChangeListener SeekbarLister = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar mSeekBar, int pos, boolean b) {
            //updating Text view as seekbar progresses.
            TextView mTextAge = (TextView) findViewById(R.id.age);
            mTextAge.setText(Integer.toString(pos));
        }

        @Override
        public void onStartTrackingTouch(SeekBar mSeekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar mSeekBar) {

        }
    };


}

