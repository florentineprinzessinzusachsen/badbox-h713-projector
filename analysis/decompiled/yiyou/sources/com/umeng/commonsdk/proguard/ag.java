package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TMessage.java */
/* JADX INFO: loaded from: classes.dex */
public final class ag {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3914a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f3915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3916c;

    public ag() {
        this("", (byte) 0, 0);
    }

    public boolean a(ag agVar) {
        return this.f3914a.equals(agVar.f3914a) && this.f3915b == agVar.f3915b && this.f3916c == agVar.f3916c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ag) {
            return a((ag) obj);
        }
        return false;
    }

    public String toString() {
        return "<TMessage name:'" + this.f3914a + "' type: " + ((int) this.f3915b) + " seqid:" + this.f3916c + ">";
    }

    public ag(String str, byte b2, int i) {
        this.f3914a = str;
        this.f3915b = b2;
        this.f3916c = i;
    }
}
