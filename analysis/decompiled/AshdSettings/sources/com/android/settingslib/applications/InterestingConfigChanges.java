package com.android.settingslib.applications;

import android.content.res.Configuration;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class InterestingConfigChanges {
    private final Configuration mLastConfiguration = new Configuration();
    private int mLastDensity;

    public boolean applyNewConfig(Resources resources) {
        int iUpdateFrom = this.mLastConfiguration.updateFrom(resources.getConfiguration());
        if (!(this.mLastDensity != resources.getDisplayMetrics().densityDpi) && (iUpdateFrom & 772) == 0) {
            return false;
        }
        this.mLastDensity = resources.getDisplayMetrics().densityDpi;
        return true;
    }
}
