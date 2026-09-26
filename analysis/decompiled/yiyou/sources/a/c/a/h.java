package a.c.a;

/* JADX INFO: compiled from: Pools.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> implements g<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object[] f95a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f96b;

    h(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.f95a = new Object[i];
    }

    @Override // a.c.a.g
    public T a() {
        int i = this.f96b;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.f95a;
        T t = (T) objArr[i2];
        objArr[i2] = null;
        this.f96b = i - 1;
        return t;
    }

    @Override // a.c.a.g
    public boolean a(T t) {
        int i = this.f96b;
        Object[] objArr = this.f95a;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.f96b = i + 1;
        return true;
    }

    @Override // a.c.a.g
    public void a(T[] tArr, int i) {
        if (i > tArr.length) {
            i = tArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            int i3 = this.f96b;
            Object[] objArr = this.f95a;
            if (i3 < objArr.length) {
                objArr[i3] = t;
                this.f96b = i3 + 1;
            }
        }
    }
}
