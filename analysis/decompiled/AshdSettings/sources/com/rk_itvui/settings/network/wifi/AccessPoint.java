package com.rk_itvui.settings.network.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.ImageView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class AccessPoint implements Comparable<AccessPoint> {
    private static final int EAP_UNKNOWN = 0;
    private static final int EAP_WPA = 1;
    private static final int EAP_WPA2_WPA3 = 2;
    public static final int SECURITY_EAP = 3;
    public static final int SECURITY_EAP_SUITE_B = 6;
    public static final int SECURITY_MAX_VAL = 7;
    public static final int SECURITY_NONE = 0;
    public static final int SECURITY_OWE = 4;
    public static final int SECURITY_PSK = 2;
    public static final int SECURITY_SAE = 5;
    public static final int SECURITY_WEP = 1;
    public final String bssid;
    private CallBack mCallBack;
    private WifiConfiguration mConfig;
    private Context mContext;
    int mEapType;
    private WifiInfo mInfo;
    private int mRssi;
    private ImageView mSignal;
    private NetworkInfo.DetailedState mState;
    private ImageView mWPS;
    public boolean mWPS_enabled;
    public final int networkId;
    PskType pskType;
    public final int security;
    public final String ssid;
    boolean wpsAvailable;
    private static final int[] STATE_SECURED = {R.attr.state_encrypted};
    private static final int[] STATE_NONE = new int[0];

    enum PskType {
        UNKNOWN,
        WPA,
        WPA2,
        WPA_WPA2,
        WPA_WPA3
    }

    static int getSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        return (wifiConfiguration.wepTxKeyIndex < 0 || wifiConfiguration.wepTxKeyIndex >= wifiConfiguration.wepKeys.length || wifiConfiguration.wepKeys[wifiConfiguration.wepTxKeyIndex] == null) ? 0 : 1;
    }

    private static PskType getPskType(ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WPA-PSK");
        boolean zContains2 = scanResult.capabilities.contains("RSN-PSK");
        boolean zContains3 = scanResult.capabilities.contains("RSN-SAE");
        if (zContains2 && zContains) {
            return PskType.WPA_WPA2;
        }
        if (zContains2) {
            return PskType.WPA2;
        }
        if (zContains) {
            return PskType.WPA;
        }
        if (!zContains3) {
            return PskType.UNKNOWN;
        }
        return PskType.WPA_WPA3;
    }

    private static int getSecurity(Context context, ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WEP");
        boolean zContains2 = scanResult.capabilities.contains("SAE");
        boolean zContains3 = scanResult.capabilities.contains("PSK");
        boolean zContains4 = scanResult.capabilities.contains("EAP_SUITE_B_192");
        boolean zContains5 = scanResult.capabilities.contains("EAP");
        boolean zContains6 = scanResult.capabilities.contains("OWE");
        boolean zContains7 = scanResult.capabilities.contains("OWE_TRANSITION");
        if (zContains2 && zContains3) {
            return 2;
        }
        if (zContains7) {
            return 4;
        }
        if (zContains) {
            return 1;
        }
        if (zContains2) {
            return 5;
        }
        if (zContains3) {
            return 2;
        }
        if (zContains4) {
            return 6;
        }
        if (zContains5) {
            return 3;
        }
        return zContains6 ? 4 : 0;
    }

    public AccessPoint(Context context, String str) {
        this.pskType = PskType.UNKNOWN;
        this.wpsAvailable = false;
        this.mEapType = 0;
        this.mContext = context;
        this.ssid = str;
        this.bssid = null;
        this.security = 0;
        this.networkId = -1;
        this.mRssi = Integer.MAX_VALUE;
        this.mWPS_enabled = false;
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        this.pskType = PskType.UNKNOWN;
        this.wpsAvailable = false;
        this.mEapType = 0;
        this.mContext = context;
        this.ssid = wifiConfiguration.SSID == null ? "" : removeDoubleQuotes(wifiConfiguration.SSID);
        this.security = getSecurity(wifiConfiguration);
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
        this.mRssi = Integer.MAX_VALUE;
        this.mWPS_enabled = false;
        this.bssid = null;
    }

    public String toString() {
        return "AccessPoint{ssid='" + this.ssid + "', bssid='" + this.bssid + "', security=" + this.security + ", networkId=" + this.networkId + ", pskType=" + this.pskType + ", mConfig=" + this.mConfig + ", mRssi=" + this.mRssi + ", mWPS_enabled=" + this.mWPS_enabled + ", mInfo=" + this.mInfo + ", mState=" + this.mState + ", mSignal=" + this.mSignal + ", mWPS=" + this.mWPS + ", mContext=" + this.mContext + ", wpsAvailable=" + this.wpsAvailable + ", mCallBack=" + this.mCallBack + '}';
    }

    public AccessPoint(Context context, ScanResult scanResult) {
        this.pskType = PskType.UNKNOWN;
        boolean z = false;
        this.wpsAvailable = false;
        this.mEapType = 0;
        this.mContext = context;
        this.ssid = scanResult.SSID;
        this.security = getSecurity(context, scanResult);
        if (this.security == 2 || this.security == 5) {
            this.pskType = getPskType(scanResult);
        }
        if (this.security == 3) {
            this.mEapType = getEapType(scanResult);
        }
        this.networkId = -1;
        this.mRssi = scanResult.level;
        this.mWPS_enabled = scanResult.capabilities.contains("WPS");
        this.bssid = scanResult.BSSID;
        if (this.security != 3 && scanResult.capabilities.contains("WPS")) {
            z = true;
        }
        this.wpsAvailable = z;
    }

    private static int getEapType(ScanResult scanResult) {
        if (scanResult.capabilities.contains("RSN-EAP")) {
            return 2;
        }
        return scanResult.capabilities.contains("WPA-EAP") ? 1 : 0;
    }

    public String getSecurityString(boolean z) {
        Context context = this.mContext;
        switch (this.security) {
            case 1:
                if (z) {
                    return context.getString(R.string.wifi_security_short_wep);
                }
                return context.getString(R.string.wifi_security_wep);
            case 2:
                switch (this.pskType) {
                    case WPA:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_wpa);
                        }
                        return context.getString(R.string.wifi_security_wpa);
                    case WPA2:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_wpa2);
                        }
                        return context.getString(R.string.wifi_security_wpa2);
                    case WPA_WPA2:
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
                switch (this.mEapType) {
                    case 1:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_eap_wpa);
                        }
                        return context.getString(R.string.wifi_security_eap_wpa);
                    case 2:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_eap_wpa2_wpa3);
                        }
                        return context.getString(R.string.wifi_security_eap_wpa2_wpa3);
                    default:
                        if (z) {
                            return context.getString(R.string.wifi_security_short_eap);
                        }
                        return context.getString(R.string.wifi_security_eap);
                }
            case 4:
                if (z) {
                    return context.getString(R.string.wifi_security_short_owe);
                }
                return context.getString(R.string.wifi_security_owe);
            case 5:
                if (z) {
                    return context.getString(R.string.wifi_security_short_sae);
                }
                return context.getString(R.string.wifi_security_sae);
            case 6:
                if (z) {
                    return context.getString(R.string.wifi_security_short_eap_suiteb);
                }
                return context.getString(R.string.wifi_security_eap_suiteb);
            default:
                return z ? "" : context.getString(R.string.wifi_security_none);
        }
    }

    public void onBindView(View view) {
        this.mSignal = (ImageView) view.findViewById(R.id.signal);
        this.mWPS = (ImageView) view.findViewById(R.id.wps_enabled);
        if (!this.mWPS_enabled) {
            this.mWPS.setImageDrawable(null);
        }
        if (this.mRssi == Integer.MAX_VALUE) {
            this.mSignal.setImageDrawable(null);
        } else {
            this.mSignal.setImageResource(R.drawable.wifi_signal);
            this.mSignal.setImageState(this.security != 0 ? STATE_SECURED : STATE_NONE, true);
        }
        this.mSignal.setImageLevel(getLevel());
    }

    @Override // java.lang.Comparable
    public int compareTo(AccessPoint accessPoint) {
        if (accessPoint == null) {
            return 1;
        }
        if (this.mInfo != accessPoint.mInfo) {
            return this.mInfo != null ? -1 : 1;
        }
        if ((this.mRssi ^ accessPoint.mRssi) < 0) {
            return this.mRssi != Integer.MAX_VALUE ? -1 : 1;
        }
        if ((this.networkId ^ accessPoint.networkId) < 0) {
            return this.networkId != -1 ? -1 : 1;
        }
        int iCompareSignalLevel = WifiManager.compareSignalLevel(accessPoint.mRssi, this.mRssi);
        return iCompareSignalLevel != 0 ? iCompareSignalLevel : this.ssid.compareToIgnoreCase(accessPoint.ssid);
    }

    public boolean update(ScanResult scanResult) {
        if (!this.ssid.equals(scanResult.SSID) || this.security != getSecurity(this.mContext, scanResult)) {
            return false;
        }
        if (WifiManager.compareSignalLevel(scanResult.level, this.mRssi) > 0) {
            this.mRssi = scanResult.level;
        }
        this.mWPS_enabled = scanResult.capabilities.contains("WPS");
        refresh();
        return true;
    }

    public void update(WifiInfo wifiInfo, NetworkInfo.DetailedState detailedState) {
        if (wifiInfo != null && this.networkId != -1 && this.networkId == wifiInfo.getNetworkId()) {
            WifiInfo wifiInfo2 = this.mInfo;
            this.mRssi = wifiInfo.getRssi();
            this.mInfo = wifiInfo;
            this.mState = detailedState;
            refresh();
            return;
        }
        if (this.mInfo != null) {
            this.mInfo = null;
            this.mState = null;
            refresh();
        }
    }

    public int getLevel() {
        if (this.mRssi == Integer.MAX_VALUE) {
            return -1;
        }
        return WifiManager.calculateSignalLevel(this.mRssi, 4);
    }

    boolean isWPSEnabled() {
        return this.mWPS_enabled;
    }

    public WifiConfiguration getConfig() {
        return this.mConfig;
    }

    public WifiInfo getInfo() {
        return this.mInfo;
    }

    public NetworkInfo.DetailedState getState() {
        return this.mState;
    }

    static String removeDoubleQuotes(String str) {
        int length = str.length();
        if (length > 1 && str.charAt(0) == '\"') {
            int i = length - 1;
            if (str.charAt(i) == '\"') {
                return str.substring(1, i);
            }
        }
        return str;
    }

    public static String convertToQuotedString(String str) {
        return "\"" + str + "\"";
    }

    private void refresh() {
        if (this.mCallBack != null) {
            this.mCallBack.onCallBack();
        }
    }

    public String getSummary() {
        String string;
        if (this.mState != null) {
            return Summary.get(this.mContext, this.mState);
        }
        if (this.mRssi == Integer.MAX_VALUE) {
            return this.mContext.getString(R.string.wifi_not_in_range);
        }
        if (this.mConfig != null && this.mConfig.status == 1) {
            switch (this.mConfig.getNetworkSelectionStatus().getNetworkSelectionDisableReason()) {
                case 3:
                    return this.mContext.getString(R.string.wifi_disabled_password_failure);
                case 4:
                case 5:
                    return this.mContext.getString(R.string.wifi_disabled_network_failure);
                default:
                    return this.mContext.getString(R.string.wifi_disabled_generic);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (this.mConfig != null) {
            sb.append(this.mContext.getString(R.string.wifi_remembered));
        }
        if (this.security != 0) {
            if (sb.length() == 0) {
                string = this.mContext.getString(R.string.wifi_secured_first_item);
            } else {
                string = this.mContext.getString(R.string.wifi_secured_second_item);
            }
            sb.append(String.format(string, getSecurityString(true)));
        }
        if (this.mConfig == null && this.wpsAvailable) {
            if (sb.length() == 0) {
                sb.append(this.mContext.getString(R.string.wifi_wps_available_first_item));
            } else {
                sb.append(this.mContext.getString(R.string.wifi_wps_available_second_item));
            }
        }
        return sb.toString();
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }
}
