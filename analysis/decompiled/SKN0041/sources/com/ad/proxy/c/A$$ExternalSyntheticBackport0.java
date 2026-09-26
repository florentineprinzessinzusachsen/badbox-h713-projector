package com.ad.proxy.c;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class A$$ExternalSyntheticBackport0 {
    public static /* synthetic */ long m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (Long.MIN_VALUE ^ j2) ? j : j - j2;
        }
        if (j >= 0) {
            return j % j2;
        }
        long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
        if ((j3 ^ Long.MIN_VALUE) < (Long.MIN_VALUE ^ j2)) {
            j2 = 0;
        }
        return j3 - j2;
    }
}
