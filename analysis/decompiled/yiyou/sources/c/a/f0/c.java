package c.a.f0;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Timed.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final T f3143a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f3144b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final TimeUnit f3145c;

    public c(T t, long j, TimeUnit timeUnit) {
        this.f3143a = t;
        this.f3144b = j;
        c.a.b0.b.b.a(timeUnit, "unit is null");
        this.f3145c = timeUnit;
    }

    public long a() {
        return this.f3144b;
    }

    public T b() {
        return this.f3143a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return c.a.b0.b.b.a(this.f3143a, cVar.f3143a) && this.f3144b == cVar.f3144b && c.a.b0.b.b.a(this.f3145c, cVar.f3145c);
    }

    public int hashCode() {
        T t = this.f3143a;
        int iHashCode = t != null ? t.hashCode() : 0;
        long j = this.f3144b;
        return (((iHashCode * 31) + ((int) (j ^ (j >>> 31)))) * 31) + this.f3145c.hashCode();
    }

    public String toString() {
        return "Timed[time=" + this.f3144b + ", unit=" + this.f3145c + ", value=" + this.f3143a + "]";
    }
}
