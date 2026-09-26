package ddth2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class c2 {
    public int a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public long f4a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public d f5a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public x1 f6a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public ByteBuffer f7a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public SocketChannel f8a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicBoolean f9a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public byte[] f10a;
    public int b;

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public long f11b;
    public int c;

    public c2(int i, d dVar, x1 x1Var) {
        int iN;
        this.a = i;
        this.f5a = dVar;
        this.f6a = x1Var;
        q2 q2VarM11a = dVar.m11a();
        if (q2VarM11a == null || q2VarM11a.a() == null) {
            this.b = dVar.m9a().m32b() * 1000;
            iN = dVar.m9a().n();
        } else {
            p2 p2VarA = q2VarM11a.a();
            this.b = p2VarA.a() * 1000;
            iN = p2VarA.b();
        }
        this.c = iN * 1000;
        this.f4a = System.currentTimeMillis();
        this.f11b = System.currentTimeMillis();
    }

    public void a() {
        if (this.b <= 0 || this.f9a.get() || System.currentTimeMillis() - this.f4a <= this.b) {
            return;
        }
        b(this.a);
    }

    public void a(int i) {
        try {
            this.f6a.a(this.f8a, this);
            this.f9a.set(true);
            this.f11b = System.currentTimeMillis();
            this.f5a.s(1, i, null);
        } catch (Exception unused) {
            this.f5a.s(0, i, null);
            c();
        }
    }

    public void a(int i, int i2, byte[] bArr) {
        this.f9a.set(false);
        c();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m6a() {
        try {
            if (this.f9a.get() && this.f8a != null) {
                this.f11b = System.currentTimeMillis();
                e();
                this.f7a.clear();
                int i = this.f8a.read(this.f7a);
                if (i == -1) {
                    this.f9a.set(false);
                    this.f5a.s(4, this.a, null);
                    c();
                    return false;
                }
                if (i <= 0) {
                    return true;
                }
                this.f7a.flip();
                byte[] bArr = this.f10a;
                if (bArr == null || bArr.length < i) {
                    int iP = this.f5a.m9a().p();
                    if (iP < 512) {
                        iP = 512;
                    }
                    if (iP > 65536) {
                        iP = 65536;
                    }
                    this.f10a = new byte[Math.max(i, iP)];
                }
                this.f7a.get(this.f10a, 0, i);
                this.f5a.b(3, this.a, this.f10a, i);
                return true;
            }
            return false;
        } catch (IOException | Exception unused) {
            this.f9a.set(false);
            this.f5a.s(4, this.a, null);
            c();
            return false;
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m7a(int i, int i2, byte[] bArr) {
        try {
            String[] strArrA = a(new String(bArr));
            if (strArrA == null) {
                this.f5a.s(0, i2, null);
                return false;
            }
            String str = strArrA[0];
            int i3 = Integer.parseInt(strArrA[1]);
            this.f4a = System.currentTimeMillis();
            SocketChannel socketChannelOpen = SocketChannel.open();
            this.f8a = socketChannelOpen;
            socketChannelOpen.configureBlocking(false);
            if (this.f8a.connect(new InetSocketAddress(str, i3))) {
                a(i2);
            } else {
                this.f6a.a(this.f8a, this, i2);
            }
            return true;
        } catch (Exception unused) {
            this.f5a.s(0, i2, null);
            c();
            return false;
        }
    }

    public final String[] a(String str) {
        try {
            if (str.toLowerCase().startsWith("http://")) {
                str = str.substring(7);
            } else if (str.toLowerCase().startsWith("https://")) {
                str = str.substring(8);
            }
            int iIndexOf = str.indexOf(58);
            if (iIndexOf <= 0) {
                int iIndexOf2 = str.indexOf(47);
                if (iIndexOf2 > 0) {
                    str = str.substring(0, iIndexOf2);
                }
                return new String[]{str, "80"};
            }
            String strSubstring = str.substring(0, iIndexOf);
            String strSubstring2 = str.substring(iIndexOf + 1);
            int iIndexOf3 = strSubstring2.indexOf(47);
            if (iIndexOf3 > 0) {
                strSubstring2 = strSubstring2.substring(0, iIndexOf3);
            }
            return new String[]{strSubstring, strSubstring2};
        } catch (Exception unused) {
            return null;
        }
    }

    public void b() {
        if (this.c <= 0 || !this.f9a.get() || System.currentTimeMillis() - this.f11b <= this.c) {
            return;
        }
        this.f9a.set(false);
        this.f5a.s(4, this.a, null);
        c();
    }

    public void b(int i) {
        this.f5a.s(0, i, null);
        c();
    }

    public void b(int i, int i2, byte[] bArr) {
        SocketChannel socketChannel;
        if (this.f9a.get() && (socketChannel = this.f8a) != null && socketChannel.isOpen()) {
            try {
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                long jNanoTime = System.nanoTime();
                while (byteBufferWrap.hasRemaining()) {
                    long jNanoTime2 = (System.nanoTime() - jNanoTime) / 1000000;
                    if (jNanoTime2 > 5000) {
                        throw new IOException("Write timeout after " + jNanoTime2 + "ms");
                    }
                    this.f8a.write(byteBufferWrap);
                }
            } catch (IOException unused) {
                this.f5a.s(4, i2, null);
                this.f9a.set(false);
                c();
            }
        }
    }

    public void c() {
        this.f9a.set(false);
        this.f5a.a(this.a);
        try {
            SocketChannel socketChannel = this.f8a;
            if (socketChannel == null || !socketChannel.isOpen()) {
                return;
            }
            this.f8a.close();
        } catch (IOException unused) {
        }
    }

    public void d() {
        this.f9a.set(false);
        try {
            SocketChannel socketChannel = this.f8a;
            if (socketChannel == null || !socketChannel.isOpen()) {
                return;
            }
            if (this.f8a.isRegistered()) {
                this.f8a.keyFor(this.f6a.a()).cancel();
            }
            this.f8a.close();
        } catch (IOException unused) {
        }
    }

    public final void e() {
        if (this.f7a != null) {
            return;
        }
        int iP = this.f5a.m9a().p();
        if (iP < 512) {
            iP = 512;
        }
        if (iP > 65536) {
            iP = 65536;
        }
        this.f7a = ByteBuffer.allocate(iP);
    }

    public void f() {
        this.f9a.set(false);
        this.f5a.s(0, this.a, null);
        c();
    }
}
