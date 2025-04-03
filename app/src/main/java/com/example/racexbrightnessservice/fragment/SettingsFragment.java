package com.example.racexbrightnessservice.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.R;
import com.example.racexbrightnessservice.helpers.AdbRootAccessHelper;
import com.example.racexbrightnessservice.service.BrightnessService;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    SharedPreferences SP;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        SP = PreferenceManager.getDefaultSharedPreferences(MyApp.getContext());
        SP.registerOnSharedPreferenceChangeListener(this);

        SeekBarPreference seekBarDayMode = findPreference("dayModeBrightness");
        if (seekBarDayMode != null)
            seekBarDayMode.setOnPreferenceChangeListener((preference, newValue) -> {
                int value = (int) newValue;
                value = Math.round(value / 10) * 10; // Ajuste par paliers de 10
                seekBarDayMode.setValue(value);
                return true;
            });

        SeekBarPreference seekBarNightMode = findPreference("nightModeBrightness");
        if (seekBarNightMode != null)
            seekBarNightMode.setOnPreferenceChangeListener((preference, newValue) -> {
                int value = (int) newValue;
                value = Math.round(value / 10) * 10; // Ajuste par paliers de 10
                seekBarNightMode.setValue(value);
                return true;
            });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SP.unregisterOnSharedPreferenceChangeListener(this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
        assert key != null;
        if (key.equals("isBrightnessServiceEnabled")) {
            boolean value = sharedPreferences.getBoolean("isBrightnessServiceEnabled",false);
            if (value)
                BrightnessService.StartService(MyApp.getContext());
            else
                BrightnessService.StopService(MyApp.getContext());
        }
        else if (key.equals("hotspotSupport")) {
            boolean value = sharedPreferences.getBoolean("hotspotSupport",false);
            if (value){
                if(AdbRootAccessHelper.disableHotspot(MyApp.getContext()))
                    Toast.makeText(MyApp.getContext(),"Hotspot has been disabled", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MyApp.getContext(),"An error occured while disabling hotspot", Toast.LENGTH_SHORT).show();
            }

        }
    }
}