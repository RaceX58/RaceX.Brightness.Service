package com.example.racexbrightnessservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.racexbrightnessservice.MainActivity;
import com.example.racexbrightnessservice.helpers.MCUEventHelper;
import com.example.racexbrightnessservice.helpers.PreferenceHelper;
import com.example.racexbrightnessservice.helpers.TimeHelper;
import com.example.racexbrightnessservice.service.BrightnessService;
import com.example.racexbrightnessservice.utils.KswUtils;

public class MainActivityReceiver extends BroadcastReceiver {

    MainActivity mainActivity;
    public  MainActivityReceiver(MainActivity activity){
        mainActivity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Bundle extras = intent.getExtras();

        assert action != null;
        switch (action) {
            case "ovh.msinfo.DAYTIME_DURATION":

                double latitude = Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLatitude", "-9999"));
                double longitude = Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLongitude", "-9999"));

                if (latitude != -9999 && longitude != -9999)
                    mainActivity.tvDaylightDuration.setText(TimeHelper.GetSunriseTimeString(latitude, longitude) + " - " + TimeHelper.GetSunsetTimeString(latitude, longitude));
                break;
            case "ovh.msinfo.BRIGHTNESS_UPDATED":
                mainActivity.sliderCurrentBrightness.setValue(BrightnessService.brightnessValue);
                mainActivity.tvCurrentBrightness.setText(String.valueOf(BrightnessService.brightnessValue));
                break;
            case "ovh.msinfo.NIGHT_MODE":
                mainActivity.sliderCurrentBrightness.setValue(BrightnessService.brightnessValue);
                mainActivity.testNightMode.setChecked(BrightnessService.isNightMode);
                mainActivity.tvCurrentBrightness.setText(String.valueOf(BrightnessService.brightnessValue));
                break;
            case KswUtils.ZXW_CAN_KEY_EVT:
                int value = extras.getInt(KswUtils.ZXW_CAN_KEY_EVT_EXTRA, -1);
                Toast.makeText(context,KswUtils.ZXW_CAN_KEY_EVT + " - " + String.valueOf(value), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
