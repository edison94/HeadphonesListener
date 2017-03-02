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
        //Creamos el Broadcast que vamos a recibir en este caso de conexión de auriculares
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        // Registramos el receptor de conexión de auriculares
        registerReceiver(myReceiver, filter);
        //Decimos que si el sistema mata el servicio lo vuelva a crear
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        //Desregistramos el receptor
        unregisterReceiver(myReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //Como siempre llamaremos al servicio desde un startService() podemos hacer que este retorne null
        return null;
    }
}