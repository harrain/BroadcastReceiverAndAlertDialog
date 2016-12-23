package com.example.broadcastdialog;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private LocalBroadcastManager localBroadcastManager;
    private ForceOfflineReceiver offlineReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        findViewById(R.id.force_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.example.broadcastdialog.FORCE_OFFLINE");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastdialog.FORCE_OFFLINE");
        offlineReceiver = new ForceOfflineReceiver();
        //注册本地广播器
        localBroadcastManager.registerReceiver(offlineReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(offlineReceiver);
    }
}
