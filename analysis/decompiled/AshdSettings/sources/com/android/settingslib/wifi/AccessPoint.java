package com.android.settingslib.wifi;

import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.IWifiManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.Log;
import android.util.LruCache;
import com.android.settingslib.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AccessPoint implements Comparable<AccessPoint> {
    public static final int HIGHER_FREQ_24GHZ = 2500;
    public static final int HIGHER_FREQ_5GHZ = 5900;
    private static final String KEY_CONFIG = "key_config";
    private static final String KEY_NETWORKINFO = "key_networkinfo";
    private static final String KEY_PSKTYPE = "key_psktype";
    private static final String KEY_SCANRESULT = "key_scanresult";
    private static final String KEY_SCANRESULTCACHE = "key_scanresultcache";
    private static final String KEY_SECURITY = "key_security";
    private static final String KEY_SSID = "key_ssid";
    private static final String KEY_WIFIINFO = "key_wifiinfo";
    public static final int LOWER_FREQ_24GHZ = 2400;
    public static final int LOWER_FREQ_5GHZ = 4900;
    private static final int PSK_UNKNOWN = 0;
    private static final int PSK_WPA = 1;
    private static final int PSK_WPA2 = 2;
    private static final int PSK_WPA_WPA2 = 3;
    public static final int SECURITY_EAP = 3;
    public static final int SECURITY_NONE = 0;
    public static final int SECURITY_PSK = 2;
    public static final int SECURITY_WEP = 1;
    public static final int SIGNAL_LEVELS = 4;
    static final String TAG = "SettingsLib.AccessPoint";
    private String bssid;
    private AccessPointListener mAccessPointListener;
    private WifiConfiguration mConfig;
    private final Context mContext;
    private WifiInfo mInfo;
    private NetworkInfo mNetworkInfo;
    private int mRssi;
    public LruCache<String, ScanResult> mScanResultCache;
    private long mSeen;
    private Object mTag;
    private int networkId;
    private int pskType;
    private int security;
    private String ssid;

    public interface AccessPointListener {
        void onAccessPointChanged(AccessPoint accessPoint);

        void onLevelChanged(AccessPoint accessPoint);
    }

    public static String securityToString(int i, int i2) {
        if (i == 1) {
            return "WEP";
        }
        if (i != 2) {
            return i == 3 ? "EAP" : "NONE";
        }
        if (i2 == 1) {
            return "WPA";
        }
        if (i2 == 2) {
            return "WPA2";
        }
        return i2 == 3 ? "WPA_WPA2" : "PSK";
    }

    public AccessPoint(Context context, Bundle bundle) {
        this.mScanResultCache = new LruCache<>(32);
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MAX_VALUE;
        this.mSeen = 0L;
        this.mContext = context;
        this.mConfig = (WifiConfiguration) bundle.getParcelable(KEY_CONFIG);
        if (this.mConfig != null) {
            loadConfig(this.mConfig);
        }
        if (bundle.containsKey(KEY_SSID)) {
            this.ssid = bundle.getString(KEY_SSID);
        }
        if (bundle.containsKey(KEY_SECURITY)) {
            this.security = bundle.getInt(KEY_SECURITY);
        }
        if (bundle.containsKey(KEY_PSKTYPE)) {
            this.pskType = bundle.getInt(KEY_PSKTYPE);
        }
        this.mInfo = (WifiInfo) bundle.getParcelable(KEY_WIFIINFO);
        if (bundle.containsKey(KEY_NETWORKINFO)) {
            this.mNetworkInfo = (NetworkInfo) bundle.getParcelable(KEY_NETWORKINFO);
        }
        if (bundle.containsKey(KEY_SCANRESULTCACHE)) {
            ArrayList<ScanResult> parcelableArrayList = bundle.getParcelableArrayList(KEY_SCANRESULTCACHE);
            this.mScanResultCache.evictAll();
            for (ScanResult scanResult : parcelableArrayList) {
                this.mScanResultCache.put(scanResult.BSSID, scanResult);
            }
        }
        update(this.mConfig, this.mInfo, this.mNetworkInfo);
        this.mRssi = getRssi();
        this.mSeen = getSeen();
    }

    AccessPoint(Context context, ScanResult scanResult) {
        this.mScanResultCache = new LruCache<>(32);
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MAX_VALUE;
        this.mSeen = 0L;
        this.mContext = context;
        initWithScanResult(scanResult);
    }

    AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        this.mScanResultCache = new LruCache<>(32);
        this.networkId = -1;
        this.pskType = 0;
        this.mRssi = Integer.MAX_VALUE;
        this.mSeen = 0L;
        this.mContext = context;
        loadConfig(wifiConfiguration);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull AccessPoint accessPoint) {
        if (isActive() && !accessPoint.isActive()) {
            return -1;
        }
        if (!isActive() && accessPoint.isActive()) {
            return 1;
        }
        if (this.mRssi != Integer.MAX_VALUE && accessPoint.mRssi == Integer.MAX_VALUE) {
            return -1;
        }
        if (this.mRssi == Integer.MAX_VALUE && accessPoint.mRssi != Integer.MAX_VALUE) {
            return 1;
        }
        if (this.networkId != -1 && accessPoint.networkId == -1) {
            return -1;
        }
        if (this.networkId == -1 && accessPoint.networkId != -1) {
            return 1;
        }
        int iCalculateSignalLevel = WifiManager.calculateSignalLevel(accessPoint.mRssi, 4) - WifiManager.calculateSignalLevel(this.mRssi, 4);
        return iCalculateSignalLevel != 0 ? iCalculateSignalLevel : this.ssid.compareToIgnoreCase(accessPoint.ssid);
    }

    public boolean equals(Object obj) {
        return (obj instanceof AccessPoint) && compareTo((AccessPoint) obj) == 0;
    }

    public int hashCode() {
        return (this.mInfo != null ? 0 + (this.mInfo.hashCode() * 13) : 0) + (this.mRssi * 19) + (this.networkId * 23) + (this.ssid.hashCode() * 29);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccessPoint(");
        sb.append(this.ssid);
        if (isSaved()) {
            sb.append(',');
            sb.append("saved");
        }
        if (isActive()) {
            sb.append(',');
            sb.append("active");
        }
        if (isEphemeral()) {
            sb.append(',');
            sb.append("ephemeral");
        }
        if (isConnectable()) {
            sb.append(',');
            sb.append("connectable");
        }
        if (this.security != 0) {
            sb.append(',');
            sb.append(securityToString(this.security, this.pskType));
        }
        sb.append(')');
        return sb.toString();
    }

    public boolean matches(ScanResult scanResult) {
        return this.ssid.equals(scanResult.SSID) && this.security == getSecurity(scanResult);
    }

    public boolean matches(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint() && this.mConfig != null && this.mConfig.isPasspoint()) {
            return wifiConfiguration.FQDN.equals(this.mConfig.providerFriendlyName);
        }
        return this.ssid.equals(removeDoubleQuotes(wifiConfiguration.SSID)) && this.security == getSecurity(wifiConfiguration) && (this.mConfig == null || this.mConfig.shared == wifiConfiguration.shared);
    }

    public WifiConfiguration getConfig() {
        return this.mConfig;
    }

    public void clearConfig() {
        this.mConfig = null;
        this.networkId = -1;
    }

    public WifiInfo getInfo() {
        return this.mInfo;
    }

    public int getLevel() {
        if (this.mRssi == Integer.MAX_VALUE) {
            return -1;
        }
        return WifiManager.calculateSignalLevel(this.mRssi, 4);
    }

    public int getRssi() {
        int i = Integer.MIN_VALUE;
        for (ScanResult scanResult : this.mScanResultCache.snapshot().values()) {
            if (scanResult.level > i) {
                i = scanResult.level;
            }
        }
        return i;
    }

    public long getSeen() {
        long j = 0;
        for (ScanResult scanResult : this.mScanResultCache.snapshot().values()) {
            if (scanResult.timestamp > j) {
                j = scanResult.timestamp;
            }
        }
        return j;
    }

    public NetworkInfo getNetworkInfo() {
        return this.mNetworkInfo;
    }

    public int getSecurity() {
        return this.security;
    }

    public String getSecurityString(boolean z) {
        Context context = this.mContext;
        if (this.mConfig != null && this.mConfig.isPasspoint()) {
            if (z) {
                return context.getString(R.string.wifi_security_short_eap);
            }
            return context.getString(R.string.wifi_security_eap);
        }
        switch (this.security) {
            case 1:
                if (z) {
                    return context.getString(R.string.wifi_security_short_wep);
                }
                return context.getString(R.string.wifi_security_wep);
            case 2:
                switch (this.pskType) {
                    case 1:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_wpa);
                        }
                        return context.getString(R.string.wifi_security_wpa);
                    case 2:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_wpa2);
                        }
                        return context.getString(R.string.wifi_security_wpa2);
                    case 3:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_wpa_wpa2);
                        }
                        return context.getString(R.string.wifi_security_wpa_wpa2);
                    default:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_psk_generic);
                        }
                        return context.getString(R.string.wifi_security_psk_generic);
                }
            case 3:
                if (z) {
                    return context.getString(R.string.wifi_security_short_eap);
                }
                return context.getString(R.string.wifi_security_eap);
            default:
                return z ? "" : context.getString(R.string.wifi_security_none);
        }
    }

    public String getSsidStr() {
        return this.ssid;
    }

    public String getBssid() {
        return this.bssid;
    }

    public CharSequence getSsid() {
        SpannableString spannableString = new SpannableString(this.ssid);
        spannableString.setSpan(new TtsSpan.VerbatimBuilder(this.ssid).build(), 0, this.ssid.length(), 18);
        return spannableString;
    }

    public String getConfigName() {
        if (this.mConfig != null && this.mConfig.isPasspoint()) {
            return this.mConfig.providerFriendlyName;
        }
        return this.ssid;
    }

    public NetworkInfo.DetailedState getDetailedState() {
        if (this.mNetworkInfo != null) {
            return this.mNetworkInfo.getDetailedState();
        }
        Log.w(TAG, "NetworkInfo is null, cannot return detailed state");
        return null;
    }

    public String getSavedNetworkSummary() {
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration == null) {
            return "";
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        String nameForUid = packageManager.getNameForUid(1000);
        int userId = UserHandle.getUserId(wifiConfiguration.creatorUid);
        ApplicationInfo applicationInfo = null;
        if (wifiConfiguration.creatorName != null && wifiConfiguration.creatorName.equals(nameForUid)) {
            applicationInfo = this.mContext.getApplicationInfo();
        } else {
            try {
                applicationInfo = AppGlobals.getPackageManager().getApplicationInfo(wifiConfiguration.creatorName, 0, userId);
            } catch (RemoteException unused) {
            }
        }
        return (applicationInfo == null || applicationInfo.packageName.equals(this.mContext.getString(R.string.settings_package)) || applicationInfo.packageName.equals(this.mContext.getString(R.string.certinstaller_package))) ? "" : this.mContext.getString(R.string.saved_network, applicationInfo.loadLabel(packageManager));
    }

    public String getSummary() {
        return getSettingsSummary(this.mConfig);
    }

    public String getSettingsSummary() {
        return getSettingsSummary(this.mConfig);
    }

    private String getSettingsSummary(WifiConfiguration wifiConfiguration) {
        StringBuilder sb = new StringBuilder();
        if (isActive() && wifiConfiguration != null && wifiConfiguration.isPasspoint()) {
            sb.append(getSummary(this.mContext, getDetailedState(), false, wifiConfiguration.providerFriendlyName));
        } else if (isActive()) {
            sb.append(getSummary(this.mContext, getDetailedState(), this.mInfo != null && this.mInfo.isEphemeral()));
        } else if (wifiConfiguration != null && wifiConfiguration.isPasspoint()) {
            sb.append(String.format(this.mContext.getString(R.string.available_via_passpoint), wifiConfiguration.providerFriendlyName));
        } else if (wifiConfiguration != null && wifiConfiguration.hasNoInternetAccess()) {
            sb.append(this.mContext.getString(wifiConfiguration.getNetworkSelectionStatus().isNetworkPermanentlyDisabled() ? R.string.wifi_no_internet_no_reconnect : R.string.wifi_no_internet));
        } else if (wifiConfiguration != null && !wifiConfiguration.getNetworkSelectionStatus().isNetworkEnabled()) {
            switch (wifiConfiguration.getNetworkSelectionStatus().getNetworkSelectionDisableReason()) {
                case 2:
                    sb.append(this.mContext.getString(R.string.wifi_disabled_generic));
                    break;
                case 3:
                    sb.append(this.mContext.getString(R.string.wifi_disabled_password_failure));
                    break;
                case 4:
                case 5:
                    sb.append(this.mContext.getString(R.string.wifi_disabled_network_failure));
                    break;
            }
        } else if (this.mRssi == Integer.MAX_VALUE) {
            sb.append(this.mContext.getString(R.string.wifi_not_in_range));
        } else if (wifiConfiguration != null) {
            sb.append(this.mContext.getString(R.string.wifi_remembered));
        }
        if (WifiTracker.sVerboseLogging > 0) {
            if (this.mInfo != null && this.mNetworkInfo != null) {
                sb.append(" f=" + Integer.toString(this.mInfo.getFrequency()));
            }
            sb.append(" " + getVisibilityStatus());
            if (wifiConfiguration != null && !wifiConfiguration.getNetworkSelectionStatus().isNetworkEnabled()) {
                sb.append(" (" + wifiConfiguration.getNetworkSelectionStatus().getNetworkStatusString());
                if (wifiConfiguration.getNetworkSelectionStatus().getDisableTime() > 0) {
                    long jCurrentTimeMillis = (System.currentTimeMillis() - wifiConfiguration.getNetworkSelectionStatus().getDisableTime()) / 1000;
                    long j = jCurrentTimeMillis % 60;
                    long j2 = (jCurrentTimeMillis / 60) % 60;
                    long j3 = (j2 / 60) % 60;
                    sb.append(", ");
                    if (j3 > 0) {
                        sb.append(Long.toString(j3) + "h ");
                    }
                    sb.append(Long.toString(j2) + "m ");
                    sb.append(Long.toString(j) + "s ");
                }
                sb.append(")");
            }
            if (wifiConfiguration != null) {
                WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfiguration.getNetworkSelectionStatus();
                for (int i = 0; i < 10; i++) {
                    if (networkSelectionStatus.getDisableReasonCounter(i) != 0) {
                        sb.append(" " + WifiConfiguration.NetworkSelectionStatus.getNetworkDisableReasonString(i) + "=" + networkSelectionStatus.getDisableReasonCounter(i));
                    }
                }
            }
        }
        return sb.toString();
    }

    private String getVisibilityStatus() {
        String bssid;
        StringBuilder sb = new StringBuilder();
        System.currentTimeMillis();
        int i = 0;
        if (this.mInfo != null) {
            bssid = this.mInfo.getBSSID();
            if (bssid != null) {
                sb.append(" ");
                sb.append(bssid);
            }
            sb.append(" rssi=");
            sb.append(this.mInfo.getRssi());
            sb.append(" ");
            sb.append(" score=");
            sb.append(this.mInfo.score);
            sb.append(String.format(" tx=%.1f,", Double.valueOf(this.mInfo.txSuccessRate)));
            sb.append(String.format("%.1f,", Double.valueOf(this.mInfo.txRetriesRate)));
            sb.append(String.format("%.1f ", Double.valueOf(this.mInfo.txBadRate)));
            sb.append(String.format("rx=%.1f", Double.valueOf(this.mInfo.rxSuccessRate)));
        } else {
            bssid = null;
        }
        int i2 = WifiConfiguration.INVALID_RSSI;
        int i3 = WifiConfiguration.INVALID_RSSI;
        StringBuilder sb2 = null;
        int i4 = 0;
        int i5 = i2;
        int i6 = i3;
        StringBuilder sb3 = null;
        int i7 = 0;
        int i8 = 0;
        for (ScanResult scanResult : this.mScanResultCache.snapshot().values()) {
            if (scanResult.frequency >= 4900 && scanResult.frequency <= 5900) {
                i8++;
            } else if (scanResult.frequency >= 2400 && scanResult.frequency <= 2500) {
                i++;
            }
            if (scanResult.frequency >= 4900 && scanResult.frequency <= 5900) {
                if (scanResult.level > i5) {
                    i5 = scanResult.level;
                }
                if (i4 < 4) {
                    if (sb2 == null) {
                        sb2 = new StringBuilder();
                    }
                    sb2.append(" \n{");
                    sb2.append(scanResult.BSSID);
                    if (bssid != null && scanResult.BSSID.equals(bssid)) {
                        sb2.append("*");
                    }
                    sb2.append("=");
                    sb2.append(scanResult.frequency);
                    sb2.append(",");
                    sb2.append(scanResult.level);
                    sb2.append("}");
                    i4++;
                }
            } else if (scanResult.frequency >= 2400 && scanResult.frequency <= 2500) {
                if (scanResult.level > i6) {
                    i6 = scanResult.level;
                }
                if (i7 < 4) {
                    if (sb3 == null) {
                        sb3 = new StringBuilder();
                    }
                    sb3.append(" \n{");
                    sb3.append(scanResult.BSSID);
                    if (bssid != null && scanResult.BSSID.equals(bssid)) {
                        sb3.append("*");
                    }
                    sb3.append("=");
                    sb3.append(scanResult.frequency);
                    sb3.append(",");
                    sb3.append(scanResult.level);
                    sb3.append("}");
                    i7++;
                }
            }
        }
        sb.append(" [");
        if (i > 0) {
            sb.append("(");
            sb.append(i);
            sb.append(")");
            if (i7 > 4) {
                sb.append("max=");
                sb.append(i6);
                if (sb3 != null) {
                    sb.append(",");
                    sb.append(sb3.toString());
                }
            } else if (sb3 != null) {
                sb.append(sb3.toString());
            }
        }
        sb.append(";");
        if (i8 > 0) {
            sb.append("(");
            sb.append(i8);
            sb.append(")");
            if (i4 > 4) {
                sb.append("max=");
                sb.append(i5);
                if (sb2 != null) {
                    sb.append(",");
                    sb.append(sb2.toString());
                }
            } else if (sb2 != null) {
                sb.append(sb2.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isActive() {
        return (this.mNetworkInfo == null || (this.networkId == -1 && this.mNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED)) ? false : true;
    }

    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }

    public boolean isEphemeral() {
        return (this.mInfo == null || !this.mInfo.isEphemeral() || this.mNetworkInfo == null || this.mNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED) ? false : true;
    }

    public boolean isPasspoint() {
        return this.mConfig != null && this.mConfig.isPasspoint();
    }

    private boolean isInfoForThisAccessPoint(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo) {
        if (!isPasspoint() && this.networkId != -1) {
            return this.networkId == wifiInfo.getNetworkId();
        }
        if (wifiConfiguration != null) {
            return matches(wifiConfiguration);
        }
        return this.ssid.equals(removeDoubleQuotes(wifiInfo.getSSID()));
    }

    public boolean isSaved() {
        return this.networkId != -1;
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void generateOpenNetworkConfig() {
        if (this.security != 0) {
            throw new IllegalStateException();
        }
        if (this.mConfig != null) {
            return;
        }
        this.mConfig = new WifiConfiguration();
        this.mConfig.SSID = convertToQuotedString(this.ssid);
        this.mConfig.allowedKeyManagement.set(0);
    }

    void loadConfig(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            this.ssid = wifiConfiguration.providerFriendlyName;
        } else {
            this.ssid = wifiConfiguration.SSID == null ? "" : removeDoubleQuotes(wifiConfiguration.SSID);
        }
        this.bssid = wifiConfiguration.BSSID;
        this.security = getSecurity(wifiConfiguration);
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
    }

    private void initWithScanResult(ScanResult scanResult) {
        this.ssid = scanResult.SSID;
        this.bssid = scanResult.BSSID;
        this.security = getSecurity(scanResult);
        if (this.security == 2) {
            this.pskType = getPskType(scanResult);
        }
        this.mRssi = scanResult.level;
        this.mSeen = scanResult.timestamp;
    }

    public void saveWifiState(Bundle bundle) {
        if (this.ssid != null) {
            bundle.putString(KEY_SSID, getSsidStr());
        }
        bundle.putInt(KEY_SECURITY, this.security);
        bundle.putInt(KEY_PSKTYPE, this.pskType);
        if (this.mConfig != null) {
            bundle.putParcelable(KEY_CONFIG, this.mConfig);
        }
        bundle.putParcelable(KEY_WIFIINFO, this.mInfo);
        bundle.putParcelableArrayList(KEY_SCANRESULTCACHE, new ArrayList<>(this.mScanResultCache.snapshot().values()));
        if (this.mNetworkInfo != null) {
            bundle.putParcelable(KEY_NETWORKINFO, this.mNetworkInfo);
        }
    }

    public void setListener(AccessPointListener accessPointListener) {
        this.mAccessPointListener = accessPointListener;
    }

    boolean update(ScanResult scanResult) {
        if (!matches(scanResult)) {
            return false;
        }
        this.mScanResultCache.get(scanResult.BSSID);
        this.mScanResultCache.put(scanResult.BSSID, scanResult);
        int level = getLevel();
        int rssi = getRssi();
        this.mSeen = getSeen();
        this.mRssi = (getRssi() + rssi) / 2;
        int level2 = getLevel();
        if (level2 > 0 && level2 != level && this.mAccessPointListener != null) {
            this.mAccessPointListener.onLevelChanged(this);
        }
        if (this.security == 2) {
            this.pskType = getPskType(scanResult);
        }
        if (this.mAccessPointListener == null) {
            return true;
        }
        this.mAccessPointListener.onAccessPointChanged(this);
        return true;
    }

    boolean update(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo, NetworkInfo networkInfo) {
        if (wifiInfo != null && isInfoForThisAccessPoint(wifiConfiguration, wifiInfo)) {
            boolean z = this.mInfo == null;
            this.mRssi = wifiInfo.getRssi();
            this.mInfo = wifiInfo;
            this.mNetworkInfo = networkInfo;
            if (this.mAccessPointListener == null) {
                return z;
            }
            this.mAccessPointListener.onAccessPointChanged(this);
            return z;
        }
        if (this.mInfo == null) {
            return false;
        }
        this.mInfo = null;
        this.mNetworkInfo = null;
        if (this.mAccessPointListener == null) {
            return true;
        }
        this.mAccessPointListener.onAccessPointChanged(this);
        return true;
    }

    void update(WifiConfiguration wifiConfiguration) {
        this.mConfig = wifiConfiguration;
        this.networkId = wifiConfiguration.networkId;
        if (this.mAccessPointListener != null) {
            this.mAccessPointListener.onAccessPointChanged(this);
        }
    }

    void setRssi(int i) {
        this.mRssi = i;
    }

    public static String getSummary(Context context, String str, NetworkInfo.DetailedState detailedState, boolean z, String str2) {
        NetworkCapabilities networkCapabilities;
        if (detailedState == NetworkInfo.DetailedState.CONNECTED && str == null) {
            if (!TextUtils.isEmpty(str2)) {
                return String.format(context.getString(R.string.connected_via_passpoint), str2);
            }
            if (z) {
                return context.getString(R.string.connected_via_wfa);
            }
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (detailedState == NetworkInfo.DetailedState.CONNECTED) {
            try {
                networkCapabilities = connectivityManager.getNetworkCapabilities(IWifiManager.Stub.asInterface(ServiceManager.getService("wifi")).getCurrentNetwork());
            } catch (RemoteException unused) {
                networkCapabilities = null;
            }
            if (networkCapabilities != null) {
                if (networkCapabilities.hasCapability(17)) {
                    return context.getString(android.R.string.face_acquired_pan_too_extreme);
                }
                if (!networkCapabilities.hasCapability(16)) {
                    return context.getString(R.string.wifi_connected_no_internet);
                }
            }
        }
        if (detailedState == null) {
            Log.w(TAG, "state is null, returning empty summary");
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(str == null ? R.array.wifi_status : R.array.wifi_status_with_ssid);
        int iOrdinal = detailedState.ordinal();
        return (iOrdinal >= stringArray.length || stringArray[iOrdinal].length() == 0) ? "" : String.format(stringArray[iOrdinal], str);
    }

    public static String getSummary(Context context, NetworkInfo.DetailedState detailedState, boolean z) {
        return getSummary(context, null, detailedState, z, null);
    }

    public static String getSummary(Context context, NetworkInfo.DetailedState detailedState, boolean z, String str) {
        return getSummary(context, null, detailedState, z, str);
    }

    public static String convertToQuotedString(String str) {
        return "\"" + str + "\"";
    }

    private static int getPskType(ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WPA-PSK");
        boolean zContains2 = scanResult.capabilities.contains("WPA2-PSK");
        if (zContains2 && zContains) {
            return 3;
        }
        if (zContains2) {
            return 2;
        }
        if (zContains) {
            return 1;
        }
        Log.w(TAG, "Received abnormal flag string: " + scanResult.capabilities);
        return 0;
    }

    private static int getSecurity(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return 1;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        return scanResult.capabilities.contains("EAP") ? 3 : 0;
    }

    static int getSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        return wifiConfiguration.wepKeys[0] != null ? 1 : 0;
    }

    static String removeDoubleQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (length > 1 && str.charAt(0) == '\"') {
            int i = length - 1;
            if (str.charAt(i) == '\"') {
                return str.substring(1, i);
            }
        }
        return str;
    }
}
