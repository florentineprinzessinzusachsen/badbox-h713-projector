package com.umeng.commonsdk.proguard;

import java.io.Serializable;

/* JADX INFO: compiled from: FieldValueMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public class w implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f4007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f4009d;

    public w(byte b2, boolean z) {
        this.f4007b = b2;
        this.f4006a = false;
        this.f4008c = null;
        this.f4009d = z;
    }

    public boolean a() {
        return this.f4006a;
    }

    public String b() {
        return this.f4008c;
    }

    public boolean c() {
        return this.f4007b == 12;
    }

    public boolean d() {
        byte b2 = this.f4007b;
        return b2 == 15 || b2 == 13 || b2 == 14;
    }

    public boolean e() {
        return this.f4009d;
    }

    public w(byte b2) {
        this(b2, false);
    }

    public w(byte b2, String str) {
        this.f4007b = b2;
        this.f4006a = true;
        this.f4008c = str;
        this.f4009d = false;
    }
}
