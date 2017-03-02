package dam.m8.headphoneslistener;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class HeadphoneIntentReciver extends BroadcastReceiver {
    private static final int NOTIFICACION_AURICULARES_CONECTADOS = 1;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {
        //obtenemos el estado del intent
        int state = intent.getIntExtra("state", -1);

        //segun el estado mostraremos la notificacion de que se han conectado los auriculares
        //o eliminaremos la notificacion si luego de conectarlos los desconecta
        switch (state) {
            case 0:
                if (mNotificationManager != null)
                    mNotificationManager.cancel(NOTIFICACION_AURICULARES_CONECTADOS);
                break;
            case 1:
                showNotification("Auriculares conectados", context);
                break;
            default:
                showNotification("Error inesperado", context);
        }
    }

    //Metodo en el que crearemos la notificacion
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showNotification(String s, Context context) {
        //obtenemos el tono de notificacion
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //creamos la notificacion y le añadimos el sonido, vibracion, color de leds y el contenido
        mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(android.R.drawable.stat_sys_headset)
                        .setLargeIcon((((BitmapDrawable) context.getDrawable(R.drawable.logo)).getBitmap()))
                        .setContentTitle(context.getString(R.string.app_name))
                        .setContentText(s)
                        .setVibrate(new long[]{500, 1000})
                        .setLights(Color.BLUE, 5000, 5000)
                        .setSound(alarmSound);

        //Creamos un intent para abrir el reproductor de musica
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);

        //Creamos un PendingIntent para cuando el usuario haga click en la notificacion se abra el
        //reproductor de musica
        PendingIntent contIntent =
                PendingIntent.getActivity(
                        context, 0, intent, 0);
        //Añadimos el PendingIntent a la notificacion
        mBuilder.setContentIntent(contIntent);

        //obtenemos el manager de notificaciones
        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //Mostramos la notificacion al usuario
        mNotificationManager.notify(NOTIFICACION_AURICULARES_CONECTADOS, mBuilder.build());
    }
}