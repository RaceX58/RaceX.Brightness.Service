package com.example.racexbrightnessservice

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.example.racexbrightnessservice.helpers.PreferenceHelper
import com.example.racexbrightnessservice.helpers.ServiceHelper
import com.example.racexbrightnessservice.helpers.TimeHelper
import com.example.racexbrightnessservice.receiver.MainActivityReceiver
import com.example.racexbrightnessservice.service.BrightnessService
import com.example.racexbrightnessservice.utils.KswUtils
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : ComponentActivity() {

    private lateinit var receiver : MainActivityReceiver
    lateinit var tvCurrentBrightness: TextView
    lateinit var tvSunriseTime: TextView
    lateinit var tvSunsetTime: TextView
    lateinit var sliderCurrentBrightness: SeekBar
    lateinit var autoModeSwitch: SwitchMaterial
    lateinit var switchEnableService: SwitchMaterial

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!checkPermissions())
            finish()
        else
            loadActivity()
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

        receiver = MainActivityReceiver(this)
        ContextCompat.registerReceiver(
            MyApp.getContext(),
            receiver,
            intentFilter,
            ContextCompat.RECEIVER_EXPORTED
        )

        val latitude = PreferenceHelper.sInstance.getString("lastKnowLocationLatitude", "-9999").toDouble()
        val longitude = PreferenceHelper.sInstance.getString("lastKnowLocationLongitude", "-9999").toDouble()

        if (latitude.toInt() != -9999 && longitude.toInt() != -9999){
            val sunriseTime = TimeHelper.GetSunriseTimeDate(latitude, longitude)
            val sunsetTime = TimeHelper.GetSunsetTimeDate(latitude, longitude)
            // Formatteur d'heure au format HH:mm
            val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())


            // Conversion et affichage
            checkNotNull(sunriseTime)
            tvSunriseTime.text = sdf.format(sunriseTime)
            checkNotNull(sunsetTime)
            tvSunsetTime.text = sdf.format(sunsetTime)
        }
        else{
            tvSunriseTime.text = "--:--"
            tvSunsetTime.text = "--:--"
        }


    }

    override fun onPause() {
        super.onPause()
        MyApp.getContext().unregisterReceiver(receiver)
    }

    private fun loadActivity(){
        setContentView(R.layout.activity_main)
        tvCurrentBrightness = findViewById(R.id.tvCurrentBrightness)
        sliderCurrentBrightness = findViewById(R.id.sliderCurrentBrightness)
        tvSunriseTime = findViewById(R.id.tvSunriseTime)
        tvSunsetTime = findViewById(R.id.tvSunsetTime)
        autoModeSwitch = findViewById(R.id.autoModeSwitch)
        switchEnableService = findViewById(R.id.switchEnableService)

        tvCurrentBrightness.text = BrightnessService.brightnessValue.toString()
        sliderCurrentBrightness.progress = BrightnessService.brightnessValue

        sliderCurrentBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            var progressChangedValue: Int = 0

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                progressChangedValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                BrightnessService.SetBrightness(this@MainActivity, progressChangedValue)
            }
        })

        switchEnableService.isChecked = BrightnessService.isBrightnessServiceEnabled
        switchEnableService.setOnClickListener {
            BrightnessService.isBrightnessServiceEnabled = switchEnableService.isChecked
            PreferenceHelper.sInstance.saveBoolean("isBrightnessServiceEnabled", BrightnessService.isBrightnessServiceEnabled )
            if (BrightnessService.isBrightnessServiceEnabled)
                BrightnessService.StartService(MyApp.getContext())
            else
                BrightnessService.StopService(MyApp.getContext())
        }

        autoModeSwitch.isChecked = BrightnessService.isAutomaticBrightness
        autoModeSwitch.setOnClickListener {
            BrightnessService.isAutomaticBrightness = autoModeSwitch.isChecked
            PreferenceHelper.sInstance.saveBoolean("isAutomaticBrightness", BrightnessService.isAutomaticBrightness )
        }
    }

    private fun checkPermissions(): Boolean {
        if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.INTERNET), 0)
            if (checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED)
                return false
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
                return false
        }


        return true
    }

//    fun LinearLayout.setEnabledWithChildren() {
//        var enabled = ServiceHelper.isServiceRunning(MyApp.getContext(),BrightnessService::class.java)
//        this.isEnabled = enabled
//        for (i in 0 until childCount) {
//            val child = getChildAt(i)
//            child.isEnabled = enabled
//        }
//    }

}

