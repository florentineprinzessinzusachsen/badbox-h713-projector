package a.c.a.j;

import com.baidu.mobstat.Config;

/* JADX INFO: compiled from: ConstraintAnchor.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final f f116b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final d f117c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    e f118d;
    private int h;
    a.c.a.i i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private m f115a = new m(this);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f119e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f120f = -1;
    private c g = c.NONE;

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f121a = new int[d.values().length];

        static {
            try {
                f121a[d.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f121a[d.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f121a[d.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f121a[d.TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f121a[d.BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f121a[d.BASELINE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f121a[d.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f121a[d.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f121a[d.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    public enum b {
        RELAXED,
        STRICT
    }

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    public enum c {
        NONE,
        STRONG,
        WEAK
    }

    /* JADX INFO: compiled from: ConstraintAnchor.java */
    public enum d {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public e(f fVar, d dVar) {
        b bVar = b.RELAXED;
        this.h = 0;
        this.f116b = fVar;
        this.f117c = dVar;
    }

    public void a(a.c.a.c cVar) {
        a.c.a.i iVar = this.i;
        if (iVar == null) {
            this.i = new a.c.a.i(a.c.a.i.a.UNRESTRICTED, null);
        } else {
            iVar.a();
        }
    }

    public int b() {
        e eVar;
        if (this.f116b.r() == 8) {
            return 0;
        }
        return (this.f120f <= -1 || (eVar = this.f118d) == null || eVar.f116b.r() != 8) ? this.f119e : this.f120f;
    }

    public f c() {
        return this.f116b;
    }

    public m d() {
        return this.f115a;
    }

    public a.c.a.i e() {
        return this.i;
    }

    public c f() {
        return this.g;
    }

    public e g() {
        return this.f118d;
    }

    public d h() {
        return this.f117c;
    }

    public boolean i() {
        return this.f118d != null;
    }

    public void j() {
        this.f118d = null;
        this.f119e = 0;
        this.f120f = -1;
        this.g = c.STRONG;
        this.h = 0;
        b bVar = b.RELAXED;
        this.f115a.d();
    }

    public String toString() {
        return this.f116b.f() + Config.TRACE_TODAY_VISIT_SPLIT + this.f117c.toString();
    }

    public int a() {
        return this.h;
    }

    public boolean a(e eVar, int i, c cVar, int i2) {
        return a(eVar, i, -1, cVar, i2, false);
    }

    public boolean a(e eVar, int i, int i2, c cVar, int i3, boolean z) {
        if (eVar == null) {
            this.f118d = null;
            this.f119e = 0;
            this.f120f = -1;
            this.g = c.NONE;
            this.h = 2;
            return true;
        }
        if (!z && !a(eVar)) {
            return false;
        }
        this.f118d = eVar;
        if (i > 0) {
            this.f119e = i;
        } else {
            this.f119e = 0;
        }
        this.f120f = i2;
        this.g = cVar;
        this.h = i3;
        return true;
    }

    public boolean a(e eVar) {
        if (eVar == null) {
            return false;
        }
        d dVarH = eVar.h();
        d dVar = this.f117c;
        if (dVarH == dVar) {
            return dVar != d.BASELINE || (eVar.c().x() && c().x());
        }
        switch (a.f121a[dVar.ordinal()]) {
            case 1:
                return (dVarH == d.BASELINE || dVarH == d.CENTER_X || dVarH == d.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = dVarH == d.LEFT || dVarH == d.RIGHT;
                if (eVar.c() instanceof i) {
                    return z || dVarH == d.CENTER_X;
                }
                return z;
            case 4:
            case 5:
                boolean z2 = dVarH == d.TOP || dVarH == d.BOTTOM;
                if (eVar.c() instanceof i) {
                    return z2 || dVarH == d.CENTER_Y;
                }
                return z2;
            case 6:
            case 7:
            case 8:
            case 9:
                return false;
            default:
                throw new AssertionError(this.f117c.name());
        }
    }
}
