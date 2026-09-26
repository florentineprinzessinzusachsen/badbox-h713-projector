package com.ad.proxy.b;

import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class F {
    public int a = -1;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public int g;

    public final String toString() {
        Locale locale = Locale.US;
        return "Status Code: " + this.a + "\nDNS Time: " + this.b + " ms\nConnect Time: " + this.c + " ms\nSSL Time: " + this.d + " ms\nTTFB: " + this.e + " ms\nTotal Time: " + this.f + " ms\nBytes Read: " + this.g + " bytes";
    }
}
