package com.example.hijack;

import java.util.Timer;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class HijackService extends Service{
    private Timer mTimer;
    private static final String TAG = "HijackService";
    private static final int FOREGROUND_ID = 0;
    
    @Override
    public void onCreate() {        
        super.onCreate();
        this.startForeground(FOREGROUND_ID, new Notification() );
    }
    
    @Override
    public IBinder onBind(Intent intent) {
      
        return null;
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { 
        startTimer();
        return super.onStartCommand(intent, flags, startId);
    }
    
    private void startTimer() {
        Log.d(TAG, "HijackService startTimer ");
        if (mTimer == null) {
            mTimer = new Timer();
            LockTask lockTask = new LockTask(this);
            mTimer.schedule(lockTask, 0L, 1000L);
        }
    }
}
