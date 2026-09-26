package d.h0.i;

import com.baidu.mobstat.Config;
import e.s;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c[] f4466a = {new c(c.i, ""), new c(c.f4462f, "GET"), new c(c.f4462f, "POST"), new c(c.g, "/"), new c(c.g, "/index.html"), new c(c.h, "http"), new c(c.h, "https"), new c(c.f4461e, "200"), new c(c.f4461e, "204"), new c(c.f4461e, "206"), new c(c.f4461e, "304"), new c(c.f4461e, "400"), new c(c.f4461e, "404"), new c(c.f4461e, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c("content-type", ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c("from", ""), new c("host", ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c(Config.LAUNCH_REFERER, ""), new c("refresh", ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Map<e.f, Integer> f4467b = a();

    /* JADX INFO: compiled from: Hpack.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<c> f4468a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.e f4469b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f4470c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f4471d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c[] f4472e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f4473f;
        int g;
        int h;

        a(int i, s sVar) {
            this(i, i, sVar);
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f4472e.length;
                while (true) {
                    length--;
                    if (length < this.f4473f || i <= 0) {
                        break;
                    }
                    c[] cVarArr = this.f4472e;
                    i -= cVarArr[length].f4465c;
                    this.h -= cVarArr[length].f4465c;
                    this.g--;
                    i2++;
                }
                c[] cVarArr2 = this.f4472e;
                int i3 = this.f4473f;
                System.arraycopy(cVarArr2, i3 + 1, cVarArr2, i3 + 1 + i2, this.g);
                this.f4473f += i2;
            }
            return i2;
        }

        private void d() {
            int i = this.f4471d;
            int i2 = this.h;
            if (i < i2) {
                if (i == 0) {
                    e();
                } else {
                    b(i2 - i);
                }
            }
        }

        private void e() {
            Arrays.fill(this.f4472e, (Object) null);
            this.f4473f = this.f4472e.length - 1;
            this.g = 0;
            this.h = 0;
        }

        private void f(int i) {
            a(-1, new c(c(i), b()));
        }

        private void g(int i) throws IOException {
            this.f4468a.add(new c(c(i), b()));
        }

        private void h() throws IOException {
            e.f fVarB = b();
            d.a(fVarB);
            this.f4468a.add(new c(fVarB, b()));
        }

        public List<c> a() {
            ArrayList arrayList = new ArrayList(this.f4468a);
            this.f4468a.clear();
            return arrayList;
        }

        void c() throws IOException {
            while (!this.f4469b.j()) {
                int i = this.f4469b.readByte() & 255;
                if (i == 128) {
                    throw new IOException("index == 0");
                }
                if ((i & 128) == 128) {
                    e(a(i, 127) - 1);
                } else if (i == 64) {
                    g();
                } else if ((i & 64) == 64) {
                    f(a(i, 63) - 1);
                } else if ((i & 32) == 32) {
                    this.f4471d = a(i, 31);
                    int i2 = this.f4471d;
                    if (i2 < 0 || i2 > this.f4470c) {
                        throw new IOException("Invalid dynamic table size update " + this.f4471d);
                    }
                    d();
                } else if (i == 16 || i == 0) {
                    h();
                } else {
                    g(a(i, 15) - 1);
                }
            }
        }

        a(int i, int i2, s sVar) {
            this.f4468a = new ArrayList();
            this.f4472e = new c[8];
            this.f4473f = this.f4472e.length - 1;
            this.g = 0;
            this.h = 0;
            this.f4470c = i;
            this.f4471d = i2;
            this.f4469b = e.l.a(sVar);
        }

        private int a(int i) {
            return this.f4473f + 1 + i;
        }

        private void a(int i, c cVar) {
            this.f4468a.add(cVar);
            int i2 = cVar.f4465c;
            if (i != -1) {
                i2 -= this.f4472e[a(i)].f4465c;
            }
            int i3 = this.f4471d;
            if (i2 > i3) {
                e();
                return;
            }
            int iB = b((this.h + i2) - i3);
            if (i == -1) {
                int i4 = this.g + 1;
                c[] cVarArr = this.f4472e;
                if (i4 > cVarArr.length) {
                    c[] cVarArr2 = new c[cVarArr.length * 2];
                    System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                    this.f4473f = this.f4472e.length - 1;
                    this.f4472e = cVarArr2;
                }
                int i5 = this.f4473f;
                this.f4473f = i5 - 1;
                this.f4472e[i5] = cVar;
                this.g++;
            } else {
                this.f4472e[i + a(i) + iB] = cVar;
            }
            this.h += i2;
        }

        private boolean d(int i) {
            return i >= 0 && i <= d.f4466a.length - 1;
        }

        private int f() {
            return this.f4469b.readByte() & 255;
        }

        private void g() throws IOException {
            e.f fVarB = b();
            d.a(fVarB);
            a(-1, new c(fVarB, b()));
        }

        private void e(int i) throws IOException {
            if (d(i)) {
                this.f4468a.add(d.f4466a[i]);
                return;
            }
            int iA = a(i - d.f4466a.length);
            if (iA >= 0) {
                c[] cVarArr = this.f4472e;
                if (iA < cVarArr.length) {
                    this.f4468a.add(cVarArr[iA]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        e.f b() {
            int iF = f();
            boolean z = (iF & 128) == 128;
            int iA = a(iF, 127);
            if (z) {
                return e.f.a(k.b().a(this.f4469b.i(iA)));
            }
            return this.f4469b.e(iA);
        }

        private e.f c(int i) throws IOException {
            if (d(i)) {
                return d.f4466a[i].f4463a;
            }
            int iA = a(i - d.f4466a.length);
            if (iA >= 0) {
                c[] cVarArr = this.f4472e;
                if (iA < cVarArr.length) {
                    return cVarArr[iA].f4463a;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        int a(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int iF = f();
                if ((iF & 128) == 0) {
                    return i2 + (iF << i4);
                }
                i2 += (iF & 127) << i4;
                i4 += 7;
            }
        }
    }

    /* JADX INFO: compiled from: Hpack.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.c f4474a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f4475b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f4476c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f4477d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f4478e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c[] f4479f;
        int g;
        int h;
        int i;

        b(e.c cVar) {
            this(4096, true, cVar);
        }

        private void a(c cVar) {
            int i = cVar.f4465c;
            int i2 = this.f4478e;
            if (i > i2) {
                b();
                return;
            }
            b((this.i + i) - i2);
            int i3 = this.h + 1;
            c[] cVarArr = this.f4479f;
            if (i3 > cVarArr.length) {
                c[] cVarArr2 = new c[cVarArr.length * 2];
                System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                this.g = this.f4479f.length - 1;
                this.f4479f = cVarArr2;
            }
            int i4 = this.g;
            this.g = i4 - 1;
            this.f4479f[i4] = cVar;
            this.h++;
            this.i += i;
        }

        private void b() {
            Arrays.fill(this.f4479f, (Object) null);
            this.g = this.f4479f.length - 1;
            this.h = 0;
            this.i = 0;
        }

        b(int i, boolean z, e.c cVar) {
            this.f4476c = Integer.MAX_VALUE;
            this.f4479f = new c[8];
            this.g = this.f4479f.length - 1;
            this.h = 0;
            this.i = 0;
            this.f4478e = i;
            this.f4475b = z;
            this.f4474a = cVar;
        }

        private int b(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.f4479f.length;
                while (true) {
                    length--;
                    if (length < this.g || i <= 0) {
                        break;
                    }
                    c[] cVarArr = this.f4479f;
                    i -= cVarArr[length].f4465c;
                    this.i -= cVarArr[length].f4465c;
                    this.h--;
                    i2++;
                }
                c[] cVarArr2 = this.f4479f;
                int i3 = this.g;
                System.arraycopy(cVarArr2, i3 + 1, cVarArr2, i3 + 1 + i2, this.h);
                c[] cVarArr3 = this.f4479f;
                int i4 = this.g;
                Arrays.fill(cVarArr3, i4 + 1, i4 + 1 + i2, (Object) null);
                this.g += i2;
            }
            return i2;
        }

        /* JADX WARN: Code duplicated, block: B:22:0x006c  */
        void a(List<c> list) {
            int length;
            int length2;
            if (this.f4477d) {
                int i = this.f4476c;
                if (i < this.f4478e) {
                    a(i, 31, 32);
                }
                this.f4477d = false;
                this.f4476c = Integer.MAX_VALUE;
                a(this.f4478e, 31, 32);
            }
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                c cVar = list.get(i2);
                e.f fVarG = cVar.f4463a.g();
                e.f fVar = cVar.f4464b;
                Integer num = d.f4467b.get(fVarG);
                if (num != null) {
                    length = num.intValue() + 1;
                    if (length <= 1 || length >= 8) {
                        length2 = length;
                        length = -1;
                    } else if (d.h0.c.a(d.f4466a[length - 1].f4464b, fVar)) {
                        length2 = length;
                    } else if (d.h0.c.a(d.f4466a[length].f4464b, fVar)) {
                        length2 = length;
                        length++;
                    } else {
                        length2 = length;
                        length = -1;
                    }
                } else {
                    length = -1;
                    length2 = -1;
                }
                if (length == -1) {
                    int length3 = this.f4479f.length;
                    for (int i3 = this.g + 1; i3 < length3; i3++) {
                        if (d.h0.c.a(this.f4479f[i3].f4463a, fVarG)) {
                            if (d.h0.c.a(this.f4479f[i3].f4464b, fVar)) {
                                length = d.f4466a.length + (i3 - this.g);
                                break;
                            } else if (length2 == -1) {
                                length2 = (i3 - this.g) + d.f4466a.length;
                            }
                        }
                    }
                }
                if (length != -1) {
                    a(length, 127, 128);
                } else if (length2 == -1) {
                    this.f4474a.writeByte(64);
                    a(fVarG);
                    a(fVar);
                    a(cVar);
                } else if (fVarG.b(c.f4460d) && !c.i.equals(fVarG)) {
                    a(length2, 15, 0);
                    a(fVar);
                } else {
                    a(length2, 63, 64);
                    a(fVar);
                    a(cVar);
                }
            }
        }

        void a(int i, int i2, int i3) {
            if (i < i2) {
                this.f4474a.writeByte(i | i3);
                return;
            }
            this.f4474a.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.f4474a.writeByte(128 | (i4 & 127));
                i4 >>>= 7;
            }
            this.f4474a.writeByte(i4);
        }

        void a(e.f fVar) {
            if (this.f4475b && k.b().a(fVar) < fVar.f()) {
                e.c cVar = new e.c();
                k.b().a(fVar, cVar);
                e.f fVarN = cVar.n();
                a(fVarN.f(), 127, 128);
                this.f4474a.a(fVarN);
                return;
            }
            a(fVar.f(), 127, 0);
            this.f4474a.a(fVar);
        }

        void a(int i) {
            int iMin = Math.min(i, 16384);
            int i2 = this.f4478e;
            if (i2 == iMin) {
                return;
            }
            if (iMin < i2) {
                this.f4476c = Math.min(this.f4476c, iMin);
            }
            this.f4477d = true;
            this.f4478e = iMin;
            a();
        }

        private void a() {
            int i = this.f4478e;
            int i2 = this.i;
            if (i < i2) {
                if (i == 0) {
                    b();
                } else {
                    b(i2 - i);
                }
            }
        }
    }

    private static Map<e.f, Integer> a() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f4466a.length);
        int i = 0;
        while (true) {
            c[] cVarArr = f4466a;
            if (i >= cVarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(cVarArr[i].f4463a)) {
                linkedHashMap.put(f4466a[i].f4463a, Integer.valueOf(i));
            }
            i++;
        }
    }

    static e.f a(e.f fVar) throws IOException {
        int iF = fVar.f();
        for (int i = 0; i < iF; i++) {
            byte bA = fVar.a(i);
            if (bA >= 65 && bA <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + fVar.i());
            }
        }
        return fVar;
    }
}
