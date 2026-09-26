package androidx.core.e;

/* JADX INFO: compiled from: Pair.java */
/* JADX INFO: loaded from: classes.dex */
public class d<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f1050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f1051b;

    public d(F f2, S s) {
        this.f1050a = f2;
        this.f1051b = s;
    }

    public static <A, B> d<A, B> a(A a2, B b2) {
        return new d<>(a2, b2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return c.a(dVar.f1050a, this.f1050a) && c.a(dVar.f1051b, this.f1051b);
    }

    public int hashCode() {
        F f2 = this.f1050a;
        int iHashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.f1051b;
        return iHashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f1050a) + " " + String.valueOf(this.f1051b) + "}";
    }
}
