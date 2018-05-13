package no.gimse.haakon.smsbot;

import android.Manifest;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Copyright 2018, Hakon Gimse, All rights reserved.
 */

public class MainActivity extends Activity {
    private String s;
    private MainProgram program;
    private SmsSender smsSender;
    private SmsReceiver mSMSreceiver;
    private IntentFilter mIntentFilter;

    private TextView eroorview;

    private final String startMessage="  ";
    boolean botReady=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        eroorview =(TextView)this.findViewById(R.id.errorText);
        eroorview.setText("");

        ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.RECEIVE_SMS,Manifest.permission.INTERNET,Manifest.permission.SEND_SMS},1);

        smsSender=  new SmsSender(program);
        program = new MainProgram(this,smsSender);


        //SMS event receiver
        mSMSreceiver = new SmsReceiver();
        mSMSreceiver.setMainprogram(program);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);


    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        // Unregister the SMS receiver
        unregisterReceiver(mSMSreceiver);
    }

    public void switchClick(View view){
        Switch OnOffSwitch = (Switch)view;
        program.setRunning(OnOffSwitch.isChecked());
    }

    public void dispError(String text){
        eroorview.setText(text);
    }
}
