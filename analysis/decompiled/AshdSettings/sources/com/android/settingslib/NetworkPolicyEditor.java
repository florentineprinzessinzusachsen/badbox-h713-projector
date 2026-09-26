package com.android.settingslib;

import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.net.wifi.WifiInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.text.format.Time;
import com.android.internal.util.Preconditions;
import com.google.android.collect.Lists;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetworkPolicyEditor {
    public static final boolean ENABLE_SPLIT_POLICIES = false;
    private ArrayList<NetworkPolicy> mPolicies = Lists.newArrayList();
    private NetworkPolicyManager mPolicyManager;

    public NetworkPolicyEditor(NetworkPolicyManager networkPolicyManager) {
        this.mPolicyManager = (NetworkPolicyManager) Preconditions.checkNotNull(networkPolicyManager);
    }

    public void read() {
        NetworkPolicy[] networkPolicies = this.mPolicyManager.getNetworkPolicies();
        this.mPolicies.clear();
        boolean z = false;
        for (NetworkPolicy networkPolicy : networkPolicies) {
            if (networkPolicy.limitBytes < -1) {
                networkPolicy.limitBytes = -1L;
                z = true;
            }
            if (networkPolicy.warningBytes < -1) {
                networkPolicy.warningBytes = -1L;
                z = true;
            }
            this.mPolicies.add(networkPolicy);
        }
        if (z) {
            writeAsync();
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.settingslib.NetworkPolicyEditor$1] */
    public void writeAsync() {
        final NetworkPolicy[] networkPolicyArr = (NetworkPolicy[]) this.mPolicies.toArray(new NetworkPolicy[this.mPolicies.size()]);
        new AsyncTask<Void, Void, Void>() { // from class: com.android.settingslib.NetworkPolicyEditor.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                NetworkPolicyEditor.this.write(networkPolicyArr);
                return null;
            }
        }.execute(new Void[0]);
    }

    public void write(NetworkPolicy[] networkPolicyArr) {
        this.mPolicyManager.setNetworkPolicies(networkPolicyArr);
    }

    public boolean hasLimitedPolicy(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        return (policy == null || policy.limitBytes == -1) ? false : true;
    }

    public NetworkPolicy getOrCreatePolicy(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        if (policy != null) {
            return policy;
        }
        NetworkPolicy networkPolicyBuildDefaultPolicy = buildDefaultPolicy(networkTemplate);
        this.mPolicies.add(networkPolicyBuildDefaultPolicy);
        return networkPolicyBuildDefaultPolicy;
    }

    public NetworkPolicy getPolicy(NetworkTemplate networkTemplate) {
        for (NetworkPolicy networkPolicy : this.mPolicies) {
            if (networkPolicy.template.equals(networkTemplate)) {
                return networkPolicy;
            }
        }
        return null;
    }

    public NetworkPolicy getPolicyMaybeUnquoted(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        return policy != null ? policy : getPolicy(buildUnquotedNetworkTemplate(networkTemplate));
    }

    @Deprecated
    private static NetworkPolicy buildDefaultPolicy(NetworkTemplate networkTemplate) {
        boolean z;
        String str;
        int i;
        if (networkTemplate.getMatchRule() == 4) {
            z = false;
            i = -1;
            str = "UTC";
        } else {
            Time time = new Time();
            time.setToNow();
            int i2 = time.monthDay;
            z = true;
            str = time.timezone;
            i = i2;
        }
        return new NetworkPolicy(networkTemplate, i, str, -1L, -1L, -1L, -1L, z, true);
    }

    public int getPolicyCycleDay(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        if (policy != null) {
            return policy.cycleDay;
        }
        return -1;
    }

    public void setPolicyCycleDay(NetworkTemplate networkTemplate, int i, String str) {
        NetworkPolicy orCreatePolicy = getOrCreatePolicy(networkTemplate);
        orCreatePolicy.cycleDay = i;
        orCreatePolicy.cycleTimezone = str;
        orCreatePolicy.inferred = false;
        orCreatePolicy.clearSnooze();
        writeAsync();
    }

    public long getPolicyWarningBytes(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        if (policy != null) {
            return policy.warningBytes;
        }
        return -1L;
    }

    public void setPolicyWarningBytes(NetworkTemplate networkTemplate, long j) {
        NetworkPolicy orCreatePolicy = getOrCreatePolicy(networkTemplate);
        orCreatePolicy.warningBytes = j;
        orCreatePolicy.inferred = false;
        orCreatePolicy.clearSnooze();
        writeAsync();
    }

    public long getPolicyLimitBytes(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        if (policy != null) {
            return policy.limitBytes;
        }
        return -1L;
    }

    public void setPolicyLimitBytes(NetworkTemplate networkTemplate, long j) {
        NetworkPolicy orCreatePolicy = getOrCreatePolicy(networkTemplate);
        orCreatePolicy.limitBytes = j;
        orCreatePolicy.inferred = false;
        orCreatePolicy.clearSnooze();
        writeAsync();
    }

    public boolean getPolicyMetered(NetworkTemplate networkTemplate) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        if (policy != null) {
            return policy.metered;
        }
        return false;
    }

    public void setPolicyMetered(NetworkTemplate networkTemplate, boolean z) {
        NetworkPolicy policy = getPolicy(networkTemplate);
        boolean z2 = false;
        if (z) {
            if (policy == null) {
                NetworkPolicy networkPolicyBuildDefaultPolicy = buildDefaultPolicy(networkTemplate);
                networkPolicyBuildDefaultPolicy.metered = true;
                networkPolicyBuildDefaultPolicy.inferred = false;
                this.mPolicies.add(networkPolicyBuildDefaultPolicy);
            } else if (!policy.metered) {
                policy.metered = true;
                policy.inferred = false;
            }
            z2 = true;
        } else if (policy != null && policy.metered) {
            policy.metered = false;
            policy.inferred = false;
            z2 = true;
        }
        NetworkPolicy policy2 = getPolicy(buildUnquotedNetworkTemplate(networkTemplate));
        if (policy2 != null) {
            this.mPolicies.remove(policy2);
            z2 = true;
        }
        if (z2) {
            writeAsync();
        }
    }

    private static NetworkTemplate buildUnquotedNetworkTemplate(NetworkTemplate networkTemplate) {
        if (networkTemplate == null) {
            return null;
        }
        String networkId = networkTemplate.getNetworkId();
        String strRemoveDoubleQuotes = WifiInfo.removeDoubleQuotes(networkId);
        if (TextUtils.equals(strRemoveDoubleQuotes, networkId)) {
            return null;
        }
        return new NetworkTemplate(networkTemplate.getMatchRule(), networkTemplate.getSubscriberId(), strRemoveDoubleQuotes);
    }
}
