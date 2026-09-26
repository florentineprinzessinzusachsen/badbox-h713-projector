package androidx.appcompat.widget;

/* JADX INFO: compiled from: RtlSpacingHelper.java */
/* JADX INFO: loaded from: classes.dex */
class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f834a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f835b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f836c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f837d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f838e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f839f = 0;
    private boolean g = false;
    private boolean h = false;

    w() {
    }

    public int a() {
        return this.g ? this.f834a : this.f835b;
    }

    public int b() {
        return this.f834a;
    }

    public int c() {
        return this.f835b;
    }

    public int d() {
        return this.g ? this.f835b : this.f834a;
    }

    public void a(int i, int i2) {
        this.h = false;
        if (i != Integer.MIN_VALUE) {
            this.f838e = i;
            this.f834a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f839f = i2;
            this.f835b = i2;
        }
    }

    public void b(int i, int i2) {
        this.f836c = i;
        this.f837d = i2;
        this.h = true;
        if (this.g) {
            if (i2 != Integer.MIN_VALUE) {
                this.f834a = i2;
            }
            if (i != Integer.MIN_VALUE) {
                this.f835b = i;
                return;
            }
            return;
        }
        if (i != Integer.MIN_VALUE) {
            this.f834a = i;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f835b = i2;
        }
    }

    public void a(boolean z) {
        if (z == this.g) {
            return;
        }
        this.g = z;
        if (!this.h) {
            this.f834a = this.f838e;
            this.f835b = this.f839f;
            return;
        }
        if (z) {
            int i = this.f837d;
            if (i == Integer.MIN_VALUE) {
                i = this.f838e;
            }
            this.f834a = i;
            int i2 = this.f836c;
            if (i2 == Integer.MIN_VALUE) {
                i2 = this.f839f;
            }
            this.f835b = i2;
            return;
        }
        int i3 = this.f836c;
        if (i3 == Integer.MIN_VALUE) {
            i3 = this.f838e;
        }
        this.f834a = i3;
        int i4 = this.f837d;
        if (i4 == Integer.MIN_VALUE) {
            i4 = this.f839f;
        }
        this.f835b = i4;
    }
}
