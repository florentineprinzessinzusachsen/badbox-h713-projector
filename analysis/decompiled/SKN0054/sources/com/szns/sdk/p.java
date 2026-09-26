package com.szns.sdk;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    public String a;
    public int b;
    public String c;
    public int d;
    public String e;

    public p(String str, int i, String str2, int i2) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = i2;
    }

    public final String toString() {
        return "PortMap{remoteHost='" + this.a + "', remotePort=" + this.b + ", localHost='" + this.c + "', localPort=" + this.d + ", key='" + this.e + "'}";
    }
}
