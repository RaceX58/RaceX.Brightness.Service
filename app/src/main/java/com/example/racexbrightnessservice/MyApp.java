package com.example.racexbrightnessservice;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.mcu.CustomMcuCommunicator;
import com.example.racexbrightnessservice.service.BrightnessService;

public class MyApp extends Application {
    private static MyApp instance;
    public  static final String CHANNEL_ID = "racexbrightnessserviceID";
    public static MyApp getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }
    private void initPreferences() {
        PreferenceHelper.initDefault(this);
        CustomMcuCommunicator.mcuSource = PreferenceHelper.sInstance.getString("mcuSource","undefined");
        BrightnessService.brightnessValue = PreferenceHelper.sInstance.getInt("brightnessValue",50);
        BrightnessService.isNightMode = PreferenceHelper.sInstance.getBoolean("isNightMode",false);
        BrightnessService.isAutomaticBrightness = PreferenceHelper.sInstance.getBoolean("isAutomaticBrightness",true);
        BrightnessService.enableSWC = PreferenceHelper.sInstance.getBoolean("enableSWC",false);
        BrightnessService.isBrightnessServiceEnabled = PreferenceHelper.sInstance.getBoolean("isBrightnessServiceEnabled",false);
        BrightnessService.dayModeBrightness = PreferenceHelper.sInstance.getInt("dayModeBrightness",80);
        BrightnessService.nightModeBrightness = PreferenceHelper.sInstance.getInt("nightModeBrightness",20);
        BrightnessService.hotspotSupport = PreferenceHelper.sInstance.getBoolean("hotspotSupport",false);
        BrightnessService.restoreBrightnessService = PreferenceHelper.sInstance.getBoolean("restoreBrightnessService",false);
    }
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
        initPreferences();
        createNotificationChannel();
    }
    private void createNotificationChannel(){
        // Créer un canal de notification si l'Android est >= API 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Brightness Service Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
