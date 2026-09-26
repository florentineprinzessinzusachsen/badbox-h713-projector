package d;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final d n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4293a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f4294b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4295c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4296d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f4297e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f4298f;
    private final boolean g;
    private final int h;
    private final int i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    String m;

    static {
        a aVar = new a();
        aVar.b();
        aVar.a();
        a aVar2 = new a();
        aVar2.c();
        aVar2.a(Integer.MAX_VALUE, TimeUnit.SECONDS);
        n = aVar2.a();
    }

    private d(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.f4293a = z;
        this.f4294b = z2;
        this.f4295c = i;
        this.f4296d = i2;
        this.f4297e = z3;
        this.f4298f = z4;
        this.g = z5;
        this.h = i3;
        this.i = i4;
        this.j = z6;
        this.k = z7;
        this.l = z8;
        this.m = str;
    }

    private String k() {
        StringBuilder sb = new StringBuilder();
        if (this.f4293a) {
            sb.append("no-cache, ");
        }
        if (this.f4294b) {
            sb.append("no-store, ");
        }
        if (this.f4295c != -1) {
            sb.append("max-age=");
            sb.append(this.f4295c);
            sb.append(", ");
        }
        if (this.f4296d != -1) {
            sb.append("s-maxage=");
            sb.append(this.f4296d);
            sb.append(", ");
        }
        if (this.f4297e) {
            sb.append("private, ");
        }
        if (this.f4298f) {
            sb.append("public, ");
        }
        if (this.g) {
            sb.append("must-revalidate, ");
        }
        if (this.h != -1) {
            sb.append("max-stale=");
            sb.append(this.h);
            sb.append(", ");
        }
        if (this.i != -1) {
            sb.append("min-fresh=");
            sb.append(this.i);
            sb.append(", ");
        }
        if (this.j) {
            sb.append("only-if-cached, ");
        }
        if (this.k) {
            sb.append("no-transform, ");
        }
        if (this.l) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public boolean a() {
        return this.l;
    }

    public boolean b() {
        return this.f4297e;
    }

    public boolean c() {
        return this.f4298f;
    }

    public int d() {
        return this.f4295c;
    }

    public int e() {
        return this.h;
    }

    public int f() {
        return this.i;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.f4293a;
    }

    public boolean i() {
        return this.f4294b;
    }

    public boolean j() {
        return this.j;
    }

    public String toString() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        String strK = k();
        this.m = strK;
        return strK;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0041  */
    /* JADX WARN: Code duplicated, block: B:28:0x0099  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:37:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:40:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:43:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:44:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:47:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:49:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:50:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:52:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:53:0x0109  */
    /* JADX WARN: Code duplicated, block: B:55:0x0111  */
    /* JADX WARN: Code duplicated, block: B:56:0x0119  */
    /* JADX WARN: Code duplicated, block: B:58:0x0122  */
    /* JADX WARN: Code duplicated, block: B:59:0x0125  */
    /* JADX WARN: Code duplicated, block: B:61:0x012d  */
    /* JADX WARN: Code duplicated, block: B:62:0x0130  */
    /* JADX WARN: Code duplicated, block: B:64:0x0138  */
    /* JADX WARN: Code duplicated, block: B:87:0x013a A[SYNTHETIC] */
    public static d a(s sVar) {
        int i;
        int iA;
        String strTrim;
        int iA2;
        String strTrim2;
        int iB = sVar.b();
        boolean z = true;
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        int iA3 = -1;
        int iA4 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int iA5 = -1;
        int iA6 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        for (int i2 = 0; i2 < iB; i2++) {
            String strA = sVar.a(i2);
            String strB = sVar.b(i2);
            if (strA.equalsIgnoreCase("Cache-Control")) {
                if (str == null) {
                    str = strB;
                }
                for (i = 0; i < strB.length(); i = iA2) {
                    iA = d.h0.g.e.a(strB, i, "=,;");
                    strTrim = strB.substring(i, iA).trim();
                    if (iA != strB.length() || strB.charAt(iA) == ',' || strB.charAt(iA) == ';') {
                        iA2 = iA + 1;
                        strTrim2 = null;
                    } else {
                        int iB2 = d.h0.g.e.b(strB, iA + 1);
                        if (iB2 >= strB.length() || strB.charAt(iB2) != '\"') {
                            iA2 = d.h0.g.e.a(strB, iB2, ",;");
                            strTrim2 = strB.substring(iB2, iA2).trim();
                        } else {
                            int i3 = iB2 + 1;
                            int iA7 = d.h0.g.e.a(strB, i3, "\"");
                            strTrim2 = strB.substring(i3, iA7);
                            iA2 = iA7 + 1;
                        }
                    }
                    if ("no-cache".equalsIgnoreCase(strTrim)) {
                        z2 = true;
                    } else if ("no-store".equalsIgnoreCase(strTrim)) {
                        z3 = true;
                    } else if ("max-age".equalsIgnoreCase(strTrim)) {
                        iA3 = d.h0.g.e.a(strTrim2, -1);
                    } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                        iA4 = d.h0.g.e.a(strTrim2, -1);
                    } else if ("private".equalsIgnoreCase(strTrim)) {
                        z4 = true;
                    } else if ("public".equalsIgnoreCase(strTrim)) {
                        z5 = true;
                    } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                        z6 = true;
                    } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                        iA5 = d.h0.g.e.a(strTrim2, Integer.MAX_VALUE);
                    } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                        iA6 = d.h0.g.e.a(strTrim2, -1);
                    } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                        z7 = true;
                    } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                        z8 = true;
                    } else if ("immutable".equalsIgnoreCase(strTrim)) {
                        z9 = true;
                    }
                }
            } else {
                if (strA.equalsIgnoreCase("Pragma")) {
                }
            }
            z = false;
            while (i < strB.length()) {
                iA = d.h0.g.e.a(strB, i, "=,;");
                strTrim = strB.substring(i, iA).trim();
                if (iA != strB.length()) {
                    iA2 = iA + 1;
                    strTrim2 = null;
                } else {
                    iA2 = iA + 1;
                    strTrim2 = null;
                }
                if ("no-cache".equalsIgnoreCase(strTrim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(strTrim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(strTrim)) {
                    iA3 = d.h0.g.e.a(strTrim2, -1);
                } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                    iA4 = d.h0.g.e.a(strTrim2, -1);
                } else if ("private".equalsIgnoreCase(strTrim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(strTrim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                    iA5 = d.h0.g.e.a(strTrim2, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                    iA6 = d.h0.g.e.a(strTrim2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                    z8 = true;
                } else if ("immutable".equalsIgnoreCase(strTrim)) {
                    z9 = true;
                }
            }
        }
        return new d(z2, z3, iA3, iA4, z4, z5, z6, iA5, iA6, z7, z8, z9, !z ? null : str);
    }

    /* JADX INFO: compiled from: CacheControl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f4299a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4300b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f4301c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f4302d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f4303e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        boolean f4304f;
        boolean g;
        boolean h;

        public a a(int i, TimeUnit timeUnit) {
            if (i >= 0) {
                long seconds = timeUnit.toSeconds(i);
                this.f4302d = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
                return this;
            }
            throw new IllegalArgumentException("maxStale < 0: " + i);
        }

        public a b() {
            this.f4299a = true;
            return this;
        }

        public a c() {
            this.f4304f = true;
            return this;
        }

        public d a() {
            return new d(this);
        }
    }

    d(a aVar) {
        this.f4293a = aVar.f4299a;
        this.f4294b = aVar.f4300b;
        this.f4295c = aVar.f4301c;
        this.f4296d = -1;
        this.f4297e = false;
        this.f4298f = false;
        this.g = false;
        this.h = aVar.f4302d;
        this.i = aVar.f4303e;
        this.j = aVar.f4304f;
        this.k = aVar.g;
        this.l = aVar.h;
    }
}
