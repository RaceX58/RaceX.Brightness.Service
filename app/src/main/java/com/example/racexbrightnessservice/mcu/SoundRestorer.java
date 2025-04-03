package com.example.racexbrightnessservice.mcu;

import projekt.auto.mcu.ksw.serial.McuCommunicator;
import projekt.auto.mcu.ksw.serial.collection.McuCommands;
import projekt.auto.mcu.ksw.serial.enums.SOUND_SRC_TYPE;

public class SoundRestorer {
    public static void restoreSound(McuCommunicator mcuCommunicator) throws Exception {
        mcuCommunicator.sendCommand(new McuCommands.SetMusicSource(SOUND_SRC_TYPE.SRC_ATSL_AIRCONSOLE));
    }
}
