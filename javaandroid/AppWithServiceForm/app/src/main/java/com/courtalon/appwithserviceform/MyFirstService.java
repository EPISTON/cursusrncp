package com.courtalon.appwithserviceform;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyFirstService extends Service {
    public MyFirstService() {
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("formation_service", "demarrage du service (onStart)");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("formation_service", "bin du service (onBind)");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("formation_service", "destruction du service (onStart)");
    }
}
