package c.a.b0.j;

import java.io.Serializable;

/* JADX INFO: compiled from: NotificationLite.java */
/* JADX INFO: loaded from: classes.dex */
public enum n {
    COMPLETE;

    /* JADX INFO: compiled from: NotificationLite.java */
    static final class a implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.y.b f3101a;

        a(c.a.y.b bVar) {
            this.f3101a = bVar;
        }

        public String toString() {
            return "NotificationLite.Disposable[" + this.f3101a + "]";
        }
    }

    /* JADX INFO: compiled from: NotificationLite.java */
    static final class b implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Throwable f3102a;

        b(Throwable th) {
            this.f3102a = th;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return c.a.b0.b.b.a(this.f3102a, ((b) obj).f3102a);
            }
            return false;
        }

        public int hashCode() {
            return this.f3102a.hashCode();
        }

        public String toString() {
            return "NotificationLite.Error[" + this.f3102a + "]";
        }
    }

    public static Object a() {
        return COMPLETE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T b(Object obj) {
        return obj;
    }

    public static <T> boolean b(Object obj, c.a.s<? super T> sVar) {
        if (obj == COMPLETE) {
            sVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            sVar.onError(((b) obj).f3102a);
            return true;
        }
        if (obj instanceof a) {
            sVar.onSubscribe(((a) obj).f3101a);
            return false;
        }
        sVar.onNext(obj);
        return false;
    }

    public static boolean c(Object obj) {
        return obj == COMPLETE;
    }

    public static boolean d(Object obj) {
        return obj instanceof b;
    }

    public static <T> Object e(T t) {
        return t;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }

    public static Object a(Throwable th) {
        return new b(th);
    }

    public static Object a(c.a.y.b bVar) {
        return new a(bVar);
    }

    public static Throwable a(Object obj) {
        return ((b) obj).f3102a;
    }

    public static <T> boolean a(Object obj, c.a.s<? super T> sVar) {
        if (obj == COMPLETE) {
            sVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            sVar.onError(((b) obj).f3102a);
            return true;
        }
        sVar.onNext(obj);
        return false;
    }
}
