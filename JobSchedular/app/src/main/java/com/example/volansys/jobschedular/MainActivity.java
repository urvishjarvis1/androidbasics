package com.example.volansys.jobschedular;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mbtnJobSch,mbtnJobCancle;
    private RadioGroup mRadioGroupConditions;
    private JobScheduler mJobScheduler;
    private static final int JOB_ID = 0;
    private ComponentName componentName;
    private Switch idleSwitch,chargingSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        componentName=new ComponentName(getPackageName(),NotificationJobService.class.getName());
        final JobInfo.Builder jobBuilder=new JobInfo.Builder(JOB_ID,componentName);
        mbtnJobSch=findViewById(R.id.jobsch);
        idleSwitch=findViewById(R.id.idle);
        chargingSwitch=findViewById(R.id.charging);
        mbtnJobCancle=findViewById(R.id.jobcancel);
        mRadioGroupConditions=findViewById(R.id.netwrokOption);
        mbtnJobSch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedNetworkId=mRadioGroupConditions.getCheckedRadioButtonId();
                int selectedNetworkOpn= JobInfo.NETWORK_TYPE_NONE;


                switch (selectedNetworkId){
                    case R.id.nonetwork:
                        selectedNetworkOpn= JobInfo.NETWORK_TYPE_NONE;
                        break;
                    case R.id.any:
                        selectedNetworkOpn= JobInfo.NETWORK_TYPE_ANY;
                        break;
                    case R.id.wifi:
                        selectedNetworkOpn= JobInfo.NETWORK_TYPE_UNMETERED;
                        break;


                }
                jobBuilder.setRequiredNetworkType(selectedNetworkOpn);
                jobBuilder.setRequiresDeviceIdle(idleSwitch.isChecked());
                jobBuilder.setRequiresCharging(chargingSwitch.isChecked());
                boolean constraintSet = (selectedNetworkOpn != JobInfo.NETWORK_TYPE_NONE)||idleSwitch.isChecked()||chargingSwitch.isChecked();
                if (constraintSet) {
                    JobInfo jobInfo=jobBuilder.build();
                    mJobScheduler.schedule(jobInfo);
                    Toast.makeText(MainActivity.this, "Job Scheduled", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Please Select the Constraint", Toast.LENGTH_SHORT).show();
                }


            }
        });
        mbtnJobCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mJobScheduler!=null){
                    mJobScheduler.cancelAll();
                    Toast.makeText(MainActivity.this, "Job Cancelled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
