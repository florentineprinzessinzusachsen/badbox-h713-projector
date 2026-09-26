package c.a;

/* JADX INFO: compiled from: Notification.java */
/* JADX INFO: loaded from: classes.dex */
public final class k<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final k<Object> f3163b = new k<>(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f3164a;

    private k(Object obj) {
        this.f3164a = obj;
    }

    public static <T> k<T> f() {
        return (k<T>) f3163b;
    }

    public Throwable a() {
        Object obj = this.f3164a;
        if (c.a.b0.j.n.d(obj)) {
            return c.a.b0.j.n.a(obj);
        }
        return null;
    }

    public T b() {
        Object obj = this.f3164a;
        if (obj == null || c.a.b0.j.n.d(obj)) {
            return null;
        }
        return (T) this.f3164a;
    }

    public boolean c() {
        return this.f3164a == null;
    }

    public boolean d() {
        return c.a.b0.j.n.d(this.f3164a);
    }

    public boolean e() {
        Object obj = this.f3164a;
        return (obj == null || c.a.b0.j.n.d(obj)) ? false : true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof k) {
            return c.a.b0.b.b.a(this.f3164a, ((k) obj).f3164a);
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f3164a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        Object obj = this.f3164a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (c.a.b0.j.n.d(obj)) {
            return "OnErrorNotification[" + c.a.b0.j.n.a(obj) + "]";
        }
        return "OnNextNotification[" + this.f3164a + "]";
    }

    public static <T> k<T> a(T t) {
        c.a.b0.b.b.a((Object) t, "value is null");
        return new k<>(t);
    }

    public static <T> k<T> a(Throwable th) {
        c.a.b0.b.b.a(th, "error is null");
        return new k<>(c.a.b0.j.n.a(th));
    }
}
