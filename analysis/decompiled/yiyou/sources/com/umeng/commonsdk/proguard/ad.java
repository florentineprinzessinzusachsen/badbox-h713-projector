package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TField.java */
/* JADX INFO: loaded from: classes.dex */
public class ad {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3906a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f3907b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final short f3908c;

    public ad() {
        this("", (byte) 0, (short) 0);
    }

    public boolean a(ad adVar) {
        return this.f3907b == adVar.f3907b && this.f3908c == adVar.f3908c;
    }

    public String toString() {
        return "<TField name:'" + this.f3906a + "' type:" + ((int) this.f3907b) + " field-id:" + ((int) this.f3908c) + ">";
    }

    public ad(String str, byte b2, short s) {
        this.f3906a = str;
        this.f3907b = b2;
        this.f3908c = s;
    }
}
