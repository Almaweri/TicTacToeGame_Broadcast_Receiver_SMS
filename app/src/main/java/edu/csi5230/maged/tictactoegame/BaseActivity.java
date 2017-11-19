package edu.csi5230.maged.tictactoegame;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by maged on 2017/11/11.
 *
 * Here I created mReceiver and override it with onCreate, on Resume, onPause
 */

public class BaseActivity extends AppCompatActivity{

    protected SMSMessageBroadcastReceiver mReceiver;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mReceiver = new SMSMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(SMSMessageBroadcastReceiver.SMS_ACTION);
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

}
