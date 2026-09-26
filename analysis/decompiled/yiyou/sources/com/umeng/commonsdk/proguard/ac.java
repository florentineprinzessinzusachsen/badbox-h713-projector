package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TCompactProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class ac extends ai {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final an f3893d = new an("");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final ad f3894e = new ad("", (byte) 0, 0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final byte[] f3895f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    byte[] f3896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    byte[] f3897b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    byte[] f3898c;
    private h m;
    private short n;
    private ad o;
    private Boolean p;
    private final long q;
    private byte[] r;

    /* JADX INFO: compiled from: TCompactProtocol.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final byte f3900a = 1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final byte f3901b = 2;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final byte f3902c = 3;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final byte f3903d = 4;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final byte f3904e = 5;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final byte f3905f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b() {
        }
    }

    static {
        byte[] bArr = f3895f;
        bArr[0] = 0;
        bArr[2] = 1;
        bArr[3] = 3;
        bArr[6] = 4;
        bArr[8] = 5;
        bArr[10] = 6;
        bArr[4] = 7;
        bArr[11] = 8;
        bArr[15] = 9;
        bArr[14] = 10;
        bArr[13] = 11;
        bArr[12] = 12;
    }

    public ac(aw awVar, long j2) {
        super(awVar);
        this.m = new h(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.f3896a = new byte[5];
        this.f3897b = new byte[10];
        this.r = new byte[1];
        this.f3898c = new byte[1];
        this.q = j2;
    }

    private int E() throws ax {
        int i2 = 0;
        if (this.g.h() >= 5) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = bArrF[iG + i2];
                i3 |= (b2 & 127) << i4;
                if ((b2 & 128) != 128) {
                    this.g.a(i2 + 1);
                    return i3;
                }
                i4 += 7;
                i2++;
            }
        } else {
            int i5 = 0;
            while (true) {
                byte bU = u();
                i2 |= (bU & 127) << i5;
                if ((bU & 128) != 128) {
                    return i2;
                }
                i5 += 7;
            }
        }
    }

    private long F() throws ax {
        int i2 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            int i3 = 0;
            while (true) {
                byte b2 = bArrF[iG + i2];
                j2 |= ((long) (b2 & 127)) << i3;
                if ((b2 & 128) != 128) {
                    break;
                }
                i3 += 7;
                i2++;
            }
            this.g.a(i2 + 1);
        } else {
            while (true) {
                byte bU = u();
                j2 |= ((long) (bU & 127)) << i2;
                if ((bU & 128) != 128) {
                    break;
                }
                i2 += 7;
            }
        }
        return j2;
    }

    private int c(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    private long c(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private boolean c(byte b2) {
        int i2 = b2 & ap.m;
        return i2 == 1 || i2 == 2;
    }

    private long d(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    private byte[] e(int i2) throws ax {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws aj {
        if (i2 < 0) {
            throw new aj("Negative length: " + i2);
        }
        long j2 = this.q;
        if (j2 == -1 || i2 <= j2) {
            return;
        }
        throw new aj("Length exceeded max allowed: " + i2);
    }

    private int g(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ByteBuffer A() throws ax, aj {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[iE];
        this.g.d(bArr, 0, iE);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ag agVar) {
        b(h);
        d(((agVar.f3915b << 5) & (-32)) | 1);
        b(agVar.f3916c);
        a(agVar.f3914a);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void b() {
        this.n = this.m.a();
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void c() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void d() {
        b((byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void e() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void f() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void g() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ag h() throws ax, aj {
        byte bU = u();
        if (bU != -126) {
            throw new aj("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(bU));
        }
        byte bU2 = u();
        byte b2 = (byte) (bU2 & j);
        if (b2 == 1) {
            return new ag(z(), (byte) ((bU2 >> 5) & 3), E());
        }
        throw new aj("Expected version 1 but got " + ((int) b2));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void i() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public an j() {
        this.m.a(this.n);
        this.n = (short) 0;
        return f3893d;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void k() {
        this.n = this.m.a();
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ad l() throws ax {
        byte bU = u();
        if (bU == 0) {
            return f3894e;
        }
        short s = (short) ((bU & 240) >> 4);
        short sV = s == 0 ? v() : (short) (this.n + s);
        byte b2 = (byte) (bU & ap.m);
        ad adVar = new ad("", d(b2), sV);
        if (c(bU)) {
            this.p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = adVar.f3908c;
        return adVar;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void m() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public af n() throws ax {
        int iE = E();
        byte bU = iE == 0 ? (byte) 0 : u();
        return new af(d((byte) (bU >> 4)), d((byte) (bU & ap.m)), iE);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void o() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ae p() throws ax {
        byte bU = u();
        int iE = (bU >> 4) & 15;
        if (iE == 15) {
            iE = E();
        }
        return new ae(d(bU), iE);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void q() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public am r() {
        return new am(p());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void s() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public boolean t() {
        Boolean bool = this.p;
        if (bool == null) {
            return u() == 1;
        }
        boolean zBooleanValue = bool.booleanValue();
        this.p = null;
        return zBooleanValue;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public byte u() throws ax {
        if (this.g.h() <= 0) {
            this.g.d(this.f3898c, 0, 1);
            return this.f3898c[0];
        }
        byte b2 = this.g.f()[this.g.g()];
        this.g.a(1);
        return b2;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public short v() {
        return (short) g(E());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public int w() {
        return g(E());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public long x() {
        return d(F());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public double y() throws ax {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public String z() {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return "";
        }
        try {
            if (this.g.h() < iE) {
                return new String(e(iE), "UTF-8");
            }
            String str = new String(this.g.f(), this.g.g(), iE, "UTF-8");
            this.g.a(iE);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new p("UTF-8 not supported!");
        }
    }

    /* JADX INFO: compiled from: TCompactProtocol.java */
    public static class a implements ak {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final long f3899a;

        public a() {
            this.f3899a = -1L;
        }

        @Override // com.umeng.commonsdk.proguard.ak
        public ai a(aw awVar) {
            return new ac(awVar, this.f3899a);
        }

        public a(int i) {
            this.f3899a = i;
        }
    }

    private void b(int i2) {
        int i3 = 0;
        while ((i2 & (-128)) != 0) {
            this.f3896a[i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
            i3++;
        }
        byte[] bArr = this.f3896a;
        bArr[i3] = (byte) i2;
        this.g.b(bArr, 0, i3 + 1);
    }

    private void d(int i2) {
        b((byte) i2);
    }

    private byte d(byte b2) throws aj {
        byte b3 = (byte) (b2 & ap.m);
        switch (b3) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return ap.m;
            case 10:
                return ap.l;
            case 11:
                return ap.k;
            case 12:
                return (byte) 12;
            default:
                throw new aj("don't know what type: " + ((int) b3));
        }
    }

    private byte e(byte b2) {
        return f3895f[b2];
    }

    private void b(long j2) {
        int i2 = 0;
        while (((-128) & j2) != 0) {
            this.f3897b[i2] = (byte) ((127 & j2) | 128);
            j2 >>>= 7;
            i2++;
        }
        byte[] bArr = this.f3897b;
        bArr[i2] = (byte) j2;
        this.g.b(bArr, 0, i2 + 1);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(an anVar) {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ad adVar) {
        if (adVar.f3907b == 2) {
            this.o = adVar;
        } else {
            a(adVar, (byte) -1);
        }
    }

    private void b(byte b2) {
        byte[] bArr = this.r;
        bArr[0] = b2;
        this.g.b(bArr);
    }

    private void a(ad adVar, byte b2) {
        if (b2 == -1) {
            b2 = e(adVar.f3907b);
        }
        short s = adVar.f3908c;
        short s2 = this.n;
        if (s > s2 && s - s2 <= 15) {
            d(b2 | ((s - s2) << 4));
        } else {
            b(b2);
            a(adVar.f3908c);
        }
        this.n = adVar.f3908c;
    }

    public ac(aw awVar) {
        this(awVar, -1L);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(af afVar) {
        int i2 = afVar.f3913c;
        if (i2 == 0) {
            d(0);
            return;
        }
        b(i2);
        d(e(afVar.f3912b) | (e(afVar.f3911a) << 4));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ae aeVar) {
        a(aeVar.f3909a, aeVar.f3910b);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(am amVar) {
        a(amVar.f3928a, amVar.f3929b);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(boolean z) {
        ad adVar = this.o;
        if (adVar != null) {
            a(adVar, z ? (byte) 1 : (byte) 2);
            this.o = null;
        } else {
            b(z ? (byte) 1 : (byte) 2);
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(byte b2) {
        b(b2);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(short s) {
        b(c((int) s));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(int i2) {
        b(c(i2));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(long j2) {
        b(c(j2));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(double d2) {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new p("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ByteBuffer byteBuffer) {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i2, int i3) {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    protected void a(byte b2, int i2) {
        if (i2 <= 14) {
            d(e(b2) | (i2 << 4));
        } else {
            d(e(b2) | 240);
            b(i2);
        }
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (j2 & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 7] = (byte) ((j2 >> 56) & 255);
    }

    private long a(byte[] bArr) {
        return ((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48) | ((((long) bArr[5]) & 255) << 40) | ((((long) bArr[4]) & 255) << 32) | ((((long) bArr[3]) & 255) << 24) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[1]) & 255) << 8) | (255 & ((long) bArr[0]));
    }
}
