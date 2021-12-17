package com.example.jsuisla;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;
import java.util.Random;


public class MyService extends Service {

        private Looper mServiceLooper;
        private ServiceHandler mServiceHandler;
        //my variables
        private String message,phoneNumber;
        int NotID = 1;
        NotificationManager notificationManager;

        // Handler that receives messages from the thread
        private final class ServiceHandler extends Handler {
            public ServiceHandler(Looper looper) {
                super(looper);
            }

            @Override
            public void handleMessage(Message msg) {
                // Normally we would do some work here, like download a file.
                // For our sample, we just sleep for 5 seconds.
                //setup how many messages
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Integer year,month,day,hour,minutes;
                Messenger messenger = null;
                Bundle extras = msg.getData();

                Calendar currentDate = Calendar.getInstance();
                Calendar calendar = Calendar.getInstance();

                if (extras != null) {

                    year = extras.getInt("year");
                    month = extras.getInt("month");
                    day = extras.getInt("day");
                    hour = extras.getInt("hour");
                    minutes = extras.getInt("minutes");
                    phoneNumber = extras.getString("phoneNumber");
                    message = extras.getString("message");

                    messenger = (Messenger) extras.get("MESSENGER");


                    calendar.set(year,month,day,hour,minutes,0);
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                }

                boolean isbefore = currentDate.before(calendar);

                while (currentDate.before(calendar)) {
                    synchronized (this) {
                        try {
                            wait(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    currentDate = Calendar.getInstance();

                }

                    if(currentDate.after(calendar)){
                        String info = "Service run ";
                        Log.d("intentServer", info);
                        if (messenger != null) {
                            Message mymsg = Message.obtain();
                            mymsg.obj = info;
                            try {
                                messenger.send(mymsg);
                            } catch (android.os.RemoteException e1) {
                                Log.w(getClass().getName(), "Exception sending message", e1);
                            }
                        }
                        // } else {
                        //no handler, so use notification
                        makeNotification(info + "calendar :"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+ calendar.get(Calendar.DAY_OF_MONTH) + "// Current : "+ currentDate.toString());
                        sendSMS_by_smsManager();
                        stopSelf(msg.arg1);
                    }


                // Stop the service using the startId, so that we don't stop
                // the service in the middle of handling another job

            }
        }

        @Override
        public void onCreate() {
            // Start up the thread running the service.  Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block.  We also make it
            // background priority so CPU-intensive work will not disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();

            // Get the HandlerThread's Looper and use it for our Handler
            mServiceLooper = thread.getLooper();
            mServiceHandler = new ServiceHandler(mServiceLooper);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

            // For each start request, send a message to start a job and deliver the
            // start ID so we know which request we're stopping when we finish the job
            Message msg = mServiceHandler.obtainMessage();
            msg.arg1 = startId;//needed for stop.
            msg.setData(intent.getExtras());
            mServiceHandler.sendMessage(msg);

            // If we get killed, after returning from here, restart
            return START_STICKY;
        }

        @Override
        public IBinder onBind(Intent intent) {
            // We don't provide binding, so return null
            return null;
        }

        @Override
        public void onDestroy() {
            Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
        }

        public void makeNotification(String message) {

            Notification notification = new NotificationCompat.Builder(getApplicationContext(), ConfigMessage.id)
                    .setSmallIcon(R.drawable.logo)
                    .setWhen(System.currentTimeMillis())  //When the event occurred, now, since noti are stored by time.
                    .setContentTitle("Service")   //Title message top row.
                    .setContentText(message)  //message when looking at the notification, second row
                    .setAutoCancel(true)   //allow auto cancel when pressed.
                    .build();  //finally build and return a Notification.

            //Show the notification
            notificationManager.notify(NotID, notification);
            NotID++;
        }

    private void sendSMS_by_smsManager()  {



        try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            // Send Message
            smsManager.sendTextMessage(phoneNumber,
                    null,
                    message,
                    null,
                    null);

            Toast.makeText(getApplicationContext(),"Your sms has successfully sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your sms has failed... " + ex.getMessage() + phoneNumber,
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    }

