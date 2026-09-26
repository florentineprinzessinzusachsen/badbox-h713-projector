package c.a.b0.b;

import c.a.a0.d;

/* JADX INFO: compiled from: ObjectHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final d<Object, Object> f1794a = new a();

    /* JADX INFO: compiled from: ObjectHelper.java */
    static final class a implements d<Object, Object> {
        a() {
        }

        @Override // c.a.a0.d
        public boolean a(Object obj, Object obj2) {
            return b.a(obj, obj2);
        }
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int a(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> d<T, T> a() {
        return (d<T, T>) f1794a;
    }

    public static int a(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + i);
    }

    public static long a(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " > 0 required but it was " + j);
    }
}
