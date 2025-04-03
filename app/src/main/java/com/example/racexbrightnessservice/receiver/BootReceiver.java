package com.example.racexbrightnessservice.receiver;

import static java.util.concurrent.Executors.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.helpers.ServiceHelper;
import com.example.racexbrightnessservice.service.BrightnessService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isBrightnessServiceEnabled = PreferenceHelper.sInstance.getBoolean("isBrightnessServiceEnabled", false);
        boolean restoreBrightnessService = PreferenceHelper.sInstance.getBoolean("restoreBrightnessService", false);
        if (!isBrightnessServiceEnabled || !restoreBrightnessService)
            return;
        BrightnessService.StartService(MyApp.getContext());
    }
}
