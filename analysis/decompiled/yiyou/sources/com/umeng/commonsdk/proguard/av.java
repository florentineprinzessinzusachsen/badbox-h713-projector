package com.umeng.commonsdk.proguard;

/* JADX INFO: compiled from: TMemoryInputTransport.java */
/* JADX INFO: loaded from: classes.dex */
public final class av extends aw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private byte[] f3939a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3940b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3941c;

    public av() {
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public boolean a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void b() {
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void b(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void c() {
    }

    public void c(byte[] bArr, int i, int i2) {
        this.f3939a = bArr;
        this.f3940b = i;
        this.f3941c = i + i2;
    }

    public void e() {
        this.f3939a = null;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public byte[] f() {
        return this.f3939a;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public int g() {
        return this.f3940b;
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public int h() {
        return this.f3941c - this.f3940b;
    }

    public av(byte[] bArr) {
        a(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public int a(byte[] bArr, int i, int i2) {
        int iH = h();
        if (i2 > iH) {
            i2 = iH;
        }
        if (i2 > 0) {
            System.arraycopy(this.f3939a, this.f3940b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    public av(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    @Override // com.umeng.commonsdk.proguard.aw
    public void a(int i) {
        this.f3940b += i;
    }
}
