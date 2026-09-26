package d.h0.e;

import d.a0;
import d.c0;
import d.s;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f4350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0 f4351b;

    c(a0 a0Var, c0 c0Var) {
        this.f4350a = a0Var;
        this.f4351b = c0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0056, code lost:
    
        if (r3.b().b() == false) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(d.c0 r3, d.a0 r4) {
        /*
            int r0 = r3.m()
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 0
            if (r0 == r1) goto L5a
            r1 = 410(0x19a, float:5.75E-43)
            if (r0 == r1) goto L5a
            r1 = 414(0x19e, float:5.8E-43)
            if (r0 == r1) goto L5a
            r1 = 501(0x1f5, float:7.02E-43)
            if (r0 == r1) goto L5a
            r1 = 203(0xcb, float:2.84E-43)
            if (r0 == r1) goto L5a
            r1 = 204(0xcc, float:2.86E-43)
            if (r0 == r1) goto L5a
            r1 = 307(0x133, float:4.3E-43)
            if (r0 == r1) goto L31
            r1 = 308(0x134, float:4.32E-43)
            if (r0 == r1) goto L5a
            r1 = 404(0x194, float:5.66E-43)
            if (r0 == r1) goto L5a
            r1 = 405(0x195, float:5.68E-43)
            if (r0 == r1) goto L5a
            switch(r0) {
                case 300: goto L5a;
                case 301: goto L5a;
                case 302: goto L31;
                default: goto L30;
            }
        L30:
            goto L59
        L31:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.a(r0)
            if (r0 != 0) goto L5a
            d.d r0 = r3.b()
            int r0 = r0.d()
            r1 = -1
            if (r0 != r1) goto L5a
            d.d r0 = r3.b()
            boolean r0 = r0.c()
            if (r0 != 0) goto L5a
            d.d r0 = r3.b()
            boolean r0 = r0.b()
            if (r0 == 0) goto L59
            goto L5a
        L59:
            return r2
        L5a:
            d.d r3 = r3.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            d.d r3 = r4.b()
            boolean r3 = r3.i()
            if (r3 != 0) goto L6f
            r2 = 1
        L6f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: d.h0.e.c.a(d.c0, d.a0):boolean");
    }

    /* JADX INFO: compiled from: CacheStrategy.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final long f4352a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final a0 f4353b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c0 f4354c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Date f4355d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f4356e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private Date f4357f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l;

        public a(long j, a0 a0Var, c0 c0Var) {
            this.l = -1;
            this.f4352a = j;
            this.f4353b = a0Var;
            this.f4354c = c0Var;
            if (c0Var != null) {
                this.i = c0Var.x();
                this.j = c0Var.v();
                s sVarO = c0Var.o();
                int iB = sVarO.b();
                for (int i = 0; i < iB; i++) {
                    String strA = sVarO.a(i);
                    String strB = sVarO.b(i);
                    if ("Date".equalsIgnoreCase(strA)) {
                        this.f4355d = d.h0.g.d.a(strB);
                        this.f4356e = strB;
                    } else if ("Expires".equalsIgnoreCase(strA)) {
                        this.h = d.h0.g.d.a(strB);
                    } else if ("Last-Modified".equalsIgnoreCase(strA)) {
                        this.f4357f = d.h0.g.d.a(strB);
                        this.g = strB;
                    } else if ("ETag".equalsIgnoreCase(strA)) {
                        this.k = strB;
                    } else if ("Age".equalsIgnoreCase(strA)) {
                        this.l = d.h0.g.e.a(strB, -1);
                    }
                }
            }
        }

        private long b() {
            Date date = this.f4355d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.l;
            if (i != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.j;
            return jMax + (j - this.i) + (this.f4352a - j);
        }

        private long c() {
            d.d dVarB = this.f4354c.b();
            if (dVarB.d() != -1) {
                return TimeUnit.SECONDS.toMillis(dVarB.d());
            }
            if (this.h != null) {
                Date date = this.f4355d;
                long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f4357f == null || this.f4354c.w().g().l() != null) {
                return 0L;
            }
            Date date2 = this.f4355d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f4357f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        private c d() {
            if (this.f4354c == null) {
                return new c(this.f4353b, null);
            }
            if (this.f4353b.d() && this.f4354c.n() == null) {
                return new c(this.f4353b, null);
            }
            if (!c.a(this.f4354c, this.f4353b)) {
                return new c(this.f4353b, null);
            }
            d.d dVarB = this.f4353b.b();
            if (dVarB.h() || a(this.f4353b)) {
                return new c(this.f4353b, null);
            }
            d.d dVarB2 = this.f4354c.b();
            if (dVarB2.a()) {
                return new c(null, this.f4354c);
            }
            long jB = b();
            long jC = c();
            if (dVarB.d() != -1) {
                jC = Math.min(jC, TimeUnit.SECONDS.toMillis(dVarB.d()));
            }
            long millis = 0;
            long millis2 = dVarB.f() != -1 ? TimeUnit.SECONDS.toMillis(dVarB.f()) : 0L;
            if (!dVarB2.g() && dVarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarB.e());
            }
            if (!dVarB2.h()) {
                long j = millis2 + jB;
                if (j < millis + jC) {
                    c0.a aVarS = this.f4354c.s();
                    if (j >= jC) {
                        aVarS.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jB > 86400000 && e()) {
                        aVarS.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarS.a());
                }
            }
            String str = this.k;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.f4357f != null) {
                str = this.g;
            } else {
                if (this.f4355d == null) {
                    return new c(this.f4353b, null);
                }
                str = this.f4356e;
            }
            s.a aVarA = this.f4353b.c().a();
            d.h0.a.f4335a.a(aVarA, str2, str);
            a0.a aVarF = this.f4353b.f();
            aVarF.a(aVarA.a());
            return new c(aVarF.a(), this.f4354c);
        }

        private boolean e() {
            return this.f4354c.b().d() == -1 && this.h == null;
        }

        public c a() {
            c cVarD = d();
            return (cVarD.f4350a == null || !this.f4353b.b().j()) ? cVarD : new c(null, null);
        }

        private static boolean a(a0 a0Var) {
            return (a0Var.a("If-Modified-Since") == null && a0Var.a("If-None-Match") == null) ? false : true;
        }
    }
}
