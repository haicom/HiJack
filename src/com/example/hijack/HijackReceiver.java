package com.example.hijack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class HijackReceiver extends BroadcastReceiver{
    private static final String TAG = "HijackReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "HijackReceiver onReceive intent.getAction() = " + intent.getAction());
        if ( intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ) {
            Intent serviceIntent = new Intent(context, HijackService.class);
            context.startService(serviceIntent);
        }
        
        if ( intent.getAction().equals(Intent.ACTION_USER_PRESENT) ) {
            Intent serviceIntent = new Intent(context, HijackService.class);
            context.startService(serviceIntent);
        }
        
    }

}
