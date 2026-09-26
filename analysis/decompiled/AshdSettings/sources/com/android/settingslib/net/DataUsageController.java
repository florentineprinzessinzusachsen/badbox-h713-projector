package com.android.settingslib.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetworkStatsService;
import android.net.INetworkStatsSession;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkStatsHistory;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import com.android.settingslib.R;
import com.rk_itvui.settings.storeinfo.Storage;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageController {
    private static final int FIELDS = 10;
    private Callback mCallback;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private NetworkNameProvider mNetworkController;
    private final NetworkPolicyManager mPolicyManager;
    private INetworkStatsSession mSession;
    private final INetworkStatsService mStatsService = INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
    private final TelephonyManager mTelephonyManager;
    private static final String TAG = "DataUsageController";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final StringBuilder PERIOD_BUILDER = new StringBuilder(50);
    private static final Formatter PERIOD_FORMATTER = new Formatter(PERIOD_BUILDER, Locale.getDefault());

    public interface Callback {
        void onMobileDataEnabled(boolean z);
    }

    public static class DataUsageInfo {
        public String carrier;
        public long limitLevel;
        public String period;
        public long startDate;
        public long usageLevel;
        public long warningLevel;
    }

    public interface NetworkNameProvider {
        String getMobileDataNetworkName();
    }

    public DataUsageController(Context context) {
        this.mContext = context;
        this.mTelephonyManager = TelephonyManager.from(context);
        this.mConnectivityManager = ConnectivityManager.from(context);
        this.mPolicyManager = NetworkPolicyManager.from(this.mContext);
    }

    public void setNetworkController(NetworkNameProvider networkNameProvider) {
        this.mNetworkController = networkNameProvider;
    }

    public long getDefaultWarningLevel() {
        return ((long) this.mContext.getResources().getInteger(R.integer.default_data_warning_level_mb)) * Storage.ONE_MB_SIZE;
    }

    private INetworkStatsSession getSession() {
        if (this.mSession == null) {
            try {
                this.mSession = this.mStatsService.openSession();
            } catch (RemoteException e) {
                Log.w(TAG, "Failed to open stats session", e);
            } catch (RuntimeException e2) {
                Log.w(TAG, "Failed to open stats session", e2);
            }
        }
        return this.mSession;
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    private DataUsageInfo warn(String str) {
        Log.w(TAG, "Failed to get data usage, " + str);
        return null;
    }

    private static Time addMonth(Time time, int i) {
        Time time2 = new Time(time);
        time2.set(time.monthDay, time.month + i, time.year);
        time2.normalize(false);
        return time2;
    }

    public DataUsageInfo getDataUsageInfo() {
        String activeSubscriberId = getActiveSubscriberId(this.mContext);
        if (activeSubscriberId == null) {
            return warn("no subscriber id");
        }
        return getDataUsageInfo(NetworkTemplate.normalize(NetworkTemplate.buildTemplateMobileAll(activeSubscriberId), this.mTelephonyManager.getMergedSubscriberIds()));
    }

    public DataUsageInfo getWifiDataUsageInfo() {
        return getDataUsageInfo(NetworkTemplate.buildTemplateWifiWildcard());
    }

    public DataUsageInfo getDataUsageInfo(NetworkTemplate networkTemplate) {
        long j;
        long j2;
        long millis;
        long millis2;
        INetworkStatsSession session = getSession();
        if (session == null) {
            return warn("no stats session");
        }
        NetworkPolicy networkPolicyFindNetworkPolicy = findNetworkPolicy(networkTemplate);
        try {
            NetworkStatsHistory historyForNetwork = session.getHistoryForNetwork(networkTemplate, 10);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (networkPolicyFindNetworkPolicy == null || networkPolicyFindNetworkPolicy.cycleDay <= 0) {
                j = jCurrentTimeMillis - 2419200000L;
                j2 = jCurrentTimeMillis;
            } else {
                if (DEBUG) {
                    Log.d(TAG, "Cycle day=" + networkPolicyFindNetworkPolicy.cycleDay + " tz=" + networkPolicyFindNetworkPolicy.cycleTimezone);
                }
                Time time = new Time(networkPolicyFindNetworkPolicy.cycleTimezone);
                time.setToNow();
                Time time2 = new Time(time);
                time2.set(networkPolicyFindNetworkPolicy.cycleDay, time2.month, time2.year);
                time2.normalize(false);
                if (time.after(time2)) {
                    millis = time2.toMillis(false);
                    millis2 = addMonth(time2, 1).toMillis(false);
                } else {
                    millis = addMonth(time2, -1).toMillis(false);
                    millis2 = time2.toMillis(false);
                }
                j2 = millis2;
                j = millis;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            long j3 = j2;
            NetworkStatsHistory.Entry values = historyForNetwork.getValues(j, j2, jCurrentTimeMillis, (NetworkStatsHistory.Entry) null);
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            if (DEBUG) {
                Log.d(TAG, String.format("history call from %s to %s now=%s took %sms: %s", new Date(j), new Date(j3), new Date(jCurrentTimeMillis), Long.valueOf(jCurrentTimeMillis3 - jCurrentTimeMillis2), historyEntryToString(values)));
            }
            if (values == null) {
                return warn("no entry data");
            }
            long j4 = values.rxBytes + values.txBytes;
            DataUsageInfo dataUsageInfo = new DataUsageInfo();
            dataUsageInfo.startDate = j;
            dataUsageInfo.usageLevel = j4;
            dataUsageInfo.period = formatDateRange(j, j3);
            if (networkPolicyFindNetworkPolicy != null) {
                dataUsageInfo.limitLevel = networkPolicyFindNetworkPolicy.limitBytes > 0 ? networkPolicyFindNetworkPolicy.limitBytes : 0L;
                dataUsageInfo.warningLevel = networkPolicyFindNetworkPolicy.warningBytes > 0 ? networkPolicyFindNetworkPolicy.warningBytes : 0L;
            } else {
                dataUsageInfo.warningLevel = getDefaultWarningLevel();
            }
            if (dataUsageInfo != null && this.mNetworkController != null) {
                dataUsageInfo.carrier = this.mNetworkController.getMobileDataNetworkName();
            }
            return dataUsageInfo;
        } catch (RemoteException unused) {
            return warn("remote call failed");
        }
    }

    private NetworkPolicy findNetworkPolicy(NetworkTemplate networkTemplate) {
        NetworkPolicy[] networkPolicies;
        if (this.mPolicyManager == null || networkTemplate == null || (networkPolicies = this.mPolicyManager.getNetworkPolicies()) == null) {
            return null;
        }
        for (NetworkPolicy networkPolicy : networkPolicies) {
            if (networkPolicy != null && networkTemplate.equals(networkPolicy.template)) {
                return networkPolicy;
            }
        }
        return null;
    }

    private static String historyEntryToString(NetworkStatsHistory.Entry entry) {
        if (entry == null) {
            return null;
        }
        return "Entry[bucketDuration=" + entry.bucketDuration + ",bucketStart=" + entry.bucketStart + ",activeTime=" + entry.activeTime + ",rxBytes=" + entry.rxBytes + ",rxPackets=" + entry.rxPackets + ",txBytes=" + entry.txBytes + ",txPackets=" + entry.txPackets + ",operations=" + entry.operations + ']';
    }

    public void setMobileDataEnabled(boolean z) {
        Log.d(TAG, "setMobileDataEnabled: enabled=" + z);
        this.mTelephonyManager.setDataEnabled(z);
        if (this.mCallback != null) {
            this.mCallback.onMobileDataEnabled(z);
        }
    }

    public boolean isMobileDataSupported() {
        return this.mConnectivityManager.isNetworkSupported(0) && this.mTelephonyManager.getSimState() == 5;
    }

    public boolean isMobileDataEnabled() {
        return this.mTelephonyManager.getDataEnabled();
    }

    private static String getActiveSubscriberId(Context context) {
        return TelephonyManager.from(context).getSubscriberId(SubscriptionManager.getDefaultDataSubscriptionId());
    }

    private String formatDateRange(long j, long j2) {
        String string;
        synchronized (PERIOD_BUILDER) {
            PERIOD_BUILDER.setLength(0);
            string = DateUtils.formatDateRange(this.mContext, PERIOD_FORMATTER, j, j2, 65552, null).toString();
        }
        return string;
    }
}
