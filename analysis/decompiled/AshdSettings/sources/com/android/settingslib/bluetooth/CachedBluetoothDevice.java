package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class CachedBluetoothDevice implements Comparable<CachedBluetoothDevice> {
    public static final int ACCESS_ALLOWED = 1;
    public static final int ACCESS_REJECTED = 2;
    public static final int ACCESS_UNKNOWN = 0;
    private static final boolean DEBUG = false;
    private static final long MAX_HOGP_DELAY_FOR_AUTO_CONNECT = 30000;
    private static final long MAX_UUID_DELAY_FOR_AUTO_CONNECT = 5000;
    private static final int MESSAGE_REJECTION_COUNT_LIMIT_TO_PERSIST = 2;
    private static final String MESSAGE_REJECTION_COUNT_PREFS_NAME = "bluetooth_message_reject";
    private static final String TAG = "CachedBluetoothDevice";
    private BluetoothClass mBtClass;
    private boolean mConnectAfterPairing;
    private long mConnectAttempted;
    private final Context mContext;
    private final BluetoothDevice mDevice;
    private boolean mIsConnectingErrorPossible;
    private final LocalBluetoothAdapter mLocalAdapter;
    private boolean mLocalNapRoleConnected;
    private int mMessageRejectionCount;
    private String mName;
    private final LocalBluetoothProfileManager mProfileManager;
    private short mRssi;
    private boolean mVisible;
    private final List<LocalBluetoothProfile> mProfiles = new ArrayList();
    private final List<LocalBluetoothProfile> mRemovedProfiles = new ArrayList();
    private final Collection<Callback> mCallbacks = new ArrayList();
    Handler mH = new Handler(Looper.getMainLooper());
    private HashMap<LocalBluetoothProfile, Integer> mProfileConnectionState = new HashMap<>();

    public interface Callback {
        void onDeviceAttributesChanged();
    }

    private String describe(LocalBluetoothProfile localBluetoothProfile) {
        StringBuilder sb = new StringBuilder();
        sb.append("Address:");
        sb.append(this.mDevice);
        if (localBluetoothProfile != null) {
            sb.append(" Profile:");
            sb.append(localBluetoothProfile);
        }
        return sb.toString();
    }

    void onProfileStateChanged(LocalBluetoothProfile localBluetoothProfile, int i) {
        Log.d(TAG, "onProfileStateChanged: profile " + localBluetoothProfile + " newProfileState " + i);
        if (this.mLocalAdapter.getBluetoothState() == 13) {
            Log.d(TAG, " BT Turninig Off...Profile conn state change ignored...");
            return;
        }
        if ("A2DPSink".equals(localBluetoothProfile.toString())) {
            Intent intent = new Intent("com.ashd.bluetooth.state_changed");
            intent.putExtra("a2dpstate", i);
            this.mContext.sendBroadcast(intent);
            Log.d(TAG, "onProfileStateChanged: sendBroadcast com.ashd.bluetooth.state_changed： " + i);
        }
        this.mProfileConnectionState.put(localBluetoothProfile, Integer.valueOf(i));
        if (i == 2) {
            if (localBluetoothProfile instanceof MapProfile) {
                localBluetoothProfile.setPreferred(this.mDevice, true);
                return;
            }
            if (this.mProfiles.contains(localBluetoothProfile)) {
                return;
            }
            this.mRemovedProfiles.remove(localBluetoothProfile);
            this.mProfiles.add(localBluetoothProfile);
            if ((localBluetoothProfile instanceof PanProfile) && ((PanProfile) localBluetoothProfile).isLocalRoleNap(this.mDevice)) {
                this.mLocalNapRoleConnected = true;
                return;
            }
            return;
        }
        if ((localBluetoothProfile instanceof MapProfile) && i == 0) {
            localBluetoothProfile.setPreferred(this.mDevice, false);
            return;
        }
        if (this.mLocalNapRoleConnected && (localBluetoothProfile instanceof PanProfile) && ((PanProfile) localBluetoothProfile).isLocalRoleNap(this.mDevice) && i == 0) {
            Log.d(TAG, "Removing PanProfile from device after NAP disconnect");
            this.mProfiles.remove(localBluetoothProfile);
            this.mRemovedProfiles.add(localBluetoothProfile);
            this.mLocalNapRoleConnected = false;
        }
    }

    CachedBluetoothDevice(Context context, LocalBluetoothAdapter localBluetoothAdapter, LocalBluetoothProfileManager localBluetoothProfileManager, BluetoothDevice bluetoothDevice) {
        this.mContext = context;
        this.mLocalAdapter = localBluetoothAdapter;
        this.mProfileManager = localBluetoothProfileManager;
        this.mDevice = bluetoothDevice;
        fillData();
    }

    public void disconnect() {
        Iterator<LocalBluetoothProfile> it = this.mProfiles.iterator();
        while (it.hasNext()) {
            disconnect(it.next());
        }
        PbapServerProfile pbapProfile = this.mProfileManager.getPbapProfile();
        if (pbapProfile == null) {
            Log.d(TAG, "a2dpProfile null ");
            return;
        }
        if (pbapProfile.getConnectionStatus(this.mDevice) == 2) {
            this.mDevice.removeBond();
            pbapProfile.disconnect(this.mDevice);
        }
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile == null) {
            Log.d(TAG, "a2dpProfile null ");
            return;
        }
        if (a2dpProfile.getConnectionStatus(this.mDevice) == 2) {
            Log.d(TAG, "a2dpProfile disconnect ");
            this.mDevice.removeBond();
            a2dpProfile.disconnect(this.mDevice);
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null) {
            Log.d(TAG, "headsetProfile not  null ");
            headsetProfile.disconnect(this.mDevice);
        } else {
            Log.d(TAG, "headsetProfile is  null ");
        }
    }

    public void disconnect(LocalBluetoothProfile localBluetoothProfile) {
        if (localBluetoothProfile.disconnect(this.mDevice)) {
            Log.d(TAG, "Command sent successfully:DISCONNECT " + describe(localBluetoothProfile));
        }
    }

    public boolean connect(boolean z) {
        if (!ensurePaired()) {
            return false;
        }
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        connectWithoutResettingTimer(z);
        return true;
    }

    void onBondingDockConnect() {
        connect(false);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x003d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x001a A[SYNTHETIC] */
    private void connectWithoutResettingTimer(boolean z) {
        if (this.mProfiles.isEmpty()) {
            Log.d(TAG, "No profiles. Maybe we will connect later");
            return;
        }
        this.mIsConnectingErrorPossible = true;
        int i = 0;
        for (LocalBluetoothProfile localBluetoothProfile : this.mProfiles) {
            if (z) {
                if (localBluetoothProfile.isConnectable()) {
                    if (localBluetoothProfile.isPreferred(this.mDevice)) {
                        i++;
                        connectInt(localBluetoothProfile);
                    }
                }
            } else if (localBluetoothProfile.isAutoConnectable()) {
                if (localBluetoothProfile.isPreferred(this.mDevice)) {
                    i++;
                    connectInt(localBluetoothProfile);
                }
            }
        }
        if (i == 0) {
            connectAutoConnectableProfiles();
        }
    }

    private void connectAutoConnectableProfiles() {
        if (ensurePaired()) {
            this.mIsConnectingErrorPossible = true;
            for (LocalBluetoothProfile localBluetoothProfile : this.mProfiles) {
                if (localBluetoothProfile.isAutoConnectable()) {
                    localBluetoothProfile.setPreferred(this.mDevice, true);
                    connectInt(localBluetoothProfile);
                }
            }
        }
    }

    public void connectProfile(LocalBluetoothProfile localBluetoothProfile) {
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        this.mIsConnectingErrorPossible = true;
        connectInt(localBluetoothProfile);
        refresh();
    }

    synchronized void connectInt(LocalBluetoothProfile localBluetoothProfile) {
        if (ensurePaired()) {
            if (localBluetoothProfile.connect(this.mDevice)) {
                Log.d(TAG, "Command sent successfully:CONNECT " + describe(localBluetoothProfile));
                return;
            }
            Log.i(TAG, "Failed to connect " + localBluetoothProfile.toString() + " to " + this.mName);
        }
    }

    private boolean ensurePaired() {
        if (getBondState() != 10) {
            return true;
        }
        startPairing();
        return false;
    }

    public boolean startPairing() {
        if (this.mLocalAdapter.isDiscovering()) {
            this.mLocalAdapter.cancelDiscovery();
        }
        if (!this.mDevice.createBond()) {
            return false;
        }
        this.mConnectAfterPairing = true;
        return true;
    }

    boolean isUserInitiatedPairing() {
        return this.mConnectAfterPairing;
    }

    public void unpair() {
        BluetoothDevice bluetoothDevice;
        int bondState = getBondState();
        if (bondState == 11) {
            this.mDevice.cancelBondProcess();
        }
        if (bondState == 10 || (bluetoothDevice = this.mDevice) == null || !bluetoothDevice.removeBond()) {
            return;
        }
        Log.d(TAG, "Command sent successfully:REMOVE_BOND " + describe(null));
    }

    public int getProfileConnectionState(LocalBluetoothProfile localBluetoothProfile) {
        if (this.mProfileConnectionState == null || this.mProfileConnectionState.get(localBluetoothProfile) == null) {
            this.mProfileConnectionState.put(localBluetoothProfile, Integer.valueOf(localBluetoothProfile.getConnectionStatus(this.mDevice)));
        }
        return this.mProfileConnectionState.get(localBluetoothProfile).intValue();
    }

    public void clearProfileConnectionState() {
        Log.d(TAG, " Clearing all connection state for dev:" + this.mDevice.getName());
        Iterator<LocalBluetoothProfile> it = getProfiles().iterator();
        while (it.hasNext()) {
            this.mProfileConnectionState.put(it.next(), 0);
        }
    }

    private void fillData() {
        fetchName();
        fetchBtClass();
        updateProfiles();
        migratePhonebookPermissionChoice();
        migrateMessagePermissionChoice();
        fetchMessageRejectionCount();
        this.mVisible = false;
        dispatchAttributesChanged();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public String getName() {
        return this.mName;
    }

    void setNewName(String str) {
        if (this.mName == null) {
            this.mName = str;
            if (this.mName == null || TextUtils.isEmpty(this.mName)) {
                this.mName = this.mDevice.getAddress();
            }
            dispatchAttributesChanged();
        }
    }

    public void setName(String str) {
        if (this.mName.equals(str)) {
            return;
        }
        this.mName = str;
        this.mDevice.setAlias(str);
        dispatchAttributesChanged();
    }

    void refreshName() {
        fetchName();
        dispatchAttributesChanged();
    }

    private void fetchName() {
        this.mName = this.mDevice.getAlias();
        if (TextUtils.isEmpty(this.mName)) {
            this.mName = this.mDevice.getAddress();
        }
    }

    void refresh() {
        dispatchAttributesChanged();
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public void setVisible(boolean z) {
        if (this.mVisible != z) {
            this.mVisible = z;
            dispatchAttributesChanged();
        }
    }

    public int getBondState() {
        return this.mDevice.getBondState();
    }

    void setRssi(short s) {
        if (this.mRssi != s) {
            this.mRssi = s;
            dispatchAttributesChanged();
        }
    }

    public boolean isConnected() {
        Iterator<LocalBluetoothProfile> it = this.mProfiles.iterator();
        while (it.hasNext()) {
            if (getProfileConnectionState(it.next()) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnectedProfile(LocalBluetoothProfile localBluetoothProfile) {
        return getProfileConnectionState(localBluetoothProfile) == 2;
    }

    public boolean isBusy() {
        Iterator<LocalBluetoothProfile> it = this.mProfiles.iterator();
        while (it.hasNext()) {
            int profileConnectionState = getProfileConnectionState(it.next());
            if (profileConnectionState == 1 || profileConnectionState == 3) {
                return true;
            }
        }
        return getBondState() == 11;
    }

    private void fetchBtClass() {
        this.mBtClass = this.mDevice.getBluetoothClass();
    }

    private boolean updateProfiles() {
        ParcelUuid[] uuids;
        ParcelUuid[] uuids2 = this.mDevice.getUuids();
        if (uuids2 == null || (uuids = this.mLocalAdapter.getUuids()) == null) {
            return false;
        }
        processPhonebookAccess();
        this.mProfileManager.updateProfiles(uuids2, uuids, this.mProfiles, this.mRemovedProfiles, this.mLocalNapRoleConnected, this.mDevice);
        return true;
    }

    void refreshBtClass() {
        fetchBtClass();
        dispatchAttributesChanged();
    }

    void onUuidChanged() {
        updateProfiles();
        this.mDevice.getUuids();
        if (!this.mProfiles.isEmpty() && this.mConnectAttempted + MAX_UUID_DELAY_FOR_AUTO_CONNECT > SystemClock.elapsedRealtime()) {
            connectWithoutResettingTimer(false);
        }
        dispatchAttributesChanged();
    }

    void onBondingStateChanged(int i) {
        if (i == 10) {
            this.mProfiles.clear();
            this.mConnectAfterPairing = false;
            setPhonebookPermissionChoice(0);
            setMessagePermissionChoice(0);
            setSimPermissionChoice(0);
            this.mMessageRejectionCount = 0;
            saveMessageRejectionCount();
        }
        refresh();
        if (i == 12) {
            if (this.mConnectAfterPairing) {
                connect(false);
            }
            this.mConnectAfterPairing = false;
        }
    }

    void setBtClass(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null || this.mBtClass == bluetoothClass) {
            return;
        }
        this.mBtClass = bluetoothClass;
        dispatchAttributesChanged();
    }

    public BluetoothClass getBtClass() {
        return this.mBtClass;
    }

    public List<LocalBluetoothProfile> getProfiles() {
        return Collections.unmodifiableList(this.mProfiles);
    }

    public List<LocalBluetoothProfile> getConnectableProfiles() {
        ArrayList arrayList = new ArrayList();
        for (LocalBluetoothProfile localBluetoothProfile : this.mProfiles) {
            if (localBluetoothProfile.isConnectable()) {
                arrayList.add(localBluetoothProfile);
            }
        }
        return arrayList;
    }

    public List<LocalBluetoothProfile> getRemovedProfiles() {
        return this.mRemovedProfiles;
    }

    public void registerCallback(Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(callback);
        }
    }

    public void unregisterCallback(Callback callback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(callback);
        }
    }

    private void dispatchAttributesChanged() {
        synchronized (this.mCallbacks) {
            Iterator<Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDeviceAttributesChanged();
            }
        }
    }

    public String toString() {
        return this.mDevice.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CachedBluetoothDevice)) {
            return false;
        }
        return this.mDevice.equals(((CachedBluetoothDevice) obj).mDevice);
    }

    public int hashCode() {
        return this.mDevice.getAddress().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(CachedBluetoothDevice cachedBluetoothDevice) {
        int i = (cachedBluetoothDevice.isConnected() ? 1 : 0) - (isConnected() ? 1 : 0);
        if (i != 0) {
            return i;
        }
        int i2 = (cachedBluetoothDevice.getBondState() == 12 ? 1 : 0) - (getBondState() == 12 ? 1 : 0);
        if (i2 != 0) {
            return i2;
        }
        int i3 = (cachedBluetoothDevice.mVisible ? 1 : 0) - (this.mVisible ? 1 : 0);
        if (i3 != 0) {
            return i3;
        }
        int i4 = cachedBluetoothDevice.mRssi - this.mRssi;
        return i4 != 0 ? i4 : this.mName.compareTo(cachedBluetoothDevice.mName);
    }

    public int getPhonebookPermissionChoice() {
        int phonebookAccessPermission = this.mDevice.getPhonebookAccessPermission();
        if (phonebookAccessPermission == 1) {
            return 1;
        }
        return phonebookAccessPermission == 2 ? 2 : 0;
    }

    public void setPhonebookPermissionChoice(int i) {
        int i2 = 2;
        if (i == 1) {
            i2 = 1;
        } else if (i != 2) {
            i2 = 0;
        }
        this.mDevice.setPhonebookAccessPermission(i2);
    }

    private void migratePhonebookPermissionChoice() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("bluetooth_phonebook_permission", 0);
        if (sharedPreferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getPhonebookAccessPermission() == 0) {
                int i = sharedPreferences.getInt(this.mDevice.getAddress(), 0);
                if (i == 1) {
                    this.mDevice.setPhonebookAccessPermission(1);
                } else if (i == 2) {
                    this.mDevice.setPhonebookAccessPermission(2);
                }
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove(this.mDevice.getAddress());
            editorEdit.commit();
        }
    }

    public int getMessagePermissionChoice() {
        int messageAccessPermission = this.mDevice.getMessageAccessPermission();
        if (messageAccessPermission == 1) {
            return 1;
        }
        return messageAccessPermission == 2 ? 2 : 0;
    }

    public void setMessagePermissionChoice(int i) {
        int i2 = 2;
        if (i == 1) {
            i2 = 1;
        } else if (i != 2) {
            i2 = 0;
        }
        this.mDevice.setMessageAccessPermission(i2);
    }

    public int getSimPermissionChoice() {
        int simAccessPermission = this.mDevice.getSimAccessPermission();
        if (simAccessPermission == 1) {
            return 1;
        }
        return simAccessPermission == 2 ? 2 : 0;
    }

    void setSimPermissionChoice(int i) {
        int i2 = 2;
        if (i == 1) {
            i2 = 1;
        } else if (i != 2) {
            i2 = 0;
        }
        this.mDevice.setSimAccessPermission(i2);
    }

    private void migrateMessagePermissionChoice() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("bluetooth_message_permission", 0);
        if (sharedPreferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getMessageAccessPermission() == 0) {
                int i = sharedPreferences.getInt(this.mDevice.getAddress(), 0);
                if (i == 1) {
                    this.mDevice.setMessageAccessPermission(1);
                } else if (i == 2) {
                    this.mDevice.setMessageAccessPermission(2);
                }
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove(this.mDevice.getAddress());
            editorEdit.commit();
        }
    }

    public boolean checkAndIncreaseMessageRejectionCount() {
        if (this.mMessageRejectionCount < 2) {
            this.mMessageRejectionCount++;
            saveMessageRejectionCount();
        }
        return this.mMessageRejectionCount >= 2;
    }

    private void fetchMessageRejectionCount() {
        this.mMessageRejectionCount = this.mContext.getSharedPreferences(MESSAGE_REJECTION_COUNT_PREFS_NAME, 0).getInt(this.mDevice.getAddress(), 0);
    }

    private void saveMessageRejectionCount() {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences(MESSAGE_REJECTION_COUNT_PREFS_NAME, 0).edit();
        if (this.mMessageRejectionCount == 0) {
            editorEdit.remove(this.mDevice.getAddress());
        } else {
            editorEdit.putInt(this.mDevice.getAddress(), this.mMessageRejectionCount);
        }
        editorEdit.commit();
    }

    private void processPhonebookAccess() {
        if (this.mDevice.getBondState() == 12 && BluetoothUuid.containsAnyUuid(this.mDevice.getUuids(), PbapServerProfile.PBAB_CLIENT_UUIDS) && getPhonebookPermissionChoice() == 0) {
            if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032 || this.mDevice.getBluetoothClass().getDeviceClass() == 1028) {
                setPhonebookPermissionChoice(1);
            } else {
                setPhonebookPermissionChoice(2);
            }
        }
    }

    public int getMaxConnectionState() {
        Iterator<LocalBluetoothProfile> it = getProfiles().iterator();
        int i = 0;
        while (it.hasNext()) {
            int profileConnectionState = getProfileConnectionState(it.next());
            if (profileConnectionState > i) {
                i = profileConnectionState;
            }
        }
        return i;
    }

    public int getConnectionSummary() {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (LocalBluetoothProfile localBluetoothProfile : getProfiles()) {
            int profileConnectionState = getProfileConnectionState(localBluetoothProfile);
            switch (profileConnectionState) {
                case 0:
                    if (localBluetoothProfile.isProfileReady()) {
                        if ((localBluetoothProfile instanceof A2dpProfile) || (localBluetoothProfile instanceof A2dpSinkProfile)) {
                            z2 = true;
                        } else if ((localBluetoothProfile instanceof HeadsetProfile) || (localBluetoothProfile instanceof HfpClientProfile)) {
                            z3 = true;
                        }
                    }
                    break;
                case 1:
                case 3:
                    return Utils.getConnectionStateSummary(profileConnectionState);
                case 2:
                    z = true;
                    break;
            }
        }
        if (!z) {
            if (getBondState() == 11) {
                return R.string.bluetooth_pairing;
            }
            return 0;
        }
        if (z2 && z3) {
            return R.string.bluetooth_connected_no_headset_no_a2dp;
        }
        if (z2) {
            return R.string.bluetooth_connected_no_a2dp;
        }
        if (z3) {
            return R.string.bluetooth_connected_no_headset;
        }
        return R.string.bluetooth_connected;
    }
}
