package com.android.settingslib.bluetooth;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class LocalBluetoothManager {
    private static final String TAG = "LocalBluetoothManager";
    private static LocalBluetoothManager sInstance;
    private final CachedBluetoothDeviceManager mCachedDeviceManager;
    private final Context mContext;
    private final BluetoothEventManager mEventManager;
    private Context mForegroundActivity;
    private final LocalBluetoothAdapter mLocalAdapter;
    private final LocalBluetoothProfileManager mProfileManager;

    public interface BluetoothManagerCallback {
        void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager);
    }

    public static synchronized LocalBluetoothManager getInstance(Context context, BluetoothManagerCallback bluetoothManagerCallback) {
        if (sInstance == null) {
            LocalBluetoothAdapter localBluetoothAdapter = LocalBluetoothAdapter.getInstance();
            if (localBluetoothAdapter == null) {
                return null;
            }
            Context applicationContext = context.getApplicationContext();
            sInstance = new LocalBluetoothManager(localBluetoothAdapter, applicationContext);
            if (bluetoothManagerCallback != null) {
                bluetoothManagerCallback.onBluetoothManagerInitialized(applicationContext, sInstance);
            }
        }
        return sInstance;
    }

    private LocalBluetoothManager(LocalBluetoothAdapter localBluetoothAdapter, Context context) {
        this.mContext = context;
        this.mLocalAdapter = localBluetoothAdapter;
        this.mCachedDeviceManager = new CachedBluetoothDeviceManager(context, this);
        this.mEventManager = new BluetoothEventManager(this.mLocalAdapter, this.mCachedDeviceManager, context);
        this.mProfileManager = new LocalBluetoothProfileManager(context, this.mLocalAdapter, this.mCachedDeviceManager, this.mEventManager);
    }

    public LocalBluetoothAdapter getBluetoothAdapter() {
        return this.mLocalAdapter;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Context getForegroundActivity() {
        return this.mForegroundActivity;
    }

    public boolean isForegroundActivity() {
        return this.mForegroundActivity != null;
    }

    public synchronized void setForegroundActivity(Context context) {
        try {
            if (context != null) {
                Log.d(TAG, "setting foreground activity to non-null context");
                this.mForegroundActivity = context;
            } else if (this.mForegroundActivity != null) {
                Log.d(TAG, "setting foreground activity to null");
                this.mForegroundActivity = null;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public CachedBluetoothDeviceManager getCachedDeviceManager() {
        return this.mCachedDeviceManager;
    }

    public BluetoothEventManager getEventManager() {
        return this.mEventManager;
    }

    public LocalBluetoothProfileManager getProfileManager() {
        return this.mProfileManager;
    }
}
