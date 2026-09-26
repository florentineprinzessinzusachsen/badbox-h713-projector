package com.umeng.commonsdk.stateless;

import android.content.Context;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;

/* JADX INFO: compiled from: UMSLEnvelopeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4043e;
    private int j;
    private int k;
    private byte[] l;
    private byte[] m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[] f4039a = {0, 0, 0, 0, 0, 0, 0, 0};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4040b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4041c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4042d = "1.0";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private byte[] f4044f = null;
    private byte[] g = null;
    private byte[] h = null;
    private int i = 0;
    private boolean n = false;

    private c(byte[] bArr, String str, byte[] bArr2) throws Exception {
        this.f4043e = null;
        this.j = 0;
        this.k = 0;
        this.l = null;
        this.m = null;
        if (bArr == null || bArr.length == 0) {
            throw new Exception("entity is null or empty");
        }
        this.f4043e = str;
        this.k = bArr.length;
        this.l = f.a(bArr);
        this.j = (int) (System.currentTimeMillis() / 1000);
        this.m = bArr2;
    }

    public static c a(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            String deviceId = DeviceConfig.getDeviceId(context);
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("[stateless] build envelope, raw is  ");
            sb.append(bArr == null);
            sb.append("m app key is ");
            sb.append(str);
            sb.append("device id is ");
            sb.append(deviceId);
            sb.append(", mac is ");
            sb.append(mac);
            objArr[0] = sb.toString();
            ULog.i("walle", objArr);
            c cVar = new c(bArr, str, (deviceId + mac).getBytes());
            cVar.a();
            return cVar;
        } catch (Exception e2) {
            ULog.i("walle", "[stateless] build envelope, e is " + e2.getMessage());
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static c b(Context context, String str, byte[] bArr) {
        try {
            String mac = DeviceConfig.getMac(context);
            c cVar = new c(bArr, str, (DeviceConfig.getDeviceId(context) + mac).getBytes());
            cVar.a(true);
            cVar.a();
            return cVar;
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    private byte[] c() {
        return a(this.f4039a, (int) (System.currentTimeMillis() / 1000));
    }

    private byte[] d() {
        return f.b((f.c(this.f4044f) + this.i + this.j + this.k + f.c(this.g)).getBytes());
    }

    public byte[] b() {
        b bVar = new b();
        bVar.a(this.f4042d);
        bVar.b(this.f4043e);
        bVar.c(f.c(this.f4044f));
        bVar.a(this.i);
        bVar.b(this.j);
        bVar.c(this.k);
        bVar.a(this.l);
        bVar.d(this.n ? 1 : 0);
        bVar.d(f.c(this.g));
        bVar.e(f.c(this.h));
        try {
            return new s().a(bVar);
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(boolean z) {
        this.n = z;
    }

    public void a() {
        if (this.f4044f == null) {
            this.f4044f = c();
        }
        if (this.n) {
            byte[] bArr = new byte[16];
            try {
                System.arraycopy(this.f4044f, 1, bArr, 0, 16);
                this.l = f.a(this.l, bArr);
            } catch (Exception unused) {
            }
        }
        this.g = a(this.f4044f, this.j);
        this.h = d();
    }

    private byte[] a(byte[] bArr, int i) {
        byte[] bArrB = f.b(this.m);
        byte[] bArrB2 = f.b(this.l);
        int length = bArrB.length;
        byte[] bArr2 = new byte[length * 2];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            bArr2[i3] = bArrB2[i2];
            bArr2[i3 + 1] = bArrB[i2];
        }
        for (int i4 = 0; i4 < 2; i4++) {
            bArr2[i4] = bArr[i4];
            bArr2[(bArr2.length - i4) - 1] = bArr[(bArr.length - i4) - 1];
        }
        byte[] bArr3 = {(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) (i >>> 24)};
        for (int i5 = 0; i5 < bArr2.length; i5++) {
            bArr2[i5] = (byte) (bArr2[i5] ^ bArr3[i5 % 4]);
        }
        return bArr2;
    }
}
