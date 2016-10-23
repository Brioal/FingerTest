package com.brioal.fingertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pro100svitlo.fingerprintAuthHelper.FahErrorType;
import com.pro100svitlo.fingerprintAuthHelper.FahListener;
import com.pro100svitlo.fingerprintAuthHelper.FingerprintAuthHelper;

public class MainActivity extends AppCompatActivity implements FahListener {
    private FingerprintAuthHelper mFAH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFAH = new FingerprintAuthHelper
                .Builder(this, this) //(Context inscance of Activity, FahListener)
                .build();

        if (mFAH.isHardwareEnable()){
            //do some stuff here
        } else {
            //otherwise do
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFAH.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFAH.stopListening();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFAH.onDestroy();
    }


    @Override
    public void onFingerprintStatus(boolean authSuccessful, int errorType, CharSequence errorMess) {
        if (authSuccessful){
            Toast.makeText(this,"解锁成功",Toast.LENGTH_SHORT).show();
            // do some stuff here in case auth was successful
        } else if (mFAH != null){
            // do some stuff here in case auth failed
            switch (errorType){
                case FahErrorType.General.LOCK_SCREEN_DISABLED:
                case FahErrorType.General.NO_FINGERPRINTS:
                    mFAH.showSecuritySettingsDialog();
                    break;
                case FahErrorType.Auth.AUTH_NOT_RECOGNIZED:
                    Toast.makeText(this,"指纹不匹配",Toast.LENGTH_SHORT).show();
                    //do some stuff here
                    break;
                case FahErrorType.Auth.AUTH_TO_MANY_TRIES:
                    Toast.makeText(this,"错误次数过多",Toast.LENGTH_SHORT).show();
                    //do some stuff here
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        
    }

    @Override
    public void onFingerprintListening(boolean listening, long milliseconds) {
        if (listening){
            //add some code here
        } else {
            //add some code here
        }
        if (milliseconds > 0) {
            //if u need, u can show timeout for user
        }
    }
}
