package android.media;

import android.content.Context;
import android.hardware.audio.common.V2_0.AudioDevice;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AudioManagerEx extends AudioManager {
    public static final String ARC_MUTE = "arc_mute";
    public static final String ARC_VOLUME = "arc_volume";
    public static final String ATV_MTS_MODE = "atv_mts_mode";
    public static final String ATV_MTS_MODE_LIST = "atv_mts_mode_list";
    public static final String ATV_PRESCALE = "atv_prescale";
    public static final String ATV_STANDARD_MODE = "atv_standard_mode";
    public static final String AUDIO_INPUT_ACTIVE = "audio_devices_in_active";
    public static final String AUDIO_INPUT_TYPE = "audio_devices_in";
    public static final String AUDIO_MODE = "audio_mode";
    public static final String AUDIO_MODE_GAIN_MAX = "audio_mode_gain_max";
    public static final String AUDIO_MODE_GAIN_MIN = "audio_mode_gain_min";
    public static final String AUDIO_NAME_A2DP = "AUDIO_A2DP";
    public static final String AUDIO_NAME_AC107 = "AUDIO_AC107";
    public static final String AUDIO_NAME_ARC = "AUDIO_ARC";
    public static final String AUDIO_NAME_CAPTURE = "AUDIO_CAPTURE";
    public static final String AUDIO_NAME_CODEC = "AUDIO_CODEC";
    public static final String AUDIO_NAME_HEADPHONE = "AUDIO_HEADPHONE";
    public static final String AUDIO_NAME_MEM = "AUDIO_MEM";
    public static final String AUDIO_NAME_OWA = "AUDIO_OWA";
    public static final String AUDIO_NAME_SPK = "AUDIO_SPEAKER";
    public static final String AUDIO_OUTPUT_ACTIVE = "audio_devices_out_active";
    public static final String AUDIO_OUTPUT_TYPE = "audio_devices_out";
    public static final String AVC_EFFECT = "avc_effect";
    public static final String AVC_EFFECT_DECAY = "avc_decay";
    public static final String AVC_EFFECT_GAIN = "avc_gain";
    public static final String AVC_EFFECT_LIMIT = "avc_limit";
    public static final String AVC_EFFECT_THRESHOLD = "avc_threshold";
    public static final String AVC_MODE = "avc_mode";
    public static final String AV_PRESCALE = "av_prescale";
    public static final String BALANCE_HEADPHONE = "balance_headphone";
    public static final String BALANCE_SPEAKER = "balance_speaker";
    public static final String BAND_ARRAY = "band_array";
    public static final String DRC_AVC = "1";
    public static final String DRC_AVCMULTI = "2";
    public static final String DRC_HEADPHONE_DECAY = "headphone_drc_decay";
    public static final String DRC_HEADPHONE_ENABLE = "headphone_drc_enable";
    public static final String DRC_HEADPHONE_THRESHOLD = "headphone_drc_threshold";
    public static final String DRC_MODE = "drc_mode";
    public static final String DRC_NIGHT = "3";
    public static final String DRC_OFF = "0";
    public static final String DRC_POWER = "4";
    public static final String DRC_SPEAKER_DECAY = "speaker_drc_decay";
    public static final String DRC_SPEAKER_ENABLE = "speaker_drc_enable";
    public static final String DRC_SPEAKER_THRESHOLD = "speaker_drc_threshold";
    public static final String DRC_VOLUME = "5";
    public static final String GAIN_HEADPHONE = "headphone_gain";
    public static final String GAIN_OWA = "owa_gain";
    public static final String GAIN_SPEAKER = "speaker_gain";
    public static final String GEQ_BAND = "geq_band";
    public static final String HDMI_PRESCALE = "hdmi_prescale";
    public static final String HEADPHONE_MUTE = "headphone_mute";
    public static final String HEADPHONE_VOLUME = "headphone_volume";
    public static final String INIT_AUDIO_MODE = "init_audio_mode";
    public static final String INIT_VOLUME_CURVE = "init_volume_curve";
    public static final String MODE_MOVIE = "2";
    public static final String MODE_MUSIC = "1";
    public static final String MODE_NEWS = "3";
    public static final String MODE_STANDARD = "0";
    public static final String MODE_USER = "4";
    public static final String MOVIE_MODE_GAIN = "movie_mode_gain";
    public static final String MUSIC_MODE_GAIN = "music_mode_gain";
    public static final String NEWS_MODE_GAIN = "news_mode_gain";
    private static int OWA_ARC0 = 0;
    public static final String OWA_DELAY = "owa_delay";
    private static int OWA_IN1 = 1;
    public static final String OWA_MUTE = "owa_mute";
    public static final String OWA_VOLUME = "owa_volume";
    public static final String PEQ_BAND_FREQ = "peq_band_freq";
    public static final String PEQ_BAND_GAIN = "peq_band_gain";
    public static final String PEQ_BAND_QUALITY = "peq_band_quality";
    public static final String PEQ_MODE = "peq_mode";
    public static final String PEQ_OFF = "0";
    public static final String PEQ_ON = "1";
    public static final String PEQ_OVERALL_GAIN = "peq_overall_gain";
    private static final String PROP_DELAY_KEY = "mediasw.sft.delay";
    private static final String PROP_RAWDATA_DEFAULT_VALUE = "PCM";
    private static final String PROP_RAWDATA_KEY = "mediasw.sft.rawdata";
    private static final String PROP_RAWDATA_MODE_ARC_RAW = "ARC_RAW";
    private static final String PROP_RAWDATA_MODE_OWA_RAW = "OWA_RAW";
    private static final String PROP_RAWDATA_MODE_PCM = "PCM";
    public static final String RESET_AUDIO_DELAY = "reset_audio_delay";
    public static final String RESET_AUDIO_MODE = "reset_audio_mode";
    public static final String RESET_AVC = "reset_avc";
    public static final String RESET_BANLANCE = "reset_banlance";
    public static final String RESET_DRC = "reset_drc";
    public static final String RESET_GEQ_BAND = "reset_geq_band";
    public static final String RESET_OUTPUT_GAIN = "reset_output_gain";
    public static final String RESET_PEQ = "reset_peq";
    public static final String RESET_PRESCALE = "reset_prescale";
    public static final String RESET_SURROUND = "reset_surround";
    public static final String RESET_VOLUME_CURVE = "reset_volume_curve";
    public static final String SET_VOLUME = "set_volume";
    public static final String SPEAKER_VOLUME = "speaker_volume";
    public static final String SPK_DELAY = "spk_delay";
    public static final String SPK_MUTE = "spk_mute";
    public static final String STANDARD_MODE_GAIN = "standard_mode_gain";
    public static final String SURROUND_MODE = "surround_mode";
    private static final String TAG = "AudioManagerEx";
    public static final String USB_PRESCALE = "usb_prescale";
    public static final String USER_MODE_GAIN = "user_mode_gain";
    public static final String VALUE_ARC_VOLUME = "100";
    public static final String VALUE_OWA_VOLUME = "100";
    public static final String VOLUME_CURVE = "volume_curve";
    private static final boolean enable_close_hdmiplug = false;
    private static boolean hdmiAvailable = false;
    private static boolean hdmiExpected = false;
    private AudioPatch mAudioPatch;
    private List<AudioDevicePort> mAudioSink;
    private AudioDevicePort mAudioSource;
    private final Context mContextEx;

    public AudioManagerEx(Context context) {
        super(context);
        this.mAudioPatch = null;
        this.mContextEx = context;
        Log.d(TAG, TAG);
    }

    public String getAudioParameters(String str) {
        String parameters = getParameters(str);
        if (parameters == null) {
            Log.d(TAG, "list null");
            return null;
        }
        int length = parameters.length();
        if (parameters.contains("=")) {
            parameters = parameters.substring(0, length - 1);
        }
        Log.d(TAG, "list: " + parameters);
        return parameters;
    }

    public ArrayList<String> getAudioDevices(String str) {
        if (!str.equals(AUDIO_INPUT_TYPE) && !str.equals(AUDIO_OUTPUT_TYPE)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String parameters = getParameters(str);
        if (parameters == null) {
            return null;
        }
        Log.d(TAG, "type " + str + "  list " + parameters);
        String[] strArrSplit = parameters.split(",");
        int length = strArrSplit.length;
        for (int i = 0; i < length; i++) {
            String strSubstring = strArrSplit[i];
            int length2 = strSubstring.length();
            if (strSubstring.contains("=")) {
                strSubstring = strSubstring.substring(0, length2 - 1);
            }
            if (!"".equals(strSubstring)) {
                arrayList.add(strSubstring);
            }
        }
        return arrayList;
    }

    public ArrayList<String> getActiveAudioDevices(String str) {
        if (!str.equals(AUDIO_INPUT_ACTIVE) && !str.equals(AUDIO_OUTPUT_ACTIVE)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        String parameters = getParameters(str);
        if (parameters == null) {
            return null;
        }
        Log.d(TAG, "type " + str + "  list " + parameters);
        String[] strArrSplit = parameters.split(",");
        int length = strArrSplit.length;
        for (int i = 0; i < length; i++) {
            String strSubstring = strArrSplit[i];
            int length2 = strSubstring.length();
            if (strSubstring.contains("=")) {
                strSubstring = strSubstring.substring(0, length2 - 1);
            }
            if (!"".equals(strSubstring)) {
                arrayList.add(strSubstring);
            }
        }
        return arrayList;
    }

    public void disconnectAudioHeadphone() {
        String resetAudioOutDevice = getResetAudioOutDevice();
        ArrayList<String> arrayList = new ArrayList<>();
        if (isBluetoothA2dpOn()) {
            Log.d(TAG, "disconnectAudioHeadphone Connect to a2dp.");
            arrayList.add(AUDIO_NAME_A2DP);
        } else {
            Log.d(TAG, "disconnectAudioHeadphone Connect to last device.");
            arrayList.add(resetAudioOutDevice);
        }
        setAudioDeviceActive(arrayList, AUDIO_OUTPUT_ACTIVE);
        setParameter("headphone_exist", "0");
    }

    private AudioDevicePort findAudioDevicePort(int i) {
        if (i == 0) {
            return null;
        }
        ArrayList<AudioDevicePort> arrayList = new ArrayList();
        if (AudioManager.listAudioDevicePorts(arrayList) != 0) {
            return null;
        }
        for (AudioDevicePort audioDevicePort : arrayList) {
            if (audioDevicePort.type() == i) {
                return audioDevicePort;
            }
        }
        return null;
    }

    private void findAudioSinkFromAudioPolicy(List<AudioDevicePort> list) {
        list.clear();
        ArrayList<AudioDevicePort> arrayList = new ArrayList();
        if (AudioManager.listAudioDevicePorts(arrayList) != 0) {
            return;
        }
        int devicesForStream = super.getDevicesForStream(3);
        for (AudioDevicePort audioDevicePort : arrayList) {
            if ((audioDevicePort.type() & devicesForStream) != 0 && audioDevicePort.type() == 128) {
                list.add(audioDevicePort);
            }
        }
    }

    public void setAllStreamVolume(int i) {
        Log.d(TAG, "AwA2dpAudio: setAllStreamVolume : " + i);
        super.setStreamVolume(0, i, 0);
        super.setStreamVolume(1, i, 0);
        super.setStreamVolume(4, i, 0);
        super.setStreamVolume(2, i, 0);
        super.setStreamVolume(3, i, 0);
        super.setStreamVolume(5, i, 0);
        super.setStreamVolume(6, i, 0);
        super.setStreamVolume(11, i, 0);
    }

    public void createA2dpAudioPath() {
        Log.d(TAG, "AwA2dpAudio: a2dp createA2dpAudioPath");
        if (this.mAudioPatch == null) {
            Log.d(TAG, "AwA2dpAudio: a2dp createA2dpAudioPath try to find source and sink config");
            this.mAudioSource = findAudioDevicePort(AudioDevice.IN_LOOPBACK);
            AudioPortConfig audioPortConfigBuildConfig = this.mAudioSource.buildConfig(44100, 12, 2, (AudioGainConfig) null);
            Log.d(TAG, "\n AwA2dpAudio: sourceConfig:=" + audioPortConfigBuildConfig);
            this.mAudioSink = new ArrayList();
            findAudioSinkFromAudioPolicy(this.mAudioSink);
            ArrayList arrayList = new ArrayList();
            for (AudioDevicePort audioDevicePort : this.mAudioSink) {
                audioDevicePort.activeConfig();
                arrayList.add(audioDevicePort.buildConfig(44100, 12, 2, (AudioGainConfig) null));
            }
            Log.d(TAG, "\n AwA2dpAudio: sinkConfig=" + arrayList);
            AudioPatch[] audioPatchArr = {this.mAudioPatch};
            int iCreateAudioPatch = AudioManager.createAudioPatch(audioPatchArr, new AudioPortConfig[]{audioPortConfigBuildConfig}, (AudioPortConfig[]) arrayList.toArray(new AudioPortConfig[arrayList.size()]));
            if (iCreateAudioPatch == 0) {
                Log.d(TAG, "AwA2dpAudio: A2dpAudioPath Create Success !");
            } else {
                Log.d(TAG, "AwA2dpAudio: A2dpAudioPath Create Failed !!! -> " + iCreateAudioPatch);
            }
            this.mAudioPatch = audioPatchArr[0];
            int streamVolume = super.getStreamVolume(3);
            Log.d(TAG, "AwA2dpAudio: get the a2dp stream Volume to recover is : " + streamVolume);
            setAllStreamVolume(streamVolume);
            return;
        }
        Log.d(TAG, "AwA2dpAudio: A2dpAudioPath has been create!");
    }

    public void releaseA2dpAudioPath() {
        Log.d(TAG, "AwA2dpAudio: a2dp releaseAudioPath");
        if (this.mAudioPatch != null) {
            int iReleaseAudioPatch = AudioManager.releaseAudioPatch(this.mAudioPatch);
            if (iReleaseAudioPatch == 0) {
                Log.d(TAG, "AwA2dpAudio: A2dpAudioPath Release Success !");
            } else {
                Log.d(TAG, "AwA2dpAudio: A2dpAudioPath Release Failed !!! -> " + iReleaseAudioPatch);
            }
            this.mAudioPatch = null;
            return;
        }
        Log.d(TAG, "AwA2dpAudio: a2dp release AudioPatch is NULL !!!");
    }

    public void disconnectA2dpAudioDevice() {
        releaseA2dpAudioPath();
        ArrayList<String> arrayList = new ArrayList<>();
        if (isWiredHeadsetOn()) {
            Log.d(TAG, "AwA2dpAudio: disconnect A2dp Connect device to headphone.");
            arrayList.add(AUDIO_NAME_HEADPHONE);
        } else {
            Log.d(TAG, "AwA2dpAudio: disconnect A2dp Connect device to last device.");
            arrayList.add(getResetAudioOutDevice());
        }
        setAudioDeviceActive(arrayList, AUDIO_OUTPUT_ACTIVE);
    }

    public void connectA2dpAudioDevice() {
        Log.d(TAG, "Awa2dpAudio: Now awa2dp device connect.....");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(AUDIO_NAME_A2DP);
        setAudioDeviceActive(arrayList, AUDIO_OUTPUT_ACTIVE);
        createA2dpAudioPath();
    }

    public void setOWADelay(int i) {
        String str = getActiveAudioDevices(AUDIO_OUTPUT_ACTIVE).get(0);
        SystemProperties.set(PROP_DELAY_KEY, (str.equals(AUDIO_NAME_ARC) || str.equals(AUDIO_NAME_OWA)) ? Integer.toString(i) : "0");
        setParameter(OWA_DELAY, Integer.toString(i));
    }

    public void setAudioDeviceActive(ArrayList<String> arrayList, String str) {
        boolean z;
        String str2;
        String str3;
        String str4;
        if ((str.equals(AUDIO_INPUT_ACTIVE) || str.equals(AUDIO_OUTPUT_ACTIVE)) && arrayList != null) {
            int i = OWA_IN1;
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            Log.d(TAG, "setAudioDeviceActive state: " + str + ", dev: " + arrayList);
            boolean z2 = Settings.System.getInt(this.mContextEx.getContentResolver(), "enable_pass_through", 0) == 1;
            Log.d(TAG, "setAudioDeviceActive enablePassThrough = " + z2);
            String str5 = arrayList2.size() > 0 ? (String) arrayList2.get(0) : AUDIO_NAME_SPK;
            if (z2 && str.equals(AUDIO_OUTPUT_ACTIVE)) {
                if (str5.contains("USB")) {
                    Log.d(TAG, "USB Audio Output Device " + str5 + " is Connect!");
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    str2 = "PCM";
                } else {
                    switch (str5) {
                        case "AUDIO_SPEAKER":
                            str2 = "PCM";
                            break;
                        case "AUDIO_OWA":
                            str2 = PROP_RAWDATA_MODE_OWA_RAW;
                            break;
                        case "AUDIO_ARC":
                            str3 = str5;
                            str4 = PROP_RAWDATA_MODE_ARC_RAW;
                            str2 = PROP_RAWDATA_MODE_OWA_RAW;
                            break;
                        case "AUDIO_HEADPHONE":
                            str2 = "PCM";
                            break;
                        case "AUDIO_A2DP":
                            str2 = "PCM";
                            break;
                        default:
                            str2 = "PCM";
                            str5 = AUDIO_NAME_SPK;
                            break;
                    }
                    SystemProperties.set(PROP_RAWDATA_KEY, str2);
                    setParameter(PROP_RAWDATA_KEY, str4);
                    str5 = str3;
                }
                str3 = str5;
                str4 = str2;
                SystemProperties.set(PROP_RAWDATA_KEY, str2);
                setParameter(PROP_RAWDATA_KEY, str4);
                str5 = str3;
            }
            if (!str5.equals(AUDIO_NAME_HEADPHONE) && !str5.equals(AUDIO_NAME_A2DP)) {
                saveResetAudioOutDevice(str5);
            }
            if (str5.equals(AUDIO_NAME_HEADPHONE)) {
                setParameter("headphone_exist", "1");
            }
            if (str5.equals(AUDIO_NAME_ARC)) {
                int i2 = OWA_ARC0;
                setWiredDeviceConnectionState(1024, 1, "", "");
            } else {
                setWiredDeviceConnectionState(1024, 0, "", "");
            }
            SystemProperties.set(PROP_DELAY_KEY, (str5.equals(AUDIO_NAME_ARC) || str5.equals(AUDIO_NAME_OWA)) ? getAudioParameters(OWA_DELAY) : "0");
            Log.d(TAG, "setAudioDeviceActive audio: " + str5);
            if (!z2) {
                SystemProperties.set(PROP_RAWDATA_KEY, "PCM");
                setParameter(PROP_RAWDATA_KEY, "PCM");
            }
            setParameter(str, str5);
            if (str.equals(AUDIO_OUTPUT_ACTIVE)) {
                Settings.System.putString(this.mContextEx.getContentResolver(), "audio_output_channel", str5);
                Log.d(TAG, "Update Settings.System.AUDIO_OUTPUT_CHANNEL, now save audio is " + str5);
            }
        }
    }

    public void saveResetAudioOutDevice(String str) {
        Settings.System.putString(this.mContextEx.getContentResolver(), "audio_save_output_channel", str);
        Log.d(TAG, "Update Settings.System.AUDIO_SAVE_OUTPUT_CHANNEL: " + str);
    }

    public String getResetAudioOutDevice() {
        return Settings.System.getString(this.mContextEx.getContentResolver(), "audio_save_output_channel");
    }

    public void setAudioOutPreDevice(String str) {
        Settings.System.putString(this.mContextEx.getContentResolver(), "audio_pre_output_channel", str);
        Log.d(TAG, "Update Settings.System.AUDIO_PRE_OUTPUT_CHANNEL: " + str);
    }

    public String getPreAudioOutDevice() {
        return Settings.System.getString(this.mContextEx.getContentResolver(), "audio_pre_output_channel");
    }

    public void setAudioPassThroughMode(int i) {
        Settings.System.putInt(this.mContextEx.getContentResolver(), "enable_pass_through", i);
        Log.d(TAG, "Update Settings.System.ENABLE_PASS_THROUGH: " + i);
    }

    public int getAudioPassThroughMode() {
        return Settings.System.getInt(this.mContextEx.getContentResolver(), "enable_pass_through", 0);
    }
}
