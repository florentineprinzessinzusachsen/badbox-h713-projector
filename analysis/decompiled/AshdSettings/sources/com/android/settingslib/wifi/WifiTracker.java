package com.android.settingslib.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.android.settingslib.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class WifiTracker {
    private static final boolean DBG = false;
    private static final int NUM_SCANS_TO_CONFIRM_AP_LOSS = 3;
    private static final String TAG = "WifiTracker";
    private static final int WIFI_RESCAN_INTERVAL_MS = 10000;
    public static int sVerboseLogging;
    private ArrayList<AccessPoint> mAccessPoints;
    private final AtomicBoolean mConnected;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final IntentFilter mFilter;
    private final boolean mIncludePasspoints;
    private final boolean mIncludeSaved;
    private final boolean mIncludeScans;
    private WifiInfo mLastInfo;
    private NetworkInfo mLastNetworkInfo;
    private final WifiListener mListener;
    private final MainHandler mMainHandler;
    private WifiTrackerNetworkCallback mNetworkCallback;
    private final NetworkRequest mNetworkRequest;
    final BroadcastReceiver mReceiver;
    private boolean mRegistered;
    private boolean mSavedNetworksExist;
    private Integer mScanId;
    private HashMap<String, ScanResult> mScanResultCache;
    Scanner mScanner;
    private HashMap<String, Integer> mSeenBssids;
    private final WifiManager mWifiManager;
    private final WorkHandler mWorkHandler;

    public interface WifiListener {
        void onAccessPointsChanged();

        void onConnectedChanged();

        void onWifiStateChanged(int i);
    }

    public WifiTracker(Context context, WifiListener wifiListener, boolean z, boolean z2) {
        this(context, wifiListener, (Looper) null, z, z2);
    }

    public WifiTracker(Context context, WifiListener wifiListener, Looper looper, boolean z, boolean z2) {
        this(context, wifiListener, looper, z, z2, false);
    }

    public WifiTracker(Context context, WifiListener wifiListener, boolean z, boolean z2, boolean z3) {
        this(context, wifiListener, null, z, z2, z3);
    }

    public WifiTracker(Context context, WifiListener wifiListener, Looper looper, boolean z, boolean z2, boolean z3) {
        this(context, wifiListener, looper, z, z2, z3, (WifiManager) context.getSystemService(WifiManager.class), (ConnectivityManager) context.getSystemService(ConnectivityManager.class), Looper.myLooper());
    }

    WifiTracker(Context context, WifiListener wifiListener, Looper looper, boolean z, boolean z2, boolean z3, WifiManager wifiManager, ConnectivityManager connectivityManager, Looper looper2) {
        this.mConnected = new AtomicBoolean(false);
        this.mAccessPoints = new ArrayList<>();
        this.mSeenBssids = new HashMap<>();
        this.mScanResultCache = new HashMap<>();
        this.mScanId = 0;
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.settingslib.wifi.WifiTracker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                    WifiTracker.this.updateWifiState(intent.getIntExtra("wifi_state", 4));
                    return;
                }
                if ("android.net.wifi.SCAN_RESULTS".equals(action) || "android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action) || "android.net.wifi.LINK_CONFIGURATION_CHANGED".equals(action)) {
                    WifiTracker.this.mWorkHandler.sendEmptyMessage(0);
                    return;
                }
                if ("android.net.wifi.STATE_CHANGE".equals(action)) {
                    NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    WifiTracker.this.mConnected.set(networkInfo.isConnected());
                    WifiTracker.this.mMainHandler.sendEmptyMessage(0);
                    WifiTracker.this.mWorkHandler.sendEmptyMessage(0);
                    WifiTracker.this.mWorkHandler.obtainMessage(1, networkInfo).sendToTarget();
                }
            }
        };
        if (!z && !z2) {
            throw new IllegalArgumentException("Must include either saved or scans");
        }
        this.mContext = context;
        Looper mainLooper = looper2 == null ? Looper.getMainLooper() : looper2;
        this.mMainHandler = new MainHandler(mainLooper);
        this.mWorkHandler = new WorkHandler(looper != null ? looper : mainLooper);
        this.mWifiManager = wifiManager;
        this.mIncludeSaved = z;
        this.mIncludeScans = z2;
        this.mIncludePasspoints = z3;
        this.mListener = wifiListener;
        this.mConnectivityManager = connectivityManager;
        sVerboseLogging = this.mWifiManager.getVerboseLoggingLevel();
        this.mFilter = new IntentFilter();
        this.mFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.mFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.mFilter.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
        this.mFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        this.mFilter.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        this.mFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
    }

    public void forceUpdate() {
        updateAccessPoints();
    }

    public void forceScan() {
        if (!this.mWifiManager.isWifiEnabled() || this.mScanner == null) {
            return;
        }
        this.mScanner.forceScan();
    }

    public void pauseScanning() {
        if (this.mScanner != null) {
            this.mScanner.pause();
            this.mScanner = null;
        }
    }

    public void resumeScanning() {
        if (this.mScanner == null) {
            this.mScanner = new Scanner();
        }
        this.mWorkHandler.sendEmptyMessage(2);
        if (this.mWifiManager.isWifiEnabled()) {
            this.mScanner.resume();
        }
        this.mWorkHandler.sendEmptyMessage(0);
    }

    public void startTracking() {
        resumeScanning();
        if (this.mRegistered) {
            return;
        }
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        this.mNetworkCallback = new WifiTrackerNetworkCallback();
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback);
        this.mRegistered = true;
    }

    public void stopTracking() {
        if (this.mRegistered) {
            this.mWorkHandler.removeMessages(0);
            this.mWorkHandler.removeMessages(1);
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
            this.mRegistered = false;
        }
        pauseScanning();
    }

    public List<AccessPoint> getAccessPoints() {
        ArrayList arrayList;
        synchronized (this.mAccessPoints) {
            arrayList = new ArrayList(this.mAccessPoints);
        }
        return arrayList;
    }

    public WifiManager getManager() {
        return this.mWifiManager;
    }

    public boolean isWifiEnabled() {
        return this.mWifiManager.isWifiEnabled();
    }

    public boolean doSavedNetworksExist() {
        return this.mSavedNetworksExist;
    }

    public boolean isConnected() {
        return this.mConnected.get();
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("  - wifi tracker ------");
        Iterator<AccessPoint> it = getAccessPoints().iterator();
        while (it.hasNext()) {
            printWriter.println("  " + it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResume() {
        this.mScanResultCache.clear();
        this.mSeenBssids.clear();
        this.mScanId = 0;
    }

    private Collection<ScanResult> fetchScanResults() {
        Integer num = this.mScanId;
        this.mScanId = Integer.valueOf(this.mScanId.intValue() + 1);
        for (ScanResult scanResult : this.mWifiManager.getScanResults()) {
            if (scanResult.SSID != null && !scanResult.SSID.isEmpty()) {
                this.mScanResultCache.put(scanResult.BSSID, scanResult);
                this.mSeenBssids.put(scanResult.BSSID, this.mScanId);
            }
        }
        if (this.mScanId.intValue() > 3) {
            Integer numValueOf = Integer.valueOf(this.mScanId.intValue() - 3);
            Iterator<Map.Entry<String, Integer>> it = this.mSeenBssids.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Integer> next = it.next();
                if (next.getValue().intValue() < numValueOf.intValue()) {
                    this.mScanResultCache.get(next.getKey());
                    this.mScanResultCache.remove(next.getKey());
                    it.remove();
                }
            }
        }
        return this.mScanResultCache.values();
    }

    private WifiConfiguration getWifiConfigurationForNetworkId(int i) {
        List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            if (this.mLastInfo != null && i == wifiConfiguration.networkId && (!wifiConfiguration.selfAdded || wifiConfiguration.numAssociation != 0)) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateAccessPoints() {
        Object[] objArr;
        WifiConfiguration matchingWifiConfig;
        Object[] objArr2;
        List<AccessPoint> accessPoints = getAccessPoints();
        ArrayList<AccessPoint> arrayList = new ArrayList<>();
        Iterator<AccessPoint> it = accessPoints.iterator();
        while (it.hasNext()) {
            it.next().clearConfig();
        }
        Multimap multimap = new Multimap();
        WifiConfiguration wifiConfigurationForNetworkId = this.mLastInfo != null ? getWifiConfigurationForNetworkId(this.mLastInfo.getNetworkId()) : null;
        Collection<ScanResult> collectionFetchScanResults = fetchScanResults();
        List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
        if (configuredNetworks != null) {
            this.mSavedNetworksExist = configuredNetworks.size() != 0;
            for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                if (!wifiConfiguration.selfAdded || wifiConfiguration.numAssociation != 0) {
                    AccessPoint cachedOrCreate = getCachedOrCreate(wifiConfiguration, accessPoints);
                    if (this.mLastInfo != null && this.mLastNetworkInfo != null && !wifiConfiguration.isPasspoint()) {
                        cachedOrCreate.update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo);
                    }
                    if (this.mIncludeSaved) {
                        if (!wifiConfiguration.isPasspoint() || this.mIncludePasspoints) {
                            Iterator<ScanResult> it2 = collectionFetchScanResults.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (it2.next().SSID.equals(cachedOrCreate.getSsidStr())) {
                                        objArr2 = true;
                                        break;
                                    }
                                } else {
                                    objArr2 = false;
                                    break;
                                }
                            }
                            if (objArr2 == false) {
                                cachedOrCreate.setRssi(Integer.MAX_VALUE);
                            }
                            arrayList.add(cachedOrCreate);
                        }
                        if (!wifiConfiguration.isPasspoint()) {
                            multimap.put(cachedOrCreate.getSsidStr(), cachedOrCreate);
                        }
                    } else {
                        accessPoints.add(cachedOrCreate);
                    }
                }
            }
        }
        if (collectionFetchScanResults != null) {
            for (ScanResult scanResult : collectionFetchScanResults) {
                if (scanResult.SSID != null && scanResult.SSID.length() != 0 && !scanResult.capabilities.contains("[IBSS]")) {
                    Iterator it3 = multimap.getAll(scanResult.SSID).iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (((AccessPoint) it3.next()).update(scanResult)) {
                                objArr = true;
                                break;
                            }
                        } else {
                            objArr = false;
                            break;
                        }
                    }
                    if (objArr == false && this.mIncludeScans) {
                        AccessPoint cachedOrCreate2 = getCachedOrCreate(scanResult, accessPoints);
                        if (this.mLastInfo != null && this.mLastNetworkInfo != null) {
                            cachedOrCreate2.update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo);
                        }
                        if (scanResult.isPasspointNetwork() && (matchingWifiConfig = this.mWifiManager.getMatchingWifiConfig(scanResult)) != null) {
                            cachedOrCreate2.update(matchingWifiConfig);
                        }
                        if (this.mLastInfo != null && this.mLastInfo.getBSSID() != null && this.mLastInfo.getBSSID().equals(scanResult.BSSID) && wifiConfigurationForNetworkId != null && wifiConfigurationForNetworkId.isPasspoint()) {
                            cachedOrCreate2.update(wifiConfigurationForNetworkId);
                        }
                        arrayList.add(cachedOrCreate2);
                        multimap.put(cachedOrCreate2.getSsidStr(), cachedOrCreate2);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        for (AccessPoint accessPoint : this.mAccessPoints) {
            if (accessPoint.getSsid() != null) {
                String ssidStr = accessPoint.getSsidStr();
                for (AccessPoint accessPoint2 : arrayList) {
                    if (accessPoint2.getSsid() != null && accessPoint2.getSsid().equals(ssidStr)) {
                        break;
                    }
                }
            }
        }
        this.mAccessPoints = arrayList;
        this.mMainHandler.sendEmptyMessage(2);
    }

    private AccessPoint getCachedOrCreate(ScanResult scanResult, List<AccessPoint> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).matches(scanResult)) {
                AccessPoint accessPointRemove = list.remove(i);
                accessPointRemove.update(scanResult);
                return accessPointRemove;
            }
        }
        return new AccessPoint(this.mContext, scanResult);
    }

    private AccessPoint getCachedOrCreate(WifiConfiguration wifiConfiguration, List<AccessPoint> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).matches(wifiConfiguration)) {
                AccessPoint accessPointRemove = list.remove(i);
                accessPointRemove.loadConfig(wifiConfiguration);
                return accessPointRemove;
            }
        }
        return new AccessPoint(this.mContext, wifiConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNetworkInfo(NetworkInfo networkInfo) {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mMainHandler.sendEmptyMessage(4);
            return;
        }
        if (networkInfo != null && networkInfo.getDetailedState() == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            this.mMainHandler.sendEmptyMessage(4);
        } else {
            this.mMainHandler.sendEmptyMessage(3);
        }
        if (networkInfo != null) {
            this.mLastNetworkInfo = networkInfo;
        }
        this.mLastInfo = this.mWifiManager.getConnectionInfo();
        WifiConfiguration wifiConfigurationForNetworkId = this.mLastInfo != null ? getWifiConfigurationForNetworkId(this.mLastInfo.getNetworkId()) : null;
        boolean z = false;
        for (int size = this.mAccessPoints.size() - 1; size >= 0; size--) {
            if (this.mAccessPoints.get(size).update(wifiConfigurationForNetworkId, this.mLastInfo, this.mLastNetworkInfo)) {
                z = true;
            }
        }
        if (z) {
            synchronized (this.mAccessPoints) {
                Collections.sort(this.mAccessPoints);
            }
            this.mMainHandler.sendEmptyMessage(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWifiState(int i) {
        this.mWorkHandler.obtainMessage(3, i, 0).sendToTarget();
    }

    public static List<AccessPoint> getCurrentAccessPoints(Context context, boolean z, boolean z2, boolean z3) {
        WifiTracker wifiTracker = new WifiTracker(context, null, null, z, z2, z3);
        wifiTracker.forceUpdate();
        return wifiTracker.getAccessPoints();
    }

    private final class WifiTrackerNetworkCallback extends ConnectivityManager.NetworkCallback {
        private WifiTrackerNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            if (network.equals(WifiTracker.this.mWifiManager.getCurrentNetwork())) {
                WifiTracker.this.mWorkHandler.sendEmptyMessage(1);
            }
        }
    }

    private final class MainHandler extends Handler {
        private static final int MSG_ACCESS_POINT_CHANGED = 2;
        private static final int MSG_CONNECTED_CHANGED = 0;
        private static final int MSG_PAUSE_SCANNING = 4;
        private static final int MSG_RESUME_SCANNING = 3;
        private static final int MSG_WIFI_STATE_CHANGED = 1;

        public MainHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (WifiTracker.this.mListener == null) {
            }
            switch (message.what) {
                case 0:
                    WifiTracker.this.mListener.onConnectedChanged();
                    break;
                case 1:
                    WifiTracker.this.mListener.onWifiStateChanged(message.arg1);
                    break;
                case 2:
                    WifiTracker.this.mListener.onAccessPointsChanged();
                    break;
                case 3:
                    if (WifiTracker.this.mScanner != null) {
                        WifiTracker.this.mScanner.resume();
                    }
                    break;
                case 4:
                    if (WifiTracker.this.mScanner != null) {
                        WifiTracker.this.mScanner.pause();
                    }
                    break;
            }
        }
    }

    private final class WorkHandler extends Handler {
        private static final int MSG_RESUME = 2;
        private static final int MSG_UPDATE_ACCESS_POINTS = 0;
        private static final int MSG_UPDATE_NETWORK_INFO = 1;
        private static final int MSG_UPDATE_WIFI_STATE = 3;

        public WorkHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    WifiTracker.this.updateAccessPoints();
                    break;
                case 1:
                    WifiTracker.this.updateNetworkInfo((NetworkInfo) message.obj);
                    break;
                case 2:
                    WifiTracker.this.handleResume();
                    break;
                case 3:
                    if (message.arg1 != 3) {
                        WifiTracker.this.mLastInfo = null;
                        WifiTracker.this.mLastNetworkInfo = null;
                        if (WifiTracker.this.mScanner != null) {
                            WifiTracker.this.mScanner.pause();
                        }
                    } else if (WifiTracker.this.mScanner != null) {
                        WifiTracker.this.mScanner.resume();
                    }
                    WifiTracker.this.mMainHandler.obtainMessage(1, message.arg1, 0).sendToTarget();
                    break;
            }
        }
    }

    class Scanner extends Handler {
        static final int MSG_SCAN = 0;
        private int mRetry = 0;

        Scanner() {
        }

        void resume() {
            if (hasMessages(0)) {
                return;
            }
            sendEmptyMessage(0);
        }

        void forceScan() {
            removeMessages(0);
            sendEmptyMessage(0);
        }

        void pause() {
            this.mRetry = 0;
            removeMessages(0);
        }

        boolean isScanning() {
            return hasMessages(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            if (WifiTracker.this.mWifiManager.startScan()) {
                this.mRetry = 0;
            } else {
                int i = this.mRetry + 1;
                this.mRetry = i;
                if (i >= 3) {
                    this.mRetry = 0;
                    if (WifiTracker.this.mContext != null) {
                        Toast.makeText(WifiTracker.this.mContext, R.string.wifi_fail_to_scan, 1).show();
                        return;
                    }
                    return;
                }
            }
            sendEmptyMessageDelayed(0, 10000L);
        }
    }

    private static class Multimap<K, V> {
        private final HashMap<K, List<V>> store;

        private Multimap() {
            this.store = new HashMap<>();
        }

        List<V> getAll(K k) {
            List<V> list = this.store.get(k);
            return list != null ? list : Collections.emptyList();
        }

        void put(K k, V v) {
            List<V> arrayList = this.store.get(k);
            if (arrayList == null) {
                arrayList = new ArrayList<>(3);
                this.store.put(k, arrayList);
            }
            arrayList.add(v);
        }
    }
}
