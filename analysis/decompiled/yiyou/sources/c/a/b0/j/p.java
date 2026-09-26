package c.a.b0.j;

/* JADX INFO: compiled from: OpenHashSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class p<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final float f3103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3104b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3106d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    T[] f3107e;

    public p() {
        this(16, 0.75f);
    }

    static int a(int i) {
        int i2 = i * (-1640531527);
        return i2 ^ (i2 >>> 16);
    }

    public boolean a(T t) {
        T t2;
        T[] tArr = this.f3107e;
        int i = this.f3104b;
        int iA = a(t.hashCode()) & i;
        T t3 = tArr[iA];
        if (t3 != null) {
            if (t3.equals(t)) {
                return false;
            }
            do {
                iA = (iA + 1) & i;
                t2 = tArr[iA];
                if (t2 == null) {
                }
            } while (!t2.equals(t));
            return false;
        }
        tArr[iA] = t;
        int i2 = this.f3105c + 1;
        this.f3105c = i2;
        if (i2 >= this.f3106d) {
            b();
        }
        return true;
    }

    public boolean b(T t) {
        T t2;
        T[] tArr = this.f3107e;
        int i = this.f3104b;
        int iA = a(t.hashCode()) & i;
        T t3 = tArr[iA];
        if (t3 == null) {
            return false;
        }
        if (t3.equals(t)) {
            return a(iA, tArr, i);
        }
        do {
            iA = (iA + 1) & i;
            t2 = tArr[iA];
            if (t2 == null) {
                return false;
            }
        } while (!t2.equals(t));
        return a(iA, tArr, i);
    }

    public int c() {
        return this.f3105c;
    }

    public p(int i, float f2) {
        this.f3103a = f2;
        int iA = q.a(i);
        this.f3104b = iA - 1;
        this.f3106d = (int) (f2 * iA);
        this.f3107e = (T[]) new Object[iA];
    }

    void b() {
        T[] tArr = this.f3107e;
        int length = tArr.length;
        int i = length << 1;
        int i2 = i - 1;
        T[] tArr2 = (T[]) new Object[i];
        int i3 = this.f3105c;
        while (true) {
            int i4 = i3 - 1;
            if (i3 != 0) {
                do {
                    length--;
                } while (tArr[length] == null);
                int iA = a(tArr[length].hashCode()) & i2;
                if (tArr2[iA] != null) {
                    do {
                        iA = (iA + 1) & i2;
                    } while (tArr2[iA] != null);
                }
                tArr2[iA] = tArr[length];
                i3 = i4;
            } else {
                this.f3104b = i2;
                this.f3106d = (int) (i * this.f3103a);
                this.f3107e = tArr2;
                return;
            }
        }
    }

    boolean a(int i, T[] tArr, int i2) {
        int i3;
        T t;
        this.f3105c--;
        while (true) {
            int i4 = i + 1;
            while (true) {
                i3 = i4 & i2;
                t = tArr[i3];
                if (t == null) {
                    tArr[i] = null;
                    return true;
                }
                int iA = a(t.hashCode()) & i2;
                if (i > i3) {
                    if (i >= iA && iA > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                } else {
                    if (i >= iA || iA > i3) {
                        break;
                    }
                    i4 = i3 + 1;
                }
            }
            tArr[i] = t;
            i = i3;
        }
    }

    public Object[] a() {
        return this.f3107e;
    }
}
