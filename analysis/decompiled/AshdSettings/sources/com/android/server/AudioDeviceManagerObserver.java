package com.android.server;

import android.app.ActivityManagerNative;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioManagerEx;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.UEventObserver;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import android.util.Slog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AudioDeviceManagerObserver extends UEventObserver {
    public static final int AUDIO_INPUT_TYPE = 0;
    public static final String AUDIO_NAME = "audioName";
    public static final int AUDIO_OUTPUT_TYPE = 1;
    public static final String AUDIO_STATE = "audioState";
    public static final String AUDIO_TYPE = "audioType";
    public static final String EXTRA_MNG = "extral_mng";
    public static final String H2W_DEV = "AUDIO_H2W";
    private static final boolean LOG = true;
    private static final int MAX_AUDIO_DEVICES = 16;
    public static final int PLUG_IN = 1;
    public static final int PLUG_OUT = 0;
    private static final String TAG = "AudioDeviceManagerObserver";
    private static final String h2wDevPath = String.format("/devices/virtual/switch/%s", "h2w");
    private static AudioDeviceManagerObserver mAudioObserver = null;
    private static final String state = "state";
    private static final String uAudioDevicesPath = "/sys/class/sound/";
    private static final String uAudioInType = "c";
    private static final String uAudioOutType = "p";
    private static final String uEventAction = "ACTION";
    private static final String uEventDevName = "DEVNAME";
    private static final String uEventDevPath = "DEVPATH";
    private static final String uEventSubsystem = "SUBSYSTEM=sound";
    private static final String uPcmDev = "snd/pcm";
    private AudioManager mAudioManager;
    private AudioManagerEx mAudioManagerEx;
    private String[][] mAudioNameMap = {new String[]{"audiocodec", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_CODEC}, new String[]{"snddaudio0", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_AC107}, new String[]{"snddaudio1", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, "AUDIO_DAUDIO1"}, new String[]{"snddaudio2", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_CAPTURE}, new String[]{"TridentALSA", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_MEM}, new String[]{"sndowa1", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_ARC}, new String[]{"sndowa0", EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, AudioManagerEx.AUDIO_NAME_OWA}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}, new String[]{EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN, EnvironmentCompat.MEDIA_UNKNOWN}};
    private final Context mContext;

    private AudioDeviceManagerObserver(Context context) {
        this.mAudioManager = null;
        this.mAudioManagerEx = null;
        this.mContext = context;
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mAudioManagerEx = new AudioManagerEx(context);
        Log.d(TAG, "AudioDeviceManagerObserver construct");
        context.registerReceiver(new BootCompletedReceiver(), new IntentFilter("android.intent.action.BOOT_COMPLETED"), null, null);
    }

    public static synchronized AudioDeviceManagerObserver getInstance(Context context) {
        if (mAudioObserver == null) {
            mAudioObserver = new AudioDeviceManagerObserver(context);
        }
        return mAudioObserver;
    }

    private final class BootCompletedReceiver extends BroadcastReceiver {
        private BootCompletedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AudioDeviceManagerObserver.this.init();
            AudioDeviceManagerObserver.this.startObserving(AudioDeviceManagerObserver.uEventSubsystem);
            AudioDeviceManagerObserver.this.startObserving("DEVPATH=" + AudioDeviceManagerObserver.h2wDevPath);
            new AudioManagerPolicy(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void init() {
        String str;
        Exception e;
        String strFindNameMap;
        char[] cArr = new char[1024];
        String.format("%s", EnvironmentCompat.MEDIA_UNKNOWN);
        String str2 = String.format("%s", EnvironmentCompat.MEDIA_UNKNOWN);
        Log.v(TAG, "AudioDeviceManagerObserver init()");
        String str3 = str2;
        for (int i = 0; i < 16; i++) {
            try {
                try {
                    String str4 = String.format("/sys/class/sound/card%d/", Integer.valueOf(i));
                    String str5 = str4 + "id";
                    Log.d(TAG, "AudioDeviceManagerObserver: newCardId: " + str5);
                    FileReader fileReader = new FileReader(str5);
                    int i2 = fileReader.read(cArr, 0, 1024);
                    fileReader.close();
                    String strTrim = new String(cArr, 0, i2).trim();
                    if (i2 > 0) {
                        str = str3;
                        for (String str6 : new File(str4).list()) {
                            try {
                                if (str6.startsWith("pcm")) {
                                    int length = str6.length();
                                    String strSubstring = str6.substring(length - 1, length);
                                    Log.d(TAG, "AudioDeviceManagerObserver: devName: " + str6);
                                    if (strSubstring.equalsIgnoreCase(uAudioInType)) {
                                        strFindNameMap = findNameMap(strTrim, "snd/" + str6, true);
                                        try {
                                            if (strFindNameMap.contains("USB")) {
                                                Log.d(TAG, "USB setAudioDeviceActive name_android: " + strFindNameMap);
                                                ArrayList<String> arrayList = new ArrayList<>();
                                                arrayList.add(strFindNameMap);
                                                this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            str = strFindNameMap;
                                            Log.e(TAG, "", e);
                                            str3 = str;
                                        }
                                    } else if (strSubstring.equalsIgnoreCase(uAudioOutType)) {
                                        strFindNameMap = findNameMap(strTrim, "snd/" + str6, false);
                                    }
                                    str = strFindNameMap;
                                }
                            } catch (Exception e3) {
                                e = e3;
                            }
                        }
                        Log.d(TAG, "AudioDeviceManagerObserver: name_linux: " + strTrim + ", name_android: " + str);
                        str3 = str;
                    }
                } catch (FileNotFoundException unused) {
                    Log.v(TAG, "This kernel does not have sound card" + i);
                }
            } catch (Exception e4) {
                str = str3;
                e = e4;
            }
        }
    }

    private String findNameMap(String str, String str2, boolean z) {
        Log.d(TAG, "~~~~~~~~ AudioDeviceManagerObserver: findNameMap: id: " + str);
        if (str != null) {
            if (str2 == null) {
                str2 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            for (int i = 0; i < 16; i++) {
                if (this.mAudioNameMap[i][0].equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    this.mAudioNameMap[i][0] = str;
                    if (z) {
                        this.mAudioNameMap[i][1] = str2;
                    } else {
                        this.mAudioNameMap[i][2] = str2;
                    }
                    this.mAudioNameMap[i][3] = String.format("AUDIO_USB_%s", str);
                    return this.mAudioNameMap[i][3];
                }
                if (this.mAudioNameMap[i][0].equals(str)) {
                    if (z) {
                        this.mAudioNameMap[i][1] = str2;
                    } else {
                        this.mAudioNameMap[i][2] = str2;
                    }
                    return this.mAudioNameMap[i][3];
                }
            }
        }
        return null;
    }

    private String findNameMap(String str, boolean z, boolean z2) {
        Log.d(TAG, "~~~~~~~~ AudioDeviceManagerObserver: findNameMap: devName: " + str);
        if (str != null && !str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
            for (int i = 0; i < 16; i++) {
                if (z && this.mAudioNameMap[i][1].equals(str)) {
                    String str2 = this.mAudioNameMap[i][3];
                    if (!z2) {
                        return str2;
                    }
                    this.mAudioNameMap[i][1] = EnvironmentCompat.MEDIA_UNKNOWN;
                    return str2;
                }
                if (!z && this.mAudioNameMap[i][2].equals(str)) {
                    String str3 = this.mAudioNameMap[i][3];
                    if (!z2) {
                        return str3;
                    }
                    this.mAudioNameMap[i][2] = EnvironmentCompat.MEDIA_UNKNOWN;
                    return str3;
                }
            }
        }
        return null;
    }

    public void onUEvent(UEventObserver.UEvent uEvent) {
        Log.d(TAG, "Audio device change: " + uEvent.toString());
        try {
            String str = uEvent.get(uEventDevPath);
            String str2 = uEvent.get(uEventDevName);
            String str3 = uEvent.get(uEventAction);
            if (str != null && str.equals(h2wDevPath)) {
                String str4 = uEvent.get("SWITCH_NAME");
                int i = Integer.parseInt(uEvent.get("SWITCH_STATE"));
                String str5 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("device ");
                sb.append(str4);
                sb.append(i != 0 ? " connected" : " disconnected");
                Log.d(str5, sb.toString());
                updateState(AudioManagerEx.AUDIO_NAME_CODEC, 1, i != 0 ? 1 : 0, H2W_DEV);
            }
            if (str2 == null || !str2.substring(0, 7).equals(uPcmDev.substring(0, 7))) {
                return;
            }
            Log.d(TAG, "action: " + str3 + " devName: " + str2 + " devPath: " + str);
            char[] cArr = new char[64];
            String strSubstring = str.substring(0, str.lastIndexOf("/"));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(strSubstring);
            sb2.append("/id");
            String string = sb2.toString();
            int length = str2.length();
            String strSubstring2 = str2.substring(length - 1, length);
            if (!str3.equals("add")) {
                if (str3.equals("remove")) {
                    try {
                        if (strSubstring2.equalsIgnoreCase(uAudioInType)) {
                            SystemClock.sleep(5L);
                            String strFindNameMap = findNameMap(str2, true, true);
                            this.mAudioManagerEx.getAudioDevices(AudioManagerEx.AUDIO_INPUT_TYPE);
                            updateState(strFindNameMap, 0, 0);
                        } else if (strSubstring2.equalsIgnoreCase(uAudioOutType)) {
                            SystemClock.sleep(5L);
                            String strFindNameMap2 = findNameMap(str2, false, true);
                            this.mAudioManagerEx.getAudioDevices(AudioManagerEx.AUDIO_OUTPUT_TYPE);
                            updateState(strFindNameMap2, 1, 0);
                        }
                        return;
                    } catch (Exception e) {
                        Slog.e(TAG, "", e);
                        return;
                    }
                }
                return;
            }
            int i2 = 10;
            while (true) {
                int i3 = i2 - 1;
                if (i2 == 0) {
                    return;
                }
                try {
                    try {
                        FileReader fileReader = new FileReader("sys" + string);
                        int i4 = fileReader.read(cArr, 0, 64);
                        fileReader.close();
                        if (i4 == 1) {
                            Thread.sleep(5L);
                        } else if (i4 > 0) {
                            String strTrim = new String(cArr, 0, i4).trim();
                            if (strSubstring2.equalsIgnoreCase(uAudioInType)) {
                                updateState(findNameMap(strTrim, str2, true), 0, 1);
                                return;
                            } else {
                                if (strSubstring2.equalsIgnoreCase(uAudioOutType)) {
                                    updateState(findNameMap(strTrim, str2, false), 1, 1);
                                    return;
                                }
                                return;
                            }
                        }
                    } catch (FileNotFoundException unused) {
                        if (i3 == 0) {
                            Slog.e(TAG, "can not read card id");
                            return;
                        }
                        try {
                            Slog.w(TAG, "read card id, wait for a moment ......");
                            Thread.sleep(10L);
                        } catch (Exception e2) {
                            Slog.e(TAG, "", e2);
                        }
                    }
                } catch (Exception e3) {
                    Slog.e(TAG, "", e3);
                }
                i2 = i3;
            }
            Slog.e(TAG, "Could not parse switch state from event " + uEvent);
        } catch (NumberFormatException unused2) {
            Slog.e(TAG, "Could not parse switch state from event " + uEvent);
        }
    }

    public final synchronized void updateState(String str, int i, int i2) {
        updateState(str, i, i2, null);
    }

    public final synchronized void updateState(String str, int i, int i2, String str2) {
        Log.d(TAG, "name: " + str + ", state: " + i2 + ", type: " + i);
        if (str.contains("USB") && i == 1) {
            return;
        }
        Intent intent = new Intent("android.intent.action.AUDIO_PLUG_IN_OUT");
        intent.addFlags(1073741824);
        Bundle bundle = new Bundle();
        bundle.putInt(AUDIO_STATE, i2);
        bundle.putString(AUDIO_NAME, str);
        bundle.putInt(AUDIO_TYPE, i);
        if (str2 != null) {
            bundle.putString(EXTRA_MNG, str2);
        }
        intent.putExtras(bundle);
        ActivityManagerNative.broadcastStickyIntent(intent, (String) null, -1);
    }

    public int getHeadphoneAvailableState() {
        String str = "/sys" + h2wDevPath + "/state";
        if (str == null) {
            return 0;
        }
        try {
            byte[] bArr = new byte[32];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(str)));
            String str2 = bufferedInputStream.read(bArr, 0, 32) != -1 ? new String(bArr, 0, 1) : null;
            bufferedInputStream.close();
            int iIntValue = Integer.valueOf(str2).intValue();
            Log.d(TAG, "headphone state is " + str2);
            return (iIntValue != 0 && iIntValue == 2) ? 1 : 0;
        } catch (Exception e) {
            Slog.e(TAG, "Could not parse switch state for " + str + " ,fail because " + e.getMessage());
            return 0;
        }
    }
}
