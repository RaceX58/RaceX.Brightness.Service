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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

                if (latitude != -9999 && longitude != -9999){
                    Date sunriseTime = TimeHelper.GetSunriseTimeDate(latitude, longitude);
                    Date sunsetTime = TimeHelper.GetSunsetTimeDate(latitude, longitude);
                    // Formatteur d'heure au format HH:mm
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

                    // Conversion et affichage
                    assert sunriseTime != null;
                    mainActivity.tvSunriseTime.setText(sdf.format(sunriseTime));
                    assert sunsetTime != null;
                    mainActivity.tvSunsetTime.setText(sdf.format(sunsetTime));
                    break;
                }


            case "ovh.msinfo.BRIGHTNESS_UPDATED":
                mainActivity.sliderCurrentBrightness.setProgress(BrightnessService.brightnessValue);
                mainActivity.tvCurrentBrightness.setText(String.valueOf(BrightnessService.brightnessValue));
                break;
            case "ovh.msinfo.NIGHT_MODE":
                mainActivity.sliderCurrentBrightness.setProgress(BrightnessService.brightnessValue);
                mainActivity.tvCurrentBrightness.setText(String.valueOf(BrightnessService.brightnessValue));
                break;
            case KswUtils.ZXW_CAN_KEY_EVT:
                int value = extras.getInt(KswUtils.ZXW_CAN_KEY_EVT_EXTRA, -1);
                Toast.makeText(context,KswUtils.ZXW_CAN_KEY_EVT + " - " + String.valueOf(value), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
