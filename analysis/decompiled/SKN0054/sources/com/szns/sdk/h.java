package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class h {
    static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[m.values().length];
        a = iArr;
        try {
            iArr[m.ReConnectStop.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[m.ConnectError.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[m.ReadError.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[m.WriteError.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[m.HandleTunnelError.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            a[m.TunnelError.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            a[m.ConnectSuccess.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
    }
}
