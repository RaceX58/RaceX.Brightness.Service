package com.example.racexbrightnessservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.helpers.GPSHelper;
import com.example.racexbrightnessservice.helpers.MCUEventHelper;
import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.helpers.ServiceHelper;
import com.example.racexbrightnessservice.helpers.TimeHelper;
import com.example.racexbrightnessservice.service.BrightnessService;

import java.util.Date;

public class BrightnessControlReceiver  extends BroadcastReceiver {
    public static final String RACEX_DIMMING_ACTION = "ovh.msinfo.RACEX_DIMMING_ACTION";
    public static final String RACEX_DIMMING_EXTRA_PERCENT = "ovh.msinfo.RACEX_DIMMING_EXTRA_PERCENT";
    public static final String RACEX_DIMMING_EXTRA_DAY_NIGHT = "ovh.msinfo.RACEX_DIMMING_EXTRA_DAY_NIGHT";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();

        assert action != null;
        if (action.equals(MCUEventHelper.ZXW_CAN_KEY_EVT) && BrightnessService.enableSWC && extras != null) {
            int Value = extras.getInt(MCUEventHelper.ZXW_CAN_KEY_EVT_EXTRA, -1);
            if (Value != -1)
            {
                if (Value==3)
                    if (BrightnessService.IncreaseBrightness(context)){
                        Toast.makeText(context, "Luminosité réduite à " + BrightnessService.brightnessValue, Toast.LENGTH_SHORT).show();
                    }
                else if (Value == 2)
                    if (BrightnessService.DecreaseBrightness(context)){
                        Toast.makeText(context, "Luminosité réduite à " + BrightnessService.brightnessValue, Toast.LENGTH_SHORT).show();
                    }
                else if (Value == 6) {
                        try {
                            BrightnessService.SwitchNightMode(context);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            }
        }
        else if (action.equals(RACEX_DIMMING_ACTION) && extras != null) {
            int Value = extras.getInt(RACEX_DIMMING_EXTRA_PERCENT, -1);
            if (Value != -1 && Value>=0 && Value <=100)
            {
                BrightnessService.brightnessValue = Value;
                try {
                    BrightnessService.SetBrightness(context, BrightnessService.brightnessValue);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Value = extras.getInt(RACEX_DIMMING_EXTRA_DAY_NIGHT, -1);
                if (Value != -1)
                {
                    try {
                        BrightnessService.SwitchNightMode(context);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else if (action.equals(Intent.ACTION_TIME_TICK) && BrightnessService.isAutomaticBrightness)
            timeTicked(context);
        else  if (action.equals(MCUEventHelper.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_EVT) && extras != null) {
            int Value = extras.getInt(MCUEventHelper.ZXW_ORIGINAL_MCU_KEY_FOCUS_MOVE_DATA, -1);
            if (Value != -1)
            {
                if (Value == 8)
                {
                    Intent myIntent = new Intent();
                    myIntent.setAction("ovh.msinfo.IDRIVE_ROTATE_RIGHT");
                    MyApp.getContext().sendBroadcast(myIntent);

                }
                else if (Value == 7)
                {
                    Intent myIntent = new Intent();
                    myIntent.setAction("ovh.msinfo.IDRIVE_ROTATE_LEFT");
                    MyApp.getContext().sendBroadcast(myIntent);
                }
                else if (Value == 5 || Value==6)
                {
                    Intent myIntent = new Intent();
                    myIntent.setAction("ovh.msinfo.IDRIVE_CLICK");
                    MyApp.getContext().sendBroadcast(myIntent);
                }
            }
        }
    }
    private void timeTicked(Context context)  {
        try  {
            double latitude = GPSHelper.getLastKnowLocationLatitude();
            double longitude = GPSHelper.getLastKnowLocationLongitude();
//            Date currentTime = TimeHelper.GetCurrentTimeDate();


//            if (mlocation == null)
//            {
//                latitude = Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLatitude","-9999"));
//                longitude = Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLongitude","-9999"));
//            }
//            else
//            {
//                latitude=mlocation.getLatitude();
//                longitude=mlocation.getLongitude();
//
//                PreferenceHelper.sInstance.saveString("lastKnowLocationLatitude", String.valueOf(latitude));
//                PreferenceHelper.sInstance.saveString("lastKnowLocationLongitude", String.valueOf(longitude));
//            }

            boolean isNight = TimeHelper.IsNight();

            if (BrightnessService.isNightMode != isNight)
                BrightnessService.SwitchNightMode(context);

            Intent intent;
            intent = new Intent();
            intent.setAction("ovh.msinfo.DAYTIME_DURATION");
            MyApp.getContext().sendBroadcast(intent);

        }
        catch (Exception e)
        {
            Toast.makeText(MyApp.getContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
