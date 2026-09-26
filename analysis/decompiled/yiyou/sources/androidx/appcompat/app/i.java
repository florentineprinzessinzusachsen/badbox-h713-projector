package androidx.appcompat.app;

/* JADX INFO: compiled from: TwilightCalculator.java */
/* JADX INFO: loaded from: classes.dex */
class i {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static i f309d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f310a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f312c;

    i() {
    }

    static i a() {
        if (f309d == null) {
            f309d = new i();
        }
        return f309d;
    }

    public void a(long j, double d2, double d3) {
        float f2 = (j - 946728000000L) / 8.64E7f;
        float f3 = (0.01720197f * f2) + 6.24006f;
        double d4 = f3;
        double dSin = Math.sin(d4) * 0.03341960161924362d;
        Double.isNaN(d4);
        double dSin2 = dSin + d4 + (Math.sin(2.0f * f3) * 3.4906598739326E-4d) + (Math.sin(f3 * 3.0f) * 5.236000106378924E-6d) + 1.796593063d + 3.141592653589793d;
        double d5 = (-d3) / 360.0d;
        double d6 = f2 - 9.0E-4f;
        Double.isNaN(d6);
        double dRound = Math.round(d6 - d5) + 9.0E-4f;
        Double.isNaN(dRound);
        double dSin3 = dRound + d5 + (Math.sin(d4) * 0.0053d) + (Math.sin(2.0d * dSin2) * (-0.0069d));
        double dAsin = Math.asin(Math.sin(dSin2) * Math.sin(0.4092797040939331d));
        double d7 = 0.01745329238474369d * d2;
        double dSin4 = (Math.sin(-0.10471975803375244d) - (Math.sin(d7) * Math.sin(dAsin))) / (Math.cos(d7) * Math.cos(dAsin));
        if (dSin4 >= 1.0d) {
            this.f312c = 1;
            this.f310a = -1L;
            this.f311b = -1L;
            return;
        }
        if (dSin4 <= -1.0d) {
            this.f312c = 0;
            this.f310a = -1L;
            this.f311b = -1L;
            return;
        }
        double dAcos = (float) (Math.acos(dSin4) / 6.283185307179586d);
        Double.isNaN(dAcos);
        this.f310a = Math.round((dSin3 + dAcos) * 8.64E7d) + 946728000000L;
        Double.isNaN(dAcos);
        this.f311b = Math.round((dSin3 - dAcos) * 8.64E7d) + 946728000000L;
        if (this.f311b < j && this.f310a > j) {
            this.f312c = 0;
        } else {
            this.f312c = 1;
        }
    }
}
