package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.settingslib.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class LocalBluetoothProfileManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "LocalBluetooth";
    private static LocalBluetoothProfileManager sInstance;
    private A2dpProfile mA2dpProfile;
    private A2dpSinkProfile mA2dpSinkProfile;
    private final Context mContext;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private final BluetoothEventManager mEventManager;
    private HeadsetProfile mHeadsetProfile;
    private HfpClientProfile mHfpClientProfile;
    private final HidProfile mHidProfile;
    private final LocalBluetoothAdapter mLocalAdapter;
    private MapProfile mMapProfile;
    private OppProfile mOppProfile;
    private final PanProfile mPanProfile;
    private PbapClientProfile mPbapClientProfile;
    private PbapServerProfile mPbapProfile;
    private final Map<String, LocalBluetoothProfile> mProfileNameMap = new HashMap();
    private final Collection<ServiceListener> mServiceListeners = new ArrayList();
    private final boolean mUsePbapPce;

    public interface ServiceListener {
        void onServiceConnected();

        void onServiceDisconnected();
    }

    LocalBluetoothProfileManager(Context context, LocalBluetoothAdapter localBluetoothAdapter, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, BluetoothEventManager bluetoothEventManager) {
        this.mContext = context;
        this.mLocalAdapter = localBluetoothAdapter;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mEventManager = bluetoothEventManager;
        this.mUsePbapPce = this.mContext.getResources().getBoolean(R.bool.enable_pbap_pce_profile);
        this.mLocalAdapter.setProfileManager(this);
        this.mEventManager.setProfileManager(this);
        ParcelUuid[] uuids = localBluetoothAdapter.getUuids();
        if (uuids != null) {
            updateLocalProfiles(uuids);
        }
        this.mHidProfile = new HidProfile(context, this.mLocalAdapter, this.mDeviceManager, this);
        addProfile(this.mHidProfile, "HID", "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        this.mPanProfile = new PanProfile(context);
        addPanProfile(this.mPanProfile, "PAN", "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        Log.d(TAG, "Adding local MAP profile");
        this.mMapProfile = new MapProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
        addProfile(this.mMapProfile, "MAP", "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED");
        this.mPbapProfile = null;
    }

    void updateLocalProfiles(ParcelUuid[] parcelUuidArr) {
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.A2DP_SOURCE)) {
            if (this.mA2dpProfile == null) {
                Log.d(TAG, "Adding local A2DP SRC profile");
                this.mA2dpProfile = new A2dpProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mA2dpProfile, "A2DP", "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mA2dpProfile != null) {
            Log.w(TAG, "Warning: A2DP profile was previously added but the UUID is now missing.");
        }
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.A2DP_SINK)) {
            if (this.mA2dpSinkProfile == null) {
                Log.d(TAG, "Adding local A2DP Sink profile");
                this.mA2dpSinkProfile = new A2dpSinkProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mA2dpSinkProfile, "A2DPSink", "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mA2dpSinkProfile != null) {
            Log.w(TAG, "Warning: A2DP Sink profile was previously added but the UUID is now missing.");
        }
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HFP_AG) || ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HSP_AG)) {
            if (this.mHeadsetProfile == null) {
                Log.d(TAG, "Adding local HEADSET profile");
                this.mHeadsetProfile = new HeadsetProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mHeadsetProfile, "HEADSET", "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mHeadsetProfile != null) {
            Log.w(TAG, "Warning: HEADSET profile was previously added but the UUID is now missing.");
        }
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HFP)) {
            if (this.mHfpClientProfile == null) {
                Log.d(TAG, "Adding local HfpClient profile");
                this.mHfpClientProfile = new HfpClientProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mHfpClientProfile, "HEADSET_CLIENT", "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mHfpClientProfile != null) {
            Log.w(TAG, "Warning: Hfp Client profile was previously added but the UUID is now missing.");
        } else {
            Log.d(TAG, "Handsfree Uuid not found.");
        }
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.OBEX_OBJECT_PUSH)) {
            if (this.mOppProfile == null) {
                Log.d(TAG, "Adding local OPP profile");
                this.mOppProfile = new OppProfile();
                this.mProfileNameMap.put("OPP", this.mOppProfile);
            }
        } else if (this.mOppProfile != null) {
            Log.w(TAG, "Warning: OPP profile was previously added but the UUID is now missing.");
        }
        if (this.mUsePbapPce) {
            if (this.mPbapClientProfile == null) {
                Log.d(TAG, "Adding local PBAP Client profile");
                this.mPbapClientProfile = new PbapClientProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mPbapClientProfile, "PbapClient", "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mPbapClientProfile != null) {
            Log.w(TAG, "Warning: PBAP Client profile was previously added but the UUID is now missing.");
        }
        this.mEventManager.registerProfileIntentReceiver();
    }

    void updateLocalProfiles1(ParcelUuid[] parcelUuidArr) {
        List supportedProfiles = BluetoothAdapter.getDefaultAdapter().getSupportedProfiles();
        if (supportedProfiles != null) {
            return;
        }
        if (this.mA2dpProfile == null && supportedProfiles.contains(2)) {
            if (this.mA2dpProfile == null) {
                Log.d(TAG, "Adding local A2DP SRC profile");
                this.mA2dpProfile = new A2dpProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mA2dpProfile, "A2DP", "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mA2dpProfile != null) {
            Log.w(TAG, "Warning: A2DP profile was previously added but the UUID is now missing.");
        }
        if (this.mA2dpSinkProfile == null && supportedProfiles.contains(11)) {
            if (this.mA2dpSinkProfile == null) {
                Log.d(TAG, "Adding local A2DP Sink profile");
                this.mA2dpSinkProfile = new A2dpSinkProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mA2dpSinkProfile, "A2DPSink", "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mA2dpSinkProfile != null) {
            Log.w(TAG, "Warning: A2DP Sink profile was previously added but the UUID is now missing.");
        }
        if (this.mHeadsetProfile == null && supportedProfiles.contains(1)) {
            if (this.mHeadsetProfile == null) {
                Log.d(TAG, "Adding local HEADSET profile");
                this.mHeadsetProfile = new HeadsetProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mHeadsetProfile, "HEADSET", "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mHeadsetProfile != null) {
            Log.w(TAG, "Warning: HEADSET profile was previously added but the UUID is now missing.");
        }
        if (this.mHfpClientProfile == null && supportedProfiles.contains(16)) {
            if (this.mHfpClientProfile == null) {
                Log.d(TAG, "Adding local HfpClient profile");
                this.mHfpClientProfile = new HfpClientProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mHfpClientProfile, "HEADSET_CLIENT", "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mHfpClientProfile != null) {
            Log.w(TAG, "Warning: Hfp Client profile was previously added but the UUID is now missing.");
        } else {
            Log.d(TAG, "Handsfree Uuid not found.");
        }
        if (this.mPbapClientProfile == null && supportedProfiles.contains(17)) {
            if (this.mPbapClientProfile == null) {
                Log.d(TAG, "Adding local PBAP Client profile");
                this.mPbapClientProfile = new PbapClientProfile(this.mContext, this.mLocalAdapter, this.mDeviceManager, this);
                addProfile(this.mPbapClientProfile, "PbapClient", "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
            }
        } else if (this.mPbapClientProfile != null) {
            Log.w(TAG, "Warning: PBAP Client profile was previously added but the UUID is now missing.");
        }
        this.mEventManager.registerProfileIntentReceiver();
    }

    private void addProfile(LocalBluetoothProfile localBluetoothProfile, String str, String str2) {
        this.mEventManager.addProfileHandler(str2, new StateChangedHandler(localBluetoothProfile));
        this.mProfileNameMap.put(str, localBluetoothProfile);
    }

    private void addPanProfile(LocalBluetoothProfile localBluetoothProfile, String str, String str2) {
        this.mEventManager.addProfileHandler(str2, new PanStateChangedHandler(localBluetoothProfile));
        this.mProfileNameMap.put(str, localBluetoothProfile);
    }

    public LocalBluetoothProfile getProfileByName(String str) {
        return this.mProfileNameMap.get(str);
    }

    void setBluetoothStateOn() {
        ParcelUuid[] uuids = this.mLocalAdapter.getUuids();
        if (uuids != null) {
            updateLocalProfiles(uuids);
        }
        this.mEventManager.readPairedDevices();
    }

    private class StateChangedHandler implements BluetoothEventManager.Handler {
        final LocalBluetoothProfile mProfile;

        StateChangedHandler(LocalBluetoothProfile localBluetoothProfile) {
            this.mProfile = localBluetoothProfile;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = LocalBluetoothProfileManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                Log.w(LocalBluetoothProfileManager.TAG, "StateChangedHandler found new device: " + bluetoothDevice);
                cachedBluetoothDeviceFindDevice = LocalBluetoothProfileManager.this.mDeviceManager.addDevice(LocalBluetoothProfileManager.this.mLocalAdapter, LocalBluetoothProfileManager.this, bluetoothDevice);
            }
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
            if (intExtra == 0 && intExtra2 == 1) {
                Log.i(LocalBluetoothProfileManager.TAG, "Failed to connect " + this.mProfile + " device");
            }
            cachedBluetoothDeviceFindDevice.onProfileStateChanged(this.mProfile, intExtra);
            cachedBluetoothDeviceFindDevice.refresh();
        }
    }

    private class PanStateChangedHandler extends StateChangedHandler {
        PanStateChangedHandler(LocalBluetoothProfile localBluetoothProfile) {
            super(localBluetoothProfile);
        }

        @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.StateChangedHandler, com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            ((PanProfile) this.mProfile).setLocalRole(bluetoothDevice, intent.getIntExtra("android.bluetooth.pan.extra.LOCAL_ROLE", 0));
            super.onReceive(context, intent, bluetoothDevice);
        }
    }

    public void addServiceListener(ServiceListener serviceListener) {
        this.mServiceListeners.add(serviceListener);
    }

    public void removeServiceListener(ServiceListener serviceListener) {
        this.mServiceListeners.remove(serviceListener);
    }

    void callServiceConnectedListeners() {
        Iterator<ServiceListener> it = this.mServiceListeners.iterator();
        while (it.hasNext()) {
            it.next().onServiceConnected();
        }
    }

    void callServiceDisconnectedListeners() {
        Iterator<ServiceListener> it = this.mServiceListeners.iterator();
        while (it.hasNext()) {
            it.next().onServiceDisconnected();
        }
    }

    public synchronized boolean isManagerReady() {
        HeadsetProfile headsetProfile = this.mHeadsetProfile;
        if (headsetProfile != null) {
            return headsetProfile.isProfileReady();
        }
        A2dpProfile a2dpProfile = this.mA2dpProfile;
        if (a2dpProfile != null) {
            return a2dpProfile.isProfileReady();
        }
        A2dpSinkProfile a2dpSinkProfile = this.mA2dpSinkProfile;
        if (a2dpSinkProfile == null) {
            return false;
        }
        return a2dpSinkProfile.isProfileReady();
    }

    public A2dpProfile getA2dpProfile() {
        return this.mA2dpProfile;
    }

    public A2dpSinkProfile getA2dpSinkProfile() {
        if (this.mA2dpSinkProfile == null || !this.mA2dpSinkProfile.isProfileReady()) {
            return null;
        }
        return this.mA2dpSinkProfile;
    }

    public HeadsetProfile getHeadsetProfile() {
        return this.mHeadsetProfile;
    }

    public HfpClientProfile getHfpClientProfile() {
        if (this.mHfpClientProfile == null || !this.mHfpClientProfile.isProfileReady()) {
            return null;
        }
        return this.mHfpClientProfile;
    }

    public PbapClientProfile getPbapClientProfile() {
        return this.mPbapClientProfile;
    }

    public PbapServerProfile getPbapProfile() {
        return this.mPbapProfile;
    }

    public MapProfile getMapProfile() {
        return this.mMapProfile;
    }

    synchronized void updateProfiles(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2, Collection<LocalBluetoothProfile> collection, Collection<LocalBluetoothProfile> collection2, boolean z, BluetoothDevice bluetoothDevice) {
        collection2.clear();
        collection2.addAll(collection);
        Log.d(TAG, "Current Profiles" + collection.toString());
        collection.clear();
        if (parcelUuidArr == null) {
            return;
        }
        if (this.mHeadsetProfile != null && ((ArrayUtils.contains(parcelUuidArr2, AshdBluetoothUuid.HSP_AG) && ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HSP)) || (ArrayUtils.contains(parcelUuidArr2, AshdBluetoothUuid.HFP_AG) && ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HFP)))) {
            collection.add(this.mHeadsetProfile);
            collection2.remove(this.mHeadsetProfile);
        }
        if (this.mHfpClientProfile != null && ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HFP_AG) && ArrayUtils.contains(parcelUuidArr2, AshdBluetoothUuid.HFP)) {
            collection.add(this.mHfpClientProfile);
            collection2.remove(this.mHfpClientProfile);
        }
        if (AshdBluetoothUuid.containsAnyUuid(parcelUuidArr, A2dpProfile.SINK_UUIDS) && this.mA2dpProfile != null) {
            collection.add(this.mA2dpProfile);
            collection2.remove(this.mA2dpProfile);
        }
        if (AshdBluetoothUuid.containsAnyUuid(parcelUuidArr, A2dpSinkProfile.SRC_UUIDS) && this.mA2dpSinkProfile != null) {
            collection.add(this.mA2dpSinkProfile);
            collection2.remove(this.mA2dpSinkProfile);
        }
        if (ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.OBEX_OBJECT_PUSH) && this.mOppProfile != null) {
            collection.add(this.mOppProfile);
            collection2.remove(this.mOppProfile);
        }
        if ((ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HID) || ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.HOGP)) && this.mHidProfile != null) {
            collection.add(this.mHidProfile);
            collection2.remove(this.mHidProfile);
        }
        if (z) {
            Log.d(TAG, "Valid PAN-NAP connection exists.");
        }
        if ((ArrayUtils.contains(parcelUuidArr, AshdBluetoothUuid.NAP) && this.mPanProfile != null) || z) {
            collection.add(this.mPanProfile);
            collection2.remove(this.mPanProfile);
        }
        if (this.mMapProfile != null && this.mMapProfile.getConnectionStatus(bluetoothDevice) == 2) {
            collection.add(this.mMapProfile);
            collection2.remove(this.mMapProfile);
            this.mMapProfile.setPreferred(bluetoothDevice, true);
        }
        if (this.mUsePbapPce) {
            collection.add(this.mPbapClientProfile);
            collection2.remove(this.mPbapClientProfile);
            collection.remove(this.mPbapProfile);
            collection2.add(this.mPbapProfile);
        }
        Log.d(TAG, "New Profiles" + collection.toString());
    }
}
