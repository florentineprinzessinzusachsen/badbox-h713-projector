package com.rk_itvui.settings.sound;

import android.app.ActivityManagerNative;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.util.Slog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class AudioCommon {
    public static final String AUDIOPCMLISTUPDATE = "com.android.server.audiopcmlistupdate";
    public static final String HDMI_MULTICHANNEL_KEY = "7";
    public static final String HDMI_PASSTHROUGH_KEY = "6";
    public static final String HW_AUDIO_CURRENTCAPTURE = "persist.audio.currentcapture";
    public static final String HW_AUDIO_CURRENTPLAYBACK = "persist.audio.currentplayback";
    public static final String HW_AUDIO_HDMI_AUTO_IDENTIFY = "persist.audio.hdmi.autoidentify";
    public static final String HW_AUDIO_HDMI_BITSTREAM_CHANNELS = "persist.audio.hdmi.channels";
    public static final String HW_AUDIO_HDMI_BYPASS = "persist.audio.hdmi.bypass";
    public static final String HW_AUDIO_HDMI_MUTE = "persist.audio.hdmi.mute";
    public static final String HW_AUDIO_LASTSOCPLAYBACK = "persist.audio.lastsocplayback";
    public static final String HW_AUDIO_SPDIF_BYPASS = "persist.audio.spdif.bypass";
    public static final String HW_AUDIO_SPDIF_MUTE = "persist.audio.spdif.mute";
    public static final String MEDIA_CFG_AUDIO_BYPASS = "media.cfg.audio.bypass";
    public static final int SND_DEVICE_AUTO_IDENTIFY = 6;
    public static final int SND_DEVICE_BITSTREAM = 3;
    public static final int SND_DEVICE_BITSTREAM_5POINT1 = 4;
    public static final int SND_DEVICE_BITSTREAM_7POINT1 = 5;
    public static final int SND_DEVICE_MANUAL_IDENTIFY = 7;
    public static final int SND_DEVICE_MODE_BASE = 0;
    public static final int SND_DEVICE_MUTE = 1;
    public static final int SND_DEVICE_PCM = 2;
    public static final int SND_DEV_TYPE_BASE = 0;
    public static final int SND_DEV_TYPE_DEFAULT = 0;
    public static final int SND_DEV_TYPE_HDMI_MULTILPCM = 4;
    public static final int SND_DEV_TYPE_HDMI_PASSTHROUGH = 5;
    public static final int SND_DEV_TYPE_SOC_SPDIF = 3;
    public static final int SND_DEV_TYPE_SPDIF = 2;
    public static final int SND_DEV_TYPE_SPDIF_PASSTHROUGH = 6;
    public static final int SND_DEV_TYPE_USB = 1;
    public static final int SND_PCM_STREAM_CAPTURE = 1;
    public static final int SND_PCM_STREAM_PLAYBACK = 0;
    public static final String SOC_AND_SPDIF_KEY = "9";
    public static final String SOC_DEFAULT_KEY = "0";
    public static final String SPDIF_PASSTHROUGH_KEY = "8";
    private static final String TAG = "AudioCommon";
    private static final String USB_AUDIO_CAPTURE_SWITCH_STATE_FILE = "/sys/class/switch/usb_audio_capture/state";
    private static final String USB_AUDIO_PLAYBACK_SWITCH_STATE_FILE = "/sys/class/switch/usb_audio_playback/state";
    private static AudioManager mAudioManager;

    public static String getCurrentPlaybackDevice() {
        return SystemProperties.get(HW_AUDIO_CURRENTPLAYBACK, "0");
    }

    public static String getCurrentCaptureDevice() {
        return SystemProperties.get(HW_AUDIO_CURRENTCAPTURE, "0");
    }

    public static void setCurrentPlaybackDevice(String str) {
        SystemProperties.set(HW_AUDIO_CURRENTPLAYBACK, str);
    }

    public static void setCurrentCaptureDevice(String str) {
        SystemProperties.set(HW_AUDIO_CURRENTCAPTURE, str);
    }

    public static void setLastSocPlayback(String str) {
        SystemProperties.set(HW_AUDIO_LASTSOCPLAYBACK, str);
    }

    public static String getLastSocPlayback() {
        return SystemProperties.get(HW_AUDIO_LASTSOCPLAYBACK, "0");
    }

    public static void setDeviceConnectionState(Context context, int i, int i2) {
        if (mAudioManager == null) {
            mAudioManager = (AudioManager) context.getSystemService("audio");
        }
        mAudioManager.setWiredDeviceConnectionState(i, i2, "", "");
    }

    public static boolean isHdmiAutoIdentify() {
        return SystemProperties.get(HW_AUDIO_HDMI_AUTO_IDENTIFY, "false").equals("true");
    }

    public static int getHdmiOutputMode() {
        if (SystemProperties.get(HW_AUDIO_HDMI_MUTE, "false").equals("true")) {
            return 1;
        }
        if (!SystemProperties.get(HW_AUDIO_HDMI_BYPASS, "false").equals("true")) {
            return SystemProperties.get(HW_AUDIO_HDMI_BYPASS, "false").equals("false") ? 2 : 0;
        }
        if (SystemProperties.get(HW_AUDIO_HDMI_BITSTREAM_CHANNELS, "7.1").equals("5.1")) {
            return 4;
        }
        return SystemProperties.get(HW_AUDIO_HDMI_BITSTREAM_CHANNELS, "7.1").equals("7.1") ? 5 : 0;
    }

    public static void setHdmiOutputMode(Context context, int i) {
        switch (i) {
            case 1:
                SystemProperties.set(HW_AUDIO_HDMI_MUTE, "true");
                break;
            case 2:
                SystemProperties.set(HW_AUDIO_HDMI_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "false");
                SystemProperties.set(HW_AUDIO_HDMI_BYPASS, "false");
                doAudioDevicesRouting(context, 0, 0, "0");
                break;
            case 4:
                SystemProperties.set(HW_AUDIO_HDMI_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_BITSTREAM_CHANNELS, "5.1");
                doAudioDevicesRouting(context, 5, 0, HDMI_PASSTHROUGH_KEY);
                break;
            case 5:
                SystemProperties.set(HW_AUDIO_HDMI_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_BITSTREAM_CHANNELS, "7.1");
                doAudioDevicesRouting(context, 5, 0, HDMI_PASSTHROUGH_KEY);
                break;
            case 6:
                SystemProperties.set(HW_AUDIO_HDMI_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_HDMI_AUTO_IDENTIFY, "true");
                doAudioDevicesRouting(context, 5, 0, HDMI_PASSTHROUGH_KEY);
                break;
            case 7:
                SystemProperties.set(HW_AUDIO_HDMI_AUTO_IDENTIFY, "false");
                break;
        }
    }

    public static int getSpdifOutputMode() {
        if (SystemProperties.get(HW_AUDIO_SPDIF_MUTE, "false").equals("true")) {
            return 1;
        }
        if (SystemProperties.get(HW_AUDIO_SPDIF_BYPASS, "false").equals("false")) {
            return 2;
        }
        return SystemProperties.get(HW_AUDIO_SPDIF_BYPASS, "false").equals("true") ? 3 : 0;
    }

    public static void setSpdifOutputMode(Context context, int i) {
        switch (i) {
            case 1:
                SystemProperties.set(HW_AUDIO_SPDIF_MUTE, "true");
                break;
            case 2:
                SystemProperties.set(HW_AUDIO_SPDIF_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "false");
                SystemProperties.set(HW_AUDIO_SPDIF_BYPASS, "false");
                doAudioDevicesRouting(context, 0, 0, "0");
                break;
            case 3:
                SystemProperties.set(HW_AUDIO_SPDIF_MUTE, "false");
                SystemProperties.set(MEDIA_CFG_AUDIO_BYPASS, "true");
                SystemProperties.set(HW_AUDIO_SPDIF_BYPASS, "true");
                doAudioDevicesRouting(context, 6, 0, SPDIF_PASSTHROUGH_KEY);
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0054 A[Catch: IOException -> 0x0038, TRY_ENTER, TryCatch #2 {IOException -> 0x0038, blocks: (B:21:0x0034, B:25:0x003c, B:35:0x0054, B:37:0x0059), top: B:51:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x0059 A[Catch: IOException -> 0x0038, TRY_LEAVE, TryCatch #2 {IOException -> 0x0038, blocks: (B:21:0x0034, B:25:0x003c, B:35:0x0054, B:37:0x0059), top: B:51:0x0004 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x0066 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:45:0x0068 A[Catch: IOException -> 0x0064, TRY_LEAVE, TryCatch #4 {IOException -> 0x0064, blocks: (B:41:0x0060, B:45:0x0068), top: B:52:0x0060 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public static boolean hasSpdif() throws Throwable {
        FileReader fileReader;
        ?? line = 0;
        line = 0;
        line = 0;
        line = 0;
        line = 0;
        boolean z = false;
        try {
            try {
                try {
                    try {
                        fileReader = new FileReader("/proc/asound/cards");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        fileReader = null;
                    }
                    try {
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        while (true) {
                            try {
                                line = bufferedReader.readLine();
                                if (line != 0) {
                                    if (line.lastIndexOf(" - ") > 0 && line.indexOf("SPDIF") > 0) {
                                        line = 1;
                                        z = true;
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } catch (IOException e2) {
                                line = bufferedReader;
                                e = e2;
                                e.printStackTrace();
                                if (line != 0) {
                                    line.close();
                                }
                                if (fileReader != null) {
                                    fileReader.close();
                                }
                            } catch (Throwable th) {
                                line = bufferedReader;
                                th = th;
                                if (line != 0) {
                                    try {
                                        line.close();
                                        if (fileReader != null) {
                                            fileReader.close();
                                        }
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                        throw th;
                                    }
                                } else if (fileReader != null) {
                                    fileReader.close();
                                }
                                throw th;
                            }
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                    } catch (IOException e4) {
                        e = e4;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (line != 0) {
                        line.close();
                        if (fileReader != null) {
                            fileReader.close();
                        }
                    } else if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                fileReader = null;
                e.printStackTrace();
                if (line != 0) {
                    line.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                fileReader = null;
                if (line != 0) {
                    line.close();
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } else if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        }
        return z;
    }

    public static void doAudioDevicesRouting(Context context, int i, int i2, String str) {
        int i3 = 1;
        switch (i) {
            case 4:
                if (i2 == 0) {
                    setDeviceConnectionState(context, 524288, 0);
                    setLastSocPlayback(str);
                }
                break;
            case 5:
                if (i2 == 0) {
                    setDeviceConnectionState(context, 524288, 0);
                    setLastSocPlayback(str);
                }
                break;
            case 6:
                if (i2 == 0) {
                    setDeviceConnectionState(context, 1024, 0);
                    setDeviceConnectionState(context, 524288, 1);
                    setLastSocPlayback(str);
                }
                break;
            default:
                if (i2 == 0) {
                    if (hasSpdif()) {
                        Slog.i(TAG, "has spdif.");
                    } else {
                        i3 = 0;
                    }
                    setDeviceConnectionState(context, 524288, i3);
                    setLastSocPlayback(str);
                }
                break;
        }
        if (i == 0) {
            setCurrentPlaybackDevice("0");
        } else {
            setCurrentPlaybackDevice(str);
        }
        ActivityManagerNative.broadcastStickyIntent(new Intent(AUDIOPCMLISTUPDATE), (String) null, -1);
    }

    public static void doUsbAudioDevicesRouting(int i, String str) {
        int i2 = Integer.parseInt(str);
        if (i2 > 0) {
            i2 *= 10;
        }
        String string = Integer.toString(i2);
        switch (i) {
            case 0:
                try {
                    FileWriter fileWriter = new FileWriter(USB_AUDIO_PLAYBACK_SWITCH_STATE_FILE);
                    fileWriter.write(string);
                    fileWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
                break;
            case 1:
                try {
                    FileWriter fileWriter2 = new FileWriter(USB_AUDIO_CAPTURE_SWITCH_STATE_FILE);
                    fileWriter2.write(string);
                    fileWriter2.close();
                } catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
                break;
            default:
                Slog.e(TAG, "unknown exception!");
                break;
        }
    }
}
