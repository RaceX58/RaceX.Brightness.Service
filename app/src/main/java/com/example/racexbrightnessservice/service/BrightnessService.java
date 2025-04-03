package com.example.racexbrightnessservice.service;

import static com.example.racexbrightnessservice.MyApp.CHANNEL_ID;
import static com.example.racexbrightnessservice.helpers.ServiceHelper.isServiceRunning;
import static java.util.concurrent.Executors.newScheduledThreadPool;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.R;
import com.example.racexbrightnessservice.helpers.AdbRootAccessHelper;
import com.example.racexbrightnessservice.helpers.MCUEventHelper;
import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.helpers.TimeHelper;
import com.example.racexbrightnessservice.mcu.CustomMcuCommunicator;
import com.example.racexbrightnessservice.mcu.SoundRestorer;
import com.example.racexbrightnessservice.receiver.BrightnessControlReceiver;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import projekt.auto.mcu.ksw.serial.McuCommunicator;
import projekt.auto.mcu.ksw.serial.collection.McuCommands;
import projekt.auto.mcu.ksw.serial.reader.LogcatReader;
import projekt.auto.mcu.ksw.serial.reader.SerialReader;
import projekt.auto.mcu.ksw.serial.writer.SerialWriter;


public class BrightnessService extends Service {

    public static CustomMcuCommunicator MyMcuCommunicator;
    public static  boolean isBrightnessServiceEnabled = false;
    public static int brightnessValue = 50;
    public static boolean isNightMode = false;
    public static boolean isAutomaticBrightness = false;
    public static boolean enableSWC = false;
    public static BrightnessControlReceiver receiver;
    public static int dayModeBrightness = 80;
    public static int nightModeBrightness = 20;
    public  static boolean hotspotSupport;
    public  static boolean restoreBrightnessService;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public BrightnessService() {
        receiver = new BrightnessControlReceiver();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if (hotspotSupport){
            if(AdbRootAccessHelper.disableHotspot(MyApp.getContext()))
                Toast.makeText(MyApp.getContext(),"Hotspot has been disabled", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyApp.getContext(),"An error occured while disabling hotspot", Toast.LENGTH_SHORT).show();
        }

        CustomMcuCommunicator.GetMcuPath();
        AdbRootAccessHelper.grantMcuSourcePermissions(getApplicationContext());

        // Créer la notification
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Brightness Service")
                .setContentText("Service is running in the background")
                .setSmallIcon(R.drawable.ic_launcher_background)  // Assurez-vous d'avoir une icône valide
                .build();

        startForeground(1, notification);
        var serialWriter = new SerialWriter(CustomMcuCommunicator.mcuSource);
        MyMcuCommunicator = new CustomMcuCommunicator(serialWriter, new LogcatReader(), getApplicationContext());
        registerReceiver(MyApp.getContext());
        PreferenceHelper.sInstance.saveBoolean("isBrightnessServiceEnabled", true);
        Toast.makeText(getApplicationContext(), "Service démarré sur " + CustomMcuCommunicator.mcuSource, Toast.LENGTH_SHORT).show();


        try {
            MyMcuCommunicator.mcuReader.startReading(MyMcuCommunicator.mcuAction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            if (PreferenceHelper.sInstance.getBoolean("enableSoundRestorer", false))
                SoundRestorer.restoreSound(MyMcuCommunicator);
        } catch (Exception e) {
        }

        try {
            if(RestoreBrightness(getApplicationContext())){
                Toast.makeText(getApplicationContext(),"Brightness restored at " + brightnessValue, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Error - RestoreBrightness : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }

        return START_NOT_STICKY;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        MyMcuCommunicator.mcuReader.stopReading();
        unregisterReceiver(MyApp.getContext());
        stopForeground(true);  // Supprime la notification lorsque le service est détruit
        PreferenceHelper.sInstance.saveBoolean("isBrightnessServiceEnabled", false);
        Toast.makeText(getApplicationContext(), "Service arrêté", Toast.LENGTH_SHORT).show();
    }
    public void registerReceiver(Context context) {
        if (context != null) {
            ContextCompat.registerReceiver(
                    MyApp.getContext(),
                    receiver,
                    GetIntentFilter(),
                    ContextCompat.RECEIVER_EXPORTED);
        }
    }
    public void unregisterReceiver(Context context) {
        if (context != null) {
            context.unregisterReceiver(receiver);
        }
    }
    public IntentFilter GetIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(MCUEventHelper.ZXW_CAN_KEY_EVT);
        filter.addAction(BrightnessControlReceiver.RACEX_DIMMING_ACTION);
        filter.addAction(Intent.ACTION_TIME_TICK);
        filter.addAction(MCUEventHelper.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT);
        return filter;
    }

    // Démarrer et arrêter le service
    public static void StartService(Context context) {
        if (!isServiceRunning(context, BrightnessService.class)) {
            Intent serviceIntent = new Intent(context, BrightnessService.class);
            context.startService(serviceIntent);
        } else {
            Toast.makeText(context, "Le service est déjà en cours d'exécution", Toast.LENGTH_SHORT)
                    .show();
        }
    }
    public static void StopService(Context context) {
        if (isServiceRunning(context, BrightnessService.class)){
            Intent serviceIntent = new Intent(context, BrightnessService.class);
            context.stopService(serviceIntent);
        }
    else{
            Toast.makeText(context, "Le service est déjà arrêté", Toast.LENGTH_SHORT)
                    .show();
        }

    }


    public static boolean IncreaseBrightness(Context context){
        try {
            if (!isServiceRunning(MyApp.getContext(),BrightnessService.class))
                return false;
            if (brightnessValue < 100)
                brightnessValue += 10;

            if (brightnessValue >= 100)
                brightnessValue = 100;

            return SetBrightness(context, brightnessValue);

        } catch (Exception e) {
            Toast.makeText(context, "Erreur lors de l'augmentation de la luminosité: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();  // Affichage de l'exception dans la console pour débogage
            return  false;
        }
    }
    public static boolean DecreaseBrightness(Context context){
        try {
            if (!isServiceRunning(MyApp.getContext(),BrightnessService.class))
                return false;
            if (brightnessValue > 10)
                brightnessValue -= 10;

            if (brightnessValue <= 0)
                brightnessValue = 10;

            return SetBrightness(context, brightnessValue);

        } catch (Exception e) {
            Toast.makeText(context, "Erreur lors de la diminution de la luminosité: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();  // Affichage de l'exception dans la console pour débogage
            return  false;
        }
    }
    public static boolean SetBrightness(Context context, int value) throws Exception {
        try {
            byte[] byteArray = new byte[]{(byte) value};
            brightnessValue = value;
            PreferenceHelper.sInstance.saveInt("brightnessValue", value);
            Intent intent = new Intent();
            intent.setAction("ovh.msinfo.BRIGHTNESS_UPDATED");
            MyApp.getContext().sendBroadcast(intent);

            MyMcuCommunicator.sendCommand(new McuCommands.SetBrightnessLevel((byte)value));
            return true ;
        }
        catch (Exception e) {
            Toast.makeText(context, "Error setting brightness : " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();  // Affichage de l'exception dans la console pour débogage
            return  false;
        }

    }
    public static boolean RestoreBrightness(Context context) throws Exception {
        if (isAutomaticBrightness){
            boolean isNight = TimeHelper.IsNight();
            if (BrightnessService.isNightMode != isNight)
                return SwitchNightMode(context);
            else {
                int nightModeValue = PreferenceHelper.sInstance.getInt("nightModeBrightness", 20);
                int dayModeValue = PreferenceHelper.sInstance.getInt("dayModeBrightness", 100);
                BrightnessService.brightnessValue =  BrightnessService.isNightMode ? nightModeValue : dayModeValue;
                return SetBrightness(context, BrightnessService.brightnessValue);
            }
        }
        else {
            return SetBrightness(context, BrightnessService.brightnessValue);
        }
    }
    public static boolean SwitchNightMode(Context context) throws Exception {

        int nightModeValue = PreferenceHelper.sInstance.getInt("nightModeBrightness", 20);
        int dayModeValue = PreferenceHelper.sInstance.getInt("dayModeBrightness", 100);

        BrightnessService.isNightMode =! BrightnessService.isNightMode;
        BrightnessService.brightnessValue =  BrightnessService.isNightMode ? nightModeValue : dayModeValue;

        PreferenceHelper.sInstance.saveBoolean("isNightMode", BrightnessService.isNightMode);

        if (!SetBrightness(context, BrightnessService.brightnessValue))
            return false;

        Intent intent = new Intent();
        intent.setAction("ovh.msinfo.NIGHT_MODE");
        MyApp.getContext().sendBroadcast(intent);
        Toast.makeText(MyApp.getContext(), BrightnessService.isNightMode ? "SWITCH NIGHT MODE : " + String.valueOf(nightModeValue) + " %" : "SWITCH DAY MODE : "+ String.valueOf(dayModeValue) + " %",Toast.LENGTH_SHORT).show();

        return true;

    }


}