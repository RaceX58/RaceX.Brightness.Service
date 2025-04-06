package com.example.racexbrightnessservice.utils;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
public class SysProviderOpt {
    public static final String AUDI_GT6_LEFT_CAR_ICON = "AUDI_GT6_LEFT_CAR_ICON";
    public static final String AUDI_GT6_RIGHT_WIDGET = "AUDI_GT6_RIGHT_WIDGET";
    public static final String CAN_DST_SET_KEY = "Sys_dst_Set";
    private static final String CONTENT_NAME = "content://com.szchoiceway.eventcenter.SysVarProvider/SysVar";
    public static final String CUSTOM_ACTIVITYNAME = "Custom_ActivityName";
    public static final String CUSTOM_APPNAME = "Custom_AppName";
    public static final String CUSTOM_PACKAGENAME = "Custom_PackageName";
    public static final String IMITAGE_ORIGINAL_CAL_STYLE_CLIENT = "IMITAGE_ORIGINAL_CAL_STYLE_CLIENT";
    public static final String KESAIWEI_AMBIENT_LIGHT_B_VALUE = "KESAIWEI_AMBIENT_LIGHT_B_VALUE";
    public static final String KESAIWEI_AMBIENT_LIGHT_G_VALUE = "KESAIWEI_AMBIENT_LIGHT_G_VALUE";
    public static final String KESAIWEI_AMBIENT_LIGHT_R_VALUE = "KESAIWEI_AMBIENT_LIGHT_R_VALUE";
    public static final String KESAIWEI_CUR_SELECT_POSITION = "KESAIWEI_CUR_SELECT_POSITION";
    public static final String KESAIWEI_EQ_MODE_SELECT = "COM.KESAIWEI_EQ_MODE_SELECT";
    public static final String KESAIWEI_EQ_USER_HIGHT = "COM.KESAIWEI_EQ_USER_HIGHT";
    public static final String KESAIWEI_EQ_USER_LOW = "COM.KESAIWEI_EQ_USER_LOW";
    public static final String KESAIWEI_EQ_USER_MID = "COM.KESAIWEI_EQ_USER_MID";
    public static final String KESAIWEI_FM_CUR_FREQ = "KESAIWEI_FM_CUR_FREQ";
    public static final String KESAIWEI_FM_FREQ_0 = "KESAIWEI_FM_FREQ_0";
    public static final String KESAIWEI_FM_FREQ_1 = "KESAIWEI_FM_FREQ_1";
    public static final String KESAIWEI_FM_FREQ_2 = "KESAIWEI_FM_FREQ_2";
    public static final String KESAIWEI_FM_FREQ_3 = "KESAIWEI_FM_FREQ_3";
    public static final String KESAIWEI_FM_FREQ_4 = "KESAIWEI_FM_FREQ_4";
    public static final String KESAIWEI_FM_FREQ_5 = "KESAIWEI_FM_FREQ_5";
    public static final String KESAIWEI_FM_ON = "KESAIWEI_FM_ON";
    public static final String KESAIWEI_HORN_SELECTION = "KESAIWEI_HORN_SELECTION";
    public static final String KESAIWEI_RECORD_AMPLIFIER = "KESAIWEI_RECORD_AMPLIFIER";
    public static final String KESAIWEI_RECORD_AUX_SWITCHING = "KESAIWEI_RECORD_AUX_SWITCHING";
    public static final String KESAIWEI_RECORD_BELT = "KESAIWEI_RECORD_BELT";
    public static final String KESAIWEI_RECORD_BT_INDEX = "KESAIWEI_RECORD_BT_INDEX";
    public static final String KESAIWEI_RECORD_BT_OFF = "KESAIWEI_RECORD_BT_OFF";
    public static final String KESAIWEI_RECORD_CAR_TYPE = "KESAIWEI_RECORD_CAR_TYPE";
    public static final String KESAIWEI_RECORD_DVR = "KESAIWEI_RECORD_DVR";
    public static final String KESAIWEI_RECORD_PARK = "KESAIWEI_RECORD_PARK";
    public static final String KESAIWEI_SYS_BACKCAR_MIRROR = "KESAIWEI_SYS_BACKCAR_MIRROR";
    public static final String KESAIWEI_SYS_CAMERA_SELECTION = "KESAIWEI_SYS_CAMERA_SELECTION";
    public static final String KESAIWEI_SYS_DVD_SELECTION = "KESAIWEI_SYS_DVD_SELECTION";
    public static final String KESAIWEI_SYS_FRONT_MIRROR = "KESAIWEI_SYS_FRONT_MIRROR";
    public static final String KESAIWEI_SYS_LANGUAGE = "KESAIWEI_SYS_LANGUAGE";
    public static final String KESAIWEI_SYS_MODE_SELECTION = "KESAIWEI_SYS_MODE_SELECTION";
    public static final String KESAIWEI_SYS_RADAR = "KESAIWEI_SYS_RADAR";
    public static final String KESAIWEI_SYS_REVERSING_TRACK = "KESAIWEI_SYS_REVERSING_TRACK";
    public static final String KESAIWEI_SYS_SD_HOST = "KESAIWEI_SYS_SD_HOST";
    public static final String KESAIWEI_SYS_USER_TIME_TYPE = "KESAIWEI_SYS_USER_TIME_TYPE";
    public static final String KESAIWEI_SYS_VIDEO_DRIVING_BAN = "KESAIWEI_SYS_VIDEO_DRIVING_BAN";
    public static final String KEY_BRIGHTNESS_SETTINGS = "COM.SZCHOICEWAY_BRIGHTNESS_SETTINGS";
    public static final String KEY_HIGHZ_SETTINGS = "COM.SZCHOICEWAY_HIGHZ_SETTINGS";
    public static final String KEY_KSW_VOL_VAL_00 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_00";
    public static final String KEY_KSW_VOL_VAL_01 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_01";
    public static final String KEY_KSW_VOL_VAL_02 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_02";
    public static final String KEY_KSW_VOL_VAL_03 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_03";
    public static final String KEY_KSW_VOL_VAL_04 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_04";
    public static final String KEY_KSW_VOL_VAL_05 = "COM.SZCHOICEWAY_KEY_KSW_VOL_VAL_05";
    public static final String KEY_RADIO_ZONE_SETTINGS = "COM.SZCHOICEWAY_RADIO_ZONE_SETTINGS";
    public static final String KSE_TXZ_WARING_VALUE_OIL = "KSE_TXZ_WARING_VALUE_OIL";
    public static final String KSE_TXZ_WARING_VALUE_SPEED = "KSE_TXZ_WARING_VALUE_SPEED";
    public static final String KSW_360_CAMERA_TYPE_INDEX = "KSW_360_CAMERA_TYPE_INDEX";
    public static final String KSW_ACTION_IMPORT_CONFIG = "KSW_ACTION_IMPORT_CONFIG";
    public static final String KSW_AGREEMENT_SELECT_INDEX = "KSW_AGREEMENT_SELECT_INDEX";
    public static final String KSW_AHD_CAMERA_TYPE = "KSW_AHD_CAMERA_TYPE";
    public static final String KSW_ANDROID_CAMERA_TYPE = "KSW_ANDROID_CAMERA_TYPE";
    public static final String KSW_AUX_ACTIVATION_FUNCTION_INDEX = "KSW_AUX_ACTIVATION_FUNCTION_INDEX";
    public static final String KSW_AUX_ITEM_POSITION = "KSW_AUX_ITEM_POSITION";
    public static final String KSW_AUX_ITEM_POSITION2 = "KSW_AUX_ITEM_POSITION2";
    public static final String KSW_BACKLIGHT_CONTROL_INDEX = "KSW_BACKLIGHT_CONTROL_INDEX";
    public static final String KSW_BOOT_360_CAMERA_INDEX = "KSW_BOOT_360_CAMERA_INDEX";
    public static final String KSW_BOOT_MODE_MEMORY_INDEX = "KSW_BOOT_MODE_MEMORY_INDEX";
    public static final String KSW_CCC_IDRIVE_TYPE = "KSW_CCC_IDRIVE_TYPE";
    public static final String KSW_COLLECT_CAN_DATA_SWITCH_INDEX = "KSW_COLLECT_CAN_DATA_SWITCH_INDEX";
    public static final String KSW_DASHBOARD_SELECT = "KSW_DASHBOARD_SELECT";
    public static final String KSW_DATA_BENZ_ORIGINAL_CAR_ICON_IS_SUV = "KSW_DATA_BENZ_ORIGINAL_CAR_ICON_IS_SUV";
    public static final String KSW_DATA_BOOT_START_NAVI = "KSW_DATA_BOOT_START_NAVI";
    public static final String KSW_DATA_CAR_MANUFACTURER_INDEX = "KSW_DATA_CAR_MANUFACTURER_INDEX";
    public static final String KSW_DATA_DECODER_V3 = "KSW_DATA_DECODER_V3";
    public static final String KSW_DATA_DECODER_V3_SCREEN_SIGN = "KSW_DATA_DECODER_V3_SCREEN_SIGN";
    public static final String KSW_DATA_HAVE_CAR_KTV = "KSW_DATA_HAVE_CAR_KTV";
    public static final String KSW_DATA_MIPI_SCREEN_NAME = "KSW_DATA_MIPI_SCREEN_NAME";
    public static final String KSW_DATA_MIPI_SCREEN_ORIGINAL_FULL_SCREEN = "KSW_DATA_MIPI_SCREEN_ORIGINAL_FULL_SCREEN";
    public static final String KSW_DATA_MIPI_SCREEN_REVERSE_BRIGHTNESS = "KSW_DATA_MIPI_SCREEN_REVERSE_BRIGHTNESS";
    public static final String KSW_DATA_MIPI_SCREEN_REVERSE_CONTRAST = "KSW_DATA_MIPI_SCREEN_REVERSE_CONTRAST";
    public static final String KSW_DATA_MIPI_SCREEN_REVERSE_FRONT_CAMERA_TIME = "KSW_DATA_MIPI_SCREEN_REVERSE_FRONT_CAMERA_TIME";
    public static final String KSW_DATA_MIPI_SCREEN_REVERSE_SHOW_TRACK_TIP = "KSW_DATA_MIPI_SCREEN_REVERSE_SHOW_TRACK_TIP";
    public static final String KSW_DATA_MIPI_SCREEN_REVERSE_TRACK_OFFSET = "KSW_DATA_MIPI_SCREEN_REVERSE_TRACK_OFFSET";
    public static final String KSW_DATA_PRODUCT_INDEX = "KSW_DATA_PRODUCT_INDEX";
    public static final String KSW_DATA_SOUND_PROCESSOR_CHIP = "KSW_DATA_SOUND_PROCESSOR_CHIP";
    public static final String KSW_DISTACNE_UNIT = "KSW_DISTACNE_UNIT";
    public static final String KSW_DVR_APK_PACKAGENAME = "KSW_DVR_APK_PACKAGENAME";
    public static final String KSW_EXTERNAL_INTERNAL_MIC_SELECTION = "KSW_EXTERNAL_INTERNAL_MIC_SELECTION";
    public static final String KSW_FACTORY_SET_CLIENT = "KSW_FACTORY_SET_CLIENT";
    public static final String KSW_FACTORY_SET_PASSWORD = "KSW_FACTORY_SET_PASSWORD";
    public static final String KSW_FACTORY_VER = "KSW_FACTORY_VER";
    public static final String KSW_HANDSET_AUTOMATIC_SET_INDEX = "KSW_HANDSET_AUTOMATIC_SET_INDEX";
    public static final String KSW_HAVE_AUX = "KSW_HAVE_AUX";
    public static final String KSW_HAVE_CARAUTO = "KSW_HAVE_CARAUTO";
    public static final String KSW_HAVE_DVD = "KSW_HAVE_DVD";
    public static final String KSW_HAVE_ECAR = "KSW_HAVE_ECAR";
    public static final String KSW_HAVE_EQ = "KSW_HAVE_EQ";
    public static final String KSW_HAVE_ESEXPLORER = "KSW_HAVE_ESEXPLORER";
    public static final String KSW_HAVE_FRONT_CAMERA = "KSW_HAVE_FRONT_CAMERA";
    public static final String KSW_HAVE_HICAR = "KSW_HAVE_HICAR";
    public static final String KSW_HAVE_MANUAL = "KSW_HAVE_MANUAL";
    public static final String KSW_HAVE_TV = "KSW_HAVE_TV";
    public static final String KSW_HAVE_TXZ = "KSW_HAVE_TXZ";
    public static final String KSW_HAVE_WEATHER = "KSW_HAVE_WEATHER";
    public static final String KSW_K01_MULTI_INDEX = "KSW_K01_MULTI_INDEX";
    public static final String KSW_LANDROVER_HOST_INDEX = "KSW_LANDROVER_HOST_INDEX";
    public static final String KSW_LANDROVER_KEY_PANEL_LEFT_INDEX = "KSW_LANDROVER_KEY_PANEL_LEFT_INDEX";
    public static final String KSW_LANDROVER_KEY_PANEL_RIGHT_INDEX = "KSW_LANDROVER_KEY_PANEL_RIGHT_INDEX";
    public static final String KSW_LANDRVOER_WHEEL_CONTROL_TYPE = "KSW_LANDRVOER_WHEEL_CONTROL_TYPE";
    public static final String KSW_LCD_CLK_POLARITY = "KSW_LCD_CLK_POLARITY";
    public static final String KSW_LEXUS_AIR_CONTROL = "KSW_LEXUS_AIR_CONTROL";
    public static final String KSW_LEXUS_ORIGIN_FM = "KSW_LEXUS_ORIGIN_FM";
    public static final String KSW_MAP_KEY_FUNCTION_INDEX = "KSW_MAP_KEY_FUNCTION_INDEX";
    public static final String KSW_MIPI_ORIGINAL_RESOLUTION = "KSW_MIPI_ORIGINAL_RESOLUTION";
    public static final String KSW_MODE_KEY_FUNCTION_INDEX = "KSW_MODE_KEY_FUNCTION_INDEX";
    public static final String KSW_OIL_UNIT = "KSW_OIL_UNIT";
    public static final String KSW_ORIGINAL_CAR_VIDEO_DISPLAY = "KSW_ORIGINAL_CAR_VIDEO_DISPLAY";
    public static final String KSW_ORIGINAL_LVDS_CHIP_TYPE = "KSW_ORIGINAL_LVDS_CHIP_TYPE";
    public static final String KSW_PHONE_KEY_FUNCTION_INDEX = "KSW_PHONE_KEY_FUNCTION_INDEX";
    public static final String KSW_PRODUCT_TYPE = "KSW_PRODUCT_TYPE";
    public static final String KSW_REPLACE_VERSION_NUMBER_WITH_8953 = "KSW_REPLACE_VERSION_NUMBER_WITH_8953";
    public static final String KSW_REVEERSING_MUTE_SELECT_INDEX = "KSW_REVEERSING_MUTE_SELECT_INDEX";
    public static final String KSW_REVERSE_EXIT_TIME_CUSTOMIZE = "KSW_REVERSE_EXIT_TIME_CUSTOMIZE";
    public static final String KSW_REVERSE_EXIT_TIME_INDEX = "KSW_REVERSE_EXIT_TIME_INDEX";
    public static final String KSW_SCREEN_CAST_MS9120 = "KSW_SCREEN_CAST_MS9120";
    public static final String KSW_SELECTION_SMALL_CLOCK_INDEX = "KSW_SELECTION_SMALL_CLOCK_INDEX";
    public static final String KSW_SEND_TOUCH_DATA_CONTINUED = "KSW_SEND_TOUCH_DATA_CONTINUED";
    public static final String KSW_SHOW_AIR = "KSW_SHOW_AIR";
    public static final String KSW_SLEEP_TIME = "KSW_SLEEP_TIME";
    public static final String KSW_SPEED_TYPE_SELECTION = "KSW_SPEED_TYPE_SELECTION";
    public static final String KSW_SPLITTING_MACHINE_LVDS_MODE = "KSW_SPLITTING_MACHINE_LVDS_MODE";
    public static final String KSW_TEMP_UNIT = "KSW_TEMP_UNIT";
    public static final String KSW_TURN_SIGNAL_CONTROL = "KSW_TURN_SIGNAL_CONTROL";
    public static final String KSW_USB_HOST_MODE = "KSW_USB_HOST_MODE";
    public static final String KSW_VOICE_KEY_FUNCTION_INDEX = "KSW_VOICE_KEY_FUNCTION_INDEX";
    public static final String KSW_WHELLTRACK_INDEX = "KSW_WHELLTRACK_INDEX";
    public static final String KSW_WITHOUT_GEAR_INFO = "KSW_WITHOUT_GEAR_INFO";
    public static final String MUSIC_ACTIVITYNAME = "Music_ActivityName";
    public static final String MUSIC_PACKAGENAME = "Music_PackageName";
    public static final String NAVI_ACTIVITYNAME = "Navi_ActivityName";
    public static final String NAVI_PACKAGENAME = "Navi_PackageName";
    public static final String OS_RESOLUTION = "OS_RESOLUTION";
    public static final String PARAM_RESOLUTION = "PARAM_RESOLUTION";
    public static final String RESOLUTION = "RESOLUTION";
    public static final String SET_360CAMERA_RESOLUTION_KEY = "Sys_360Camera_Resolution_Key";
    public static final String SET_DAY_LIGHT_KEY = "Set_Day_Light";
    public static final String SET_NIGHT_LIGHT_KEY = "Set_Night_Light";
    public static final String SET_TRACK_LINE_Angle_Set = "SET_track_line_angle_Set";
    public static final String SET_TRACK_LINE_CalculateOffset_Set = "SET_track_line_CalculateOffset_Set";
    public static final String SET_TRACK_LINE_CanvasRotation_Set = "SET_track_line_CanvasRotation_Set";
    public static final String SET_TRACK_LINE_DrawOffset_Set = "SET_track_line_DrawOffset_Set";
    public static final String SET_TRACK_LINE_RedLineAdjustment_Set = "SET_track_line_RedLineAdjustment_Set";
    public static final String SET_TRACK_LINE_SWITCH = "SET_track_line_switch";
    public static final String SET_TRACK_LINE_TranslationOffset_Set = "SET_track_line_TranslationOffset_Set";
    public static final String SET_USER_UI_TYPE = "Set_User_UI_Type";
    public static final String STARTUP_ACTIVITYNAME = "Startup_ActivityName";
    public static final String STARTUP_ACTIVITYNAME2 = "Startup_ActivityName2";
    public static final String STARTUP_ACTIVITYNAME3 = "Startup_ActivityName3";
    public static final String STARTUP_ACTIVITYNAME4 = "Startup_ActivityName4";
    public static final String STARTUP_ACTIVITYNAME5 = "Startup_ActivityName5";
    public static final String STARTUP_PACKAGENAME = "Startup_PackageName";
    public static final String STARTUP_PACKAGENAME2 = "Startup_PackageName2";
    public static final String STARTUP_PACKAGENAME3 = "Startup_PackageName3";
    public static final String STARTUP_PACKAGENAME4 = "Startup_PackageName4";
    public static final String STARTUP_PACKAGENAME5 = "Startup_PackageName5";
    public static final String SYS_4G_MODE = "Sys_4gMode";
    public static final String SYS_APP_VERSION = "Sys_AppVersion";
    public static final String SYS_AUTO_ANTENNA_SET = "Sys_Auto_Antenna_Set";
    public static final String SYS_AUTO_START_CARAUTO = "Sys_auto_start_carauto";
    public static final String SYS_BENZ_CONTROL_INDEX = "SYS_BENZ_CONTROL_INDEX";
    public static final String SYS_BLUETOOTH_INTERNET = "Sys_bluetooth_internet";
    public static final String SYS_BOX_CLIENT = "Sys_box_client";
    public static final String SYS_BOX_CLIENT_INDEX = "Sys_box_client_index";
    public static final String SYS_BOX_NAME = "Sys_box_name";
    public static final String SYS_BOX_START_MODE = "Sys_box_start_mode";
    public static final String SYS_BOX_TYPE = "Sys_box_type";
    public static final String SYS_BOX_UI = "Sys_box_ui";
    public static final String SYS_BRIGHT_SET = "Sys_bright_set";
    public static final String SYS_CONTRAST_SET = "Sys_Light_contrast_set";
    public static final String SYS_CPU_MODE = "Sys_CpuMode";
    public static final String SYS_CUSTOMER_CLIENT = "Sys_customer_client";
    public static final String SYS_CUSTOMER_CLIENT_INDEX = "Sys_customer_client_index";
    public static final String SYS_CUSTOMER_STATUSBAR_KEY = "Sys_customer_statusbar";
    public static final String SYS_CUSTOMER_SYSTEMUI_PANEL_KEY = "Sys_customer_systemui_panel_bar";
    public static final String SYS_CUSTOMER_UI = "Sys_customer_ui";
    public static final String SYS_DAY_AND_NIGHT_MODE = "Sys_Day_and_night_mode";
    public static final String SYS_DEFAULT_DRIVING_WATCH_WHITE_LIST = "Sys_Default_Driving_watch_white_list";
    public static final String SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY = "SYS_DOOR_DISPLAYSET_VALUE_INDEX_KEY";
    public static final String SYS_DOOR_SET_VALUE_INDEX_KEY = "SYS_DOOR_SET_VALUE_INDEX_KEY";
    public static final String SYS_DRIVING_MODE = "Sys_driving_mode";
    public static final String SYS_DRIVING_WATCH_WHITE_LIST = "Sys_Driving_watch_white_list";
    public static final String SYS_FACTORY_SET_SHOW_KSW_LOGO = "SYS_FACTORY_SET_SHOW_KSW_LOGO";
    public static final String SYS_GLOBAL_NTP_SERVER = "SYS_GLOBAL_NTP_SERVER";
    public static final String SYS_GOOGLE_PLAY = "SYS_GOOGLE_PLAY";
    public static final String SYS_GOOGLE_VOICE_SWITCH = "Sys_GoogleVoiceSwitch";
    public static final String SYS_GPS_SWITCH = "Sys_GPSSwitch";
    public static final String SYS_HDMI_FORCE_MODE = "Sys_hdmi_force_mode";
    public static final String SYS_HDMI_SOUND_OUTPUT = "Sys_HdmiSoundOutput";
    public static final String SYS_HUE_SET = "Sys_hue_set";
    public static final String SYS_IGO_MIXING_VOLUME = "Sys_Igo_Mixing_Volume";
    public static final String SYS_INIT_FLAG = "Sys_init_flag";
    public static final String SYS_IS_DOMESTIC = "Sys_IsDomestic";
    public static final String SYS_MCU_KEYSTROKE_LEARN_SWITCH_KEY = "sys_mcu_keystroke_learn_switch_key";
    public static final String SYS_MCU_PANEL_INDEX_CUSTOM_KEY = "mcu_panel_key_learn_custom";
    public static final String SYS_MCU_SET_KEY = "Sys_McuSet";
    public static final String SYS_MCU_VERSION = "Sys_McuVersion";
    public static final String SYS_MIC_GAIN_PARAM = "SYS_MIC_GAIN_PARAM";
    public static final String SYS_MIC_GAIN_PARAM_GT6 = "SYS_MIC_GAIN_PARAM_GT6";
    public static final String SYS_MIC_GAIN_PARAM_GT7 = "SYS_MIC_GAIN_PARAM_GT7";
    public static final String SYS_MIC_MODE = "Sys_mic_mode";
    public static final String SYS_MSATURATION_SET = "Sys_msaturation_set";
    public static final String SYS_MULTIPLE_CAMERAS_KEY = "Sys_Multpile_Cameras_Key";
    public static final String SYS_POWER_OFF_DELAY = "Sys_Power_Off_Delay";
    public static final String SYS_RCRL_VALUE = "Sys_rcrl_value";
    public static final String SYS_SAMPLING_RATE = "Sys_sampling_rate";
    public static final String SYS_SCREENORIGIN_KEY = "Sys_ScreenOrigin";
    public static final String SYS_SETLOGO_INDEX = "SYS_SETLOGO_INDEX";
    public static final String SYS_SLEEP_SWITCH = "Sys_Sleep_Switch";
    public static final String SYS_SOUND_MODE_SETTINGS = "Sys_Sound_Mode_Settings";
    public static final String SYS_SPEED_SWITCH = "Sys_SpeedSwitch";
    public static final String SYS_START_DELAY = "Sys_start_delay";
    public static final String SYS_SUPPORT_ORIGINAL_GPS = "support_original_gps";
    public static final String SYS_SYS_VER = "Sys_SysVer";
    public static final String SYS_TOUCH_ASSISTANT = "Sys_Touch_assistant";
    public static final String SYS_TOUCH_ASSISTANT_MODE = "Sys_Touch_assistant_mode";
    public static final String SYS_TOUCH_MODE = "Sys_Touch_mode";
    public static final String SYS_TOUCH_ORGIN_KEY = "Sys_TouchOrgin";
    public static final String SYS_TV_MODE = "box_mode";
    public static final String SYS_USB_HUB = "Sys_usb_hub";
    public static final String SYS_USB_MODE = "Sys_UsbMode";
    public static final String SYS_WARN_AGREE = "Sys_warn_agree";
    public static final String SYS_WHEEL_INDEX_CUSTOM_KEY = "wheel_key_learn_custom";
    public static final String SYS_ZF_UI = "Sys_zf_ui";
    public static final String SYS_ZXW_FPS = "Sys_zxw_fps";
    private static final String TAG = "SysProviderOpt";
    public static final String TV_RESOLUTION = "TV_RESOLUTION";
    public static final int UI_BOX = 102;
    public static final int UI_KSW = 41;
    public static final int UI_ZF = 103;
    public static final String VIDEO_ACTIVITYNAME = "Video_ActivityName";
    public static final String VIDEO_PACKAGENAME = "Video_PackageName";
    public static final String VOICE_ACTIVITYNAME = "Voice_ActivityName";
    public static final String VOICE_PACKAGENAME = "Voice_PackageName";
    private static SysProviderOpt mSysProviderOpt;
    private final ContentResolver mCntResolver;
    private final Uri mUri = Uri.parse(CONTENT_NAME);

    private SysProviderOpt(Context context) {
        this.mCntResolver = context.getContentResolver();
    }

    public static SysProviderOpt getInstance(Context context) {
        if (mSysProviderOpt == null) {
            synchronized (SysProviderOpt.class) {
                if (mSysProviderOpt == null) {
                    mSysProviderOpt = new SysProviderOpt(context);
                }
            }
        }
        return mSysProviderOpt;
    }

    public Uri insertRecord(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("keyname", str);
        contentValues.put("keyvalue", str2);
        try {
            return this.mCntResolver.insert(this.mUri, contentValues);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0053 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0054 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getRecordValue(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            java.lang.String r4 = "keyname=?"
            r1 = 1
            java.lang.String[] r5 = new java.lang.String[r1]
            r1 = 0
            r5[r1] = r9
            r9 = 0
            android.content.ContentResolver r1 = r8.mCntResolver     // Catch:{ Exception -> 0x003a }
            android.net.Uri r2 = r8.mUri     // Catch:{ Exception -> 0x003a }
            r3 = 0
            r6 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x003a }
            if (r9 == 0) goto L_0x002e
            int r1 = r9.getCount()     // Catch:{ Exception -> 0x003a }
            if (r1 <= 0) goto L_0x002e
            boolean r1 = r9.moveToNext()     // Catch:{ Exception -> 0x003a }
            if (r1 == 0) goto L_0x002e
            java.lang.String r1 = "keyvalue"
            int r1 = r9.getColumnIndex(r1)     // Catch:{ Exception -> 0x003a }
            java.lang.String r1 = r9.getString(r1)     // Catch:{ Exception -> 0x003a }
            goto L_0x002f
        L_0x002e:
            r1 = r0
        L_0x002f:
            if (r9 == 0) goto L_0x004b
            r9.close()     // Catch:{ Exception -> 0x0035 }
            goto L_0x004b
        L_0x0035:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x003c
        L_0x003a:
            r1 = move-exception
            r2 = r0
        L_0x003c:
            if (r9 == 0) goto L_0x0041
            r9.close()
        L_0x0041:
            java.lang.String r9 = r1.toString()
            java.lang.String r1 = "SysProviderOpt"
            android.util.Log.e(r1, r9)
            r1 = r2
        L_0x004b:
            if (r1 == 0) goto L_0x0054
            boolean r9 = r0.equals(r1)
            if (r9 != 0) goto L_0x0054
            return r1
        L_0x0054:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.utils.SysProviderOpt.getRecordValue(java.lang.String, java.lang.String):java.lang.String");
    }

    @SuppressLint("Range")
    public String getRecordValue(String str) {
        String str2 = "";
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                str2 = query.getString(query.getColumnIndex("keyvalue"));
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return str2;
    }

    public int getRecordInteger(String str, int i) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    i = Integer.valueOf(string).intValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return i;
    }

    public long getRecordLong(String str, long j) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    j = Long.valueOf(string).longValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return j;
    }

    public float getRecordFloat(String str, float f) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    f = Float.valueOf(string).floatValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return f;
    }

    public boolean getRecordBoolean(String str, boolean z) {
        boolean z2 = true;
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                @SuppressLint("Range") String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    if (Integer.valueOf(string).intValue() != 1) {
                        z2 = false;
                    }
                    z = z2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return z;
    }

    public byte getRecordByte(String str, byte b) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            Cursor query = this.mCntResolver.query(this.mUri, (String[]) null, "keyname=?", strArr, (String) null);
            if (query != null && query.getCount() > 0 && query.moveToNext()) {
                    @SuppressLint("Range") String string = query.getString(query.getColumnIndex("keyvalue"));
                if (string.length() > 0) {
                    b = Byte.valueOf(string).byteValue();
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
            Log.e(TAG, e.toString());
        }
        return b;
    }

    public double getRecordDouble(String str, double d) {
        String recordValue = getRecordValue(str, "");
        return recordValue.length() > 0 ? Double.valueOf(recordValue).doubleValue() : d;
    }

    public void updateRecord(String str, String str2) {
        updateRecord(str, str2, true);
    }

    public void updateRecord(String keyName, String keyValue, boolean insertIfNotRecord) {
        String selection = "keyname=?";
        String[] selectionArgs = {keyName};
        Cursor cr = null;
        try {
            Cursor cr2 = this.mCntResolver.query(this.mUri, new String[]{"keyvalue"}, selection, selectionArgs, null);
            if (cr2 != null && cr2.getCount() > 0) {
                ContentValues values = new ContentValues();
                values.put("keyvalue", keyValue);
                if (cr2 != null) {
                    cr2.close();
                    cr2 = null;
                }
                this.mCntResolver.update(this.mUri, values, selection, selectionArgs);
            } else if (insertIfNotRecord) {
                insertRecord(keyName, keyValue);
            }
            if (cr2 != null) {
                cr2.close();
            }
        } catch (Exception e) {
            if (cr != null) {
                cr.close();
            }
            Log.e(TAG, e.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
//    public void updateRecord(java.lang.String r11, java.lang.String r12, boolean r13) {
//        /*
//            r10 = this;
//            java.lang.String r6 = "keyname=?"
//            r0 = 1
//            java.lang.String[] r7 = new java.lang.String[r0]
//            r0 = 0
//            r7[r0] = r11
//            java.lang.String r8 = "keyvalue"
//            java.lang.String[] r2 = new java.lang.String[]{r8}
//            r9 = 0
//            android.content.ContentResolver r0 = r10.mCntResolver     // Catch:{ Exception -> 0x0048 }
//            android.net.Uri r1 = r10.mUri     // Catch:{ Exception -> 0x0048 }
//            r5 = 0
//            r3 = r6
//            r4 = r7
//            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0048 }
//            if (r0 == 0) goto L_0x003c
//            int r1 = r0.getCount()     // Catch:{ Exception -> 0x0039 }
//            if (r1 <= 0) goto L_0x003c
//            android.content.ContentValues r11 = new android.content.ContentValues     // Catch:{ Exception -> 0x0039 }
//            r11.<init>()     // Catch:{ Exception -> 0x0039 }
//            r11.put(r8, r12)     // Catch:{ Exception -> 0x0039 }
//            if (r0 == 0) goto L_0x0030
//            r0.close()     // Catch:{ Exception -> 0x0039 }
//            goto L_0x0031
//        L_0x0030:
//            r9 = r0
//        L_0x0031:
//            android.content.ContentResolver r12 = r10.mCntResolver     // Catch:{ Exception -> 0x0048 }
//            android.net.Uri r13 = r10.mUri     // Catch:{ Exception -> 0x0048 }
//            r12.update(r13, r11, r6, r7)     // Catch:{ Exception -> 0x0048 }
//            goto L_0x0042
//        L_0x0039:
//            r11 = move-exception
//            r9 = r0
//            goto L_0x0049
//        L_0x003c:
//            if (r13 == 0) goto L_0x0041
//            r10.insertRecord(r11, r12)     // Catch:{ Exception -> 0x0039 }
//        L_0x0041:
//            r9 = r0
//        L_0x0042:
//            if (r9 == 0) goto L_0x0057
//            r9.close()     // Catch:{ Exception -> 0x0048 }
//            goto L_0x0057
//        L_0x0048:
//            r11 = move-exception
//        L_0x0049:
//            if (r9 == 0) goto L_0x004e
//            r9.close()
//        L_0x004e:
//            java.lang.String r11 = r11.toString()
//            java.lang.String r12 = "SysProviderOpt"
//            android.util.Log.e(r12, r11)
//        L_0x0057:
//            return
//        */
//        throw new UnsupportedOperationException("Method not decompiled: com.szchoiceway.eventcenter.utils.SysProviderOpt.updateRecord(java.lang.String, java.lang.String, boolean):void");
//    }

    public void setRecordDefaultValue(String str, String str2) {
        if (!checkRecordExist(str)) {
            insertRecord(str, str2);
        }
    }

    public boolean checkRecordExist(String str) {
        boolean z = true;
        boolean z2 = false;
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = this.mCntResolver.query(this.mUri, new String[]{"keyvalue"}, "keyname=?", strArr, (String) null);
            if (cursor == null || cursor.getCount() <= 0) {
                z = false;
            }
            if (cursor == null) {
                return z;
            }
            try {
                cursor.close();
                return z;
            } catch (Exception e) {
                e = e;
                z2 = z;
            }
        } catch (Exception e2) {
//            e = e2;
//            if (cursor != null) {
//                cursor.close();
//            }
//            Log.e(TAG, e.toString());
            return z2;
        }
        return z;
    }


}