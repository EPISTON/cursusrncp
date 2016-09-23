package com.courtalon.appwithserviceform;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MySecondService extends Service {
    private NotificationManager nm;
    private Timer timer = new Timer();
    private int compteur = 0;
    private int increment = 1;
    private static boolean isRunning = false;

    // la classe Messenger est une classe fournit par android
    // facilitant/simplifiant la communication avec un service
    ArrayList<Messenger> mClients = new ArrayList<Messenger>();

    public static final int MSG_REGISTER_CLIENT = 1;
    public static final int MSG_UNREGISTER_CLIENT = 2;
    public static final int MSG_SET_COMPTEUR = 3;
    public static final int MSG_SET_CHAINE = 4;

    // notre recepteur message
    private Messenger mMessenger = new Messenger(new IncomingHandler());


    private void doNotification() {
        /*nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        CharSequence text = getText(R.string.service_started);
        Notification notification = new Notification(
                R.drawable.luma_jaune_icone_64
                ,text
                ,System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        nm.notify(R.string.service_started, notification);*/
    }

    public MySecondService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service_exemple2", "creation du service");

        doNotification();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {onTimerTick();}
        }, 0, 100);
        isRunning = true;
    }



    // consulter si le service tourne
    public static boolean isRunning() {
        return isRunning;
    }

    public void onTimerTick() {
        Log.i("TimerTick", "timer is working: " + compteur);
        compteur += increment;
        sendMessageToUI(compteur);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    // cette fonction est appelée quand l'activité client va envoyer
    // un message au service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service_exemple2", "demarrage de mon service avec " + startId);
        // continuer l'execution du service apres la fin activité
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel(); // permet d'arreter le timer
        compteur = 0;
        // TODO nettoyage notificationManager
        Log.i("service_exemple2", "le service est arreté");
        isRunning = false;
    }

    // le role de ce handler est de gerer les messages en provenance
    // des activités clients (d'ou le incoming)
    public class IncomingHandler extends Handler  {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SET_COMPTEUR:
                    Log.i("service_exemple2", "message recu set incerement = " + msg.arg1);
                    increment = msg.arg1;
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    // servira a renvoyer a l'activité "graphique" la valeur du compteur
    private void sendMessageToUI(int valueToSend) {
        for (int i = mClients.size() - 1; i >= 0; i--) {
            try {
                // j'envoie driectement le compteur
                mClients.get(i).send(Message.obtain(null, MSG_SET_COMPTEUR, valueToSend));
                // envoyer la donnée sous forme de chaine
                Bundle b = new Bundle();
                b.putString("str1", "compteur=" + valueToSend);
                Message m = Message.obtain(null, MSG_SET_CHAINE);
                m.setData(b);
                // j'envoie un message texte (avec le compteur)
                mClients.get(i).send(m);

            } catch (RemoteException e) {
                // si un client ne repond plus (stoppé?), on l'enleve
                Log.i("service_exemple2", e.getMessage());
                mClients.remove(i);
            }

        }
    }

}
