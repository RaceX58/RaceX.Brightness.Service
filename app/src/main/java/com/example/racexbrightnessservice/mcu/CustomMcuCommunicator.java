package com.example.racexbrightnessservice.mcu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.helpers.ServiceHelper;
import com.example.racexbrightnessservice.receiver.BrightnessControlReceiver;
import com.example.racexbrightnessservice.service.BrightnessService;

import projekt.auto.mcu.ksw.serial.McuCommunicator;
import projekt.auto.mcu.ksw.serial.collection.McuEvent;
import projekt.auto.mcu.ksw.serial.collection.McuEventLogic;
import projekt.auto.mcu.ksw.serial.reader.Reader;
import projekt.auto.mcu.ksw.serial.writer.SerialWriter;
import projekt.auto.mcu.ksw.serial.writer.Writer;

public class CustomMcuCommunicator extends McuCommunicator {

    public static String mcuSource;

    public McuCommunicator.McuAction mcuAction = (cmdType, data) -> {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (cmdType != 0 || data.length > 0) {
                    McuEvent event = McuEventLogic.Companion.getMcuEvent(cmdType, data);
                    if (event == McuEvent.iDriveKnobTurnClockwise){
                        Toast.makeText(MyApp.getContext(),"McuEvent : iDriveKnobTurnClockwise", Toast.LENGTH_SHORT).show();
                    }
                    else if (event == McuEvent.MediaNextButtonPressed){
                        if (ServiceHelper.isServiceRunning(MyApp.getContext(), BrightnessService.class)){
                            BrightnessService.IncreaseBrightness(MyApp.getContext());
                        }
                    }
                    else if (event == McuEvent.MediaPreviousButtonPressed){
                        if (ServiceHelper.isServiceRunning(MyApp.getContext(), BrightnessService.class)){
                            BrightnessService.DecreaseBrightness(MyApp.getContext());
                        }
                    }
                    else if (event == McuEvent.MediaPlayPauseButtonPressed){
                        if (ServiceHelper.isServiceRunning(MyApp.getContext(), BrightnessService.class)){
                            try {
                                BrightnessService.SwitchNightMode(MyApp.getContext());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else if (event == McuEvent.SWITCHED_TO_ARM){
                        boolean isEnabled = PreferenceHelper.sInstance.getBoolean("enableSoundRestorer", false);
                        if (isEnabled && ServiceHelper.isServiceRunning(MyApp.getContext(), BrightnessService.class)){
                            try {
                                SoundRestorer.restoreSound(BrightnessService.MyMcuCommunicator);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    else if (event == McuEvent.SWITCHED_TO_OEM){
                        boolean isEnabled = PreferenceHelper.sInstance.getBoolean("enableSoundRestorer", false);
                        if (isEnabled && ServiceHelper.isServiceRunning(MyApp.getContext(), BrightnessService.class)){
                            try {
                                SoundRestorer.restoreSound(BrightnessService.MyMcuCommunicator);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        }).start();
    };

    public CustomMcuCommunicator(Writer mcuWriter, Reader mcuReader, Context context) {
        super(mcuWriter, mcuReader);

    }

    public static void GetMcuPath(){
        setMcuSource("/dev/ttyHS1");
        return;
//        var sharedValue = PreferenceHelper.sInstance.getString("mcuSource",null);
//        if (sharedValue != null){
//            setMcuSource(sharedValue);
//            return;
//        }
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q && Build.DISPLAY.contains("M600")) {
//            setMcuSource("/dev/ttyHS1");
//        } else if (Build.DISPLAY.contains("8937")) {
//            setMcuSource("/dev/ttyHSL1");
//        } else {
//            setMcuSource("/dev/ttyMSM1");
//        }
    }
    // Méthode pour mettre à jour la source MCU
    public static void setMcuSource(String newSource) {
        mcuSource = newSource;
        PreferenceHelper.sInstance.saveString("mcuSource", mcuSource);
    }
    // Méthode pour récupérer la source MCU
    public static String getMcuSource() {
        return mcuSource;
    }

    // N'oublie pas de désenregistrer le receiver lors de la destruction de l'objet
    public void unregisterReceiver(Context context) {
//        if (context != null) {
//            context.unregisterReceiver(receiver);
//        }
    }
}
