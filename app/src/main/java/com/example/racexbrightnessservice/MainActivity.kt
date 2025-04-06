package com.example.racexbrightnessservice

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.racexbrightnessservice.helpers.PreferenceHelper
import com.example.racexbrightnessservice.helpers.ServiceHelper
import com.example.racexbrightnessservice.helpers.TimeHelper
import com.example.racexbrightnessservice.receiver.MainActivityReceiver
import com.example.racexbrightnessservice.service.BrightnessService
import com.example.racexbrightnessservice.utils.KswUtils
import com.google.android.material.slider.Slider

class MainActivity : ComponentActivity() {

    private lateinit var column1: LinearLayout
    private var sliderDebounce = System.currentTimeMillis()
    private lateinit var receiver : MainActivityReceiver;

    lateinit var tvCurrentBrightness: TextView
    lateinit var sliderCurrentBrightness: Slider
    lateinit var tvDaylightDuration: TextView
    lateinit var btnTestSettings: Button
    lateinit var testNightMode: CheckBox




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!checkPermissions())
            finish()
        else
            loadActivity();
    }

    override fun onResume() {
        super.onResume()
        if (BrightnessService.isBrightnessServiceEnabled
            && !ServiceHelper.isServiceRunning(
                MyApp.getContext(),
                BrightnessService::class.java
                )
            )
            BrightnessService.StartService(MyApp.getContext())


        val intentFilter = IntentFilter()
        intentFilter.addAction("ovh.msinfo.BRIGHTNESS_UPDATED")
        intentFilter.addAction("ovh.msinfo.IDRIVE_ROTATE_RIGHT")
        intentFilter.addAction("ovh.msinfo.IDRIVE_ROTATE_LEFT")
        intentFilter.addAction("ovh.msinfo.IDRIVE_CLICK")
        intentFilter.addAction("ovh.msinfo.NIGHT_MODE")
        intentFilter.addAction("ovh.msinfo.DAYTIME_DURATION")
        intentFilter.addAction(KswUtils.ZXW_ACTION_KEY_EVT)
        intentFilter.addAction(KswUtils.ZXW_CAN_KEY_EVT)

        receiver = MainActivityReceiver(this);
        ContextCompat.registerReceiver(
            MyApp.getContext(),
            receiver,
            intentFilter,
            ContextCompat.RECEIVER_EXPORTED
        );

        column1.setEnabledWithChildren();
    }

    override fun onPause() {
        super.onPause()
        MyApp.getContext().unregisterReceiver(receiver);
    }

    private fun loadActivity(){
        setContentView(R.layout.activity_main)
        tvCurrentBrightness = findViewById(R.id.tvCurrentBrightness);
        sliderCurrentBrightness = findViewById(R.id.sliderCurrentBrightness);
        column1 = findViewById(R.id.column1);
        tvDaylightDuration = findViewById(R.id.tvDaylightDuration);
        btnTestSettings = findViewById(R.id.btnTestSettings);
        testNightMode = findViewById(R.id.testNightMode);

        tvCurrentBrightness.setText(BrightnessService.brightnessValue.toString());
        sliderCurrentBrightness.value = BrightnessService.brightnessValue.toFloat();


        sliderCurrentBrightness.addOnChangeListener { sliderCurrentBrightness, value, fromUser ->
            if (fromUser && System.currentTimeMillis() - sliderDebounce > 100) {
                sliderDebounce = System.currentTimeMillis()
                BrightnessService.SetBrightness(this, value.toInt())
                tvCurrentBrightness.setText(BrightnessService.brightnessValue.toString());
            }
        }
        val latitude = PreferenceHelper.sInstance.getString("lastKnowLocationLatitude", "-9999").toDouble()
        val longitude = PreferenceHelper.sInstance.getString("lastKnowLocationLongitude", "-9999").toDouble()
        if (latitude.toInt() != -9999 && longitude.toInt() != -9999){
            tvDaylightDuration.setText(
                TimeHelper.GetSunriseTimeString(
                    latitude,
                    longitude
                ) + " - " + TimeHelper.GetSunsetTimeString(latitude, longitude)
            )
        }
        btnTestSettings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        column1.setEnabledWithChildren();
        testNightMode.isChecked = BrightnessService.isNightMode;
        testNightMode.setOnClickListener{
            BrightnessService.SwitchNightMode(this);
        }
    }

    private fun checkPermissions(): Boolean {
        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.INTERNET), 0)
            return false
        }

        return true
    }

    fun LinearLayout.setEnabledWithChildren() {
        var enabled = ServiceHelper.isServiceRunning(MyApp.getContext(),BrightnessService::class.java)
        this.isEnabled = enabled
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.isEnabled = enabled
        }
    }

}

