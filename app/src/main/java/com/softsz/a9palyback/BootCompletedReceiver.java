package com.softsz.a9palyback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BootCompletedReceiver","BootCompletedReceiver action = "+intent.getAction());
        Intent scanIntent = new Intent(context, ScanFileService.class);
        context.startService(scanIntent);
    }
}
