package com.example.jsuisla;

import android.app.Service;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Messenger;

import java.util.Random;


class MyService extends Service {
    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    Messenger messenger = null;
    Random r;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftMillis;
    private boolean mTimerRunning;
}
