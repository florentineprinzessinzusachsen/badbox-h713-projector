package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: TSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ByteArrayOutputStream f3995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final au f3996b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ai f3997c;

    public s() {
        this(new ac.a());
    }

    public byte[] a(j jVar) {
        this.f3995a.reset();
        jVar.write(this.f3997c);
        return this.f3995a.toByteArray();
    }

    public String b(j jVar) {
        return new String(a(jVar));
    }

    public s(ak akVar) {
        this.f3995a = new ByteArrayOutputStream();
        this.f3996b = new au(this.f3995a);
        this.f3997c = akVar.a(this.f3996b);
    }

    public String a(j jVar, String str) throws p {
        try {
            return new String(a(jVar), str);
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }
}
