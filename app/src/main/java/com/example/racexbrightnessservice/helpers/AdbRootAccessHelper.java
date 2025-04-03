package com.example.racexbrightnessservice.helpers;
import android.content.Context;
import android.widget.Toast;
import com.example.racexbrightnessservice.MyApp;
import com.example.racexbrightnessservice.mcu.CustomMcuCommunicator;

import java.io.DataOutputStream;
import java.io.IOException;

import projekt.auto.mcu.adb.AdbManager;

public class AdbRootAccessHelper {

    public static void grantMcuSourcePermissions(Context context) {
        try {
            // 1. Activer ADB en mode TCP sur le port 5555
//            if (!setAdbTcpPortAndRestart())
//            {
//                Toast.makeText(MyApp.getContext(), "Error : setAdbTcpPortAndRestart", Toast.LENGTH_SHORT).show();
////                return;
//            }

            if (!AdbManager.executeCommands(context, new String[] {
                    String.format("/system/xbin/ksu -c 'chmod 666 %s'", CustomMcuCommunicator.mcuSource)
            })){
                Toast.makeText(MyApp.getContext(), String.format("Error : /system/xbin/ksu -c 'chmod 666 %s", CustomMcuCommunicator.mcuSource), Toast.LENGTH_SHORT).show();
                return;
            }

            System.out.println("Permissions modifiées avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean disableHotspot(Context context) {
        try {
            return AdbManager.executeCommands(context, new String[] {
                    "/system/xbin/ksu -c 'svc wifi tether disable'"
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
