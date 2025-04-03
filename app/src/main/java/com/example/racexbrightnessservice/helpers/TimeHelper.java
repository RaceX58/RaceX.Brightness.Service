package com.example.racexbrightnessservice.helpers;

import android.content.Intent;

import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.service.BrightnessService;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeHelper {
    public static final SimpleDateFormat DEFAULT_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String currentTime() {
        Date date = new Date();
        return DEFAULT_FORMAT.format(date);
    }

    public static String GetSunriseTimeString(double latitude, double longitude){
        Location location = new Location(String.valueOf(latitude), String.valueOf(longitude));
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getDefault());
        return calculator.getOfficialSunriseForDate(Calendar.getInstance());
    }
    public static Date GetSunriseTimeDate(double latitude, double longitude){
        try{
            String sunriseTimeString = GetSunriseTimeString(latitude,longitude);
            if (sunriseTimeString == null)
                return null;
            android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("HH:mm");
            return df.parse(sunriseTimeString);
        }
        catch (Exception e)
        {
            return  null;
        }
    }

    public static String GetSunsetTimeString(double latitude, double longitude){

        Location location = new Location(String.valueOf(latitude), String.valueOf(longitude));
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, TimeZone.getDefault());
        return calculator.getOfficialSunsetForDate(Calendar.getInstance());
    }

    public static Date GetSunsetTimeDate(double latitude, double longitude){
        try{
            String sunsetTimeString = GetSunsetTimeString(latitude,longitude);
            if (sunsetTimeString == null)
                return null;
            android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("HH:mm");
            return df.parse(sunsetTimeString);
        }
        catch (Exception e)
        {
            return  null;
        }
    }
    public static Date GetCurrentTimeDate()
    {
        try{
            android.icu.text.SimpleDateFormat df = new android.icu.text.SimpleDateFormat("HH:mm");
            return df.parse(df.format(new Date()));
        }
        catch (Exception e)
        {
            return  null;
        }
    }


    public static boolean IsNight(){
        double latitude = GPSHelper.getLastKnowLocationLatitude();
        double longitude = GPSHelper.getLastKnowLocationLongitude();
        Date currentTime = TimeHelper.GetCurrentTimeDate();
        if (latitude != -9999 && longitude != -9999)
        {
            Date sunriseTime = TimeHelper.GetSunriseTimeDate(latitude,longitude);
            Date sunsetTime = TimeHelper.GetSunsetTimeDate(latitude,longitude);

            assert currentTime != null;
            return !currentTime.equals(sunriseTime) && (!currentTime.after(sunriseTime) || !currentTime.before(sunsetTime));
        }
        else
            return false;
    }
}
