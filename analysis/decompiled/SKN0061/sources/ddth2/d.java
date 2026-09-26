package ddth2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static final byte[] a = new byte[0];

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public int f12a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final e f14a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final l2 f15a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public m2 f16a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public q2 f17a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public DataInputStream f19a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public DataOutputStream f20a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final String f22a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public Socket f23a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public volatile boolean f28a;
    public int b;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public volatile long f29b;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public final String f30b;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public byte[] f34b;

    /* JADX INFO: renamed from: c, reason: collision with other field name */
    public volatile long f35c;

    /* JADX INFO: renamed from: c, reason: collision with other field name */
    public String f36c;

    /* JADX INFO: renamed from: d, reason: collision with other field name */
    public byte[] f39d;
    public int e;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicInteger f26a = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicBoolean f25a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public AtomicBoolean f31b = new AtomicBoolean(false);

    /* JADX INFO: renamed from: c, reason: collision with other field name */
    public final AtomicBoolean f37c = new AtomicBoolean(false);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final Object f21a = new Object();

    /* JADX INFO: renamed from: c, reason: collision with other field name */
    public final byte[] f38c = new byte[4];

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public ConcurrentHashMap<Integer, c2> f24a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicLong f27a = new AtomicLong(1);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public long f13a = 0;
    public int c = 60;
    public int d = 7;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public boolean f33b = false;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public final AtomicInteger f32b = new AtomicInteger(0);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public x1 f18a = new x1();

    public d(m2 m2Var, e eVar, String str, String str2, l2 l2Var) {
        this.f16a = m2Var;
        this.f14a = eVar;
        this.f22a = str;
        this.f15a = l2Var;
        this.f30b = str2;
    }

    public int a() {
        return this.f32b.get();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public long m8a() {
        return this.f35c;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public e m9a() {
        return this.f14a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public m2 m10a() {
        return this.f16a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public q2 m11a() {
        return this.f17a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public String m12a() {
        return this.f22a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public AtomicInteger m13a() {
        return this.f26a;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public void m14a() {
        if (this.f37c.compareAndSet(false, true)) {
            this.f31b.set(true);
            this.f25a.set(false);
            if (this.f28a) {
                this.f28a = false;
                e2.a();
            }
            synchronized (this.f21a) {
                int i = this.f12a;
                if (i > 0) {
                    d2.a(-i);
                }
                this.f12a = 0;
                this.b = 0;
            }
            int size = this.f24a.size();
            Iterator<c2> it = this.f24a.values().iterator();
            while (it.hasNext()) {
                it.next().d();
            }
            this.f24a.clear();
            for (int i2 = 0; i2 < size; i2++) {
                d2.m21a();
            }
            this.f18a.e();
            try {
                DataInputStream dataInputStream = this.f19a;
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException unused) {
            }
            try {
                DataOutputStream dataOutputStream = this.f20a;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException unused2) {
            }
            try {
                Socket socket = this.f23a;
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException unused3) {
            }
        }
    }

    public void a(int i) {
        if (this.f24a.remove(Integer.valueOf(i)) != null) {
            d2.m21a();
            r(49, i, null);
        }
    }

    public final void a(int i, int i2, byte[] bArr) {
        if (bArr.length > 0 && this.f33b) {
            bArr = a(bArr);
        }
        if (i != 160) {
            if (i == 255) {
                k(i, i2, bArr);
                return;
            }
            if (i == 0) {
                g(i, i2, bArr);
                return;
            }
            if (i == 1) {
                l(i, i2, bArr);
                return;
            }
            if (i == 2) {
                e(i, i2, bArr);
                return;
            }
            if (i != 3 && i != 4) {
                if (i == 5) {
                    f(i, i2, bArr);
                    return;
                }
                switch (i) {
                    case 7:
                        h(i, i2, bArr);
                        break;
                    case 8:
                        j(i, i2, bArr);
                        break;
                    case 9:
                        i(i, i2, bArr);
                        break;
                    case 10:
                        n(i, i2, bArr);
                        break;
                    case 11:
                        m(i, i2, bArr);
                        break;
                    default:
                        switch (i) {
                            case 16:
                                b(i, i2, bArr);
                                break;
                            case 17:
                                d(i, i2, bArr);
                                break;
                            case 18:
                                c(i, i2, bArr);
                                break;
                            case 19:
                                p(i, i2, bArr);
                                break;
                            default:
                                q(i, i2, bArr);
                                break;
                        }
                        break;
                }
            }
        }
        o(i, i2, bArr);
    }

    public final void a(int i, int i2, byte[] bArr, int i3) {
        if (bArr == null || i3 <= 0) {
            c(i, i2, a, 0);
            return;
        }
        if (i3 <= 4096) {
            c(i, i2, bArr, i3);
            return;
        }
        int i4 = 0;
        while (i3 > 0) {
            int i5 = i3 > 4096 ? 4096 : i3;
            if (this.f39d == null) {
                this.f39d = new byte[4096];
            }
            System.arraycopy(bArr, i4, this.f39d, 0, i5);
            c(i, i2, this.f39d, i5);
            i4 += i5;
            i3 -= i5;
        }
    }

    public void a(long j, long j2, int i, int i2) {
        this.f29b = j;
        this.f35c = j2;
        this.f32b.set(i);
        this.f26a.set(i2);
    }

    public void a(q2 q2Var) {
        this.f17a = q2Var;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m15a() {
        return this.f25a.get();
    }

    public boolean a(String str, int i) {
        this.f37c.set(false);
        this.f36c = str;
        this.e = i;
        if (!e2.m45a()) {
            l2 l2Var = this.f15a;
            if (l2Var != null) {
                l2Var.a("max total tcp connections reached");
            }
            return false;
        }
        try {
            Socket socket = new Socket();
            this.f23a = socket;
            socket.setSoTimeout(this.f14a.n() * 1000);
            if (this.f14a.m28a() != null && !this.f14a.m28a().isEmpty()) {
                this.f23a.bind(new InetSocketAddress(this.f14a.m28a(), 0));
            }
            this.f23a.connect(new InetSocketAddress(str, i), this.f14a.m32b() * 1000);
            this.f19a = new DataInputStream(this.f23a.getInputStream());
            this.f20a = new DataOutputStream(this.f23a.getOutputStream());
            int iF = this.f14a.f();
            if (iF < 4096) {
                iF = 4096;
            }
            synchronized (this.f21a) {
                this.f34b = new byte[iF];
                this.f12a = 0;
                this.b = 0;
            }
            this.f28a = true;
            this.f25a.set(true);
            this.f31b.set(false);
            k2.m61a((Runnable) new b2(this));
            this.f18a.a(k2.a());
            f();
            l2 l2Var2 = this.f15a;
            if (l2Var2 != null) {
                l2Var2.a(str, i);
            }
            this.f32b.set(0);
            this.f29b = 0L;
            this.f35c = 0L;
            return true;
        } catch (Exception e) {
            e2.a();
            this.f28a = false;
            l2 l2Var3 = this.f15a;
            if (l2Var3 != null) {
                l2Var3.a("connect failed: " + e.getMessage());
            }
            m14a();
            return false;
        }
    }

    public final byte[] a(byte[] bArr) {
        return s2.a(bArr, c().getBytes());
    }

    public int b() {
        return this.f24a.size();
    }

    /* JADX INFO: renamed from: b, reason: collision with other method in class */
    public long m16b() {
        return this.f29b;
    }

    /* JADX INFO: renamed from: b, reason: collision with other method in class */
    public String m17b() {
        return this.f30b;
    }

    /* JADX INFO: renamed from: b, reason: collision with other method in class */
    public final void m18b() {
        this.f31b.set(true);
        this.f25a.set(false);
        m14a();
    }

    public final void b(int i, int i2, byte[] bArr) {
        c2 c2Var = new c2(i2, this, this.f18a);
        if (this.f24a.size() >= this.f14a.j() || !d2.m22a()) {
            r(0, i2, null);
            r(49, i2, null);
            return;
        }
        this.f24a.put(Integer.valueOf(i2), c2Var);
        if (c2Var.m7a(i, i2, bArr)) {
            return;
        }
        this.f24a.remove(Integer.valueOf(i2));
        d2.m21a();
    }

    public void b(int i, int i2, byte[] bArr, int i3) {
        a(i, i2, bArr, i3);
    }

    public final byte[] b(byte[] bArr) {
        return s2.a(bArr, c().getBytes());
    }

    public final String c() {
        return a2.a();
    }

    /* JADX INFO: renamed from: c, reason: collision with other method in class */
    public void m19c() {
        int iIncrementAndGet = this.f32b.incrementAndGet();
        this.f29b = i2.b(this.f14a, iIncrementAndGet);
        if (i2.m59a(this.f14a, iIncrementAndGet)) {
            this.f35c = i2.a(this.f14a);
        }
    }

    public final void c(int i, int i2, byte[] bArr) {
        c2 c2Var = this.f24a.get(Integer.valueOf(i2));
        if (c2Var != null) {
            c2Var.a(i, i2, bArr);
        }
    }

    public final void c(int i, int i2, byte[] bArr, int i3) {
        try {
            if (this.f20a == null) {
                return;
            }
            int iG = this.f14a.g();
            if (i3 > 0 && this.f33b) {
                if (i3 == bArr.length) {
                    bArr = b(bArr);
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, 0, bArr2, 0, i3);
                    bArr = b(bArr2);
                }
                i3 = bArr.length;
            }
            int i4 = i3 + 4;
            if (i4 > iG) {
                l2 l2Var = this.f15a;
                if (l2Var != null) {
                    l2Var.a("send buffer hard limit exceeded: " + i4);
                    return;
                }
                return;
            }
            synchronized (this.f20a) {
                byte[] bArr3 = this.f38c;
                bArr3[0] = (byte) (i4 & 255);
                bArr3[1] = (byte) ((i4 >> 8) & 255);
                bArr3[2] = (byte) i;
                bArr3[3] = (byte) i2;
                this.f20a.write(bArr3);
                if (i3 > 0) {
                    this.f20a.write(bArr, 0, i3);
                }
                this.f20a.flush();
            }
        } catch (IOException e) {
            l2 l2Var2 = this.f15a;
            if (l2Var2 != null) {
                l2Var2.a("send data failed: " + e.getMessage());
            }
        }
    }

    public final void d() {
        int i;
        int i2;
        byte[] bArr;
        synchronized (this.f21a) {
            if (this.f34b == null) {
                return;
            }
            int i3 = this.f12a;
            int iF = this.f14a.f();
            if (i3 > iF) {
                l2 l2Var = this.f15a;
                if (l2Var != null) {
                    l2Var.a("assemble buffer exceeds hard limit");
                }
                m18b();
                return;
            }
            j2.a(i3);
            byte[] bArr2 = this.f34b;
            while (true) {
                i = this.b;
                if (i >= i3 || (i2 = i3 - i) < 3) {
                    break;
                    break;
                }
                int i4 = (bArr2[i] & 255) | ((bArr2[i + 1] & 255) << 8);
                if (i4 >= 4 && i4 <= iF) {
                    if (i4 > i2) {
                        break;
                    }
                    int i5 = bArr2[i + 2] & 255;
                    int i6 = i4 >= 4 ? bArr2[i + 3] & 255 : 0;
                    int i7 = i4 - 4;
                    if (i7 <= 0) {
                        bArr = a;
                    } else {
                        byte[] bArr3 = new byte[i7];
                        System.arraycopy(bArr2, i + 4, bArr3, 0, i7);
                        bArr = bArr3;
                    }
                    a(i5, i6, bArr);
                    this.b += i4;
                }
                l2 l2Var2 = this.f15a;
                if (l2Var2 != null) {
                    l2Var2.a("invalid package length: " + i4);
                }
                m18b();
                return;
            }
            if (i > 16384) {
                if (i >= i3) {
                    this.f12a = 0;
                } else {
                    int i8 = i3 - i;
                    byte[] bArr4 = this.f34b;
                    System.arraycopy(bArr4, i, bArr4, 0, i8);
                    this.f12a = i8;
                }
                this.b = 0;
            }
            d2.a(this.f12a - i3);
        }
    }

    public final void d(int i, int i2, byte[] bArr) {
        c2 c2Var = this.f24a.get(Integer.valueOf(i2));
        if (c2Var != null) {
            c2Var.b(i, i2, bArr);
            return;
        }
        l2 l2Var = this.f15a;
        if (l2Var != null) {
            l2Var.a("not find fifo node");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
    
        r9.f25a.set(false);
        m14a();
        r0 = r9.f15a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
    
        if (r0 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007e, code lost:
    
        r0.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0081, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0093, code lost:
    
        d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e() {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ddth2.d.e():void");
    }

    public final void e(int i, int i2, byte[] bArr) {
        c2 c2Var = new c2(i2, this, this.f18a);
        if (this.f24a.size() >= this.f14a.j() || !d2.m22a()) {
            r(0, i2, null);
            r(49, i2, null);
            return;
        }
        this.f24a.put(Integer.valueOf(i2), c2Var);
        if (c2Var.m7a(i, i2, bArr)) {
            return;
        }
        this.f24a.remove(Integer.valueOf(i2));
        d2.m21a();
    }

    public final void f() {
        r(255, 0, null);
    }

    public final void f(int i, int i2, byte[] bArr) {
        this.f33b = true;
        r(1, 0, null);
    }

    public final void g(int i, int i2, byte[] bArr) {
        l2 l2Var = this.f15a;
        if (l2Var != null) {
            l2Var.a("error package");
        }
    }

    public final void h(int i, int i2, byte[] bArr) {
        String str = this.f22a;
        r(1, 0, (str == null || str.isEmpty()) ? null : this.f22a.getBytes());
    }

    public final void i(int i, int i2, byte[] bArr) {
        r(1, 0, String.valueOf(19).getBytes());
    }

    public final void j(int i, int i2, byte[] bArr) {
        r(1, 0, this.f14a.m44f().getBytes());
    }

    public final void k(int i, int i2, byte[] bArr) {
        this.f13a = System.currentTimeMillis() / 1000;
        f();
    }

    public final void l(int i, int i2, byte[] bArr) {
    }

    public final void m(int i, int i2, byte[] bArr) {
        try {
            this.c = Integer.parseInt(new String(bArr));
            r(1, 0, null);
        } catch (Exception unused) {
            r(0, 0, null);
        }
    }

    public final void n(int i, int i2, byte[] bArr) {
        try {
            this.d = Integer.parseInt(new String(bArr));
            r(1, 0, null);
        } catch (Exception unused) {
            r(0, 0, null);
        }
    }

    public final void o(int i, int i2, byte[] bArr) {
        c2 c2Var = this.f24a.get(Integer.valueOf(i2));
        if (c2Var != null) {
            if (i == 2) {
                c2Var.m7a(i, i2, bArr);
            } else if (i == 3) {
                c2Var.b(i, i2, bArr);
            } else if (i == 4) {
                c2Var.a(i, i2, bArr);
            }
        }
    }

    public final void p(int i, int i2, byte[] bArr) {
        c2 c2Var = this.f24a.get(Integer.valueOf(i2));
        if (c2Var != null) {
            c2Var.b(i, i2, bArr);
        }
    }

    public final void q(int i, int i2, byte[] bArr) {
        r(241, 0, null);
    }

    public final void r(int i, int i2, byte[] bArr) {
        if (bArr == null) {
            c(i, i2, a, 0);
        } else {
            a(i, i2, bArr, bArr.length);
        }
    }

    public void s(int i, int i2, byte[] bArr) {
        r(i, i2, bArr);
    }
}
