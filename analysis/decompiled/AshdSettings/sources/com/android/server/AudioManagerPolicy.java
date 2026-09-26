package com.android.server;

import android.R;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.AudioManagerEx;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AudioManagerPolicy extends BroadcastReceiver {
    private static final int AUDIO_IN_NOTIFY = 20130816;
    private static final int AUDIO_OUT_NOTIFY = 20130815;
    private static final int POLICY_0 = 0;
    private static final int POLICY_1 = 1;
    private static final int POLICY_2 = 2;
    private static final String TAG = "AudioManagerPolicy";
    private static final int USB_TOAST_TIME = 10;
    private static final boolean enable_close_hdmiplug = false;
    private AudioManager mAudioManager;
    private AudioManagerEx mAudioManagerEx;
    private Context mCtx;
    private DisplayManager mDisplayManager;
    private boolean mBooting = false;
    private ExecutorService mThreadExecutor = Executors.newSingleThreadExecutor();
    private AlertDialog alertDialog = null;
    private NotificationChannel mChannel = null;
    private boolean headPhoneConnected = false;
    private Handler mHandler = new Handler() { // from class: com.android.server.AudioManagerPolicy.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AudioManagerPolicy.this.toastMessage((String) message.obj);
        }
    };
    private ContentObserver mContentObserver = new ContentObserver(this.mHandler) { // from class: com.android.server.AudioManagerPolicy.2
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(AudioManagerPolicy.this.mAudioManagerEx.getActiveAudioDevices(AudioManagerEx.AUDIO_OUTPUT_ACTIVE), AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
        }
    };

    public AudioManagerPolicy(Context context) {
        this.mAudioManager = null;
        this.mAudioManagerEx = null;
        this.mDisplayManager = null;
        this.mCtx = context;
        this.mAudioManager = (AudioManager) this.mCtx.getSystemService("audio");
        this.mAudioManagerEx = new AudioManagerEx(this.mCtx);
        this.mDisplayManager = (DisplayManager) this.mCtx.getSystemService("display");
        initReceriver();
        initAudioOut();
        this.mCtx.getContentResolver().registerContentObserver(Settings.System.getUriFor("enable_pass_through"), true, this.mContentObserver);
    }

    private void initAudioOut() {
        int i;
        boolean z;
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> audioDevices = this.mAudioManagerEx.getAudioDevices(AudioManagerEx.AUDIO_OUTPUT_TYPE);
        this.mBooting = true;
        if (AudioDeviceManagerObserver.getInstance(this.mCtx).getHeadphoneAvailableState() != 1) {
            i = 0;
            while (true) {
                if (i >= audioDevices.size()) {
                    z = false;
                    break;
                }
                String str = audioDevices.get(i);
                if (str.contains("USB")) {
                    arrayList.add(str);
                    this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
                    z = true;
                    break;
                }
                i++;
            }
        } else {
            Log.d(TAG, "headphone is available");
            arrayList.add(AudioManagerEx.AUDIO_NAME_SPK);
            this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
            z = false;
            i = 0;
        }
        if (z) {
            return;
        }
        Log.d(TAG, "no usb audio i=" + i + " size=" + audioDevices.size());
        if (i == audioDevices.size()) {
            switchAudioDevice(0, false, true);
        }
    }

    private void initReceriver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AUDIO_PLUG_IN_OUT");
        this.mCtx.registerReceiver(this, intentFilter);
    }

    private boolean isUSBAudioDeviceExist(String str) {
        if (str == null) {
            return false;
        }
        new ArrayList();
        if (!str.contains("USB")) {
            return false;
        }
        String strSubstring = str.substring(String.format("%s", "AUDIO_USB_").length());
        char[] cArr = new char[1024];
        boolean z = false;
        for (int i = 0; i < 8; i++) {
            try {
                FileReader fileReader = new FileReader(String.format("/sys/class/sound/card%d/", Integer.valueOf(i)) + "id");
                int i2 = fileReader.read(cArr, 0, 1024);
                fileReader.close();
                String strTrim = new String(cArr, 0, i2).trim();
                if (strSubstring.contains(strTrim)) {
                    try {
                        Log.d(TAG, "USB Audio Device  " + strTrim + " exist");
                    } catch (FileNotFoundException unused) {
                        z = true;
                    } catch (Exception unused2) {
                    }
                    z = true;
                }
            } catch (FileNotFoundException unused3) {
            } catch (Exception unused4) {
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchAudioDevice(int i, boolean z, boolean z2) {
        AudioManagerEx audioManagerEx = new AudioManagerEx(this.mCtx);
        new ArrayList();
        String string = Settings.System.getString(this.mCtx.getContentResolver(), "audio_output_channel");
        Log.d(TAG, "save audio is " + string);
        ArrayList arrayList = new ArrayList();
        if (string != null) {
            for (String str : string.split(",")) {
                if (!"".equals(str)) {
                    arrayList.add(str);
                }
            }
        }
        if (!isUSBAudioDeviceExist(string) || string == null || z) {
            Log.d(TAG, " POLICY_2 Remove USB Audio or No USB Audio Output Device Connected!");
            ArrayList<String> arrayList2 = new ArrayList<>();
            if (arrayList.contains(AudioManagerEx.AUDIO_NAME_OWA)) {
                arrayList2.add(AudioManagerEx.AUDIO_NAME_OWA);
            }
            if (arrayList.contains(AudioManagerEx.AUDIO_NAME_ARC)) {
                arrayList2.add(AudioManagerEx.AUDIO_NAME_ARC);
            }
            if (arrayList.contains(AudioManagerEx.AUDIO_NAME_SPK)) {
                arrayList2.add(AudioManagerEx.AUDIO_NAME_SPK);
            }
            if (arrayList.contains(AudioManagerEx.AUDIO_NAME_HEADPHONE)) {
                if (!this.mAudioManager.isWiredHeadsetOn()) {
                    Log.d(TAG, " Now, there is no headphone inserted! so set to default spk output");
                    arrayList2.add(AudioManagerEx.AUDIO_NAME_SPK);
                } else {
                    arrayList2.add(AudioManagerEx.AUDIO_NAME_HEADPHONE);
                }
            }
            if (arrayList.contains(AudioManagerEx.AUDIO_NAME_A2DP)) {
                if (!this.mAudioManager.isBluetoothA2dpOn()) {
                    Log.d(TAG, " Now, there is no bt audio device connected! so set to default spk output");
                    arrayList2.add(AudioManagerEx.AUDIO_NAME_SPK);
                } else {
                    arrayList2.add(AudioManagerEx.AUDIO_NAME_A2DP);
                }
            }
            audioManagerEx.setAudioDeviceActive(arrayList2, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        new Intent();
        if (extras == null) {
            Log.d(TAG, "bundle is null");
            return;
        }
        int i = extras.getInt(AudioDeviceManagerObserver.AUDIO_STATE);
        String string = extras.getString(AudioDeviceManagerObserver.AUDIO_NAME);
        int i2 = extras.getInt(AudioDeviceManagerObserver.AUDIO_TYPE);
        String string2 = extras.getString(AudioDeviceManagerObserver.EXTRA_MNG);
        Log.d(TAG, "On Audio device plug in/out receive,name=" + string + " type=" + i2 + " state=" + i + " extra=" + string2);
        if (string == null) {
            Log.d(TAG, "audio name is null");
        } else {
            handleExternalDevice(string, i2, i, string2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastMessage(String str) {
        Toast.makeText(this.mCtx, str, 1).show();
    }

    private void handleToastMessage(String str) {
        if (this.mHandler == null) {
            return;
        }
        Message messageObtainMessage = this.mHandler.obtainMessage();
        messageObtainMessage.obj = str;
        this.mHandler.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastPlugOutNotification(String str, String str2, int i) {
        ((NotificationManager) this.mCtx.getSystemService("notification")).cancel(i);
        handleToastMessage(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toastPlugInNotification(String str, int i) {
        NotificationManager notificationManager = (NotificationManager) this.mCtx.getSystemService("notification");
        if (this.mChannel == null) {
            this.mChannel = new NotificationChannel("Audio_PlugIn", "PlugInToast", 3);
            notificationManager.createNotificationChannel(this.mChannel);
        }
        Notification notificationBuild = new Notification.Builder(this.mCtx).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.pointer_wait_64).setTicker(str).setContentTitle(str).setContentText(str).setChannel("Audio_PlugIn").build();
        notificationBuild.defaults &= -2;
        notificationBuild.flags = 16;
        notificationManager.notify(i, notificationBuild);
        handleToastMessage(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.android.server.AudioManagerPolicy$5] */
    public void chooseUsbAudioDeviceDialog(final ArrayList<String> arrayList) {
        this.alertDialog = new AlertDialog.Builder(this.mCtx).setTitle(R.string.permlab_bluetooth_scan).setMessage(String.format(this.mCtx.getResources().getString(R.string.permlab_bluetooth_connect), Integer.toString(10))).setPositiveButton(R.string.permlab_cameraHeadlessSystemUser, new DialogInterface.OnClickListener() { // from class: com.android.server.AudioManagerPolicy.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AudioManagerPolicy.this.toastPlugInNotification(AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_access_notification_policy), AudioManagerPolicy.AUDIO_OUT_NOTIFY);
                AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
            }
        }).setNegativeButton(R.string.permlab_bluetoothAdmin, new DialogInterface.OnClickListener() { // from class: com.android.server.AudioManagerPolicy.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AudioManagerPolicy.this.toastPlugInNotification(AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_activityRecognition), AudioManagerPolicy.AUDIO_OUT_NOTIFY);
            }
        }).create();
        this.alertDialog.getWindow().setType(2009);
        this.alertDialog.show();
        new AsyncTask() { // from class: com.android.server.AudioManagerPolicy.5
            @Override // android.os.AsyncTask
            protected Object doInBackground(Object... objArr) {
                for (int i = 10; i >= 0 && AudioManagerPolicy.this.alertDialog != null && AudioManagerPolicy.this.alertDialog.isShowing(); i--) {
                    publishProgress(Integer.valueOf(i));
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused) {
                    }
                }
                return null;
            }

            @Override // android.os.AsyncTask
            protected void onPostExecute(Object obj) {
                super.onPostExecute(obj);
                if (AudioManagerPolicy.this.alertDialog == null || !AudioManagerPolicy.this.alertDialog.isShowing()) {
                    return;
                }
                AudioManagerPolicy.this.alertDialog.dismiss();
                AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
                AudioManagerPolicy.this.alertDialog = null;
            }

            @Override // android.os.AsyncTask
            protected void onProgressUpdate(Object... objArr) {
                super.onProgressUpdate(objArr);
                int iIntValue = ((Integer) objArr[0]).intValue();
                String string = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_bluetooth_connect);
                if (AudioManagerPolicy.this.alertDialog != null) {
                    AudioManagerPolicy.this.alertDialog.setMessage(String.format(string, Integer.toString(iIntValue)));
                }
            }
        }.execute(new Object[0]);
    }

    private void handleExternalDevice(final String str, final int i, final int i2, final String str2) {
        this.mThreadExecutor.execute(new Runnable() { // from class: com.android.server.AudioManagerPolicy.6
            /* JADX WARN: Code duplicated, block: B:37:0x0132  */
            /* JADX WARN: Code duplicated, block: B:71:0x025e  */
            @Override // java.lang.Runnable
            public void run() {
                String string;
                String string2;
                ArrayList<String> audioDevices = AudioManagerPolicy.this.mAudioManagerEx.getAudioDevices(AudioManagerEx.AUDIO_OUTPUT_TYPE);
                ArrayList<String> audioDevices2 = AudioManagerPolicy.this.mAudioManagerEx.getAudioDevices(AudioManagerEx.AUDIO_INPUT_TYPE);
                switch (i2) {
                    case 0:
                        switch (i) {
                            case 0:
                                Log.d(AudioManagerPolicy.TAG, "audio input plug out");
                                String string3 = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_accessNotifications);
                                String string4 = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_activityRecognition);
                                ArrayList<String> activeAudioDevices = AudioManagerPolicy.this.mAudioManagerEx.getActiveAudioDevices(AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                if (activeAudioDevices == null || activeAudioDevices.size() == 0 || activeAudioDevices.contains(str)) {
                                    ArrayList<String> arrayList = new ArrayList<>();
                                    for (String str3 : audioDevices2) {
                                        if (str3.contains("USB")) {
                                            arrayList.add(str3);
                                            if (arrayList.size() == 0) {
                                                arrayList.add(AudioManagerEx.AUDIO_NAME_CAPTURE);
                                            }
                                            AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                            AudioManagerPolicy.this.toastPlugOutNotification(string3, string4, AudioManagerPolicy.AUDIO_IN_NOTIFY);
                                            break;
                                        }
                                    }
                                    if (arrayList.size() == 0) {
                                        arrayList.add(AudioManagerEx.AUDIO_NAME_CAPTURE);
                                    }
                                    AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                    AudioManagerPolicy.this.toastPlugOutNotification(string3, string4, AudioManagerPolicy.AUDIO_IN_NOTIFY);
                                } else if (!activeAudioDevices.contains("USB")) {
                                    ArrayList<String> arrayList2 = new ArrayList<>();
                                    arrayList2.add(AudioManagerEx.AUDIO_NAME_CAPTURE);
                                    AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList2, AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                }
                                break;
                            case 1:
                                ArrayList<String> arrayList3 = new ArrayList<>();
                                Log.d(AudioManagerPolicy.TAG, "audio output plug out");
                                if (str2 == null || !str2.equals(AudioDeviceManagerObserver.H2W_DEV)) {
                                    if (AudioManagerPolicy.this.headPhoneConnected) {
                                        arrayList3.add(AudioManagerEx.AUDIO_NAME_CODEC);
                                    } else if (str.contains("USB")) {
                                        Log.d(AudioManagerPolicy.TAG, "switchAudioDevice, remove USB Audio device!");
                                        if (AudioManagerPolicy.this.alertDialog != null && AudioManagerPolicy.this.alertDialog.isShowing()) {
                                            AudioManagerPolicy.this.alertDialog.dismiss();
                                            AudioManagerPolicy.this.alertDialog = null;
                                        }
                                        AudioManagerPolicy.this.switchAudioDevice(0, true, false);
                                    } else {
                                        Log.d(AudioManagerPolicy.TAG, "switchAudioDevice, NO USB Audio device is connected!");
                                        AudioManagerPolicy.this.switchAudioDevice(0, false, false);
                                    }
                                    string = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_accessWimaxState);
                                    string2 = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_activityRecognition);
                                } else {
                                    AudioManagerPolicy.this.headPhoneConnected = false;
                                    for (String str4 : audioDevices) {
                                        if (str4.contains("USB")) {
                                            arrayList3.add(str4);
                                            AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList3, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
                                            if (arrayList3.size() == 0) {
                                                AudioManagerPolicy.this.switchAudioDevice(0, false, false);
                                            }
                                            string = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.elapsed_time_short_format_mm_ss);
                                            string2 = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.elapsed_time_short_format_h_mm_ss);
                                        }
                                    }
                                    if (arrayList3.size() == 0) {
                                        AudioManagerPolicy.this.switchAudioDevice(0, false, false);
                                    }
                                    string = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.elapsed_time_short_format_mm_ss);
                                    string2 = AudioManagerPolicy.this.mCtx.getResources().getString(R.string.elapsed_time_short_format_h_mm_ss);
                                }
                                AudioManagerPolicy.this.toastPlugOutNotification(string, string2, AudioManagerPolicy.AUDIO_OUT_NOTIFY);
                                break;
                        }
                        break;
                    case 1:
                        switch (i) {
                            case 0:
                                Log.d(AudioManagerPolicy.TAG, "audio input plug in");
                                ArrayList<String> arrayList4 = new ArrayList<>();
                                arrayList4.add(str);
                                AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList4, AudioManagerEx.AUDIO_INPUT_ACTIVE);
                                AudioManagerPolicy.this.toastPlugInNotification(AudioManagerPolicy.this.mCtx.getResources().getString(R.string.permlab_accessNetworkState), AudioManagerPolicy.AUDIO_IN_NOTIFY);
                                break;
                            case 1:
                                Log.d(AudioManagerPolicy.TAG, "audio output plug in");
                                if (str2 != null && str2.equals(AudioDeviceManagerObserver.H2W_DEV)) {
                                    AudioManagerPolicy.this.headPhoneConnected = true;
                                }
                                final ArrayList<String> arrayList5 = new ArrayList<>();
                                if (str2 != null && str2.equals(AudioDeviceManagerObserver.H2W_DEV)) {
                                    arrayList5.add(str);
                                    AudioManagerPolicy.this.mAudioManagerEx.setAudioDeviceActive(arrayList5, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
                                    AudioManagerPolicy.this.mCtx.getResources().getString(R.string.edit_accessibility_shortcut_menu_button);
                                    AudioManagerPolicy.this.mCtx.getResources().getString(R.string.editTextMenuTitle);
                                } else if (str.contains("USB")) {
                                    arrayList5.add(str);
                                    AudioManagerPolicy.this.mHandler.post(new Runnable() { // from class: com.android.server.AudioManagerPolicy.6.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            AudioManagerPolicy.this.chooseUsbAudioDeviceDialog(arrayList5);
                                        }
                                    });
                                }
                                break;
                        }
                        break;
                }
            }
        });
    }
}
