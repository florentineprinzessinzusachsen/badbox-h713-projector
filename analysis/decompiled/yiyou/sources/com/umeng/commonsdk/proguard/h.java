package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: ShortStack.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private short[] f3980a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3981b = -1;

    public h(int i) {
        this.f3980a = new short[i];
    }

    private void d() {
        short[] sArr = this.f3980a;
        short[] sArr2 = new short[sArr.length * 2];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        this.f3980a = sArr2;
    }

    public short a() {
        short[] sArr = this.f3980a;
        int i = this.f3981b;
        this.f3981b = i - 1;
        return sArr[i];
    }

    public short b() {
        return this.f3980a[this.f3981b];
    }

    public void c() {
        this.f3981b = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.f3980a.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.f3981b) {
                sb.append(">>");
            }
            sb.append((int) this.f3980a[i]);
            if (i == this.f3981b) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }

    public void a(short s) {
        if (this.f3980a.length == this.f3981b + 1) {
            d();
        }
        short[] sArr = this.f3980a;
        int i = this.f3981b + 1;
        this.f3981b = i;
        sArr[i] = s;
    }
}
