package com.android.settingslib.bluetooth;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDiscoverableTimeoutReceiver extends BroadcastReceiver {
    private static final String INTENT_DISCOVERABLE_TIMEOUT = "android.bluetooth.intent.DISCOVERABLE_TIMEOUT";
    private static final String TAG = "BluetoothDiscoverableTimeoutReceiver";

    public static void setDiscoverableAlarm(Context context, long j) {
        Log.d(TAG, "setDiscoverableAlarm(): alarmTime = " + j);
        Intent intent = new Intent(INTENT_DISCOVERABLE_TIMEOUT);
        intent.setClass(context, BluetoothDiscoverableTimeoutReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (broadcast != null) {
            alarmManager.cancel(broadcast);
            Log.d(TAG, "setDiscoverableAlarm(): cancel prev alarm");
        }
        alarmManager.set(0, j, PendingIntent.getBroadcast(context, 0, intent, 0));
    }

    public static void cancelDiscoverableAlarm(Context context) {
        Log.d(TAG, "cancelDiscoverableAlarm(): Enter");
        Intent intent = new Intent(INTENT_DISCOVERABLE_TIMEOUT);
        intent.setClass(context, BluetoothDiscoverableTimeoutReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, AudioFormat.APTX);
        if (broadcast != null) {
            ((AlarmManager) context.getSystemService("alarm")).cancel(broadcast);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LocalBluetoothAdapter localBluetoothAdapter = LocalBluetoothAdapter.getInstance();
        if (localBluetoothAdapter != null && localBluetoothAdapter.getState() == 12) {
            Log.d(TAG, "Disable discoverable...");
            localBluetoothAdapter.setScanMode(21);
        } else {
            Log.e(TAG, "localBluetoothAdapter is NULL!!");
        }
    }
}
