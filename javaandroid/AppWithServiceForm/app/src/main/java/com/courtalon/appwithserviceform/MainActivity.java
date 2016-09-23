package com.courtalon.appwithserviceform;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnStop, btnBind, btnUnbind, btnInc1, btnInc5;
    private TextView textStatus, textCompteur, textMessage;

    // pour communiquer avec le service
    Messenger mService = null;
    // est on lié au service
    boolean isBound = false;


    // recepteur de message du coté activité
    //
    // tous les messages en provenance du service arriveront ici
    //
    Messenger mMessenger = new Messenger(new IncomingHandler());
    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MySecondService.MSG_SET_COMPTEUR:
                    textCompteur.setText("compteur: " + msg.arg1);
                    break;
                case MySecondService.MSG_SET_CHAINE:
                    textMessage.setText("message: " + msg.getData().getString("str1"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
    // sert a etre prévenue quand un Bind ou Unbind au service est réussi
    //
    // cet objet recevra la "notification" que nous somme abonné ou désabonné au service
    //
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // je suis connecté au service
            // je creer un Messenger vers le service
            mService = new Messenger(service);
            textStatus.setText("abonné");
            try {
                Message msg = Message.obtain(null, MySecondService.MSG_REGISTER_CLIENT);
                msg.replyTo = mMessenger;
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            textStatus.setText("désabonné");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("formation_service", "start de MainActivity");
        startService(new Intent(this, MyFirstService.class));

        // exemple avec 2eme service
        btnStart = (Button)findViewById(R.id.buttonStart);
        btnStop = (Button)findViewById(R.id.buttonStop);
        btnBind = (Button)findViewById(R.id.buttonBind);
        btnUnbind = (Button)findViewById(R.id.buttonUnBind);
        btnInc1 = (Button)findViewById(R.id.buttonInc1);
        btnInc5 = (Button)findViewById(R.id.buttonInc5);

        textStatus = (TextView)findViewById(R.id.textAttached);
        textCompteur = (TextView)findViewById(R.id.textCompteur);
        textMessage = (TextView)findViewById(R.id.textString);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, MySecondService.class));
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doUnBindService();
                stopService(new Intent(MainActivity.this, MySecondService.class));
            }
        });

        btnBind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {doBindService();}
        });

        btnUnbind.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {doUnBindService();}
        });

        btnInc1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessageToService(1);
            }
        });
        btnInc5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessageToService(5);
            }
        });
    }

    // fonction d'envoie d'un message au service
    private void sendMessageToService(int value) {
        // est on connecté au service?
        if (isBound && mService != null) {
            Message msg = Message.obtain(null, MySecondService.MSG_SET_COMPTEUR, value, 0);
            msg.replyTo = mMessenger;
            try {mService.send(msg);} catch (RemoteException e) {e.printStackTrace(); }
        }
    }

    // si le service tourne, se binder a lui
    private void CheckServiceRunning() {
        if (MySecondService.isRunning()) {
            doBindService();
        }
    }
    // IMPORTANT: ici, comment s'abonner a un service
    private void doBindService() {
        // le flag BIND_AUTO_CREATE demande a android de creer le service s'il n'est pas creer
        // lorsqu'on s'abonne a lui
        bindService(new Intent(this, MySecondService.class),mConnection, Context.BIND_AUTO_CREATE);
        isBound = true;
        textStatus.setText("binding...");
    }

    private void doUnBindService() {
        // desabonnement du service
        if (isBound) {
            if (mService != null) {
                /*
                 * l'activite envoie un message au service signifiant
                 * qu'il veut se désabonner
                 * et en même temps, indique dans le message a qui renvoyer la reponse
                 *  le replyTo ici (le Messenger de MainActivity)
                 */
                Message msg = Message.obtain(null, MySecondService.MSG_UNREGISTER_CLIENT);
                msg.replyTo = mMessenger;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    Log.i("formation_service", "service crashé");
                }
            }
            // le mConnection est un "callback" pour nous prevenir
            // de l'etat de la connection ou deconnection
            unbindService(mConnection);
            isBound = false;
            textStatus.setText("unbinding...");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("formation_service", "stop de MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnBindService();
    }
}
