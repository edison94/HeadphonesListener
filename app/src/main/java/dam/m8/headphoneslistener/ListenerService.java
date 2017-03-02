package dam.m8.headphoneslistener;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ListenerService extends Service {
    private HeadphoneIntentReciver myReceiver;

    @Override
    public void onCreate() {
        //Inicializamos el BroadcastReciver
        myReceiver = new HeadphoneIntentReciver();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(myReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}