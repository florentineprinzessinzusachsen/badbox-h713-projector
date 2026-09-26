package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: TBinaryProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class ab extends ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final int f3884a = -65536;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final int f3885b = -2147418112;
    private static final an h = new an();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected boolean f3886c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected boolean f3887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f3888e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected boolean f3889f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    /* JADX INFO: compiled from: TBinaryProtocol.java */
    public static class a implements ak {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected boolean f3890a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected boolean f3891b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        protected int f3892c;

        public a() {
            this(false, true);
        }

        @Override // com.umeng.commonsdk.proguard.ak
        public ai a(aw awVar) {
            ab abVar = new ab(awVar, this.f3890a, this.f3891b);
            int i = this.f3892c;
            if (i != 0) {
                abVar.c(i);
            }
            return abVar;
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.f3890a = false;
            this.f3891b = true;
            this.f3890a = z;
            this.f3891b = z2;
            this.f3892c = i;
        }
    }

    public ab(aw awVar) {
        this(awVar, false, true);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ByteBuffer A() throws ax, aj {
        int iW = w();
        d(iW);
        if (this.g.h() >= iW) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.g.f(), this.g.g(), iW);
            this.g.a(iW);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iW];
        this.g.d(bArr, 0, iW);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ag agVar) throws p {
        if (this.f3887d) {
            a(f3885b | agVar.f3915b);
            a(agVar.f3914a);
            a(agVar.f3916c);
        } else {
            a(agVar.f3914a);
            a(agVar.f3915b);
            a(agVar.f3916c);
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(an anVar) {
    }

    public String b(int i) throws p {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void b() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void c() {
    }

    public void c(int i) {
        this.f3888e = i;
        this.f3889f = true;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void d() {
        a((byte) 0);
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
    public ag h() throws aj {
        int iW = w();
        if (iW < 0) {
            if ((f3884a & iW) == f3885b) {
                return new ag(z(), (byte) (iW & 255), w());
            }
            throw new aj(4, "Bad version in readMessageBegin");
        }
        if (this.f3886c) {
            throw new aj(4, "Missing version in readMessageBegin, old client?");
        }
        return new ag(b(iW), u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void i() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public an j() {
        return h;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void k() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ad l() throws aj {
        byte bU = u();
        return new ad("", bU, bU == 0 ? (short) 0 : v());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void m() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public af n() {
        return new af(u(), u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void o() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public ae p() {
        return new ae(u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void q() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public am r() {
        return new am(u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void s() {
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public boolean t() {
        return u() == 1;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public byte u() throws aj {
        if (this.g.h() < 1) {
            a(this.m, 0, 1);
            return this.m[0];
        }
        byte b2 = this.g.f()[this.g.g()];
        this.g.a(1);
        return b2;
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public short v() throws aj {
        byte[] bArrF = this.n;
        int iG = 0;
        if (this.g.h() >= 2) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArrF[iG + 1] & 255) | ((bArrF[iG] & 255) << 8));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public int w() throws aj {
        byte[] bArrF = this.o;
        int iG = 0;
        if (this.g.h() >= 4) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArrF[iG + 3] & 255) | ((bArrF[iG] & 255) << 24) | ((bArrF[iG + 1] & 255) << 16) | ((bArrF[iG + 2] & 255) << 8);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public long x() throws aj {
        byte[] bArrF = this.p;
        int iG = 0;
        if (this.g.h() >= 8) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return ((long) (bArrF[iG + 7] & 255)) | (((long) (bArrF[iG] & 255)) << 56) | (((long) (bArrF[iG + 1] & 255)) << 48) | (((long) (bArrF[iG + 2] & 255)) << 40) | (((long) (bArrF[iG + 3] & 255)) << 32) | (((long) (bArrF[iG + 4] & 255)) << 24) | (((long) (bArrF[iG + 5] & 255)) << 16) | (((long) (bArrF[iG + 6] & 255)) << 8);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public double y() {
        return Double.longBitsToDouble(x());
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public String z() throws p {
        int iW = w();
        if (this.g.h() < iW) {
            return b(iW);
        }
        try {
            String str = new String(this.g.f(), this.g.g(), iW, "UTF-8");
            this.g.a(iW);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ab(aw awVar, boolean z, boolean z2) {
        super(awVar);
        this.f3886c = false;
        this.f3887d = true;
        this.f3889f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.f3886c = z;
        this.f3887d = z2;
    }

    protected void d(int i) throws aj {
        if (i < 0) {
            throw new aj("Negative length: " + i);
        }
        if (this.f3889f) {
            this.f3888e -= i;
            if (this.f3888e >= 0) {
                return;
            }
            throw new aj("Message length exceeded: " + i);
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ad adVar) {
        a(adVar.f3907b);
        a(adVar.f3908c);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(af afVar) {
        a(afVar.f3911a);
        a(afVar.f3912b);
        a(afVar.f3913c);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ae aeVar) {
        a(aeVar.f3909a);
        a(aeVar.f3910b);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(am amVar) {
        a(amVar.f3928a);
        a(amVar.f3929b);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(boolean z) {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(byte b2) {
        byte[] bArr = this.i;
        bArr[0] = b2;
        this.g.b(bArr, 0, 1);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(short s) {
        byte[] bArr = this.j;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.g.b(bArr, 0, 2);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(int i) {
        byte[] bArr = this.k;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.g.b(bArr, 0, 4);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(long j) {
        byte[] bArr = this.l;
        bArr[0] = (byte) ((j >> 56) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[7] = (byte) (j & 255);
        this.g.b(bArr, 0, 8);
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(double d2) {
        a(Double.doubleToLongBits(d2));
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(String str) throws p {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new p("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.ai
    public void a(ByteBuffer byteBuffer) {
        int iLimit = byteBuffer.limit() - byteBuffer.position();
        a(iLimit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), iLimit);
    }

    private int a(byte[] bArr, int i, int i2) throws aj {
        d(i2);
        return this.g.d(bArr, i, i2);
    }
}
