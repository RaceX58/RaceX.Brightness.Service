package com.example.racexbrightnessservice.helpers;

import static android.content.Context.LOCATION_SERVICE;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationManager;

import com.example.racexbrightnessservice.MyApp;

public class GPSHelper {
    static LocationManager mLocationManager;
    /**
     * @return the last know best location
     */
    public static Location getLastBestLocation() {
        LocationManager mLocationManager = (LocationManager) MyApp.getContext()
                .getSystemService(LOCATION_SERVICE);


        @SuppressLint("MissingPermission") Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        @SuppressLint("MissingPermission") Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        long GPSLocationTime = 0;
        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if ( 0 < GPSLocationTime - NetLocationTime ) {
            return locationGPS;
        }
        else {
            return locationNet;
        }
    }

    public static Double getLastKnowLocationLatitude(){
        Location mlocation = GPSHelper.getLastBestLocation();
        if (mlocation == null)
            return Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLatitude","-9999"));
        else
        {
            var latitude = mlocation.getLatitude();
            PreferenceHelper.sInstance.saveString("lastKnowLocationLatitude", String.valueOf(latitude));
            return latitude;
        }
    }
    public static Double getLastKnowLocationLongitude(){
        Location mlocation = GPSHelper.getLastBestLocation();
        if (mlocation == null)
            return Double.parseDouble(PreferenceHelper.sInstance.getString("lastKnowLocationLongitude","-9999"));
        else
        {
            var longitude = mlocation.getLongitude();
            PreferenceHelper.sInstance.saveString("lastKnowLocationLongitude", String.valueOf(longitude));
            return longitude;
        }
    }
}
